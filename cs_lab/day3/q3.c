#include<stdio.h>

int main(){

    int m,n;
    printf("Enter the length of the first array: ");
    scanf("%d",&m);
    
    printf("Enter the length of the second array: ");
    scanf("%d",&n);

    int arr1[m];     int arr2[n];

    printf("Enter the elemnts: \n");
    for(int i=0; i<m; i++){
        printf("Element at index %d for arr1 : ",i );
        scanf("%d",&arr1[i]);
    }
    for(int p=0; p<n; p++){
        printf("Element at index %d for arr2 : ",p );
        scanf("%d",&arr2[p]);
    }
    int len=n+m;
    int arr3[len];
    int a=0, b=0, k=0;

    while(a<m && b<n){
        if(arr1[a]<=arr2[b]){
            arr3[k++]=arr1[a++];
        }else{
            arr3[k++]=arr2[b++];
        }
    }
    while(a<m){
        arr3[k++]=arr1[a++];
    }
    while(b<n){
        arr3[k++]=arr2[b++];
    }

    printf("The updated merged sorted array: ");
    for(int j=0; j<len; j++){
        printf("%d ",arr3[j]);
    }

    return 0;
}