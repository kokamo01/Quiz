package view;

import org.junit.Before;
import org.junit.Test;

public class PlayerStartImplTest {

    private PlayerStart playerStart;

    /*
             * Welcome message with a list of quizzes
             *
             * select by number which one to play,
             *
             * Display: get quiz and get questions
             *
             * user enters number
             * -- invalid input
             *
             * Get score
             * if highest store
             *
             * if quiz is closed. Winner is out putted.
             *
             *
             */
    @Before
    public void buildUp() {
        playerStart = new PlayerStartImpl();
    }

    @Test
    public void shouldBeAbleToSeeListOfQuizzes() {

    }


}
