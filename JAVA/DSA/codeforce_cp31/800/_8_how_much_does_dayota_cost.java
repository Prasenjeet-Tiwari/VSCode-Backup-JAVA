import java.util.*;

public class _7_dont_count {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int check =0;
            for(int i=0; i<n; i++){
                int input=sc.nextInt();
                if(input==m){
                    check=1;

                }
            }
            if(check==0){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }

        }
        sc.close();
    }
}
