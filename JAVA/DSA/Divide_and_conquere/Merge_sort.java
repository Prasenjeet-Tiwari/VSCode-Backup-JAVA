
public class Merge_sort {

    public static void merge_sort(int arr[], int s_idx, int e_idx) {
        if (s_idx < e_idx) {
            int mid = s_idx + (e_idx - s_idx) / 2; // which is equal to (e_idx + s_idx) / 2
            merge_sort(arr, s_idx, mid); // left part
            merge_sort(arr, mid + 1, e_idx); // right part
            merge(arr, s_idx, mid, e_idx); // merge the sorted parts
        }
    }

    public static void merge(int arr[], int s_idx, int mid, int e_idx) {
        int temp[] = new int[e_idx - s_idx + 1]; // temporary array with the correct length
        int i = s_idx;
        int j = mid + 1;
        int k = 0;
 
        while (i <= mid && j <= e_idx) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) { // doing this if left part is left
            temp[k++] = arr[i++];
        }

        while (j <= e_idx) { // doing this if right part is left
            temp[k++] = arr[j++];
        }

        for (k = 0, i = s_idx; i <= e_idx; k++, i++) {  // coping it to original array arr
            arr[i] = temp[k];
        }
    }

    public static void printarr(int arr[]) { // function to print elements
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int arr[] = { 6, 5, 4, 3, 8, 2, 9 };
        System.out.print("Actual array : ");
        printarr( arr);

        merge_sort(arr, 0, arr.length - 1);     // to sort the array
        System.out.print("Sorted array : ");
        printarr(arr);
    }
}
