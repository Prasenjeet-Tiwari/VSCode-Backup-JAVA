import java.util.*;


public class _11_ambitious_kid{
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);

        int len= sc.nextInt();

        if(len==0 ){
            System.out.println(0);
        }else{
            int min=Math.abs(sc.nextInt());
            for(int i=1; i<len; i++){
                int temp=sc.nextInt();
                min=Math.min(Math.abs(temp),min);
            }
            System.out.println(min);
        }
    }
}
