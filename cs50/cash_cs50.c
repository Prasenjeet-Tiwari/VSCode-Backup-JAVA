#include <stdio.h>

int get_cents(void);
int calculate_quarters(int cents);
int calculate_dimes(int cents);
int calculate_nickels(int cents);
int calculate_pennies(int cents);

int main(void)
{
    // Ask how many cents the customer is owed
    int cents = get_cents();
    if (cents == 0)
    {
        int coins = 0;
        printf("%d\n", coins);
        return 0;
    }
    else
    {

        // Calculate the number of quarters to give the customer
        int quarters = calculate_quarters(cents);
        cents = cents - quarters * 25;

        // Calculate the number of dimes to give the customer
        int dimes = calculate_dimes(cents);
        cents = cents - dimes * 10;

        // Calculate the number of nickels to give the customer
        int nickels = calculate_nickels(cents);
        cents = cents - nickels * 5;

        // Calculate the number of pennies to give the customer
        int pennies = calculate_pennies(cents);

        // Sum coins
        int coins = quarters + dimes + nickels + pennies;
        printf("%d\n", coins);
    }
    return 0;
}

int get_cents(void)
{
    int amount;
    do
    {
        printf("Change owed: ");
        while (scanf("%d", &amount) != 1)
        {
            printf("Change owed: ");
            // Clear input buffer
            while (getchar() != '\n')
                ;
        }
    }
    while (amount < 0);
    return amount;
}

int calculate_quarters(int cents)
{
    int i = 0;
    while (cents >= 25)
    {
        cents = cents - 25;
        i += 1;
    }
    return i;
}

int calculate_dimes(int cents)
{
    int i = 0;
    while (cents >= 10)
    {
        cents = cents - 10;
        i += 1;
    }
    return i;
}

int calculate_nickels(int cents)
{
    int i = 0;
    while (cents >= 5)
    {
        cents = cents - 5;
        i += 1;
    }
    return i;
}

int calculate_pennies(int cents)
{
    int i = 0;
    while (cents > 0)
    {
        cents = cents - 1;
        i += 1;
    }
    return i;
}
