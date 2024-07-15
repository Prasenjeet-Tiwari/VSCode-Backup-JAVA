package DSA.Basic_Sorting_Algorithm;
public class Insertionsort {
    public static void main(String[] args){
        int array_1[]={4,2,5,6,3,1};
        for(int i=0; i<=(array_1.length-1); i++){
            int a= array_1[i];
            int j = i-1;
            while(j>=0 && array_1[j]>a){
                array_1[j+1]=array_1[j];
                j--;
            array_1[j+1]=a;
            }
        }
        for(int b: array_1){
            System.out.print(b);
        }
        System.out.println("\nIt took time to undersatnd but you did it bro, proud of you :)");
    }
}
