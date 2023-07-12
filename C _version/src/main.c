#include "sort.h"

CREATE_ARRAY(arr);
CREATE_ARRAY(arr2);

int main(){

    GENERATE_ARRAY(arr);
    COPY_ARRAY(arr2,arr);
    SORTING_TEST(quickSort8,arr);
    SORTING_TEST(quickSort7,arr2);

    return 0;
}