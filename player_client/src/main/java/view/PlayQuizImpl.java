package view;

import constants.ExceptionMessages;
import controllers.QuizPlayerOrchestrator;
import controllers.Server;
import exceptions.IllegalGameException;
import models.Answer;
import models.Player;
import models.Question;
import models.Quiz;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PlayQuizImpl implements PlayQuiz {

    private QuizGameOrchestrator quizGameOrchestrator;
    private QuizPlayerOrchestrator quizPlayerOrchestrator;
    private int quizSize;
    private int answerSize;
    private int answerIndex;

    public PlayQuizImpl(QuizGameOrchestrator quizGameOrchestrator, QuizPlayerOrchestrator quizPlayerOrchestrator) {
        this.quizGameOrchestrator = quizGameOrchestrator;
        this.quizPlayerOrchestrator = quizPlayerOrchestrator;
    }

    @Override
    public String getQuizMenu(Scanner scanner, Player player, Server server, String message) throws RemoteException, IllegalGameException {

        List<Quiz> quizList = quizPlayerOrchestrator.getQuizzes();
        if (quizList == null || quizList.isEmpty()) {
            System.out.println(getExitMessage());
            System.exit(0);
        } else {
            System.out.println(message);
            setQuizSize(quizList.size());
            QuizMenu quizMenu = new QuizMenuImpl(quizList);
            quizMenu.print();

            String userInput = scanner.nextLine();

            message = checkForValidNumber(userInput, message);

            while (message.equals(getValidNumberMessage())) {
                message = playQuiz(scanner, player, server, quizGameOrchestrator.getQuiz());
            }
        }
        return message;
    }

    private String playQuiz(Scanner scanner, Player player, Server server, Quiz quiz) {
        String message = play(quiz, scanner, player);

        while (message.equals(getUserHighScoreMessage())) {
            message = getUserHighScoreMessage(player, server, quiz);
        }
        return message;
    }


    private String getUserHighScoreMessage(Player player, Server server, Quiz quiz) {
        String message = quizGameOrchestrator.checkForHighScore(player, quiz, server);

        try {
            if (message.equals(getNewWinnerMessage(player))) {
                System.out.println(message);
                quizPlayerOrchestrator.setPlayerAsWinner(player, quiz);
                message = getThanksForPlayingMessage();
                System.out.println(message);
                quizPlayerOrchestrator.resetPlayerScore(player);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        message = getStartMessage();

        return message;
    }

    private String play(Quiz quiz, Scanner scanner, Player player) {
        Set<Question> questionSet = null;
        String message = null;

        try {
            questionSet = quiz.getQuestions();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (Question question : questionSet) {
            String userInput = "";
            while (!validInput(userInput)) {
                try {
                    System.out.println("\nQUESTION: " + question.getQuestion());
                    Set<Answer> answerSet = question.getAnswers();

                    Answer[] answers = answerSet.toArray(new Answer[answerSet.size()]);
                    setAnswerSize(answers.length);

                    for (int y = 0; y < answers.length; y++) {
                        System.out.println((y + 1) + ": " + answers[y].getAnswer());
                    }
                    userInput = scanner.nextLine();

                    if (validInput(userInput)) {
                        setAnswerIndex(userInput);

                        if (answers[getAnswerIndex() - 1].getAnswerType()) {
                            player.incrementScore();
                        }
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            System.out.println("¸¸.•*¨*•♫♪ You scored: " + player.getScore() + " out of " + questionSet.size() + "! ♪♫•*¨*•.¸¸");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        message = getUserHighScoreMessage();

        return message;
    }


    private boolean validRangeQuizSize(int index) {
        boolean result = true;
        if (index > quizSize || index <= 0) {
            result = false;
        }
        return result;
    }


    private boolean validInput(String userInput) {
        return (!checkForNull(userInput)) &&
                checkIfNumber(userInput) &&
                validRangeAnswerSize(Integer.parseInt(userInput));
    }


    private boolean validRangeAnswerSize(int index) {
        return index <= answerSize && index > 0;
    }

    private boolean checkIfNumber(String userInput) {
        boolean answer = true;
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            answer = false;
        }
        return answer;
    }

    private static boolean checkForNull(String userInput) {
        return userInput == null || userInput.trim().isEmpty();
    }

    private int getAnswerIndex() {
        return answerIndex;
    }

    private void setAnswerIndex(String answerIndex) {
        this.answerIndex = Integer.parseInt(answerIndex);
    }


    private void setAnswerSize(int answerSize) {
        this.answerSize = answerSize;
    }

    private String getQuizNumberSelectMessage() {
        return "☆ Please enter the quiz number you want to play! ☆ ";
    }

    private void setQuizSize(int size) {
        this.quizSize = size;
    }

    private String getStartMessage() {
        return "Game start";
    }

    private String getThanksForPlayingMessage() {
        return "♬ ☆ Thank you for playing, come back soon! ☆ ♬\n";
    }


    private String getNewWinnerMessage(Player player) throws RemoteException {
        return "\t\tYOU GOT THE HIGHEST SCORE!\n☆☆☆☆☆ Congratulations " + player.getName() + " from " + player.getCountry() + "!  ☆☆☆☆☆";
    }


    private String getValidNumberMessage() {
        return "Number valid.";
    }


    private String getExitMessage() {
        return "There are no quizzes available. :(\nPlease wait for someone to set one up.\nAlternatively, make your own quiz!";
    }


    public String getUserHighScoreMessage() {
        return "is highScore";
    }

    private String checkForValidNumber(String userInput, String message) {
        int index;
        try {
            index = Integer.parseInt(userInput);
            quizGameOrchestrator.setQuizNumber(index);

            if (validRangeQuizSize(index)) {
                message = getValidNumberMessage();
            } else {
                System.out.println(ExceptionMessages.INVALID_USER_INPUT);
            }
        } catch (NumberFormatException e) {
            System.out.println(ExceptionMessages.INVALID_USER_INPUT);
        }
        return message;
    }
}
