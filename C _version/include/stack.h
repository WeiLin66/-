#ifndef __STACK_H_
#define __STACK_H_

#define __DYNAMIC_ARRAY		0
#define __LINKED_LIST		1
#define STACK_BASE			__LINKED_LIST

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <assert.h>

#if (STACK_BASE == __DYNAMIC_ARRAY)
#define MAX_STACK_SIZE              256
#define CREATE_STACK(name)          myStack_p name=as_createStack()
#define STACK_PRINTER(stack)   		as_stackPrinter(stack)
#define ENQUEUE(stack,val)			as_enqueue(stack,val)
#define DEQUEUE(stack)				as_dequeue(stack)
#define PEEK(stack)					as_getFront(stack)
#define STACK_SIZE(stack)			as_getSize(stack)
#define STACK_ISEMPTY(stack)		as_isEmpty(stack)
#define FREE_STACK(stack)			as_freeStack(&stack)

typedef struct{
    int* buffer;
    int size;
    int capacity;
}myStack;
typedef myStack* myStack_p;

myStack_p as_createStack(void);
void as_stackPrinter(myStack_p stack);
void as_enqueue(myStack_p stack, int value);
int* as_dequeue(myStack_p stack);
int* as_getFront(myStack_p stack);
int as_getSize(myStack_p stack);
bool as_isEmpty(myStack_p stack);
void as_freeStack(myStack_p* stack);
#else /* STACK_BASE == __LINKED_LIST */
#include "linkedlist.h"
typedef struct{
    myNode* dummy;
    int size;
}myStack;
typedef myStack* myStack_p;

#define CREATE_STACK(name)          myStack* name=ll_createStack()
#define STACK_PRINTER(stack)   		ll_stackPrinter(stack)
#define ENQUEUE(stack,val)			ll_enqueue(stack,val)
#define DEQUEUE(stack)				ll_dequeue(stack)
#define PEEK(stack)					ll_getFront(stack)
#define STACK_SIZE(stack)			ll_getSize(stack)
#define STACK_ISEMPTY(stack)		ll_isEmpty(stack)
#define FREE_STACK(stack)			ll_freeStack(&stack)

myStack_p ll_createStack(void);
void ll_stackPrinter(myStack_p stack);
void ll_enqueue(myStack_p stack, int value);
int ll_dequeue(myStack_p stack);
int ll_getFront(myStack_p stack);
int ll_getSize(myStack_p stack);
bool ll_isEmpty(myStack_p stack);
void ll_freeStack(myStack_p* stack);
#endif /* STACK_BASE == __DYNAMIC_ARRAY */

#endif /* __STACK_H_ */