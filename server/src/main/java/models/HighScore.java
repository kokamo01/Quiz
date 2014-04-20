package models;

import java.rmi.RemoteException;

/**
 * A highScore holds the quiz and the player of the quiz.
 */
public interface HighScore {

    /**
     * Gets the players score.
     *
     * @return the players score.
     * @throws RemoteException if there is a problem with the server/connection.
     */
    int getHighScore() throws RemoteException;

    /**
     * Gets the player.
     *
     * @return a player.
     */
    Player getPlayer();
}
