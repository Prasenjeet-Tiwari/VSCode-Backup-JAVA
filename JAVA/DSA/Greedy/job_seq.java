/*Given an array of jobs where every job has a deadline and profit if the job is finished before the deadline. 
It is also given that every job takes a single unit of time, so the minimum possible deadline for any job is 1.
 Maximize the total profit if only one job can be scheduled at a time */

import java.util.*;
public class job_seq {
//Job class
    static class Job{

        int id;
        int profit;
        int deadline;
        
        Job(int i, int d, int p){
            this.id=i;
            this.deadline=d;
            this.profit=p;
        }
    }
 //Main
    public static void main(String[] args) {

        int jobsInfo[][]={{4,20},{1,10},{1,40},{1,30}};

        ArrayList<Job> data=new ArrayList<>();  // *** An arraylist of job class ***

        for(int i=0; i<jobsInfo.length; i++){
            data.add(new Job(i, jobsInfo[i][0], jobsInfo[i][1] )); //very imp---an object added as new arraylist
        }
        Collections.sort(data, (obj1,obj2) -> obj2.profit - obj1.profit); //descending sort
        //if wanna do asc just write "obj1.profit - obj2.profit"

        ArrayList<Integer> seq= new ArrayList<>();
        int time=0;

        for(int j=0; j<data.size(); j++){
            Job curr= data.get(j);
            if(curr.deadline>time){
                seq.add(curr.id);
                time+=curr.deadline;
            }
        }

        System.out.println("max job="+seq.size());
        for(int i=0; i<seq.size(); i++){
            System.out.print(seq.get(i)+" ");
        }
    }   
}
