#include <stdio.h>
int main(void)
{
    int initial_population; /// Taking input from the user of inital population
    do                      /// While loop to check minimum value is >9
    {
        printf("Start Size (>9): ");
        scanf("%d", &initial_population);
    }
    while (initial_population < 9);

    int ending_population; /// Taking input from the user of final population
    printf("End size (>initial pupulation): ");
    scanf("%d", &ending_population);
    while (initial_population > ending_population)
    {
        printf("End size (>initial pupulation): ");
        scanf("%d", &ending_population);
    }
    int years = 0; /// trying to calculate the years as an output from here on

    while (initial_population <= ending_population)
    {
        if (initial_population < ending_population)
        {
            initial_population = initial_population + (initial_population / 3) - (initial_population / 4);
            years++;
        }
        else
        {
            break;
        }
    }
    printf("Years: %d\n", years);
}
