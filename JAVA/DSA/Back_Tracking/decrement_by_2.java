

public class decrement_by_2 {

    public static void func(int arr[], int index, int value){
        if(index==arr.length){
            printarr(arr);      // print after assingment
            return;
        }
        arr[index]=value;
        func(arr, index+1, value+1);
        arr[index]=arr[index]-2;
        
    }
    public static void printarr(int arr[]){
        for(int j=0; j<arr.length; j++){
            System.out.print(arr[j]+ " ");
        }
    }
    public static void main(String[] args){

        int arr[]= new int[5];
        func(arr, 0, 1);
        System.out.println();
        printarr(arr); // print after decrement -2
    } 
}
