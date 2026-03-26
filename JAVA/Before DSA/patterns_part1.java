public class patterns_part1 {
    public static void main(String[] args){

//star pattern        
         for(int i = 1; i <= 4; i++){
             for(int j = 1; j <= i; j++){
                 System.out.print("*");
             }
             System.out.println();
         }

        System.out.println("\n********************************************************************\n");

//inverted star pattern        
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4-i+1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\n********************************************************************\n");

//numbered pattern (Half Number Pyramid)       
       for(int i = 1; i <= 4; i++) {
           for(int j = 1; j <= i; j++) {
               System.out.print(j);
           }
           System.out.println();
       }

        System.out.println("\n********************************************************************\n");
//Half character Pyramid (ABCD)     

       char ch = 'A';
       for(int i = 1; i <= 4; i++) {
           for(int j = 1; j <= i; j++) {
               System.out.print(ch);
               ch++;
           }
           System.out.println();
       }
    }
}
