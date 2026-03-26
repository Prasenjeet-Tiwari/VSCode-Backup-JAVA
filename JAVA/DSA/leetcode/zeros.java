
public class zeros{
    public static int longestOnes(int[] nums, int k) {
        int count=0,max=0,j=0;
        int n=nums.length;
        for(int i=0; i<n; i++){
            if(nums[i]==1){
                count=i-j+1;
            }else if(nums[i]==0 && k!=0){
                k--;
                count=i-j+1;
            }else if(nums[i]==0 && k==0){
                while(nums[j]!=0&& j<n){
                    j++;
                }
                if(j!=n){
                    j++;
                }
                count=i-j+1;
            }
            max=Math.max(count,max);
        }
        return max;
    }

    public static void main(String[] args){
        int arr[]= {1,0,1,0,1,0,1,0,1};
        int k=2;
        int output=longestOnes(arr,k);
        System.out.println(output);
    }
}


func(int arr[], int j, int k, int p){
    if(k==0){
            return 0;
    }
    int inc=func(arr,i++,j,k--,0);
    int incj=func(arr,i,j--,k--,1);
    return Math.max(inc,incj);
}