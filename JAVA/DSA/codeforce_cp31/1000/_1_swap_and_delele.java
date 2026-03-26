import java.util.*;

public class _1_swap_and_delele {
    
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

            boolean flag=true;

            int idx=a.length();    //usuaaly i kwill keep it 0 but to avaoid case of 
                                    //no change i keep it length sixe so that length -idx=0
                        
                        //Very Important: i cant have flag in loop condiitona and thenuse the i 
                        // because the loop will increment i once more  even if flag  is false
                        //hence we will use separate  var to store index and use break to break;

            for( int i=0; i<a.length() && flag; i++){
                char curr=a.charAt(i);
                if(curr=='0'){
                    if(count1!=0){
                        count1--;
                    }else{
                        idx=i;
                        break;
                    }
                }else{
                    if(count0!=0){
                        count0--;
                    }else{
                        idx=i;
                        break;
                    }

                }
            }

            System.out.println(a.length()-(idx));
            
        }

        scanner.close();
    }
}
