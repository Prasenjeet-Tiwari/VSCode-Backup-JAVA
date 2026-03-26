public class word_break {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean eow = false;

        TrieNode() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
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
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    public static boolean search(String word) {
        if (word == null || word.isEmpty()) return false;

        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch < 'a' || ch > 'z') return false;
            int idx = ch - 'a';
            if (curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return curr.eow;
    }

    public static boolean checkWord(String word) {
        if (word.length() == 0) return true;

        for (int i = 1; i <= word.length(); i++) {  
            if (search(word.substring(0, i)) && checkWord(word.substring(i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] s = {"i", "sam", "samsung", "love"};
        String input = "ilovesamsung";
        for (String word : s) insert(word);

        boolean ans = checkWord(input);
        System.out.println(ans); 
    }
}
