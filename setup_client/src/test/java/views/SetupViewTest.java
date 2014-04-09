package views;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class SetupViewTest {
    /*
    * set up person sees welcome message and menu.
    *
    * CONSOLE: "WELCOME TO THE QUIZ SETUP"
    * CONSOLE: "What would you like to do?"
    * CONSOLE: "1.Create a quiz"          "2.Close a quiz.      EXIT: To exit the program."
    * CONSOLE: "Enter 1 to create a quiz or 2 to close a quiz
    *           and 'EXIT' to terminate the program at any point."
    *
    * USER INPUTS: "1"
    *
    * CONSOLE: "Would you like to create a quiz?
    * Press 'Y' or 'EXIT' to end the setup process at any point. "
    *
    * Y:
    * CONSOLE: "Please enter the title of your quiz: "
    * USER INPUTS: ""
    *
    * CONSOLE: "Please enter a question."
    * USER INPUTS: ""
    * CONSOLE: "Please enter the correct answer."
    * USER INPUTS: ""
    * CONSOLE: "Please enter a distraction answer."
    * USER INPUTS: ""
    * CONSOLE: "Would you like to enter another distraction answer? 'Y' for yes 'N' for no."
    * USER INPUTS: "N"
    *
    * CONSOLE: "Please enter a question."
    * USER INPUTS: "EXIT"
    *
    * CONSOLE: "Quiz has been saved."
    *
    * CONSOLE: "What would you like to do?"
    * CONSOLE: "1.Create a quiz"          "2.Close a quiz.      EXIT: To exit the program."
    *
    */
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    private SetupView setupInterface;

    @Before
    public void buildUp() {
        setupInterface = new SetupViewImpl();
    }

    @Test
    public void shouldBeAbleToSeeWelcomeMessageWithOptions() {
        setupInterface.startMessage();
        assertEquals("❤ ☆ ★ ☆ ★ Welcome to the Quiz Game Setup! ★ ☆ ★ ☆ ❤\n\nWhat would you like to do?\n1.Create a quiz.          2.Close a quiz.      EXIT:To exit the program.\n", log.getLog());
    }

}