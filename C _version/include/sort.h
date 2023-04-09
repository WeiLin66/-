#ifndef __SORT_H_
#define __SORT_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define SWAP(a,b)                                   do{                 \
                                                        int tmp=a;      \
                                                        a=b;            \
                                                        b=tmp;          \
                                                    }while(0)

#define MAX_LEN                                     (10000000)
#define MAX_RANGE                                   (1000)
#define CREATE_ARRAY(name)                          int name[MAX_LEN]={0}
#define GENERATE_ARRAY(name)                        srand((unsigned int)time(NULL)); arrayGenerator1(name,MAX_LEN,MAX_RANGE)                         

#define COPY_ARRAY(src,name)                        memcpy(name,src,MAX_LEN*sizeof(int))
#define SORTING_PRINTER(arg,name)                   //printf("[%s]: ",#arg); arrayPrinter(name,MAX_LEN)
#define SORTING_CHECKER(name,arr)                   sortingCheck(name,arr,MAX_LEN)
#define SORTING_TEST(sort,name)                     do{                                                             \
                                                        SORTING_PRINTER(sort Before,(name));                        \
                                                        clock_t  start,end;                                         \
                                                        start = clock();                                            \
                                                        sort(name,MAX_LEN);                                         \
                                                        end = clock();                                              \
                                                        SORTING_CHECKER(#sort,name);                                \
                                                        SORTING_PRINTER(sort After,(name));                         \
                                                        printf("[%s] [%d elements] Time Consumes: %lf sec\n",       \
                                                                #sort,MAX_LEN,(end-start)/(double)CLOCKS_PER_SEC);  \
                                                    }while(0)

void arrayGenerator1(int* arr, int len, int range);
void arrayGenerator2(int* arr, int len, int range);
void arrayGenerator3(int* arr, int len, int range);
void arrayGenerator4(int* arr, int len, int range);
void arrayPrinter(int* arr, int len);
void sortingCheck(char* name, int* arr, int len);

void insertSort1(int* arr, int len);
void insertSort2(int* arr, int len);
void insertSort3(int* arr, int len);
void selectionSort1(int* arr, int len);
void selectionSort2(int* arr, int len);
void selectionSort3(int* arr, int len);
void bubbleSort1(int* arr, int len);
void bubbleSort2(int* arr, int len);
void bubbleSort3(int* arr, int len);
void bubbleSort4(int* arr, int len);
void bubbleSort5(int* arr, int len);
void shellSort1(int* arr, int len);
void shellSort2(int* arr, int len);
void shellSort3(int* arr, int len);
void shellSort4(int* arr, int len);
void mergeSort1(int* arr, int len);
void mergeSort2(int* arr, int len);
void mergeSort3(int* arr, int len);
void mergeSort4(int* arr, int len);
void quickSort1(int* arr, int len);
void quickSort2(int* arr, int len);
void quickSort3(int* arr, int len);
void quickSort4(int* arr, int len);
void quickSort5(int* arr, int len);
void quickSort6(int* arr, int len);
void quickSort7(int* arr, int len);

#endif /* __SORT_H_ */