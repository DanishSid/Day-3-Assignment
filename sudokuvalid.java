//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class sudokuvalid {
    // Method to validate a given Sudoku board
    public static boolean isValidSudoku(int[][] sudoku) {
        // Check each row
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(sudoku[i])) {
                return false;
            }
        }

        // Check each column
        for (int j = 0; j < 9; j++) {
            int[] column = new int[9];
            for (int i = 0; i < 9; i++) {
                column[i] = sudoku[i][j];
            }
            if (!isValidRow(column)) {
                return false;
            }
        }

        // Check each 3x3 sub-box
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                int[] subBox = new int[9];
                int index = 0;
                for (int i = boxRow * 3; i < boxRow * 3 + 3; i++) {
                    for (int j = boxCol * 3; j < boxCol * 3 + 3; j++) {
                        subBox[index++] = sudoku[i][j];
                    }
                }
                if (!isValidRow(subBox)) {
                    return false;
                }
            }
        }

        // If all checks pass, return true
        return true;
    }

    // Method to check if a row is valid (contains digits 1-9 without repetition)
    private static boolean isValidRow(int[] row) {
        boolean[] seen = new boolean[10]; // to keep track of seen digits
        for (int num : row) {
            if (num != 0) { // ignore empty cells
                if (seen[num]) {
                    return false; // if the digit is seen again, the row is invalid
                }
                seen[num] = true; // mark the digit as seen
            }
        }
        return true; // if all digits are valid, return true
    }

    // Main method to test the isValidSudoku method with example boards
    public static void main(String[] args) {
        // Example board 1
        int[][] board1 = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        System.out.println(isValidSudoku(board1)); // Output: true

        // Example board 2
        int[][] board2 = {
                {8, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        System.out.println(isValidSudoku(board2)); // Output:false
    }
}

//time complexity O(n)
//Space complexity O(1)


