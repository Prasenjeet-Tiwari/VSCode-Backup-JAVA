import java.util.*;

public class _9_Goal_of_victory{
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);

        int t= sc.nextInt();

        while(t-->0){
            int len=sc.nextInt(); //no of teams
            int ans=0;
            for(int i=0; i<len-1; i++){
                int temp=sc.nextInt();
                ans+=temp;
            }
            System.out.println((-1)*ans);
        }
    }
}