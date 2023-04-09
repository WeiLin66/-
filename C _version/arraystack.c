#include "arraystack.h"

static myStack_p createStack(){
    
    myStack_p ret = malloc(sizeof(myStack));
    ret->buffer = calloc(MAX_LEN,sizeof(int));
    ret->size = 0;
    ret->capacity = MAX_LEN;

    return ret;
}

static void stackPrinter(myStack_p stack){

    assert(stack);

    printf("[");
    for(int i=0; i<stack->size; ++i){
        printf("%d",stack->buffer[i]);
        if(i != stack->size-1){
            printf(", ");
        }
    }
    printf("] <--- top [capacity: %d, size: %d]\n",stack->capacity,stack->size);
}

static void resize(myStack_p stack, int capacity){

    assert(stack);

    stack->buffer = realloc(stack->buffer,capacity*sizeof(int));

    assert(stack->buffer);

    stack->capacity = capacity;
}

void enqueue(myStack_p stack, int value){

    assert(stack);

    if(stack->size == stack->capacity){
        resize(stack,stack->capacity<<1);
    }    

    stack->buffer[stack->size] = value;

    ++(stack->size);
}

int* dequeue(myStack_p stack){

    assert(stack);

    if(isEmpty(stack)){
        return NULL;
    }

    --(stack->size);

    if(stack->size >= MAX_LEN && stack->size == stack->capacity>>1){
         resize(stack,stack->capacity>>1);
    }

    return stack->buffer+stack->size;
}

int* getFront(myStack_p stack){

    assert(stack);

    if(isEmpty(stack)){
        return NULL;
    }

    return stack->buffer+(stack->size-1);
}

int getSize(myStack_p stack){

    assert(stack);

    return stack->size;
}

bool isEmpty(myStack_p stack){

    assert(stack);

    return getSize(stack) == 0;
}

void freeStack(myStack_p* stack){

    assert(stack);

    if((*stack)->buffer){
        free((*stack)->buffer);
        (*stack)->buffer = NULL;
    }
    (*stack)->capacity = 0;
    (*stack)->size = 0;

    free(*stack);
    *stack =NULL;
}