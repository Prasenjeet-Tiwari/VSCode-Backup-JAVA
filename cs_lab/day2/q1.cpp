//Bubble sort
#include<iostream>
using namespace std;
void bubble_sort(int arr[], int n){
    for (int i = 0; i <n ; i++){
        for(int j=0; j<n-i-1; j++){
            if(arr[j]>arr[j+1]){
                int temp= arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=temp;

            }
        }
    }
    cout<<"After:";
    for(int k=0; k<n; k++){
        cout<<arr[k]<<" ";
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
    bubble_sort(arr, n);
    
}