//package DSA.Greedy;
import java.util.*;

public class Activity_selection {

    public static void main(String[] args) {

    //Sorted by end time

        Scanner sc=new Scanner(System.in);
        int start[]={1,3,0,5,8,5};
        int end[]={2,4,6,7,9,9};    //endtime is sorted

        int maxAct=0;

        ArrayList<Integer> ans = new ArrayList<>();

        //initializing first work (first work will always happen cause its end time comes first in te sorted endtime)
        ans.add(0);
        int LastEnd=end[0];

        maxAct+=1;

        for(int i=1; i<end.length; i++){
            if(start[i]>=LastEnd){
                maxAct++;
                ans.add(i);
                LastEnd = end[i];
                
            }
        }

        for(int i : ans){
            System.out.print("A"+i+" ");
        }

        System.out.println();

/*----------------------------------------------------------------------------------------------------------*/

    //Unsorted matrix activity selection

        int[] start_2={5, 1, 3, 0, 8, 5};
        int[] end_2={9, 4, 5, 6, 9, 7};

        //placing index, start end in (no of data)x3 matrix
        int Activity_arr[][]= new int [start_2.length][3];

        for(int i=0; i<(Activity_arr.length); i++){
            Activity_arr[i][0]= i;
            Activity_arr[i][1]= start_2[i];
            Activity_arr[i][2]= end_2[i];
        }

        //sorting via lambda function
        Arrays.sort(Activity_arr,Comparator.comparingDouble(o->o[2]));

        ArrayList<Integer> ans_2= new ArrayList<>();

        ans_2.add(Activity_arr[0][0]);
        int maxAct_2=1;
        int LastEnd_2=Activity_arr[0][2];

        for(int j=1; j<Activity_arr.length; j++){
            if( Activity_arr[j][1] >= LastEnd_2){
                maxAct_2 ++;
                LastEnd_2= Activity_arr[j][2];
                ans_2.add(Activity_arr[j][0]);
            }
        }

        for(int q : ans_2){
            System.out.print("A"+q+" ");
        }

        sc.close();
    }
    
}
