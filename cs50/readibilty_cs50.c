#include <stdio.h>
#include <ctype.h>
#include <string.h>

// Function prototypes
int getting_words(const char *c);
int Coleman_Liau_index_index(float w, float s);
void output_analysis(int a);

int main(void) 
{
    char text[1000];
    printf("Text: ");
    fgets(text, sizeof(text), stdin);
        
    int a = getting_words(text);
    output_analysis(a);
    
    // refer to functions
    
    return 0;
}

int getting_words(const char *c)
{
    int words = 0, sentences = 0, letters = 0;
    
    for (int q = 0; q < strlen(c); q++)
    {
        if (isalpha(c[q]) || isdigit(c[q]))
        {
            letters += 1;
            // Count letters
            // Continue reading the next character
        }
        else if (c[q] == ' ' || c[q] == '?' || c[q] == '!' || c[q] == '.')
        {
            if (c[q] == ' ')
            {
                words += 1;
            }
            else
            {
                sentences += 1;
            }
        }
        else
        {
            continue;
        }
    }

    float actual_words = (float)words + 1;  // Convert words to float
    float reqd_w, reqd_s;

    reqd_w = (float)(letters / actual_words) * 100.0;
    reqd_s = (float)(sentences / actual_words) * 100.0;

    // printf("Words: %.2f, Sentences: %d, Letters: %d\n", actual_words, sentences, letters);
    // printf("Required letters per 100 words: %.2f, Required letters per 100 sentences: %.2f\n", reqd_w, reqd_s);
    
    int ans = Coleman_Liau_index_index(reqd_w, reqd_s);
    return ans;
}

int Coleman_Liau_index_index(float w, float s)
{
    // Coleman-Liau index index = 0.0588 * L - 0.296 * S - 15.8
    float calculation; 
    calculation = 0.0588 * w - 0.296 * s - 15.8;
    int grade = (int)(calculation);

    return grade;
}

void output_analysis(int a)
{
    if (a > 16)
    {
        printf("Grade 16+");
    }
    else if (a < 1)
    {
        printf("Before Grade 1");
    }
    else
    {
        printf("Grade %d", a);
    }
}
