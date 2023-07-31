#include "queue.h"

int main(){

    CREATE_QUEUE(queue);

    for(int i=0; i<10; ++i){
        ENQUEUE(queue,i+1);
        QUEUE_PRINTER(queue);
    }
    for(int i=0; i<10; ++i){
        DEQUEUE(queue);
        QUEUE_PRINTER(queue);
    }

    return 0;
}