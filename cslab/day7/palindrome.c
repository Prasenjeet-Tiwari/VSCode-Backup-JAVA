#include <stdio.h>
#include <string.h>

// Recursive function to check palindrome
int isPalindrome(char str[], int start, int end) {
// Base case: if start >= end, string is palindrome
    if (start >= end)
    return 1;
    
    // If characters at start and end don't match, not palindrome
    if (str[start] != str[end])
    return 0;
    
    // Recursive call, moving towards center
    return isPalindrome(str, start + 1, end- 1);
}
int main() {
    char str[100];
    printf("Enter a string: ");
    scanf("%s", str);

    int len = strlen(str);
    int result = isPalindrome(str, 0, len-1);
    if (result)
        printf("%s is a palindrome.\n", str);
    else{
        printf("%s is not a palindrome.\n", str);
    }

    return 0;
}