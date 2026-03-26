import java.util.Stack;

public class Stock_span_prb {

    public static int[] Stockspan(int arr[], int span[]) {
        Stack<Integer> stack = new Stack<>();
        span[0] = 1;       // First element always has span = 1
        stack.push(0);

        for (int i = 1; i < arr.length; i++) {
            // Pop elements from stack while stack is not empty 
            // and top of stack is less than or equal to current price
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            // If stack is empty, all elements to the left are smaller than current price
            if (stack.isEmpty()) {
                span[i] = i + 1;
            } else {
                span[i] = i - stack.peek();
            }

            // Push the current index onto the stack
            stack.push(i);
        }

        return span;
    }

    public static void main(String[] args) {
        int arr[] = {100, 80, 60, 70, 60, 85, 100};
        int span[] = new int[arr.length];

        Stockspan(arr, span);

        for (int i = 0; i < span.length; i++) {
            System.out.print(span[i] + " ");
        }
    }
}