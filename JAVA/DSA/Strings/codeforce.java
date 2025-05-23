import java.util.*;

public class codeforce {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        int frd=sc.nextInt();
        int height=sc.nextInt();
        int width=0;
        for(int i=0; i<frd; i++){
            int input=sc.nextInt();
            if(input>height){
                width+=2;
            }else{
                width+=1;
            }
        }
        System.out.println(width);
        sc.close();
    }
}
