import java.util.*;

public class Duplicate_paranthesis{

    public static boolean duplicate(String str){

        Stack<Character> stack= new Stack<>();

        for(int i=0; i< str.length(); i++){
            char ch= str.charAt(i);

            if(ch==')'){
                
                int count=0;

                while(stack.peek()!='('){
                    stack.pop();
                    count++;
                }
                if(count<1){
                    return true;
                }else{
                    stack.pop();
                }

            }else{
                stack.push(ch);
            }
        }
        return false;

    }
    public static void main(String[] args) {

        String str1= "((a+b)+c)";
        String str2= "((a+b))+c)";

        System.out.println("Case 1: "+ duplicate(str1));
        System.out.println("Case 2: "+ duplicate(str2));
        
    }
}