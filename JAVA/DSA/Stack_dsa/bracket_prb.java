import java.util.*;
public class bracket_prb {

    public static boolean isValid(String str){

        Stack<Character> stack= new Stack<>();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            if( (ch=='{') || (ch=='(') || (ch=='[')){
                stack.push(ch);
            }else{
                if(stack.isEmpty()){
                }
                if( (stack.peek()=='(' && ch==')')  ||  (stack.peek()=='{' && ch=='}')  ||  (stack.peek()=='[' && ch==']')){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {

        String str1= "({[]})";
        String str2= "[{}";
        System.out.println("case 1: "+ isValid(str1));
        System.out.println("case 2: "+ isValid(str2));
        
    }
    
}
