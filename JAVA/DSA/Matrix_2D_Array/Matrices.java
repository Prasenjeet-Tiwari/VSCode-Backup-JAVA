package DSA.Matrix_2D_Array;
import java.util.*;

public class Matrices {

    public static String search(int matrix[][], int value_to_search){
        for(int i=0; i<matrix.length; i++){    
            for(int j=0; j<matrix[0].length; j++){
                if(value_to_search==matrix[i][j]){
                    return("Yes "+value_to_search+" is in the matrix.");
                }
            }
        }
        return("No "+value_to_search+" is not in the matrix.");
    }

    public static void main(String args[]){

//taking input of element from the user and printing it

        Scanner sc= new Scanner(System.in);
        int matrix[][]= new int[3][4];  //created a 2d array note: 2 boxes([][])

        int no_of_rows = matrix.length;     // gives the no of rows
        int no_of_colums = matrix[0].length;    //so calling the first row and retriving value of no. of column
        System.out.println("No_of_rows: "+no_of_rows);
        System.out.println("No_of_colums: "+no_of_colums);

        System.out.print("Enter the value of all the elemnt at once (12 values) (WITH SPACES):" );
        for(int i=0; i<no_of_rows; i++){    //taking matrix input
            for(int j=0; j<no_of_colums; j++){
                matrix[i][j]= sc.nextInt();
            }
        }

        for(int i=0; i<no_of_rows; i++){    //output 
            for(int j=0; j<no_of_colums; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

// search a number

        System.out.print("Enter the value of element you wish to search in the matrix :" );
        int data= sc.nextInt();
        String output= search(matrix, data);
        System.out.print(output );
        
        
        sc.close();
    }   
}
