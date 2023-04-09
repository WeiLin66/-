#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define SWAP(a,b)                                   do{                 \
                                                        int tmp=a;      \
                                                        a=b;            \
                                                        b=tmp;          \
                                                    }while(0);

#define MAX_LEN                                     (10000000)
#define MAX_RANGE                                   (1000)
#define CREATE_ARRAY(name)                          int name[MAX_LEN]={0};
#define GENERATE_ARRAY(name)                        srand((unsigned int)time(NULL)); arrayGenerator1(name,MAX_LEN,MAX_RANGE)                         

#define COPY_ARRAY(src,name)                        memcpy(name,src,MAX_LEN*sizeof(int))
#define ARRAY_PRINTER(arg,name)                     //printf("[%s]: ",#arg); arrayPrinter(name,MAX_LEN)
#define SORTING_CHECKER(name,arr)                   sortingCheck(name,arr,MAX_LEN)
#define SORTING_TEST(sort,name)                     do{                                                             \
                                                        ARRAY_PRINTER(sort Before,(name));                          \
                                                        clock_t  start,end;                                         \
                                                        start = clock();                                            \
                                                        sort(name,MAX_LEN);                                         \
                                                        end = clock();                                              \
                                                        SORTING_CHECKER(#sort,name);                                \
                                                        ARRAY_PRINTER(sort After,(name));                           \
                                                        printf("[%s] [%d elements] Time Consumes: %lf sec\n",       \
                                                                #sort,MAX_LEN,(end-start)/(double)CLOCKS_PER_SEC);  \
                                                    }while(0)

static void arrayGenerator1(int* arr, int len, int range){

    for(int i=0; i<len; ++i){
        arr[i] = rand() % range + 1;
    }
}

static void arrayGenerator2(int* arr, int len, int range){

    for(int i=0; i<len; ++i){
        arr[i] = i;
    }
}

static void arrayGenerator3(int* arr, int len, int range){

    for(int i=0; i<len; ++i){
        arr[i] = i+1;
    }

    for(int i=len-2; i>=0; --i){
        SWAP(arr[i],arr[i+(len-1-i)/2]);
    }
}

static void arrayGenerator4(int* arr, int len, int range){

    for(int i=0; i<len; ++i){
        arr[i] = range;
    }
}

static void arrayPrinter(int* arr, int len){

    for(int i=0; i<len; ++i){
        printf("%d",arr[i]);
        if(i != len-1){
            printf(", ");
        }
    }
    printf("\n");
}

static void sortingCheck(char* name, int* arr, int len){

    for(int i=0; i<len-1; ++i){
        if(arr[i] > arr[i+1]){
            fprintf(stderr,"Error, %s Result is Wrong!\n",name);
            exit(0);
        }
    }
}

void insertSort1(int* arr, int len){

    int i,j;

    for(i=1; i<len; ++i){
        int find=arr[i];
        for(j=i; j-1>=0; --j){
            if(arr[j-1] <= find){
                break;
            }
            arr[j] = arr[j-1];
        }
        arr[j] = find;
    }
}

void insertSort2(int* arr, int len){

    for(int i=1; i<len; ++i){
        for(int j=i; j-1>=0; --j){
            if(arr[j-1] > arr[j]){
                SWAP(arr[j],arr[j-1]);
            }else{
                break;
            }
        }
    }
}

void insertSort3(int* arr, int len){

    int i,j;

    for(i=len-2; i>=0; --i){
        int find=arr[i];
        for(j=i; j+1<len; ++j){
            if(arr[j+1] >= find){
                break;
            }
            arr[j] = arr[j+1];
        }
        arr[j] = find;
    }
}

void selectionSort1(int* arr, int len){

    for(int i=0; i<len; ++i){
        int find = i;
        for(int j=i+1; j<len; ++j){
            find = arr[find] > arr[j] ? j : find;
        }        
        SWAP(arr[i],arr[find]);
    }
}

void selectionSort2(int* arr, int len){

    for(int i=len-1; i>=0; --i){
        int find = i;
        for(int j=i-1; j>=0; --j){
            find = arr[find] < arr[j] ? j : find;
        }
        SWAP(arr[i],arr[find]);
    }
}

void selectionSort3(int* arr, int len){

    for(int i=len-1; i>=0; --i){
        int find = i;
        for(int j=i-1; j>=0; --j){
            find = arr[find] < arr[j] ? j : find;
        }
        if(find != i){
            SWAP(arr[i],arr[find]);
        }
    }
}

void bubbleSort1(int* arr, int len){

    for(int i=0; i<len-1; ++i){
        for(int j=0; j<len-i-1; ++j){
            if(arr[j] > arr[j+1]){
                SWAP(arr[j],arr[j+1]);
            }   
        }
    }
}

void bubbleSort2(int* arr, int len){

    for(int i=0; i<len-1; ++i){
        bool isSwapped = false; 
        for(int j=0; j<len-i-1; ++j){
            if(arr[j] > arr[j+1]){
                SWAP(arr[j],arr[j+1]);
                isSwapped = true;
            }   
        }
        if(!isSwapped){
            break;
        }
    }
}

void bubbleSort3(int* arr, int len){

    for(int i=len-1; i>0; --i){
        int isSwapped=0;
        for(int j=0; j<i; ++j){
            if(arr[j] > arr[j+1]){
                SWAP(arr[j],arr[j+1]);
                isSwapped = j+1;
            }
        }
        if(isSwapped == 0){
            break;
        }
        i = isSwapped;
    }
}

void bubbleSort4(int* arr, int len){

    for(int i=0; i<len-1; ++i){
        int isSwapped=0;
        for(int j=0; j<len-i-1; ++j){
            if(arr[j] > arr[j+1]){
                SWAP(arr[j],arr[j+1]);
                isSwapped = j+1;
            }
        }
        if(isSwapped == 0){
            break;
        }
        i = len-isSwapped-1;
    }
}

void bubbleSort5(int* arr, int len){

    for(int i=0; i<len-1; i++){
        int isSwapped=0;
        for(int j=len-1; j>i; --j){
            if(arr[j] < arr[j-1]){
                SWAP(arr[j],arr[j-1]);
                isSwapped = j;
            }
        }
        if(isSwapped == 0){
            break;
        }
        i = isSwapped - 1;
    }
}

void shellSort1(int* arr, int len){

    int i=len>>1,j,k;

    for(i=len>>1; i>0; i>>=1){
        for(j=i; j<len; ++j){
            int tmp = arr[j];
            for(k=j; k>=i && tmp < arr[k-i]; k-=i){
                arr[k] = arr[k-i];
            }
            arr[k] = tmp;
        }
    }
}

void shellSort2(int* arr, int len){

    int i=len>>1,j,k;

    while(i){
        for(int start=0; start<i; ++start){
            for(j=start+i; j<len; j+=i){
                int tmp = arr[j];
                for(k=j; k>=i && tmp < arr[k-i]; k-=i){
                    arr[k] = arr[k-i];
                }
                arr[k] = tmp;
            }
        }
        i >>= 1;
    }
}

void shellSort3(int* arr, int len){
    
    int i=len>>1,j,k;

    while(i){
        for(j=i; j<len; ++j){
            int tmp = arr[j];
            for(k=j; k>=i && tmp < arr[k-i]; k-=i){
                arr[k] = arr[k-i];
            }
            arr[k] = tmp;
        }
        i >>= 1;
    }
}

void shellSort4(int* arr, int len){
    
    int i=1,j,k;
    /* 1,5,21,85... */
    while(i<len){
        i=(i<<2)+1;
    }

    while(i){
        for(j=i; j<len; ++j){
            int tmp = arr[j];
            for(k=j; k>=i && tmp < arr[k-i]; k-=i){
                arr[k] = arr[k-i];
            }
            arr[k] = tmp;
        }
        i >>= 2;
    }
}

static void merge1(int* arr, int left, int right){

    int* temp = malloc(sizeof(int)*(right-left+1));
    int mid = left+(right-left)/2;
    int l=left,r=mid+1;
    memcpy(temp,arr+left,sizeof(int)*(right-left+1));

    for(int i=left; i<=right; ++i){
        if(l > mid){
            arr[i] = temp[r-left];
            r++;
        }else if(r > right){
            arr[i] = temp[l-left];
            l++;
        }else if(temp[l-left] < temp[r-left]){
            arr[i] = temp[l-left];
            l++;
        }else{
            arr[i] = temp[r-left];
            r++;
        }
    }
    free(temp);
}

static void merge2(int* arr, int* temp, int left, int right){

    int mid = left+(right-left)/2;
    int l=left,r=mid+1;
    memcpy(temp,arr+left,sizeof(int)*(right-left+1));

    for(int i=left; i<=right; ++i){
        if(l > mid){
            arr[i] = temp[r-left];
            r++;
        }else if(r > right){
            arr[i] = temp[l-left];
            l++;
        }else if(temp[l-left] < temp[r-left]){
            arr[i] = temp[l-left];
            l++;
        }else{
            arr[i] = temp[r-left];
            r++;
        }
    }
}

static void insertMergeOp(int* arr, int left, int right){

    int i,j;

    for(i=left; i<=right; ++i){
        int find=arr[i];
        for(j=i; j-1>=left; --j){
            if(arr[j-1] <= find){
                break;
            }
            arr[j] = arr[j-1];
        }
        arr[j] = find;
    }
}

static void mergesort1(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    int mid = left+(right-left)/2;

    mergesort1(arr,left,mid);
    mergesort1(arr,mid+1,right);
    merge1(arr,left,right);
}

static void mergesort2(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    int mid = left+(right-left)/2;

    mergesort2(arr,left,mid);
    mergesort2(arr,mid+1,right);
    if(arr[mid] > arr[mid+1]){
        merge1(arr,left,right);
    }
}

static void mergesort3(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    if(right-left+1 <= 16){
        insertMergeOp(arr,left,right);
        return;
    }

    int mid = left+(right-left)/2;

    mergesort3(arr,left,mid);
    mergesort3(arr,mid+1,right);
    if(arr[mid] > arr[mid+1]){
        merge1(arr,left,right);
    }
}

static void mergesort4(int* arr, int* temp, int left, int right){

    if(left >= right){
        return;
    }

    if(right-left+1 <= 16){
        insertMergeOp(arr,left,right);
        return;
    }

    int mid = left+(right-left)/2;

    mergesort4(arr,temp,left,mid);
    mergesort4(arr,temp,mid+1,right);
    if(arr[mid] > arr[mid+1]){
        merge2(arr,temp,left,right);
    }
}

void mergeSort1(int* arr, int len){

    mergesort1(arr,0,len-1);
}

void mergeSort2(int* arr, int len){

    mergesort2(arr,0,len-1);
}

void mergeSort3(int* arr, int len){

    mergesort3(arr,0,len-1);
}

void mergeSort4(int* arr, int len){

    int* temp = malloc(sizeof(int)*len);
    mergesort4(arr,temp,0,len-1);
    free(temp);
}

static int partition1(int* arr, int left, int right){

    int i=left;
    int p = arr[left+(right-left)/2];
    SWAP(arr[i],arr[left+(right-left)/2]);

    for(int j=i+1; j<=right; ++j){
        if(arr[j] <= p){
            i++;
            if(i != j && arr[i] != arr[j]){
                SWAP(arr[i],arr[j]);
            }
        }
    }
    if(i != left && arr[i] != arr[left]){
        SWAP(arr[i],arr[left]);
    }

    return i;
}

static int partition2(int* arr, int left, int right){

    int i=left;
    int p = arr[i];

    for(int j=i+1; j<=right; ++j){
        if(arr[j] <= p){
            i++;
            if(i != j && arr[i] != arr[j]){
                SWAP(arr[i],arr[j]);
            }
        }
    }
    if(i != left && arr[i] != arr[left]){
        SWAP(arr[i],arr[left]);
    }

    return i;
}

static int partition3(int* arr, int left, int right){

    int i=left;
    int p = rand()%(right-left+1)+left;
    SWAP(arr[i],arr[p]);

    for(int j=i+1; j<=right; ++j){
        if(arr[j] <= arr[left]){
            i++;
            if(i != j && arr[i] != arr[j]){
                SWAP(arr[i],arr[j]);
            }
        }
    }
    if(i != left && arr[i] != arr[left]){
        SWAP(arr[i],arr[left]);
    }

    return i;   
}

static int partition4(int* arr, int left, int right){
    
    int p = rand()%(right-left+1)+left;
    SWAP(arr[left],arr[p]);

    int l=left+1,r=right;

    while(l <= r){
        if(arr[l] < arr[left]){
            ++l;
        }else if(arr[r] > arr[left]){
            --r;
        }else{
            SWAP(arr[l],arr[r]);
            ++l;
            --r;
        }
    }
    SWAP(arr[left],arr[r]);

    return r;
}

static int partition5(int* arr, int left, int right){

    int p = rand()%(right-left+1)+left;
    SWAP(arr[left],arr[p]);

    int l=left+1,r=right;

    while(true){

        while(l <= r && arr[l] < arr[left]){
            ++l;
        }

        while(l <= r && arr[r] > arr[left]){
            --r;
        }

        if(l >= r){
            break;
        }

        SWAP(arr[l],arr[r]);
        ++l;
        --r;
    }
    SWAP(arr[left],arr[r]);

    return r;
}

static void quicksort1(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    int mid = partition1(arr,left,right);

    quicksort1(arr,left,mid-1);
    quicksort1(arr,mid+1,right);
}

static void quicksort2(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    int mid = partition2(arr,left,right);

    quicksort2(arr,left,mid-1);
    quicksort2(arr,mid+1,right);
}

static void quicksort3(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    if(right-left+1 <= 16){
        insertMergeOp(arr,left,right);
        return;
    }    

    int mid = partition1(arr,left,right);

    quicksort3(arr,left,mid-1);
    quicksort3(arr,mid+1,right);
}

static void quicksort4(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    if(right-left+1 <= 16){
        insertMergeOp(arr,left,right);
        return;
    }    

    int mid = partition3(arr,left,right);

    quicksort4(arr,left,mid-1);
    quicksort4(arr,mid+1,right);
}

static void quicksort5(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    if(right-left+1 <= 16){
        insertMergeOp(arr,left,right);
        return;
    }    

    int mid = partition4(arr,left,right);

    quicksort5(arr,left,mid-1);
    quicksort5(arr,mid+1,right);
}

static void quicksort6(int* arr, int left, int right){

    if(left >= right){
        return;
    }

    if(right-left+1 <= 16){
        insertMergeOp(arr,left,right);
        return;
    }    

    int mid = partition5(arr,left,right);

    quicksort6(arr,left,mid-1);
    quicksort6(arr,mid+1,right);
}

static void quicksort7(int* arr, int left, int right){
    
    if(left >= right){
        return;
    }

    if(right-left+1 <= 16){
        insertMergeOp(arr,left,right);
        return;
    }

    int p = rand()%(right-left+1)+left;
    SWAP(arr[left],arr[p]);

    int lt=left,gt=right+1;
    int i=left+1;

    while(i < gt){

        if(arr[i] < arr[left]){
            lt++;
            if(lt != i){
                SWAP(arr[lt],arr[i]);
            }
            ++i;
        }else if(arr[i] == arr[left]){
            ++i;
        }else{
            SWAP(arr[gt-1],arr[i]);
            gt--;
        }
    }
    SWAP(arr[left],arr[lt]);

    quicksort7(arr,left,lt);
    quicksort7(arr,gt,right);
}

void quickSort1(int* arr, int len){

    quicksort1(arr,0,len-1);
}

void quickSort2(int* arr, int len){

    quicksort2(arr,0,len-1);
}

void quickSort3(int* arr, int len){

    quicksort3(arr,0,len-1);
}

void quickSort4(int* arr, int len){

    quicksort4(arr,0,len-1);
}

void quickSort5(int* arr, int len){

    quicksort5(arr,0,len-1);
}

void quickSort6(int* arr, int len){

    quicksort6(arr,0,len-1);
}

void quickSort7(int* arr, int len){

    quicksort7(arr,0,len-1);
}

CREATE_ARRAY(arr1);
CREATE_ARRAY(arr2);
CREATE_ARRAY(arr3);
CREATE_ARRAY(arr4);
CREATE_ARRAY(arr5);

int main(int argc, char* argv[]){

    GENERATE_ARRAY(arr1);
    COPY_ARRAY(arr1,arr2);
    COPY_ARRAY(arr1,arr3);
    COPY_ARRAY(arr1,arr4);
    COPY_ARRAY(arr1,arr5);

    SORTING_TEST(quickSort5,arr1);
    SORTING_TEST(quickSort6,arr2);
    SORTING_TEST(quickSort7,arr3);
    SORTING_TEST(mergeSort4,arr4);
    SORTING_TEST(shellSort4,arr5);

    return 0;
}