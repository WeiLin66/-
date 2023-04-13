#include "heap.h"

int main(){

    CREATE_HEAP(heap);
    for(int i=0; i<10; ++i){
        MINHEAP_INSERT(heap,i+1);
    }
    HEAP_PRINTER(heap);
    for(int i=0; i<10; ++i){
        printf("%d\n", MINHEAP_REMOVE(heap));
        HEAP_PRINTER(heap);
    }
    FREE_HEAP(heap);

    return 0;
}