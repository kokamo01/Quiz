import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizMakerTest {

    private UniqueNumberGenoratorUtilities uniqueNumberGenoratorUtilities;
    private QuizMaker quizmaker;
    private Quiz quiz;
    private Server server;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void buildUp() {
        uniqueNumberGenoratorUtilities = mock(UniqueNumberGenoratorUtilities.class);
        quiz = mock(Quiz.class);
        server = mock(Server.class);

        quizmaker = new QuizMakerImpl(server);
    }

    @Test
    public void shouldBeAbleToCreateAQuiz() {
        String expected = "The colour quiz!";
        when(server.createQuiz(expected)).thenReturn(quiz);
        quizmaker.createQuiz(expected);

        verify(server).createQuiz(expected);

        when(quiz.getTitle()).thenReturn(expected);
        String actual = quizmaker.getTitle();

        assertEquals(expected, actual);

        expected = "The animal quiz!";
        when(server.createQuiz(expected)).thenReturn(quiz);
        quizmaker.createQuiz(expected);

        verify(server).createQuiz(expected);

        when(quiz.getTitle()).thenReturn(expected);
        actual = quizmaker.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldHaveAppropriateMessageIfTitleIsNull() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Title is empty. Please enter a title with at least one character.");

        quizmaker.createQuiz(null);
    }
}