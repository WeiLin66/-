#ifndef __LOOPQUEUE_H_
#define __LOOPQUEUE_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <assert.h>

#define MAX_LOOPQUEUE_SIZE			256
#define CREATE_QUEUE(name)			myQueue_p name=alq_createQueue()
#define QUEUE_PRINTER(queue)		alq_queuePrinter(queue)
#define ENQUEUE(queue,value)		alq_enqueue(queue,value)
#define DEQUEUE(queue)				alq_dequeue(queue)
#define QUEUE_GET_FRONT(queue)		alq_queueGetFront(queue)
#define QUEUE_GET_SIZE(queue)		alq_queueGetSize(queue)
#define QUEUE_IS_EMPTY(queue)		alq_queueIsEmpty(queue)
#define FREE_QUEUE(queue)			alq_freeQueue(&queue)

typedef struct{
	int* buffer;
	int front;
	int tail;
	int size;
	int capacity;
}myQueue;
typedef myQueue* myQueue_p;

myQueue_p alq_createQueue(void);
void alq_queuePrinter(myQueue_p queue);
void alq_enqueue(myQueue_p queue, int value);
int alq_dequeue(myQueue_p queue);
int alq_queueGetFront(myQueue_p queue);
int alq_queueGetSize(myQueue_p queue);
bool alq_queueIsEmpty(myQueue_p queue);
void alq_freeQueue(myQueue_p* queue);

#endif /* __LOOPQUEUE_H_ */