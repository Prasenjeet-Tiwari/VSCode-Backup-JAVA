public class Grid_total_number_possible_ways{

    public static int countPaths(int i, int j, int row, int col){
        // Base case: If we reach the bottom-right corner, return 1 path found.
        if(i == row - 1 && j == col - 1){
            return 1;
        }

        // If we go out of bounds, return 0 paths.
        if(i >= row || j >= col){
            return 0;
        }

        // Recursive calls:
        // Move right (column + 1 direction)
        int rightPaths = countPaths(i, j + 1, row, col);
        // Move down (row + 1 direction)
        int downPaths = countPaths(i + 1, j, row, col);

        // Return total paths found by summing right and down paths
        return rightPaths + downPaths;
    }

    public static void main(String[] args) {
        int rows = 4;
        int cols = 5;
        int totalPaths = countPaths(0, 0, rows, cols);

        System.out.println("Total number of paths: " + totalPaths);
    }
}
