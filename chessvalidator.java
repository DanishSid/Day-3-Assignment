//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class chessvalidator{
    public static boolean isKingSafe(int[][] chessBoard) {
        // Find king's position
        int kingRow = -1, kingCol = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j] == 6) {
                    kingRow = i;
                    kingCol = j;
                    break;
                }
            }
        }

        // Check if king is safe from enemies
        return isSafeFromHorse(chessBoard, kingRow, kingCol) &&
                isSafeFromCamel(chessBoard, kingRow, kingCol) &&
                isSafeFromQueen(chessBoard, kingRow, kingCol) &&
                isSafeFromElephant(chessBoard, kingRow, kingCol);
    }

    private static boolean isSafeFromHorse(int[][] chessBoard, int kingRow, int kingCol) {
        int[][] horseMoves = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };
        for (int[] move : horseMoves) {
            int newRow = kingRow + move[0];
            int newCol = kingCol + move[1];
            if (isValidMove(newRow, newCol) && chessBoard[newRow][newCol] == 2) {
                return false; // King is threatened by a horse
            }
        }
        return true;
    }

    private static boolean isSafeFromCamel(int[][] chessBoard, int kingRow, int kingCol) {
        // Camel can attack in 8 directions
        int[][] camelMoves = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };
        for (int[] move : camelMoves) {
            int newRow = kingRow + move[0];
            int newCol = kingCol + move[1];
            if (isValidMove(newRow, newCol) && chessBoard[newRow][newCol] == 3) {
                return false; // King is threatened by a camel
            }
        }
        return true;
    }

    private static boolean isSafeFromQueen(int[][] chessBoard, int kingRow, int kingCol) {
        // Queen can move in all 8 directions
        return !isThreatenedInDirection(chessBoard, kingRow, kingCol, -1, -1) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, -1, 0) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, -1, 1) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, 0, -1) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, 0, 1) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, 1, -1) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, 1, 0) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, 1, 1);
    }

    private static boolean isThreatenedInDirection(int[][] chessBoard, int kingRow, int kingCol, int rowDelta, int colDelta) {
        int newRow = kingRow + rowDelta;
        int newCol = kingCol + colDelta;
        while (isValidMove(newRow, newCol)) {
            if (chessBoard[newRow][newCol] == 4 || chessBoard[newRow][newCol] == 5) {
                return true; // King is threatened by a queen or elephant
            }
            newRow += rowDelta;
            newCol += colDelta;
        }
        return false;
    }

    private static boolean isSafeFromElephant(int[][] chessBoard, int kingRow, int kingCol) {
        // Elephant can move in all 8 directions
        return !isThreatenedInDirection(chessBoard, kingRow, kingCol, -1, -1) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, -1, 1) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, 1, -1) &&
                !isThreatenedInDirection(chessBoard, kingRow, kingCol, 1, 1);
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public static void main(String[] args) {
        int[][] chessBoard = {
                {0, 0, 0, 0, 0, 0, 0, 0}, // 0 represents empty cell
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 6, 0, 0, 0}, // 6 represents king
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        System.out.println(isKingSafe(chessBoard)); // Output: true
    }
}

//time complexity=O(n)
//space complexity=O(1)