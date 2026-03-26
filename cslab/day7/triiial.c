// C Program to print Array 
// of Pointers
#include <stdio.h>


int main() {
    FILE *fptr;
    fptr=fopen("apple.txt","w+");
    if(fptr==NULL){
        return 1;
    }

    char buffer[255];

    

    fprintf(fptr, "apple pineapple");
    rewind(fptr);
    char ch;

    while((ch=fgetc(fptr))!=EOF){
        printf("%c", ch);
        
    }
    fclose(fptr);
   
    return 0;
}