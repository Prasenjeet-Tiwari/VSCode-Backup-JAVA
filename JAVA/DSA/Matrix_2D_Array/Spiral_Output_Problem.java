package DSA.Matrix_2D_Array;

public class Spiral_Output_Problem {
    public static void spiralPrinter(int matrix[][], int rows, int columns){
        int startRow = 0;
        int endRow = rows - 1;
        int startColumn = 0;
        int endColumn = columns - 1;

        while (startRow <= endRow && startColumn <= endColumn) {
            // Print top row
            for (int i = startColumn; i <= endColumn; i++) {
                System.out.print(matrix[startRow][i] + " ");
            }
            startRow++;

            // Print rightmost column
            for (int i = startRow; i <= endRow; i++) {
                System.out.print(matrix[i][endColumn] + " ");
            }
            endColumn--;

            // Print bottom row
            if (startRow <= endRow) {
                for (int i = endColumn; i >= startColumn; i--) {
                    System.out.print(matrix[endRow][i] + " ");
                }
                endRow--;
            }

            // Print leftmost column
            if (startColumn <= endColumn) {
                for (int i = endRow; i >= startRow; i--) {
                    System.out.print(matrix[i][startColumn] + " ");
                }
                startColumn++;
            }
        }
    }

    public static void main(String[] args) {
        int matrix[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int numOfRows = matrix.length;
        int numOfColumns = matrix[0].length;
        spiralPrinter(matrix, numOfRows, numOfColumns);
    }
}
