#include <stdio.h>
#include <string.h>
void func(char initial[], int i){
    if(initial[i]=='i' && initial[i+1]=='n' && initial[i+2]=='g'){
        printf("yes");
    }else{
        printf("no");
    }
}

int main(){
    char initial[100];
    printf("please enter the string: ");
    fgets(initial, sizeof(initial), stdin);
    printf("the size : %d \n",strlen(initial));
    int i= strlen(initial)-4;
    printf("value of i: %d \n", i);
    func(initial, i);
    
    
    return 0;
}

