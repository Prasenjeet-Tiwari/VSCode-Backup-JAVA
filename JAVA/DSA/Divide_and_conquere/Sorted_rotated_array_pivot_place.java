public class a{

    public static int func(int arr[], int start, int end,int target){
        int x;
        int mid= start-(end+start)/2;   // equal to (start+end)/2
        if(mid==target){
            return mid;
        }

        if(arr[mid]<=target){
            end= mid;
            x = func(arr, start, end, target);
        }else{
            start= mid;
            x = func(arr, start, end, target);               
        }
        
        return x;
    }
    public static void main(String[] args){

        int arr[]= {5,6,7,0,1,2,3};

        int output= func(arr,0,arr.length-1,0);
        System.out.println("The pivoted point is: "+ output);

    }
    
}