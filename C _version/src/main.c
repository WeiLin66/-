#include "priorityqueue.h"

int main(){

    CREATE_PQ(priorityQueue);
    for(int i=0; i<100; ++i){
        PQ_ENQUEUE(priorityQueue,i+1);
    }
    PQ_PRINTER(priorityQueue);
    for(int i=0; i<100; ++i){
        printf("%d\n",PQ_DEQUEUE(priorityQueue));
    }
    
    return 0;
}