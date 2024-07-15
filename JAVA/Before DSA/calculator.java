import java.util.Scanner;

class calculator {
    public static void main(String args[]) {
    	
        Scanner input = new Scanner(System.in);
    	
        System.out.print("Enter first integer: ");
        int number1 = input.nextInt();
        System.out.print("Enter second integer: ");
        int number2 = input.nextInt();
        System.out.print("Enter the operator: ");
        char operator = input.next().charAt(0);
        switch(operator){
            case '+':System.out.println("your output is: "+(number1+number2));
                break;
            case '-':System.out.println("your output is: "+(number1-number2));
                break;
            case '*':System.out.println("your output is: "+(number1*number2));
                break;
            case '/':System.out.println("your output is: "+(number1/number2));
                break;
            default:System.out.println("nuuull");
        }

        // closing the scanner object
        input.close();
    }
}
