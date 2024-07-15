public class sudoku {

    // Print Sudoku
    public static void printArr(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Block condition check function
    public static boolean func_block(int arr[][], int row, int col, int value) {
        int block_row = (row / 3) * 3;
        int block_col = (col / 3) * 3;

        for (int i = block_row; i < block_row + 3; i++) {
            for (int j = block_col; j < block_col + 3; j++) {
                if (arr[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    // Is safe function for row, column checking, and block check
    public static boolean isSafe(int arr[][], int row, int col, int value) {
        // Row check
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][col] == value) {
                return false;
            }
        }

        // Column check
        for (int j = 0; j < arr.length; j++) {
            if (arr[row][j] == value) {
                return false;
            }
        }

        // Block check
        if (!func_block(arr, row, col, value)) {
            return false;
        }

        return true;
    }

    public static boolean sudoku(int arr[][], int row, int col) {
        if (row == arr.length) {
            return true;
        }
        if (col == arr[row].length) {
            return sudoku(arr, row + 1, 0);
        }
        if (arr[row][col] != 0) {
            return sudoku(arr, row, col + 1);
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(arr, row, col, num)) {
                arr[row][col] = num;
                if (sudoku(arr, row, col + 1)) {
                    return true;
                }
                arr[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        if (sudoku(arr, 0, 0)) {
            printArr(arr);
        } else {
            System.out.println("No solution exists");
        }
    }
}
