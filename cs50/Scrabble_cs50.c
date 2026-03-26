#include <ctype.h>
#include <stdio.h>
#include <string.h>

// Points assigned to each letter of the alphabet
int POINTS[] = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

int compute_score(const char *word);

int main(void)
{
    // Get input words from both players
    char word1[100], word2[100];
    printf("Player 1: ");
    fgets(word1, sizeof(word1), stdin);
    printf("Player 2: ");
    fgets(word2, sizeof(word2), stdin);

    // Score both words
    int score1 = compute_score(word1);
    int score2 = compute_score(word2);

    // Print the winner
    if (score1 > score2)
    {
        printf("Player 1 wins!\n");
    }
    else if (score1 == score2)
    {
        printf("Tie!\n");
    }
    else
    {
        printf("Player 2 wins!\n");
    }

    return 0;
}

int compute_score(const char *word)
{
    int p = 0;

    for (int i = 0; word[i] != '\0'; i++)
    {
        if (isalpha(word[i]))
        {
            char c = tolower(word[i]);    // Convert to lowercase
            p += POINTS[(int)(c - 'a')];  // Calculate score based on letter
        }
    }

    return p;
}
