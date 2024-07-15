//package DSA.Strings;

import java.util.Scanner;
import java.util.StringTokenizer;

public class experiment{
    public static void main(String[] args) {
        String a = "abcdefghi";
        int length = a.length();

        // Define a set of vowels
        String vowels = "aeiou";

        for (int i = 0; i < length; i++) {
            // Check if the character is not in the set of vowels
            if (vowels.indexOf(a.charAt(i)) == -1) {
                System.out.print(a.charAt(i));
            }
        }

        // Define the delimiters as specified in the constraints
        String delimiters = " !,?._'@";
        String s="She sells Sea Shells at The Sea Shore";

        // Use StringTokenizer to split the input string into tokens
        StringTokenizer tokenizer = new StringTokenizer(s, delimiters);

        // Print the number of tokens
        System.out.println(tokenizer.countTokens());

        // Print each token on a new line
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }
}
/*import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        // Define a regular expression pattern
        String regexPattern = "\\d+"; // This example pattern matches one or more digits

        // Compile the pattern
        Pattern pattern = Pattern.compile(regexPattern);

        // Create a Matcher object for a given input string
        String inputString = "123abc456def";
        Matcher matcher = pattern.matcher(inputString);

        // Find matches in the input string
        while (matcher.find()) {
            // Access the matched substring
            String match = matcher.group();
            System.out.println("Match: " + match);
        }
    }
}*/
