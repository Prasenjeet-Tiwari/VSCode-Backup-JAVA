import java.util.*;
public class codeforce {
    public static void main(String[] args) {

        Scanner sc=new Scanner( System.in);

        String s1= sc.nextLine();
        String s2= sc.nextLine();

        if(s1.compareToIgnoreCase(s2)==0)
        {
            System.out.println(0);
        }else if (s1.compareToIgnoreCase(s2)>0){
            System.out.println(1);
        }else{
            System.out.println(-1);
        }
        sc.close();
    }
}
