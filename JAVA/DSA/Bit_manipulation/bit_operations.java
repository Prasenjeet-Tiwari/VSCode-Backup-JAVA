package DSA.Bit_manipulation;

public class bit_operations {

    public static void ith_of(int number, int position_LtoR){

        int bitMask = 1 << position_LtoR ;   //left shiftet the 1 to the ith position

        if((bitMask & number)==0 ){
            System.out.println("The "+position_LtoR+"th value of "+number+" is: 0");
        }else{
            System.out.println("The "+position_LtoR+"th value of "+number+" is: 1");
        }
    }

    public static void setith(int number, int position){
            }

    public static void main(String args[]){

// Get ith bit

        // Create a bitwise ; shift it to the location of ith ; do AND opertaion

        ith_of(10,3);   //10=1010
        ith_of(2,2);    //2= 0010
        ith_of(5,2);    //5= 0101
        
//set ith bit
        // 


    }
    
}
