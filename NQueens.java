public class NQueens {
    static final int N = 8;
    static boolean foundSolution = false; // Added flag to stop after one solution

    static boolean isSafe(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q')
                return false;
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }

    static void printSolution(char[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void solveNQueens(char[][] board, int row) {
        if (row == N) {
            printSolution(board);
            foundSolution = true; // Stop after first solution
            return;
        }
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                solveNQueens(board, row + 1);
                if (foundSolution)
                    return; // Stop recursion if solution found
                board[row][col] = '-';
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = '-';
            }
        }
        solveNQueens(board, 0);
    }
}
