import java.util.*;

/*Optimized one is after main code(Optimied one is from ChatGPT) */

public class _10_target_practise{
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);

        int t= sc.nextInt();

        int[][] arr=new int[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                arr[i][j]=0;
            }
            
        }

        for (int i = 0; i < 10; i++) {
            for(int j=0; j<10; j++){
                if((i==0 || j==0 || i==9 ||j==9)&&arr[i][j]==0){

                    arr[i][j]=arr[j][i]=1;
                }else if((i==1 || j==1 || i==8 ||j==8)&&arr[i][j]==0){
                    arr[i][j]=arr[j][i]=2;
                
                }else if((i==2 || j==2 || i==7 ||j==7)&&arr[i][j]==0){
                    arr[i][j]=arr[j][i]=3;
                
                }else if((i==3 || j==3 || i==6 ||j==6)&&arr[i][j]==0){
                    arr[i][j]=arr[j][i]=4;
                
                }else if((i==4 || j==4 || i==5 ||j==5)&&arr[i][j]==0){
                    arr[i][j]=arr[j][i]=5;
                }
            }
        }


        while(t-->0){
            int ans=0;
            for(int i=0; i<10; i++){
                String temp=sc.next();
                for(int j=0; j<10; j++){
                    
                    if(temp.charAt(j)=='X'){
                        ans+=arr[i][j];
                    }
                }
            
            }
            System.out.println(ans);
        }
    }
}

// import java.util.*;

// public class _10_target_practise {
//     public static void main(String[] args) {

//         Scanner sc = new Scanner(System.in);
//         int t = sc.nextInt();

//         int[][] arr = new int[10][10];

//         for (int i = 0; i < 10; i++) {
//             for (int j = 0; j < 10; j++) {
//                 arr[i][j] = Math.min(
//                         Math.min(i + 1, j + 1),
//                         Math.min(10 - i, 10 - j)
//                 );
//             }
//         }

//         while (t-- > 0) {
//             int ans = 0;

//             for (int i = 0; i < 10; i++) {
//                 String row = sc.next();
//                 for (int j = 0; j < 10; j++) {
//                     if (row.charAt(j) == 'X') {
//                         ans += arr[i][j];
//                     }
//                 }
//             }

//             System.out.println(ans);
//         }

//         sc.close();
//     }
// }
