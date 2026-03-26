public class pattern_part2 
{
    public static void main(String args[])
    {
// hollow square 

        for(int i = 1; i <= 4; i++) 
        {
            for(int j = 1; j <= 4; j++) 
            {
                if(i == 1 || i == 4 || j == 1 || j == 4)
                {
                    System.out.print("*");
                } 
                else 
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    
// half piramid

        for(int k=1; k<=4; k++)
        {
            for(int l=1;l<=4-k; l++)
            {
                System.out.print(" ");
            }
            for(int m=1;m<=k;m++)
            {
                System.out.print("*");
            }
        System.out.println();
        }

// inverted number pyramid

        for(int n=0; n<=5; n++)
        {
            for(int o=1; o<=5-n; o++)
            {
                System.out.print(o);
            }
            System.out.println();
        }

// Floyd's triangle pattern(number increment)

        int r=0;
        for(int p=1; p<=5; p++)
        {
            for(int q=1; q<=p; q++)
            {
                r=r+1;
                System.out.print(r+" ");
            }
            System.out.println();
        }

// 0-1 pattern (alternate output)

        for (int s=1; s<=5; s++)
        {
            for(int t=1; t<=s;t++)
            {
                if((t+s)%2==0)
                {
                    System.out.print("1");
                }
                else
                {
                    System.out.print("0");
                }
            }
            System.out.println();
        }

// Butterfly pattern

        //1st half 

        for(int u=1;u<=4;u++)
        {
            for(int v1=1; v1<=u;v1++)
            {
                System.out.print("*");
            }
            for(int v2=1;v2<=(8-2*(u));v2++)
            {
                System.out.print(" ");
            }
            for(int v3=1; v3<=u; v3++)
            {
                System.out.print("*");
            }
            System.out.println();
        }

        //2ndhalf

        for(int u=4;u>=1;u--)
        {
            for(int v1=1; v1<=u;v1++)
            {
                System.out.print("*");
            }
            for(int v2=1;v2<=(8-2*(u));v2++)
            {
                System.out.print(" ");
            }
            for(int v3=1; v3<=u; v3++)
            {
                System.out.print("*");
            }
            System.out.println();
        }

//slant forward

        for(int w=1;w<=5;w++)   // change w=5 w>=1 w-- to get slant backward
        {
            for(int u=1; u<=5-w;u++)
            {
                System.out.print(" ");
            }
            for(int v=1; v<=5; v++)
            {
                System.out.print("*");
            }
            System.out.println();
        }

// HOLLOW SLANTLY SQUARE WITH GAPS

        for(int k=1; k<=5; k++)
        {
            for(int j=1;j<=5-k;j++) //using the concept of SLANT FORWARD for spacing
            {
                System.out.print(" ");
                
            }
            for(int m=1; m<=5; m++) //using the hollow square concept for star
            {
                if(m == 1 || m == 5 || k == 1||k == 5)
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

// Diamond pyramid 

        //top half

        for(int p=1; p<=4; p++)
        {
            for(int q=1;q<=4-p;q++)
            {
                System.out.print(" ");
            }
            for(int a=1;a<=p;a++)
            {
                System.out.print("*");
            }
            for(int s=1; s<p;s++)
            {
                System.out.print("*");
            }
            System.out.println();
        }

        //bottom half

        for(int p=4; p>=1; p--)
        {
            for(int q=1;q<=4-p;q++)
            {
                System.out.print(" ");
            }
            for(int a=1;a<=p;a++)
            {
                System.out.print("*");
            }
            for(int s=1; s<p;s++)
            {
                System.out.print("*");
            }
            System.out.println();
        }

// Number pyramid of repitation of same output in each row
        for(int t=1;t<=5;t++)
        {
            for(int s=1;s<=5-t; s++)
            {
                System.out.print(" ");
            }
            for(int u=1; u<=t;u++)
            {
                System.out.print(t+" ");
            }
            System.out.println();
        }

// PALINDROMIC Pattern with Numbers pattern
            
        for (int a = 1; a <= 5; a++) 
        {
            int first_outputer = a; // Initialize first_outputer inside the loop
            int second_outputer = 2; // already got 1,so the first out of this "for" loop should be 2(which will be printed on 2nd column) so starting with 2 then increasing p

            for (int b = 1; b <= 5 - a; b++) //spacing
            {
                System.out.print(" ");
            }
            for (int c = 1; c <= a; c++) //output concerned with left of 1
            {
                System.out.print(first_outputer);
                first_outputer = first_outputer - 1;
            }
            for (int c = 1; c < a; c++) // output concerned with right of 1
            {
                System.out.print(second_outputer);
                second_outputer = second_outputer + 1;
            }
            System.out.println();
        }


            //or version of PALINDROMIC Pattern

            //     for(int i = 1; i <= 5; i++) {
            //         for(int j = 1; j <= 5-i; j++) {
            //             System.out.print(" ");
            //         }                                         /* IMP */
            //         for(int j = i; j >=1; j--) {
            //             System.out.print(j);
            //         }
            //         for(int j = 2; j <= i; j++) {
            //             System.out.print(j);
            //         }
            //         System.out.println();
            //     }
            
            
    }
}
    

