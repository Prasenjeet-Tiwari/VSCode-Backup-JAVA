public class Remove_duplicate {
    
    
    public static void duplicate(String str, int idx, StringBuilder newstr, boolean[] map) {
        if (idx == str.length()) {
            System.out.println(newstr.toString());
            return;
        }
        
        char curCharacter = str.charAt(idx);
        if (map[curCharacter - 'a']==true) {
            duplicate(str, idx + 1, newstr, map);
        } else {
            map[curCharacter - 'a'] = true;
            newstr.append(curCharacter);
            duplicate(str, idx + 1, newstr, map);
        }
    }

    public static void main(String[] args) {
        System.out.print("The corrected version : ");
        duplicate("abccaddeeadf", 0, new StringBuilder(""), new boolean[26]);
    }
}