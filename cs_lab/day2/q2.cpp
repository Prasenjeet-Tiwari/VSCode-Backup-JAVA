//Selection sort
#include<iostream>
using namespace std;
void Selection_sort(int arr[], int n){
    for (int i = 0; i <n ; i++){
        int small=arr[i]; int idx=i;
        for(int j=i; j<n; j++){
            if(arr[j]<=small){
                small=arr[j];
                idx=j;
            }
        }

        int temp=arr[i];
        arr[i]=arr[idx];
        arr[idx]=temp;

        cout<<"After Iteration number "<< i+1<< " : ";
        for(int k=0; k<n; k++){
             cout<<arr[k]<<" ";
        }
        cout<<endl;
    }
}
    
    
   

int main(){
    int n;
    cout<<"Enter the length of the array: ";
    cin>>n;
    int arr[n];
    cout<<"Enter the element: "<<endl;
    for(int i=0; i<n; i++){
        cout<<"Element at "<<i<<" :";  //[]{}-)(Pp;.,/)
        cin>>arr[i];
    }
    cout<<"Before:";
    for(int i=0; i<n; i++){
        cout<<arr[i]<<" ";
    }
    cout<<endl;
    Selection_sort(arr, n);
    
}