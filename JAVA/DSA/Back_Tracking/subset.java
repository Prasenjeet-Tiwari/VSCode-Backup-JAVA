public class subset {
    
    public static void Subsetfunc(String str, String output, int index){
        //base case
        if(index == str.length()){

            if(output.length()==0){     //case if null subset
                System.out.println("Null");
            }

            System.out.println(output); 
            return;
        }

        //yes
        Subsetfunc(str, output + str.charAt(index), index +1);
        
        //no
        Subsetfunc(str, output, index+1);
    }
    public static void main(String[] args){
    
        String str= "abc";
        String output= "";
        Subsetfunc(str,output,0);
    }
}
