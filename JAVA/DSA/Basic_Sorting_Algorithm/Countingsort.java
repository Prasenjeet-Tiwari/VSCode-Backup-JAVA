//package DSA.Basic_Sorting_Algorithm;

public class Countingsort {

// try using it when the array is big the maximum value of the array is not a greater number

    public static void main(String[] args){
        int array[]={1,3,4,2,3,4,1,2,3,4,2,1,3,2,3,2,2,9,100,45,55};
        int maximum=Integer.MIN_VALUE;

        for(int k=0; k<array.length; k++){  //finding the max value from the original array
            if(array[k]>maximum){
                maximum=array[k];
            }
        }

        int array_counter[]= new int[maximum+1];  //length= ((max value of original list) + 1)  since index starts from 0

        for(int i=0; i<array.length; i++){
            int temp= array[i]; //taking the value from first array
            array_counter[temp]+=1; // increasing the index value of that number by one in the second array where index is the elemantal value
        }

        for(int j=0; j<array_counter.length; j++){  //printing output
            if(array_counter[j]!=0){
                System.out.println("Occurance of "+j+" : "+array_counter[j]);
            }
        }
    }
}
    

