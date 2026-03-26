// ============================================================
// 📘 Trie Implementation with Edge Case Handling
// ============================================================

public class Basics_Tries {

    // ============================================================
    // 🧩 Node Class
    // ============================================================
    static class Node {
        Node[] children = new Node[26];  // for 'a' to 'z'
        boolean eow = false;              // End Of Word flag

        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    // Root node (shared by all operations)
    public static Node root = new Node();

    // ============================================================
    // 🧠 Insert a Word (Safe)
    // ============================================================
    public static void insert(String word) {
        if (word == null || word.isEmpty()) {
            System.out.println("⚠️ Skipped inserting empty or null word.");
            return;
        }

        word = word.toLowerCase().trim();
        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            // handle non-alphabet characters
            if (ch < 'a' || ch > 'z') {
                System.out.println("⚠️ Invalid character '" + ch + "' in word '" + word + "'. Skipping insertion.");
                return;
            }

            int idx = ch - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    // ============================================================
    // 🔍 Search a Word (Safe)
    // ============================================================
    public static boolean search(String word) {
        if (word == null || word.isEmpty()) return false;

        word = word.toLowerCase().trim();
        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch < 'a' || ch > 'z') return false;

            int idx = ch - 'a';
            if (curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return curr.eow;
    }

    // ============================================================
    // 🧭 Starts With (Prefix Search)
    // ============================================================
    public static boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) return false;

        prefix = prefix.toLowerCase().trim();
        Node curr = root;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (ch < 'a' || ch > 'z') return false;

            int idx = ch - 'a';
            if (curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return true;
    }

    // ============================================================
    // 🗑️ Delete a Word (Optional, Safe)
    // ============================================================
    public static boolean delete(String word) {
        if (word == null || word.isEmpty()) return false;
        return deleteHelper(root, word.toLowerCase().trim(), 0);
    }

    private static boolean deleteHelper(Node curr, String word, int i) {
        if (curr == null) return false;

        if (i == word.length()) {
            if (!curr.eow) return false; // word not found
            curr.eow = false;
            return isEmpty(curr);
        }

        char ch = word.charAt(i);
        if (ch < 'a' || ch > 'z') return false;
        int idx = ch - 'a';

        if (curr.children[idx] == null) return false;

        boolean shouldDelete = deleteHelper(curr.children[idx], word, i + 1);

        if (shouldDelete) {
            curr.children[idx] = null;
            return !curr.eow && isEmpty(curr);
        }
        return false;
    }

    private static boolean isEmpty(Node curr) {
        for (Node child : curr.children) {
            if (child != null) return false;
        }
        return true;
    }

    // ============================================================
    // 🚀 Main Function (Driver Code)
    // ============================================================
    public static void main(String[] args) {
        insert("Apple");
        insert("App");
        insert("bat");
        insert("ball");
        insert("");          // edge case: empty word
        insert("123");       // edge case: invalid chars
        insert(null);        // edge case: null

        System.out.println("\n🔍 Search Results:");
        System.out.println("Search 'apple': " + search("apple"));
        System.out.println("Search 'app': " + search("app"));
        System.out.println("Search 'bat': " + search("bat"));
        System.out.println("Search 'batman': " + search("batman"));

        System.out.println("\n🧭 Prefix Results:");
        System.out.println("StartsWith 'ap': " + startsWith("ap"));
        System.out.println("StartsWith 'ba': " + startsWith("ba"));
        System.out.println("StartsWith 'cat': " + startsWith("cat"));

        System.out.println("\n🗑️ Deletion Tests:");
        System.out.println("Delete 'bat': " + delete("bat"));
        System.out.println("Search 'bat' after deletion: " + search("bat"));
        System.out.println("Search 'ball': " + search("ball"));
    }
}
