import java.util.*;
public class Q_find_iterary {
    
    public static String revFunc(HashMap<String,String> hm){
        HashMap<String,String> revhm=new HashMap<>();
        for(String key: hm.keySet()){
            revhm.put(hm.get(key),key);
        } 

        for(String key: hm.keySet()){
            if(!revhm.containsKey(key)){
                return key;
            }
        }
        return null;
    }


    public static void main(String[] args) {

        HashMap<String,String> tickets = new HashMap<>();

        tickets.put("chennai","bengluru");
        tickets.put("Mumbai","delhi");
        tickets.put("goa","chennai");
        tickets.put("delhi","goa");

        HashSet <String> hs1=new HashSet<>();

        String start=revFunc(tickets);
        System.out.print(start+" -> ");
        while(true){
            String end=tickets.get(start);
            System.out.print(end+" -> ");
            
            if(tickets.containsKey(end)){
                start=end;
            }else{
                System.out.print("null");
                break;
            }
        }
    }
}
