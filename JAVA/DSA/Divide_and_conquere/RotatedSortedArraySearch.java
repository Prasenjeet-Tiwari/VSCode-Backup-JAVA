public class RotatedSortedArraySearch {

    public static int search(int arr[], int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // If mid element is the target, return mid index
            if (arr[mid] == target) {
                return mid;
            }

            // Check which half is sorted and decide where to search next
            if (arr[start] <= arr[mid]) {
                // Left half is sorted
                if (target >= arr[start] && target < arr[mid]) {
                    end = mid - 1; // Target is in the left sorted half
                } else {
                    start = mid + 1; // Target is in the right half
                }
            } else {
                // Right half is sorted
                if (target > arr[mid] && target <= arr[end]) {
                    start = mid + 1; // Target is in the right sorted half
                } else {
                    end = mid - 1; // Target is in the left half
                }
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int arr[] = {5, 6, 7, 0, 1, 2, 3};
        int target = 0;

        int output = search(arr, target);
        System.out.println("The target element is at index: " + output);
    }
}
