#include <stdio.h>
#include <string.h>

int main(){
    char initial[100];
    printf("please enter the string: ");
    fgets(initial, sizeof(initial), stdin);
    printf("the size : %d \n",strlen(initial));
    for (int i=0; i<strlen(initial);i++){
        if((initial[i]<'a'|| initial[i]>'z')&& (initial[i]<'A'|| initial[i]>'Z')){
            for( int j =i; j< strlen(initial); j++){
                initial[j]=initial[j+1];
            }
        }
    }

    printf("the updated %s \n", initial);
    printf("the size : %d \n",strlen(initial));



    /*fgets(initial, sizeof(initial), stdin);
    printf("the size : %d \n",strlen(initial));
    int i= strlen(initial)-4;
    printf("value of i: %d \n", i);
    func(initial, i);
    */
    
    return 0;
}