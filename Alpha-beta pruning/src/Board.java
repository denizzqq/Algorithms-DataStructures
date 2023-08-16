import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;

import static java.lang.Math.abs;
/**
 * This class represents a generic TicTacToe game board.
 */
public class Board {
    private int n;
    private int[][] board;
    private int free;
    
    /**
     *  Creates Board object, am game board of size n * n with 1<=n<=10.
     */
    public Board(int n)
    {
        // TODO
        if (n < 1 || 10 < n) {
            throw new InputMismatchException("Input is not appropriate for the board size.");
        }

        this.n = n;
        this.board = new int[n][n];
        this.free = n * n;
    }
    
    /**
     *  @return     length/width of the Board object
     */
    public int getN() { return n; }
    
    /**
     *  @return     number of currently free fields
     */
    public int nFreeFields() {
        // TODO
        int count = 0;
        for (int[] row : board) {
            for (int col : row) {
                if (col != 0) {
                    count++;
                }
            }
        }
        return n * n - count;
    }
    
    /**
     *  @return     token at position pos
     */
    public int getField(Position pos) throws InputMismatchException
    {
        // TODO
        if (pos.x < 0 || pos.y < 0 || pos.x >= n || pos.y >= n) {
            throw new InputMismatchException("The aim position is invalid.");
        }

        return board[pos.x][pos.y];
    }

    /**
     *  Sets the specified token at Position pos.
     */    
    public void setField(Position pos, int token) throws InputMismatchException
    {
        // TODO
        if (pos.x < 0 || pos.y < 0 || pos.x >= n || pos.y >= n) {
            throw new InputMismatchException("The aim position is invalid.");
        }

        if (token < -1 || token > 1) {
            throw new InputMismatchException("The aim position is invalid.");
        }

        if (board[pos.x][pos.y] == 0) {
            this.free--;
        }

        board[pos.x][pos.y] = token;
    }
    
    /**
     *  Places the token of a player at Position pos.
     */
    public void doMove(Position pos, int player)
    {
        // TODO
        if (board[pos.x][pos.y] != 0) {
            throw new InputMismatchException("The aim position is invalid.");
        }
        this.setField(pos, player);
    }

    /**
     *  Clears board at Position pos.
     */
    public void undoMove(Position pos)
    {
        // TODO
        if (board[pos.x][pos.y] == 0) {
            throw new InputMismatchException("The aim position is invalid.");
        }

        this.setField(pos, 0);
    }
    
    /**
     *  @return     true if game is won, false if not
     */
    public boolean isGameWon() {
        // TODO
        for (int i = 0; i < n; i++) {
            if (board[i][i] != board[0][0] || board[i][i] == 0) {
                break;
            }
            if (i == n-1) {
                return true;
            }
        }

        for (int i = n-1; i >= 0; i--) {
            if (board[n-1-i][i] != board[0][n-1] || board[n-1-i][i] == 0) {
                break;
            }
            if (i == 0) {
                return true;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != board[i][0] || board[i][j] == 0) {
                    break;
                }
                if (j == n-1) {
                    return true;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (board[i][j] != board[0][j] || board[i][j] == 0) {
                    break;
                }
                if (i == n-1) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *  @return     set of all free fields as some Iterable object
     */
    public Iterable<Position> validMoves()
    {
        // TODO
        ArrayList<Position> moves = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    moves.add(new Position(i, j));
                }
            }
        }

        return moves;
    }

    /**
     *  Outputs current state representation of the Board object.
     *  Practical for debugging.
     */
    public void print()
    {
        // TODO
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (board[j][i]) {
                    case -1:
                        System.out.print("o");
                        break;
                    case 1:
                        System.out.print("x");
                        break;
                    case 0:
                        System.out.print("_");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}

