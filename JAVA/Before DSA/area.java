import java.util.Scanner;
class area {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.print("input the radius of the circle:");
        int number=input.nextInt();
        int area=(22/7)*number*number;
        System.out.println("the area is: "+area);

        // type casting and type conversion

       
     
        System.out.print("input the radius of the circle:");
        int number1=input.nextInt();
        float area1= (float)3.14*(float)number1*(float)number1;
        System.out.println("the area is: "+(int)area1);
        input.close();
    
    }
}