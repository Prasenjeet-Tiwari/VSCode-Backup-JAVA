
public class permuatation {
   
   public static void permutation(String str, String output, int index){
       //base case
       if(index == str.length()){
           if(output.length()==0){     //case if null subset
               System.out.println("Null");
           }
           System.out.println(output); 
           return;
       }
       //yes
       permutation(str, output + str.charAt(index), index +1);
       
       //no
       permutation(str, output, index+1);
   }
   public static void main(String[] args){
   
       String str= "abc";
       String output= "";
       permutation(str,output,0);
   }
}
