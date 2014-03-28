import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizMakerTest {
    private QuizMaker quizmaker;
    private Quiz quiz;
    private Server server;
    private Question question;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void buildUp() {
        quiz = mock(Quiz.class);
        server = mock(Server.class);
        question = mock(Question.class);

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

    @Test
    public void shouldHaveAppropriateMessageIfTitleIsAnEmptyString() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Title is empty. Please enter a title with at least one character.");

        quizmaker.createQuiz("    ");
    }

    @Test
    public void shouldBeAbleToAddQuestionToQuizCreated() throws IllegalQuizException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        quizmaker.createQuiz(title);
        verify(server).createQuiz(title);

        String question1 = "What is the biggest cat?";
        when(server.createQuestion(anyString())).thenReturn(question);
        quizmaker.addQuestion(question1);
        verify(quiz).addQuestion(question);
    }

    @Test
    public void shouldThrowIllegalQuizExceptionIfQuizIsNull() throws IllegalQuizException {
        thrown.expect(IllegalQuizException.class);
        thrown.expectMessage("Quiz does not exist. Please create a quiz and try again.");

        String question1 = "What is the biggest cat?";
        quizmaker.addQuestion(question1);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsNull() throws IllegalQuizException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        quizmaker.createQuiz(title);
        verify(server).createQuiz(title);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Question entered is empty. Please try again.");

        quizmaker.addQuestion(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfStringIsEmpty() throws IllegalQuizException {
        String title = "A quiz";
        when(server.createQuiz(anyString())).thenReturn(quiz);
        quizmaker.createQuiz(title);
        verify(server).createQuiz(title);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Question entered is empty. Please try again.");

        quizmaker.addQuestion("    ");
    }
}
