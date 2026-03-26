import java.util.Stack;

public class max_Area_histogram {

    //NextSmallerLeft

    public static void NextSmallerLeft(int arr[], int nsl[]){

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<arr.length; i++){
            while(!stack.isEmpty() && arr[i]<=arr[stack.peek()]){
                stack.pop();
            }
            if(stack.isEmpty()){
                nsl[i]=-1;

            }else{
                nsl[i]=stack.peek();
            }
            stack.push(i);
        }
        
    }

    //NextSmallerRight

    public static void NextSmallerright(int arr[], int nsr[]){

        Stack<Integer> stack = new Stack<>();
        int n= arr.length;


        for(int i=arr.length-1; i>=0; i--){
            while(!stack.isEmpty() && arr[i]<=arr[stack.peek()]){
                stack.pop();
            }
            if(stack.isEmpty()){
                nsr[i]=n;

            }else{
                nsr[i]=stack.peek();
            }
            stack.push(i); 
        }
    }

    // return max area 
    public static int Area(int nsl[], int nsr[] , int arr[]){
        int largest=0;
        for(int i=0; i<arr.length; i++){
            arr[i]= arr[i]*(nsr[i]-nsl[i]-1);
            if(arr[i]>largest){
                largest=arr[i];
            }
        }
        return largest;
    }

    //Print

    public static void print(int arr[]){
        System.out.print('[');
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+",");
        }
        System.out.print(']');
        System.out.println();
        
    }

    public static void main(String[] args) {

        int arr1[]={2,1,5,6,2,3};
        int nsl[]=new int[arr1.length];
        int nsr[]=new int[arr1.length];

        NextSmallerLeft(arr1, nsl);
        NextSmallerright(arr1, nsr);
        print(nsl);
        print(nsr);

        int output= Area(nsl,nsr,arr1);
        System.out.println("The largest area: "+output);
    }
}
