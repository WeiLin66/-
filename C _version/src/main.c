#include "linkedlist.h"
#include "sort.h"
#include "stack.h"

int main(){
    
    CREATE_STACK(stack);
    for(int i=0; i<258; ++i){
        ENQUEUE(stack,i+1);
    }
    STACK_PRINTER(stack);
    for(int i=0; i<258; ++i){
        DEQUEUE(stack);
    }
    STACK_PRINTER(stack);
    FREE_STACK(stack);

    return 0;
}