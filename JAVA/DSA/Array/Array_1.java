package DSA.Array;
import java.util.Scanner;

public class Array_1 
{
    public static int searchit(int number[], int key) 
    {
        for (int i = 0; i < number.length; i++) 
        {
            if (number[i] == key) 
            {
                return 1;
            }
        }
        return 2;
    }

    public static void binary_search(int arraylist[], int number_to_search)
    {
        

    }
    
// MAIN
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of students whose data are to be calculated: ");
        int i = sc.nextInt();
        int data[] = new int[i];
        for (int j = 0; j < i; j++) 
        {
            System.out.print("Enter the roll number of student number" + (j + 1) + " : ");
            int roll_no = sc.nextInt();
            data[j] = roll_no;
        }
        for (int k : data) 
        {
            System.out.println(k);
        } 
        System.out.println("\n");

// LINEAR SEARCH 
    // NOTHING MUCH JUST SEARCHING A PARTICULAR VALUE IN POOL OF DATA
    //Time complexity = O(n) where n is number of oops runned

        System.out.print("ENTER THE DIGIT YOU WISH TO SEARCH IN THE LIST: ");
        int input_1 = sc.nextInt();
        int list_of_values[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int output = searchit(list_of_values, input_1);
        if (output == 1) 
        {
            System.out.println("Yes, the number is there");
        } 
        else 
        {
            System.out.println("Sorry, the number is not there");
        }
        System.out.println("\n");

// FINDING THE GREATEST AND SMALLEST NUMBBER IN AN ARRAY
        // first way
        int largest_no=Integer.MIN_VALUE; int smallest_no=Integer.MAX_VALUE;
        for(int j=0; j<list_of_values.length; j++ )
        {
            if(list_of_values[j]>largest_no)
            {
                largest_no=list_of_values[j];
            }
            if(list_of_values[j]<smallest_no)
            {
                smallest_no=list_of_values[j];
            }  
        }
        System.out.println("The greatest and smallest value in the list are as follows: "+largest_no+" , "+smallest_no);
        System.out.println("\n");
        
// BINARY SEARCH 
        


        int array_1[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
        System.out.print("Please input the number to be searched in BINARY SEARCH:");
        int to_find= sc.nextInt();
        System.out.println("\n");
        
    //can define the rest inside a function

        int e= array_1.length;
        int start = array_1[0]; int end = array_1[e-1];
        int steps=0;
        while(start<=end)
        {
            int middle=(start+end)/2;
            if(middle < to_find)
            {
                start=middle+1; // so to_find on right side
            }
            else
            {
                end=middle-1;   // so to_find on left side
            }
            steps+=1;
        }
        System.out.println("No of times the while loop runs: "+steps);
        System.out.println("\n");

// swapping value of an array without using extra space
        
        int array_2[]={1,2,3,4,5,6,7,8,9,0,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
        int length_array_2 = (array_2.length)-1;
        for( int a=0; array_2[a] < array_2[length_array_2-a]; a++)
        {
            int temp = array_2[a];
            array_2[a] = array_2[length_array_2-a];
            array_2[length_array_2-a] = temp;
            
        }
        System.out.println("now reversing:");
        for(int b : array_2)
        {
            System.out.print(b+" "+",");

        }
        System.out.println("\n");


// pairing

        int array_3 []= {2,4,6,8};
        int end_1= array_3.length;
        int pairs=0;
        for(int p=0; p<end_1; p++)
        {
            for(int q = p+1; q<end_1; q++)
            {
                System.out.print("( "+array_3[p]+","+array_3[q]+" ), ");
                pairs+=1;
            }
            System.out.println();
        }
        System.out.println("the number of pairs: "+pairs);
        System.out.println("\n");

// Subarray and also calculating Max value of a subarray: (time complexity O(n^3))

        int array_4[]={2,4,6,8,10};
        int max=0;
        int end_2=array_4.length;

        for(int u=0; u<end_2; u++)  // triple "for" is used so that all element within the two end points can beincluded
        {
            for(int v=u+1; v<end_2; v++)    // v always has indentation one unit more than "u" so that we can make a upper and lower bound
            {
                int current_value=0;
                for(int w=u; w<=v; w++)     // initiallizing "w" as "u" to start subway of "u", and going till "v" where we vary "v" to obtain subways of 2
                {
                    System.out.print(array_4[w]+(" "));
                    current_value+=array_4[w];      //each elemt of subarray sum
                }
                System.out.print("  ,  ");      // adding comma after each subway of a "u"
                if(current_value>max)       //comparision of sum
                {
                    max=current_value;
                }
            }
            System.out.println();       // so that all of the subway of "u+1" is printed on next line
        }
        System.out.print("The maximum value of the subarray is : "+max);
        System.out.println("\n");

// Subway sum : (with time complexity O(n^2))

        int array_5[]={-2,4,-6,8,-1,2-10};
        int iteration_max=0;
        int end_3=array_4.length;

        for(int u=0; u<end_3; u++)  // triple "for" is used so that all element within the two end points can beincluded
        {
            int maximum=array_5[u];
            for(int v=u+1; v<end_2; v++)    // v always has indentation one unit more than "u" so that we can make a upper and lower bound
            {
                maximum += array_5[v];
                if(maximum > iteration_max)
                {
                    iteration_max=maximum;
                }
            }
        }
        System.out.print("The maximum value of the subarray is : "+iteration_max);
        System.out.println("\n");

// Subarray sum using kedane's rule

        int array_6[]={-2,-3,4,-1,-2,1,5,-3};
        int curr_sum=0;
        int max_sum = Integer.MIN_VALUE;
        for(int a: array_6)
        {
            curr_sum += a;
            if(curr_sum<0)
            {
                curr_sum=0;
            }
            max_sum=Math.max(max_sum,curr_sum);
            
        }
        System.out.println("the greatest sum using KEDANE'S RULE IS:  "+max_sum);
        System.out.println("\n");

// BEST SELLING BUYING STOCK CASE
        int[] prices = {7, 1, 5, 3, 6, 4};
        int maxProfit = 0, minPrice = Integer.MAX_VALUE;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        System.out.println(maxProfit);

        sc.close();
    }
}   
