import java.util.*;

public class _7_dont_count {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            String curr=sc.next();
            String check=sc.next();
            
            int ans=0;
            boolean found=false;

            while(curr.length()<=36){
                if(curr.contains(check)){
                    found=true;
                    break;
                }
                curr=curr+curr;
                ans+=1;

            }
            if(found==true){
                System.out.println(ans);
            }else{
                System.out.println(-1);
            }

        }
        sc.close();
    }
}
