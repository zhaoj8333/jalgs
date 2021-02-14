package phase1.ds.array.game;

import java.util.Arrays;
import java.util.Random;

public class ScoreBoard {
    private int numEntries = 0;
    private GameEntry[] board;

    @Override
    public String toString() {
        return "ScoreBoard{\n" +
                "numEntries=" + numEntries +
                ", \nboard=" + Arrays.toString(board) +
                '}';
    }

    public ScoreBoard(int capacity) {
        this.board = new GameEntry[capacity];
    }

    public void add(GameEntry e) {
        int newScore = e.getScore();
        if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
            if (numEntries < board.length) {
                numEntries ++;
            }
            int j = numEntries - 1;
            while (j > 0 && board[j - 1].getScore() < newScore) {
                board[j] = board[j - 1];
                j--;
            }
            board[j] = e;
        }
    }

    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= numEntries) {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
        GameEntry gameEntry = board[i];
        for (int j = 0; j < numEntries - 1; j++) {
            board[j] = board[j + 1];
        }
        board[numEntries - 1] = null;
        numEntries --;
        return gameEntry;
    }

    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard(10);
        for (int i = 0; i < 11; i++) {
            short index = (short) (65 + i);
            scoreBoard.add(new GameEntry(index + "", (new Random()).nextInt(100)));
        }
        System.out.println(scoreBoard);
    }
}
