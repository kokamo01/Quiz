package models;

public class ScoreKeeperImpl implements ScoreKeeper {
    private HighScoreBoard highScoreBoard;

    public ScoreKeeperImpl(HighScoreBoard highScoreBoard) {
        this.highScoreBoard = highScoreBoard;
    }

    @Override
    public boolean highScoreContains(Quiz quiz) {
        return highScoreBoard.contains(quiz);
    }

    @Override
    public boolean scoreIsHighest(Quiz quiz) {
        return highScoreBoard.scoreIsHighest(quiz);
    }

    @Override
    public void addHighScore(Quiz quiz) {
        highScoreBoard.addHighScore(quiz);
    }

    @Override
    public int getHighScore(Quiz quiz) {
        return highScoreBoard.getHighScore(quiz);
    }
}
