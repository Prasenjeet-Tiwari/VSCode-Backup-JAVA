import java.util.*;

public class Main{



    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);

        int t= sc.nextInt();
        while(t>0){
            
            int size= sc.nextInt();
            
            HashMap<Long, Map.Entry<Long>> hm= new HashMap<>();
            for(int i=0; i<size; i++){
                long key=sc.nextLong();
                hm.put(key, hm.getOrDefault(key, 0L)+1);
            }
            if(hm.size()>2){
                System.out.println("NO");
            }else if(hm.get(0).getValue()==hm.get(1)
        }
        return;
    }
}