#include "stack.h"

#if (STACK_BASE == __DYNAMIC_ARRAY)
/**
 * Dynamic Array Base Stack
 */ 
static void as_resize(myStack_p stack, int capacity){

    assert(stack);
    assert(stack->buffer);

    stack->buffer = realloc(stack->buffer,capacity*sizeof(int));

    assert(stack->buffer);

    stack->capacity = capacity;
}

myStack_p as_createStack(){
    
    myStack_p ret = malloc(sizeof(myStack));
    ret->buffer = calloc(MAX_STACK_SIZE,sizeof(int));
    ret->size = 0;
    ret->capacity = MAX_STACK_SIZE;

    return ret;
}

void as_stackPrinter(myStack_p stack){

    assert(stack);
    assert(stack->buffer);

    printf("[");
    for(int i=0; i<stack->size; ++i){
        printf("%d",stack->buffer[i]);
        if(i != stack->size-1){
            printf(", ");
        }
    }
    printf("] <--- top [capacity: %d, size: %d]\n",stack->capacity,stack->size);
}

void as_push(myStack_p stack, int value){

    assert(stack);
    assert(stack->buffer);

    if(stack->size == stack->capacity){
        as_resize(stack,stack->capacity<<1);
    }    

    stack->buffer[stack->size] = value;

    ++(stack->size);
}

int* as_pop(myStack_p stack){

    assert(stack);
    assert(stack->buffer);

    if(as_isStackEmpty(stack)){
        return NULL;
    }

    --(stack->size);

    if(stack->size >= MAX_STACK_SIZE && stack->size == stack->capacity>>1){
         as_resize(stack,stack->capacity>>1);
    }

    return stack->buffer+stack->size;
}

int* as_peek(myStack_p stack){

    assert(stack);
    assert(stack->buffer);

    if(as_isStackEmpty(stack)){
        return NULL;
    }

    return stack->buffer+(stack->size-1);
}

int as_getStackSize(myStack_p stack){

    assert(stack);

    return stack->size;
}

bool as_isStackEmpty(myStack_p stack){

    assert(stack);

    return as_getStackSize(stack) == 0;
}

void as_freeStack(myStack_p* stack){

    assert(stack);

    if((*stack)->buffer){
        free((*stack)->buffer);
        (*stack)->buffer = NULL;
    }
    (*stack)->capacity = 0;
    (*stack)->size = 0;

    free(*stack);
    *stack = NULL;
}
#else /* STACK_BASE == __LINKED_LIST */
/**
 * Linked List Base Stack
 */ 
myStack_p ll_createStack(){

    myStack_p ret = malloc(sizeof(myStack));
    ret->root = mll_createNode(-1,NULL);
    ret->size = 0;

    return ret;
}

void ll_stackPrinter(myStack_p stack){

    assert(stack);

    myNode* node = stack->root->next;
    printf("[");
    while(node){
        printf("%d", node->data);
        node = node->next;
        if(node){
            printf(", ");
        }
    }
    printf("] <--- top [size: %d]\n",stack->size);
}

void ll_push(myStack_p stack, int value){

    assert(stack);
    assert(stack->root);
    mll_pushNode(&(stack->root->next),value);
    ++(stack->size);
}

int ll_pop(myStack_p stack){

    assert(stack);

    if(ll_isStackEmpty(stack)){
        MLL_DBG("Stack is Empty!");
        return -1;
    }

    int ret = mll_popNode(&(stack->root->next));
    --(stack->size);

    return ret;
}

int ll_peek(myStack_p stack){

    assert(stack);

    if(ll_isStackEmpty(stack)){
        MLL_DBG("Stack is Empty!");
        return -1;
    }

    return stack->root->next->data;
}

int ll_getStackSize(myStack_p stack){

    assert(stack);

    return stack->size;
}

bool ll_isStackEmpty(myStack_p stack){

    assert(stack);

    return stack->size == 0;
}

void ll_freeStack(myStack_p* stack){

    assert(stack);

    myNode* pre = (*stack)->root;
    myNode* curr = (*stack)->root->next;

    while(curr){

        pre->next = NULL;
        free(pre);
        pre = curr;
        curr = curr->next;
    }
    free(pre);
    free(*stack);
    *stack = NULL;
}
#endif /* STACK_BASE == __DYNAMIC_ARRAY */