#include "dequeue.h"

int main(){

    CREATE_QUEUE(queue);
    for(int i=0; i<10; ++i){
        ADD_FRONT(queue,i+1);
    }
    QUEUE_PRINTER(queue);
    for(int i=0; i<247; ++i){
        ADD_LAST(queue,i+1);
    }
    QUEUE_PRINTER(queue);
    for(int i=0; i<57; ++i){
        REMOVE_LAST(queue);
    }
    QUEUE_PRINTER(queue);
    for(int i=0; i<200; ++i){
        REMOVE_FRONT(queue);
    }
    QUEUE_PRINTER(queue);
    for(int i=0; i<2; ++i){
        ADD_FRONT(queue,i+1);
    }
    QUEUE_PRINTER(queue);
    for(int i=0; i<2; ++i){
        ADD_LAST(queue,i+1);
    }
    QUEUE_PRINTER(queue);
    FREE_QUEUE(queue);

    return 0;
}