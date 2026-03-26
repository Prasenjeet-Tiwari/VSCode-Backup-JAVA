import java.util.*;
public class right_highest {

    public static void func(int[] arr, int nxtGreater[]) {

        Stack<Integer> stack = new Stack<>();
        for(int i=arr.length-1; i>=0; i--){

            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]){
                stack.pop();
            }

            if(stack.isEmpty()){
                nxtGreater[i]=-1;
            }else{
                nxtGreater[i]=arr[stack.peek()];

            }

            stack.push(i);
        }
        return;
    }
    

    public static void main(String[] args) {
        int arr[]={6,8,0,1,3};
        int nxtGreater[]= new int[arr.length];

        func(arr, nxtGreater);


        for(int i=0; i<nxtGreater.length; i++){
            System.out.print(nxtGreater[i]+" ");
        }
    }
}
