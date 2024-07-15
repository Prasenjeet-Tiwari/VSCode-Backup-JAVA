//package DSA.Strings;
import java.util.Scanner;


public class Strings_Basics {
    public static void main(String args[]){

        Scanner sc= new Scanner(System.in);

// How to create

        String s1= "a"; //creating using String (data define) 
        char s2[]= {'a','b','c'};   //in form of array
        String s3="abcde";  //all in one

// How to get input and output
        System.out.print("Enter 2 words for (next): ");
        String a= sc.next();

        sc.nextLine();  // Consume the newline character left by the previous next() call

        System.out.print("Enter 2 words for (nextline): ");
        String b=sc.nextLine();

        System.out.println("Output of next  (next only takes the first word before 1st space): "+a);
        System.out.println("Output of nextLine  (takes all the string data before end of line): "+b);
        System.out.print("\n");

// length
        System.out.println("length of s2: "+s1.length()); //use paranthesis cause here string is a function
        System.out.println("length of s2: "+s2.length);  //no paranthesis cause its array
        System.out.println("length of s3: "+s3.length());   //use paranthesis cause here string is a function
        System.out.print("\n");
        
//concatenation

        String s4= s1+s3;
        System.out.println("s4: "+s4);
        System.out.print("\n");

// chartAt() Method - value at that index
        // structure - String.charAt(index));

        for(int i=0; i<s4.length(); i++){
            System.out.print(s4.charAt(i)+" "); // to print each indices in a loop[]
        }
        System.out.println("\n");

// comparing
    
        String e="tom";
        String f="tom";
        String g=new String("tom");
        System.out.println(e==f);   // same object since data same
        System.out.println(e==g);   // here not same object hence false as output

            // equals only compare the value and ignors if its same object or not
        System.out.println(e.equals(f));
        System.out.println(e.equals(g));
        System.out.print("\n");

// substring

        String name="Prasenjeet";
        System.out.println(name.substring(0,4));    //index value of 4 excluded
        System.out.print("\n");

// compareTo and compareToIgnoreCase lexiography

        /* str1.compare(str2)
        if 0:equal
        greater than 0: str1>str2
        smaller than 0: str1<str2
        
        Note: replace "compareTo" with "compareToIgnoreCase" and it treats 'A' and 'a'as same   */

        String fruits[]={"apple","Guava","guava","bannana","Jammun"};
        String largest=fruits[0];

        for(int k=1; k<fruits.length; k++){     //compareTo
            if(largest.compareTo(fruits[k])<0){
                largest=fruits[k];
            }
        }
        System.out.println("The largest string (compareTo): "+largest);

        for(int k=1; k<fruits.length; k++){     //compareToIgnoreCase
            if(largest.compareToIgnoreCase(fruits[k])<0){
                largest=fruits[k];
            }
        }
        System.out.println("The largest string (compareToIgnoreCase): "+largest);
        System.out.print("\n");

// String Builder

        StringBuilder sb= new StringBuilder("xyz");
        for(char ch='a'; ch<='f'; ch++){
            sb.append(ch);
        }
        System.out.print(sb);
        System.out.println("\n");

// string builder to string
        String l= sb.toString(); // since sb is an object
        System.out.println("now its string: "+l);
        System.out.println("\n");

// to uppercase
        String upper="apple";
        char output=Character.toUpperCase(upper.charAt(0));
        System.out.println(output+"\n");

// String compression

        // Use String Builder to make the code efficient

        String comp="aaaabbbccdeee";
        String newcomp="";
        for(int i=0; i<comp.length(); i++){
                Integer count=1;        //Integer class is used to convert this object to string { count.toString()}
                while(i<comp.length()-1 && comp.charAt(i)==comp.charAt(i+1)){
                        count+=1;
                        i++;  //very important to understand this, in this way we prevent another for loop iteration
                }
                newcomp+=comp.charAt(i); //so that if it repeats 0 time then no int to be mentioned (hint: look for d)
                if(count>1){
                        newcomp += count.toString();    //since Integer is class  so .toString() works

                }
                        
        }
        System.out.println(newcomp);

  
        sc.close();   
    }
    
}
