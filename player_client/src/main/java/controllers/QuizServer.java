package controllers;

import java.util.ArrayList;

/**
 * Interface for the quiz server.
 */
public interface QuizServer {

    /**
     * Getter for a list of available quizzes.
     *
     * @return A list of available quizzes.
     */
    ArrayList<Quiz> getQuizzes();

    /**
     * Getter for a quiz.
     *
     * @param id ID of a quiz.
     * @return A quiz with the corresponding ID.
     */
    Quiz getQuiz(int id);

    /**
     * Checks if the score is the highest store.
     *
     * @param player
     * @return False if the score is not the highest.
     */
    boolean checkForHighScore(Player player);
}
