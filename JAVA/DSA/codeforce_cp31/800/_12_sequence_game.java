import java.util.*;

public class _12_sequence_game{
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while(t-- > 0){
            int len = sc.nextInt(); // letters
            ArrayList<Integer> arr = new ArrayList<>();
            
            int prev = sc.nextInt();
            arr.add(prev);
            
            for(int i = 1; i < len; i++){
                int cur = sc.nextInt();
                if(cur < prev){      // compare with previous input, not arr
                    arr.add(1);      // insert smaller number to allow decreasing
                }
                arr.add(cur);
                prev = cur;
            }
            
            System.out.println(arr.size());
            for(int i : arr){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
