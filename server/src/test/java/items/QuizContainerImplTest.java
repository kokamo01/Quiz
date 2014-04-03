package items;

import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuizContainerImplTest {

    private QuizContainer quizContainer;
    private Quiz quiz;

    @Before
    public void buildUp() {
        quizContainer = new QuizContainerImpl();

        quiz = mock(Quiz.class);
    }
    
    @Test
    public void shouldBeAbleToGetASavedQuiz() {
        int id = 5;
        when(quiz.getId()).thenReturn(id);
        quizContainer.save(quiz);
        Quiz actualQuiz = quizContainer.getQuizBy(id);

        assertEquals(quiz, actualQuiz);
    }

    @Test
    public void shouldBeAbleToValidateSavedQuizByTitle() {
        int id = 6;
        String title = "Quiz about cookies?";

        when(quiz.getId()).thenReturn(id);
        when(quiz.getTitle()).thenReturn(title);
        quizContainer.save(quiz);

        assertFalse(quizContainer.hasValid(title));
    }

    @Test
    public void shouldBeAbleToValidateSavedQuizById() {

    }
}