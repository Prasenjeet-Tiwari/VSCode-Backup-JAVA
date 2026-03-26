import java.util.*;

public class _30_01_game {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt(); // number of test cases

        while (t-- > 0) {

            String a = scanner.next(); // input 01 sequence

            int count0=0,count1=0;  //freq of 0 and 1
            
            for(int i=0; i<a.length(); i++){
                char temp=a.charAt(i);
                if(temp=='0'){
                    count0++;
                }else{
                    count1++;
                }
            }

            int decider=Math.min(count0,count1);
            if(decider%2==0){
                System.out.println("NET");
            }else{
                System.out.println("DA");
            }

        
            
        }

        scanner.close();
    }
}
