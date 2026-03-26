// #include <Stdio.h>
// #include <math.h>
// //to calculate the sin(x) series expansion  = x - (x^3)/3! + (x^5)/5! - ......


// // Function to calculate the factorial of each term
// long long factorial(int n) {
//     long long fact = 1;
//     for(int i = 2; i <= n; i++) {
//         fact *= i;
//     }
//     return fact;
// }

// // Function to calculate Taylor series value for sin(x)
// double sin_series(double x, int n) {
//     double sum = 0;
//     double pow_x = x;
//     int count=0;
//     while(count<n){
//         sum += pow_x / factorial(2 * count + 1);
//         pow_x *= -x * x;  // Alternate between positive and negative terms
//         count++;
//     }
//     return sum;
// }

// // Main function
// int main() {
//     int n;
//     double x;

//     printf("Enter the number of terms (n): ");
//     scanf("%d", &n);
//     printf("Enter the value of x (in radians): ");
//     scanf("%lf", &x);

//     double output = sin_series(x, n);
//     printf("The approximate value of sin(%lf) is: %lf\n", x, output);

//     return 0;
// }