package controllers;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServerTest {

    private Server server;
    private QuizServer quizServer;
    private ServerLink serverLink;
    private PlayerFactory playerFactory;

    @Before
    public void buildUp() {
        serverLink = mock(ServerLink.class);
        quizServer = mock(QuizServer.class);
        playerFactory = mock(PlayerFactory.class);
        when(serverLink.getQuizServer()).thenReturn(quizServer);
        when(quizServer.getPlayerFactory()).thenReturn(playerFactory);
        server = new ServerImpl(serverLink);
    }

    @Test
    public void shouldBeAbleToGetListOfQuizzes() {
        server.getQuizzes();
        verify(quizServer).getQuizzes();
    }

    @Test
    public void shouldBeAbleToGetQuizById() {
        int id = 5;
        server.getQuiz(id);
        verify(quizServer).getQuiz(anyInt());
    }

    @Test
    public void shouldBeAbleToCheckForHighScore() {
        Player player = mock(Player.class);
        server.checkForHighScore(player);
        verify(quizServer).checkForHighScore(player);
    }

    @Test
    public void shouldBeAbleToCreateNewPlayer() {
        int age = 5;
        String name = "Superman";
        String country = "Krypton";

        server.createPlayer(name, country, age);
        verify(playerFactory).generatePlayer(anyString(), anyString(), anyInt());
    }

    @Test
    public void shouldBeAbleToGetWinnerByQuizId() {
        int quizId = 5;
        server.getWinnerBy(quizId);
        verify(quizServer).getWinnerBy(quizId);
    }

    @Test
    public void shouldBeAbleSetPlayerAsWinner() {
        Player player = mock(Player.class);
        server.setPlayerAsWinner(player);
        verify(quizServer).setPlayerAsWinner(player);
    }
}
