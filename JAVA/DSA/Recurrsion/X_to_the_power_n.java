public class X_to_the_power_n {
    
    public static int Power(int a, int n){

        if(n==0){
            return 1;
        }
        int halfpower=Power(a, n/2);
        int halfpowersqare= halfpower * halfpower;

        if(n%2!=0){
            halfpowersqare= a * halfpowersqare;
        }

        return halfpowersqare;
    }
    public static void main(String[] args){

        System.out.println("The output is : "+Power(2,10));
    }    
}
