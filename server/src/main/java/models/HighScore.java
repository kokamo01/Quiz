package models;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A highScore holds the quiz and the player of the quiz.
 */
public interface HighScore extends Remote {

    String getPlayerCountry() throws RemoteException;

    int getPlayerAge() throws RemoteException;

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
    String getPlayerName() throws RemoteException;
}
