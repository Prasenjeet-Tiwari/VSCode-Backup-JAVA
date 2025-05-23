package DSA.Matrix_2D_Array;
import java.util.*;

public class Search_in_Sorted {
    public static void search(int matrix[][], int value){
        int rows=0;
        int columns=matrix[0].length-1;
        while(rows<matrix.length && columns>=0){
            if(matrix[rows][columns]==value){
                System.out.print("Found "+value+" at ("+rows+","+columns+")");
                return;
            }
            else if(matrix[rows][columns]>value){
                columns--;
            }
            else{
                rows++;
            }
        }
        System.out.println("not found.");
        

    }
    public static void main (String[] args){

        Scanner sc= new Scanner(System.in);

        //The given matrix is sorted in both columns and rows
        int matrix[][]= { {10,20,30,40},
                          {15,25,35,45},
                          {27,29,37,48},
                          {32,33,39,50}};
        System.out.print("Enter the value to search through Staircase Search(brutforce): ");
        int value=sc.nextInt();
        search(matrix,value);
        sc.close();
        
    }
    
}
