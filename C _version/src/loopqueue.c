#include "loopqueue.h"

static void alq_resize(myQueue_p queue, int capacity){

	assert(queue);

	int* temp = malloc(sizeof(int)*queue->size);
	for(int i=0; i<queue->size; ++i){
		temp[i] = queue->buffer[(queue->front+i)%queue->capacity];
	}
	memcpy(queue->buffer,temp,sizeof(int)*queue->size);

	queue->buffer = realloc(queue->buffer,sizeof(int)*capacity);
	queue->capacity = capacity;
	queue->front = 0;
	queue->tail = queue->size%queue->capacity;
	free(temp);
}  

myQueue_p alq_createQueue(void){

	myQueue_p ret = malloc(sizeof(myQueue));
	ret->buffer = malloc(sizeof(int)*MAX_LOOPQUEUE_SIZE);
	ret->front = 0;
	ret->tail = 0;
	ret->size = 0;
	ret->capacity = MAX_LOOPQUEUE_SIZE;

	return ret;
}

void alq_queuePrinter(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	printf("front --> [");
	int size = alq_queueGetSize(queue);

	for(int i=0; i<size; ++i){
		printf("%d",queue->buffer[(queue->front+i)%queue->capacity]);
		if(i != size-1){
			printf(", ");
		}
	}
	printf("] <-- tail [front: %d, tail: %d, size: %d, capacity: %d]\n",
								queue->front,
								queue->tail,
								size,
								queue->capacity);
}

void alq_enqueue(myQueue_p queue, int value){

	assert(queue);
	assert(queue->buffer);

	if(queue->tail == queue->front && queue->size != 0){
		alq_resize(queue,queue->capacity<<1);
	}

	queue->buffer[queue->tail] = value;
	queue->tail = (queue->tail+1)%queue->capacity;
	++(queue->size);
}

int alq_dequeue(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	if(alq_queueIsEmpty(queue)){
		printf("LoopQueue is Empty!\n");
		return -1;
	}

	if(queue->size >= MAX_LOOPQUEUE_SIZE && queue->size == queue->capacity>>1){
		alq_resize(queue,queue->capacity>>1);
	}

	int ret = queue->buffer[queue->front];
	queue->front = (queue->front+1)%queue->capacity;
	--(queue->size);

	return ret;
}

int alq_queueGetFront(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	if(alq_queueIsEmpty(queue)){
		printf("LoopQueue is Empty!\n");
		return -1;
	}

	return queue->buffer[queue->front];
}

int alq_queueGetSize(myQueue_p queue){

	assert(queue);

	return queue->size;
}

bool alq_queueIsEmpty(myQueue_p queue){

	assert(queue);

	return queue->size == 0;
}

void alq_freeQueue(myQueue_p* queue){

	assert(queue);

	if((*queue)->buffer){
		free((*queue)->buffer);
	}
	(*queue)->front = 0;
	(*queue)->tail = 0;
	(*queue)->size = 0;
	(*queue)->capacity = 0;
	free(*queue);
	*queue = NULL;
}