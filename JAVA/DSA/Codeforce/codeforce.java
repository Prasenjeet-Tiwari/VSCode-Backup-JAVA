import java.util.*;
public class codeforce{
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int size= sc.nextInt();
        for(int k=0; k<size; k++){

            int len= sc.nextInt();
            int[] arr1= new int[len];
            int[] arr2= new int[len];

            for(int i=0; i<len; i++) arr1[i]=sc.nextInt();
            for(int j=0; j<len; j++) arr2[j]=sc.nextInt();
            int count=1;

            while (true) { 
                int idx=-1;
                for (int p=0; p<len; p++){
                    if(arr1[p]>arr2[p]){
                        idx=p;
                        arr1[idx]--;
                        break;
                    }
                }
                
                if(idx==-1) break;

                for(int j=0; j<len; j++){
                    if(arr1[j]<arr2[j]) arr1[j]++;
                    break;
                }

                count++;
            }
            
            System.out.println(count);
        }


        sc.close();
    }
}