package DSA.Bit_manipulation;

public class Bit_types {

    public static void main(String args[]){
    
//AND "/&"
        int and= 5&6;
        System.out.println("The AND output of 5 & 6: "+and); 
        /* A=5 =101
         * B=6 =110
         * ___________
         * A&B= 100 = (4) BASE 10
         * 
         * THUS OUTPUT = 4
         */

//OR  "|"
        int or= 5|6;
        System.out.println("The OR output of 5 | 6: "+or);
        /* 5   =   101
         * 6   =   110
         * 5|6 = 111 =7
         */

//XNOR "^"
        /* 0 0=1
         * 1 0=1
         * 0 1=1
         * 1 1=0
         */

        System.out.println("The output of XOR of 5^6: "+(5^6));    
        /*5=101
         *6=110
          ^=011=3
         */

//NOT(Complement) "~"
        //0 to 1 ; 1 to 0

        /* 5=101
         * step 1:  5=00000101
         * step 2: ~5=11111010
         * step 3:   =00000101  here, taking 1 common
         * step 4:   =00000101 + 1
         *           =00000110 = (6)base 10
         * step 5:   = but opposite sign that is = (-6) base 10 //output
         *
         *Similarly ~0=(-1)base 10 */
        
        System.out.println("The output of (NOT) of ~5: "+(~5));

        

//Binary Left Shift "<<"

        /*Rule is A<<B and output is A*(2^B)

         * eg 5<<2 implies
         *     5= 000101
         * 5<<2 = (00)(01)(01) //  MAKING PAIR OF TWO since shift is by B = 2
         *      = (01)(01)(00) //   LEFT SHIFT
         *      = 010100 =(20) 
         *      = base 10      // 5*(2^2)=20 or just convert 010100 to decimal form. 
         */

        System.out.println("The output of (binary left shift) of 5<<2: "+(5<<2));

// Bitwise right shift ">>"

        /*Rule is A>>B and output= A/(2*B)
         * eg:
         *  6  = 000110
         *     = (0)(0)(0)(1)(1)(0) //PAIRNG IN 3 SINCE B=3
         * 6>>3= (0(0)(0)(0)(1)(1) //RIGHT SHIFT
         *     =  000011 
         *     =  (3) base 10   // 6/(2^1) OR USE DECIMAL CONVERSION 
         */
        System.out.println("The output of (binary right shift) of 6>>1: "+(6>>1));




    }

}

    

