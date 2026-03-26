package DSA.Bit_manipulation;

public class problems {

    public static void odd_even(int number){
        int bitMask=1;
        //eg 5=0101 and for "AND" the output is 1 only if both are 1 and for odd last digit is 1

        if((number & bitMask)==0){
            System.out.println("The number "+number+" is even");
        }else{
            System.out.println("The number "+number+" is odd");
        }
    }

    public static void main(String args[]){

// Checking odd even by using AND 

        odd_even(5);
        odd_even(6);
        odd_even(7);
        System.out.println("\n");
        

    }
    
}
