#include "sort.h"

CREATE_ARRAY(arr);
CREATE_ARRAY(arr2);

int main(){

    GENERATE_ARRAY(arr);
    COPY_ARRAY(arr2,arr);

    SORTING_TEST(shellSort4,arr);
    SORTING_TEST(hp_heapSort2,arr2);

    return 0;
}