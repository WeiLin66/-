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
#define PUSH(stack,val)			    as_push(stack,val)
#define POP(stack)				    as_pop(stack)
#define PEEK(stack)					as_peek(stack)
#define STACK_SIZE(stack)			as_getStackSize(stack)
#define STACK_IS_EMPTY(stack)		as_isStackEmpty(stack)
#define FREE_STACK(stack)			as_freeStack(&stack)

typedef struct{
    int* buffer;
    int size;
    int capacity;
}myStack;
typedef myStack* myStack_p;

myStack_p as_createStack(void);
void as_stackPrinter(myStack_p stack);
void as_push(myStack_p stack, int value);
int* as_pop(myStack_p stack);
int* as_peek(myStack_p stack);
int as_getStackSize(myStack_p stack);
bool as_isStackEmpty(myStack_p stack);
void as_freeStack(myStack_p* stack);
#else /* STACK_BASE == __LINKED_LIST */
#include "linkedlist.h"
typedef struct{
    myNode* root;
    int size;
}myStack;
typedef myStack* myStack_p;

#define CREATE_STACK(name)          myStack* name=ll_createStack()
#define STACK_PRINTER(stack)   		ll_stackPrinter(stack)
#define PUSH(stack,val)			    ll_push(stack,val)
#define POP(stack)				    ll_pop(stack)
#define PEEK(stack)					ll_peek(stack)
#define STACK_SIZE(stack)			ll_getStackSize(stack)
#define STACK_IS_EMPTY(stack)		ll_isStackEmpty(stack)
#define FREE_STACK(stack)			ll_freeStack(&stack)

myStack_p ll_createStack(void);
void ll_stackPrinter(myStack_p stack);
void ll_push(myStack_p stack, int value);
int ll_pop(myStack_p stack);
int ll_peek(myStack_p stack);
int ll_getStackSize(myStack_p stack);
bool ll_isStackEmpty(myStack_p stack);
void ll_freeStack(myStack_p* stack);
#endif /* STACK_BASE == __DYNAMIC_ARRAY */

#endif /* __STACK_H_ */