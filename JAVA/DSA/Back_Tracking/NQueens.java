import java.util.*;

public class NQueens {

    public static void printboard(char[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
    
    public static boolean issafe(char[][] board, int row, int col) {
        int n = board.length;
        
        // Check left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // Check right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // Check column
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    
    public static void nqueen_all_permutation(char[][] board, int row) {
        int n = board.length;

        if (row == n) {
            printboard(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (issafe(board, row, col)) {
                board[row][col] = 'Q'; // Place queen
                nqueen_all_permutation(board, row + 1);
                board[row][col] = 'X'; // Backtrack
            }
            // No need for else; backtrack happens naturally
        }
    }

    public static void main(String[] args) {
        int n = 4;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 'X'; // Initialize board with 'X'
            }
        }

        nqueen_all_permutation(board, 0);
    }
}
