
public class Selection_sort {
    public static void main(String args[]){
        int array[]= {1,4,2,3,10,9,7,8};
        for(int i=0; i<=(array.length-2); i++){
            int minimum = i;
            for(int j= i+1; j<=(array.length-1); j++){
                if(array[minimum]> array[j]){
                    minimum=j;
                }
            }
            int temp= array[minimum];
            array[minimum]=array[i];
            array[i]=temp;
        }
        for(int a: array){
            System.out.print(a+" ");
        }
    }
}
