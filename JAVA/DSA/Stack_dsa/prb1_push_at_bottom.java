import java.util.*;

public class prb1_push_at_bottom {

    public static void func(Stack<Integer> st, int data){
        if(st.isEmpty()){
            st.push(data);
            return;
        }
        int val= st.pop();
        func(st, data);
        st.push(val);
    }
    public static void main(String[] args) {

        Stack<Integer> st= new Stack<>();
        st.push(10);    st.push(20);    st.push(30);    st.push(40);   
        
        func(st, 25);
        while (!st.isEmpty()) {
            System.out.print(st.peek()+" ");
            st.pop();
            
        }    
    }
}
