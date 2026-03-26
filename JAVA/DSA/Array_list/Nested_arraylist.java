import java.util.*;

public class Nested_arraylist {
    public static void main(String[] args){

        ArrayList<ArrayList<Integer>> mainlist= new ArrayList<>();

        ArrayList<Integer> list_1= new ArrayList<>();   //sub element arraylist creation
        ArrayList<Integer> list_2= new ArrayList<>();
        ArrayList<Integer> list_3= new ArrayList<>();

        for(int i=0; i<3; i++){
            list_1.add(i*1);    //0, 1, 2
            list_2.add(i*2);    //0, 2, 4
            list_3.add(i*3);    //0, 3, 6
        }

        mainlist.add(list_1);   //addding sub elements to main list
        mainlist.add(list_2);
        mainlist.add(list_3);

        System.out.println(mainlist);   //printing output
    }
}
