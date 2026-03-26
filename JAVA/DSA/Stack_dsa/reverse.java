import java.util.*;

public class reverse {

    //push at bottom func

    public static void push_at_bottom(Stack<Integer> st, int data){
        if(st.isEmpty()){
            st.push(data);
            return;
        }
        int val= st.pop();
        push_at_bottom(st, data);
        st.push(val);
    }

    //Integer func

    public static void Intgerfunc(Stack<Integer> list){
        if(list.isEmpty()){
            return;
        }
        int val= list.pop();
        Intgerfunc(list);
        push_at_bottom(list, val);

    }

    //String func

    public static String Stringfunc(String str){

        Stack<Character> stack= new Stack<>();

        int idx=0;
        while(idx<str.length()){
            stack.push( str.charAt(idx));
            idx++;
        }

        StringBuilder sb= new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop()); 
        }

        str= sb.toString();
        return str;
    }
    public static void main(String[] args) {

        //string
        String str="abcd";
        String result= Stringfunc(str);
        System.out.println(result);    
        
        //Integer
        Stack<Integer> list= new Stack<>();
        list.push(20);  list.push(40);  list.push(60);  list.push(80);  
        Intgerfunc(list);
        System.out.println(list);
    }
}
