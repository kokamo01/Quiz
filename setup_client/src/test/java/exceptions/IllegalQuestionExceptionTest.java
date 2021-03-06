package exceptions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalQuestionExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowIllegalQuestionException() throws IllegalQuestionException {
        thrown.expect(IllegalQuestionException.class);
        thrown.expectMessage("Question does not exist. Please create a quiz and try again.");

        throw new IllegalQuestionException("Question does not exist. Please create a quiz and try again.");
    }
}
