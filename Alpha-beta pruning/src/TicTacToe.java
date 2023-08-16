
/**
 * This class implements and evaluates game situations of a TicTacToe game.
 */
public class TicTacToe {

    /**
     * Returns an evaluation for player at the current board state.
     * Arbeitet nach dem Prinzip der Alphabeta-Suche. Works with the principle of Alpha-Beta-Pruning.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     * @return          rating of game situation from player's point of view
    **/
    public static int alphaBeta(Board board, int player)
    {
        // TODO
        return alphaBeta(board, player, -Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static int alphaBeta(Board board, int player, int alpha, int beta) {
        if (!board.isGameWon() && board.nFreeFields() == 0) {
            return 0;
        }

        if (board.isGameWon()) {
            return -board.nFreeFields() - 1;
        }

        for (Position position : board.validMoves()) {
            board.doMove(position, player);
            int score = -alphaBeta(board, -player, -beta, -alpha);
            board.undoMove(position);
            if (score > alpha) {
                alpha = score;
                if (alpha >= beta) {
                    break;
                }
            }
        }

        return alpha;
    }

    /**
     * Vividly prints a rating for each currently possible move out at System.out.
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     * formatting: See "Beispiel 1: Bewertung aller Zugmöglichkeiten" (Aufgabenblatt 4).
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
    **/
    public static void evaluatePossibleMoves(Board board, int player)
    {
        // TODO
        int n = board.getN();
        int result[][] = new int[n][n];

        for (Position position : board.validMoves()) {
            board.doMove(position, player);
            int score = -alphaBeta(board, -player, -Integer.MAX_VALUE, Integer.MAX_VALUE);
            board.undoMove(position);
            result[position.x][position.y] = score;
        }

        System.out.print("Evaluation for player");
        if (player == 1) {
            System.out.println(" 'x':");
        } else {
            System.out.println(" 'o':");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Position pos = new Position(j, i);
                int value = result[j][i];
                if (board.getField(pos) == 1) {
                    System.out.print("x");
                } else if (board.getField(pos) == -1) {
                    System.out.print("o");
                } else if (value < 0) {
                    System.out.print(value);
                } else {
                    System.out.print(value);
                }
                if (j != n-1) {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }

    }

    public static void main(String[] args)
    {
    }
}

