import java.util.Scanner;
/*  you have to create all posiible binary words of length n such that
    no two adjuscent character is 1 */
public class NO_continuous_11 {

    public static void func(int n, int lastplace , String str){

        if(n==0){
            System.out.println(str);
            return;
        }
        func(n-1, 0, str + 0);
        if(lastplace==0){
            func(n-1, 1, str+1);    // not using string builder cause we need to remove and append 
        }

    }
    public static void main(String[] args){
        System.out.print("The posibble valid  worlds are are : \n" );
        func(3,0,"");
    }
}


