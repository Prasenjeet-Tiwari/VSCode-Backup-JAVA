public class check_if_sorteed {

    public static boolean is_sorted(int arr[], int i){
        if(i==arr.length-1){
           return true;
        }
        if(arr[i]>arr[i+1]){
            return false;
        }
        return is_sorted(arr, i+1);
    }
    public static void main(String [] args){
        int arr1[]={1,2,3,4,5,6,7};
        int arr2[]={3,5,1,6,2};

        System.out.println("the array arr1 is sorted(True/false): "+is_sorted(arr1,0));
        System.out.println("the array arr1 is sorted(True/false): "+is_sorted(arr2,0));
    }
}
