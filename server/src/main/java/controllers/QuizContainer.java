package controllers;

import models.Quiz;

/**
 * A container class that contains and validates Quizzes.
 */
public interface QuizContainer {
    /**
     * Checks if a quiz with the same title exists.
     *
     * @param title A title of a Quiz.
     * @return False if the same name exists.
     */
    boolean contains(String title);

    /**
     * Checks if a quiz with the specified ID exists.
     *
     * @param id ID of a quiz.
     * @return False if the quiz does not exist.
     */
    boolean contains(int id);

    /**
     * Finds the Quiz with the corresponding ID.
     *
     * @param id ID of a quiz.
     */
    void closeQuizWith(int id);

    /**
     * Saves a quiz to a container.
     *
     * @param quiz A quiz object.
     */
    void save(Quiz quiz);

    /**
     * Getter for a quiz by ID.
     *
     * @param id ID of a quiz.
     * @return A Quiz object.
     */
    Quiz getQuizBy(int id);
}