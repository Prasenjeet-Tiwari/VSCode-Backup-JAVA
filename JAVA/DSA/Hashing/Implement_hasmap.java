import java.util.*;

public class Implement_hasmap {
    static class HashMap<K,V>{

        //Node Class
        private class Node{

            K key;
            V value;

            //Node Class constructor
            public Node(K key,V value){
                this.key=key;
                this.value=value;
            }   
        }

        //HashMap Data stored
        private int n;   
        private int N;  //n (size of main linked list)
        private LinkedList<Node> buckets[]; //N (Bucket Size)

        //HashMap constructor
        @SuppressWarnings("unchecked")
        public HashMap(){
            this.N=4;
            this.buckets=new LinkedList[4];
            for(int i=0; i<4; i++){
                buckets[i]=new LinkedList<>();
            }
        }

        //Hashing Function
        private int hashFunction(K key){
            int hc = key.hashCode();
            return Math.abs(hc)%N;  //( since main linked list has N elements)
        }

        //Seach in a linked list fucntion
        private int SearchInLL(K key, int bi){
            LinkedList<Node> ll= buckets[bi];

            for(int i=0; i<ll.size(); i++){
                if(ll.get(i).key==key){
                    return i;
                }
            }
            return -1;
        }
        
        //Rehash
        @SuppressWarnings("unchecked")
        private void rehash(){
            LinkedList<Node> oldBuckets[]= buckets;
            buckets = new LinkedList[N*2];
            N=N*2;
            for(int i=0; i< buckets.length; i++){
                LinkedList<Node> ll= oldBuckets[i];
                for(int j=0; j<ll.size(); j++){
                    Node node= ll.remove();
                    put(node.key, node.value);
                }
            }
        }

        
    //Insert/Update Method
        public void put(K key, V value){
            int bi= hashFunction(key);     //Buckets index
            int di= SearchInLL(key,bi);       //Data index 

            if(di!=-1){
                Node node= buckets[bi].get(di);
                node.value=value;
            }else{
                buckets[bi].add(new Node(key,value));
                n++;    //incrementing internal node linked list size
            }

            //checking for increasing Main Linked List
            double lambda= (double)n/N;
            if(lambda>2.0){
                rehash();
            }
        }

    //Contains key
        public boolean containKey (K key){
            int bi = hashFunction(key);   // bucket index
            int di = SearchInLL(key, bi);   //data index

            if(di == -1){   // key doesn't exist
               return false ; 
            }else{
                return true;
            }

        }

    //Remove key
        public V remove (K key){
            int bi = hashFunction(key);   // bucket index
            int di = SearchInLL(key, bi);   //data index

            if(di == -1){   // key doesn't exist
                return null;
            }else{
                Node data = buckets[bi].remove(di);
                n--;
                return data.value;
            }
        }
        
        public V get(K key){
            int bi = hashFunction(key);   // bucket index
            int di = SearchInLL(key, bi);   //data index

            if(di == -1){   // key doesn't exist
                return null; 
            }else{
                Node data = buckets[bi].get(di);
                return data.value;
            }
              
        }
        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();
            for(int i=0 ; i< buckets.length; i++){
                LinkedList<Node> ll = buckets[i];
                for(int j = 0; j<ll.size(); j++){
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty(){
            return n == 0 ;
        }
    }
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 130);
        map.put("US",30);  
        map.put("China",200); 

        ArrayList<String> keys = map.keySet();
        for(int i = 0; i<keys.size();i++){
            System.out.println(keys.get(i)+" : " + map.get(keys.get(i)));
        }        
    }   
}