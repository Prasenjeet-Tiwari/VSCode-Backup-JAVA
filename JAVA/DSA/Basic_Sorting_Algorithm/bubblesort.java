public class bubblesort {

    public static void printarr(int arr[]) {     // for printing array;
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println(); // For better readability
    }

    public static void bubble(int arr[], int start, int end) {   // sorting
        for (int i = start; i < end; i++) {
            for (int j = start; j < end - (i - start); j++) {
                if (arr[j] > arr[j + 1]) {                  // swapping
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {7, 8, 3, 4, 1, 6, 2, 5};
        System.out.print("\nArray before bubble sorting: ");    // printing condition before swap
        printarr(arr);

        System.out.print("\nArray after bubble sorting: ");
        bubble(arr, 0, arr.length-1);                  // bubble function call
        printarr(arr);                                  // final array print
    }
}
