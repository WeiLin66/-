#ifndef __ARRAYSTACK_H_
#define __ARRAYSTACK_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <assert.h>

#define MAX_LEN                     256
#define CREATE_STACK(name)          myStack_p name=createStack()
#define ARRATY_PRINTER(stack)       stackPrinter(stack)

typedef struct{
    int* buffer;
    int size;
    int capacity;
}myStack;

typedef myStack* myStack_p;

void enqueue(myStack_p stack, int value);
int* dequeue(myStack_p stack);
int* getFront(myStack_p stack);
int getSize(myStack_p stack);
bool isEmpty(myStack_p stack);
void freeStack(myStack_p* stack);

#endif /* __ARRAYSTACK_H_ */