package items;

import items.Answer;
import items.Question;
import items.Quiz;

/**
 * Factory for items to do with a quiz.
 */

public interface ItemsFactory {
    /**
     * Generated a quiz.
     *
     * @param title A title of a quiz
     * @return Quiz object
     */
    Quiz generateQuiz(String title);

    /**
     * Generates a question.
     *
     * @param question A question
     * @return Question object
     */
    Question generateQuestion(String question);

    /**
     * Generates an answer.
     *
     * @param answer An answer to a question
     * @return Answer object
     */
    Answer generateAnswer(String answer);
}
