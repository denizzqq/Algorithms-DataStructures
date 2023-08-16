import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class implements a game of Row of Bowls.
 * For the games rules see Blatt05. The goal is to find an optimal strategy.
 */
public class RowOfBowls {
    int[][] lookup;
    int[] values;

    public RowOfBowls() {
    }
    
    /**
     * Implements an optimal game using dynamic programming
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGain(int[] values)
    {
        this.lookup = new int[values.length][values.length];
        this.values = new int[values.length];

        for (int i = 0; i < lookup.length; i++) {
            for (int j = 0; j < lookup.length; j++) {
                lookup[i][j] = -1;
            }
        }

        for (int i = 0; i < values.length; i++) {
            this.values[i] = values[i];
        }

        return maxGainHelper(values, 0, values.length - 1);
    }

    public int maxGainHelper(int[] values, int i, int j) {
        if (values.length == 0) {
            return 0;
        }

        if (lookup[i][j] == -1) {
            if (values.length == 1) {
                lookup[i][j] = values[0];
                return values[0];
            }

            int left = values[0];
            int right = values[values.length - 1];

            int[] valuesLeft = Arrays.copyOfRange(values, 1, values.length);
            int[] valuesRight = Arrays.copyOfRange(values, 0, values.length - 1);

            lookup[i][j] = Math.max(left - maxGainHelper(valuesLeft, i + 1, j), right - maxGainHelper(valuesRight, i, j - 1));
        }

        return lookup[i][j];
    }

    /**
     * Implements an optimal game recursively.
     *
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGainRecursive(int[] values) {
        // TODO
        if (values.length == 0) {
            return 0;
        }

        if (values.length == 1) {
            return values[0];
        }

        int left = values[0];
        int right = values[values.length - 1];

        int[] valuesLeft = Arrays.copyOfRange(values, 1, values.length);
        int[] valuesRight = Arrays.copyOfRange(values, 0, values.length - 1);

        return Math.max(left - maxGainRecursive(valuesLeft), right - maxGainRecursive(valuesRight));
    }

    
    /**
     * Calculates an optimal sequence of bowls using the partial solutions found in maxGain(int values)
     * @return optimal sequence of chosen bowls (represented by the index in the values array)
     */
    public Iterable<Integer> optimalSequence()
    {
        // TODO
        ArrayList<Integer> sequence = new ArrayList<>();

        int currentI = 0;
        int currentJ = lookup.length - 1;

        try {
            for (int i = 0; i < lookup.length; i++) {
                if (values[currentI] - lookup[currentI + 1][currentJ] > values[currentJ] - lookup[currentI][currentJ - 1]) {
                    sequence.add(currentI);
                    currentI++;
                } else {
                    sequence.add(currentJ);
                    currentJ--;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            if (currentI == values.length - 1) {
                sequence.add(currentI);
            } else {
                sequence.add(currentJ);
            }
        }

        return sequence;
    }


    public static void main(String[] args)
    {
        // For Testing
        RowOfBowls x = new RowOfBowls();
    }
}

