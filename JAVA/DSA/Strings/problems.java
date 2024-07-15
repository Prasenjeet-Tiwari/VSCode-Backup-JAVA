package DSA.Strings;


public class problems {
    
    public static void palindrome(String a) {
        int length = a.length();
        boolean isPalindrome = true;

        for (int i = 0; i < length / 2; i++) {
            if (a.charAt(i) != a.charAt(length - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("Congrats! " + a + " is a palindrome");
        } else {
            System.out.println("Sorry, " + a + " is not a palindrome");
        }
    }

    public static double ShortestPath(String direction){
        int length= direction.length();
        int yaxis=0;
        int xaxis=0;
        for(int i=0; i<length; i++){
            if(direction.charAt(i)=='w' || direction.charAt(i)=='W'){
                xaxis--;
            }
            else  if(direction.charAt(i)=='e'|| direction.charAt(i)=='E'){
                xaxis++;
            }
            else if(direction.charAt(i)=='s'|| direction.charAt(i)=='S'){
                yaxis++;
            }
            else if(direction.charAt(i)=='n'|| direction.charAt(i)=='N'){
                yaxis--;
            }
        }
        double calc=((xaxis)*(xaxis)+(yaxis)*(yaxis));
        double distance= Math.sqrt(calc);
        return distance;
    }
    
    public static void main(String args[]){

    // palindrome
        
        String word="racecar";
        palindrome(word);

    // Shortest path

        String code="WNEENESENNN";
        System.out.println("Shortest distance is: "+ ShortestPath(code));

    // Uppercase the string

        String input = "what is. my name, yo yo";

        StringBuilder sb = new StringBuilder("");
        char ch = Character.toUpperCase(input.charAt(0));   //first word printed here
        sb.append(ch);
        
        for(int i = 1; i<input.length(); i++){
            if(input.charAt(i) ==' ' &&  i<input.length()-1 ){
                sb.append(input.charAt(i));
                i++; 
                sb.append(Character.toUpperCase(input.charAt(i)));
            }else{
                sb.append(input.charAt(i));
            }
        }
        System.out.println(sb.toString());  //rest output printed here 

    // Strig Compression

        String data= "aaabbcccdd";
        int length= data.length();
        int counter=1;
        char prv=data.charAt(0);     

        for(int i=1; i<length; i++){
            if(data.charAt(i)==prv){
                counter++;
            }else{
                System.out.print(prv);
                System.out.print(counter);
                prv=data.charAt(i);
                counter=1;
            }
        }
        System.out.print(prv);  // for the last letter //IMPORTANT
        System.out.print(counter);

        //END

    }
    
}
