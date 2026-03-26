import java.util.ArrayList;

public class Shortest_prefix {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean eow = false;
        int freq;

        TrieNode() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            freq = 1;
        }
    }

    public static TrieNode root = new TrieNode();

    public static void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            } else {
                curr.children[idx].freq += 1; // ✅ update child freq
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    public static String shortest_prefix(String word) {
        TrieNode curr = root;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            curr = curr.children[idx];
            sb.append(word.charAt(i));

            if (curr.freq == 1) { // ✅ check after moving
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] s = {"apple", "zac", "zebra", "dove"};

        for (String word : s) insert(word);

        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            arr.add(shortest_prefix(s[i]));
            System.out.print(arr.get(i) + " , ");
        }
    }
}
