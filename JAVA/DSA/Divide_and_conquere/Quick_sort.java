public class Quick_sort {
    /* quick sort = moves smaller elements to left of a pivot.
   recursively divide array in 2 partitions

   run-time complexity = Best case O(n log(n))
                         Average case O(n log(n))
                         Worst case O(n^2) if already sorted

   space complexity    = O(log(n)) due to recursion */

    public static void quick_sort(int arr[], int start, int end) {
        if (start >= end) {
            return;
        }
        int pidx = partition(arr, start, end);
        quick_sort(arr, start, pidx - 1);
        quick_sort(arr, pidx + 1, end);
    }

    public static int partition(int arr[], int start, int end) {
        int pivot = arr[end];
        int i = start - 1;

        for (int j = start; j < end; j++) { // corrected the loop to start from `start` and go up to `end-1`
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }

        // Place the pivot in the correct position
        i++;
        int temp = arr[end];
        arr[end] = arr[i];
        arr[i] = temp;

        return i;
    }

    public static void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " "); // added space for readability
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {6, 3, 4, 5, 2, 1};
        System.out.print("Actual array: ");
        printarr(arr);

        quick_sort(arr, 0, arr.length - 1); // sorting
        System.out.print("Sorted array: ");
        printarr(arr);
    }
}