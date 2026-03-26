import java.util.*;

public class Q_anagram_N_byThree {

    public static boolean anagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        HashMap<Character, Integer> hm1 = new HashMap<>();

        // Count chars in s1
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            hm1.put(c, hm1.getOrDefault(c, 0) + 1);
        }

        // Subtract counts with s2
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if (!hm1.containsKey(c)) return false;
            hm1.put(c, hm1.get(c) - 1);
            if (hm1.get(c) == 0) hm1.remove(c);
        }

        return hm1.isEmpty();
    }

    public static void main(String[] args) {
        // Majority element (> n/3 times)
        int[] arr = {1, 2, 3, 1, 2, 3, 4, 5, 6, 7};
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int num : arr) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        System.out.println("Numbers appearing more than n/3 times:");
        for (Integer key : hm.keySet()) {
            if (hm.get(key) > arr.length / 3) {
                System.out.print(key + " ");
            }
        }

        // Anagram check
        System.out.println("\n\nAnagram check:");
        String s1 = "race";
        String s2 = "care";
        boolean output = anagram(s1, s2);
        System.out.println(s1 + " and " + s2 + " → " + output);
    }
}
