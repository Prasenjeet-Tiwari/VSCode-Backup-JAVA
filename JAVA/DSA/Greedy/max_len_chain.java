import java.util.*;
public class max_len_chain {
    public static void main(String[] args) {

        //input starting and ending length
        int input[][]={{5,28},{39,60},{27,40},{5,24},{50,90}};

        //sorting on the basis of endlength
        Arrays.sort(input , Comparator.comparingDouble(o->o[1]));

        int length=0;
        int count=0;

        //taking first entry into account
        int start=input[0][0];
        int end=input[0][1];
        length += Math.abs(start - end);    //length increment
        count++;    //count increment

        for(int i=1; i<input.length; i++){
            start= input[i][0];
            if(start>=end){
                count++;
                end=input[i][1];
                length += Math.abs(start -end);
            }
        }
        System.err.println("Number of chains used: "+count);
        System.err.println("Maximum length of chain used: "+length);
        
    }
}
