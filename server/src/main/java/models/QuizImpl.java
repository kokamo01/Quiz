package models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

public class QuizImpl extends UnicastRemoteObject implements Quiz {

    private int id;
    private Set<Question> questionSet;
    private String title;


    public QuizImpl(int id, String title) throws RemoteException {
        this.id = id;
        this.title = title;
        questionSet = new HashSet<>();
    }

    @Override
    public void addQuestion(Question question) throws RemoteException {
        questionSet.add(question);
    }

    @Override
    public boolean contains(String question) throws RemoteException {
        boolean result = false;
        for (Question value : questionSet) {
            if (value.getQuestion().equals(question)) result = true;
        }
        return result;
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return questionSet.isEmpty();
    }

    @Override
    public String getTitle() throws RemoteException {
        return title;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public Set<Question> getQuestions() throws RemoteException {
        return questionSet;
    }
}
