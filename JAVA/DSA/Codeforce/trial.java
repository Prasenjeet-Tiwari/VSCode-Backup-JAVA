
import java.util.*;
public class trial{
    static void func(long arr[],long check,long cad, long vad,long count,ArrayList<Long> arrlist){
        if(arr[0]==cad || arr[1]==vad){
            return;
        }
        if(arr[0]==arr[1]){
            if(cad>vad){
                vgreatc(arr,count,arrlist);
            }else{
                cgreatv(arr,count,arrlist);
            }
        }
        if(arr[0]> check){
            cgreatv(arr,count,arrlist);
        }else{
            vgreatc(arr,count,arrlist);
        }
        func(arr, check, cad, vad,count,arrlist);

    }
    static void cgreatv(long arr[],long count,ArrayList<Long> arrlist){
        long temp=arr[0]/2;
        arr[0]=temp;
        arr[1]+=temp;
        count++;
        arrlist.add((long)1);

    }
    static void vgreatc(long arr[], long count,ArrayList<Long> arrlist){
        long temp=arr[1]/2;
        arr[1]=temp;
        arr[0]+=temp;
        count++;
        arrlist.add((long)2);
    }
   
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        long t=sc.nextLong();
        while(t-->0){
            long count=0;
            long k=sc.nextLong();
            long cad=sc.nextLong();
            long twotopowerk=1L<<k;
            
            long half=twotopowerk;
            long vad=(half<<k)-cad;

            long check=Math.abs(cad-vad);
            ArrayList<Long> arrlist=new ArrayList<>();

            long[] arr=new long[2];
            arr[0]=half; arr[1]=half;
            func(arr,check,cad,vad,count,arrlist);
            System.out.println(count);
            for(Long i :arrlist){
                System.out.print(i);
            }
        }
    }
}
