#include "queue.h"

#if (QUEUE_BASE == __DYNAMIC_ARRAY)
/**
 * Dynamic Array Base Queue
 */
 static void aq_resize(myQueue_p queue, int capacity){

 	assert(queue);
 	assert(queue->buffer);

	int size = aq_queueGetSize(queue);
	
	for(int i=0; i<size; ++i){
		queue->buffer[i] = queue->buffer[i+queue->front];
	}
	
	queue->front = 0;
	queue->tail = size;

	if(capacity == queue->capacity || capacity < MAX_QUEUE_SIZE){
		return;
	}

 	queue->buffer = realloc(queue->buffer,sizeof(int)*capacity);

 	assert(queue->buffer);
 	queue->capacity = capacity;
 }

myQueue_p aq_createQueue(void){

	myQueue_p ret = malloc(sizeof(myQueue));
	ret->buffer = malloc(sizeof(int)*MAX_QUEUE_SIZE);
	ret->front = 0;
	ret->tail = 0;
	ret->capacity = MAX_QUEUE_SIZE;

	return ret;
}

void aq_queuePrinter(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	printf("front --> [");
	int size = aq_queueGetSize(queue);

	for(int i=0; i<size; ++i){
		printf("%d",queue->buffer[queue->front+i]);
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

void aq_enqueue(myQueue_p queue, int value){

	assert(queue);
	assert(queue->buffer);

	if(queue->tail >= queue->capacity){
		int size = aq_queueGetSize(queue);
		size = size/MAX_QUEUE_SIZE+1; 
		aq_resize(queue,size*MAX_QUEUE_SIZE);
	}

	queue->buffer[queue->tail] = value;
	++(queue->tail);
}

int aq_dequeue(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	int size = aq_queueGetSize(queue);
	if(size == queue->capacity>>1){
		aq_resize(queue,queue->capacity>>1);
	}

	int ret = queue->buffer[queue->front];
	++(queue->front);

	return ret;
}

int aq_queueGetFront(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	return queue->buffer[queue->front];
}

int aq_queueGetSize(myQueue_p queue){

	assert(queue);

	return queue->tail-queue->front;
}

bool aq_queueIsEmpty(myQueue_p queue){

	assert(queue);

	return queue->tail-queue->front == 0;
}

void aq_freeQueue(myQueue_p* queue){

	assert(queue);

	(*queue)->front = 0;
	(*queue)->tail = 0;
	(*queue)->capacity = 0;

	if((*queue)->buffer != NULL){
		free((*queue)->buffer);
	}

	free((*queue));
	*queue = NULL;
}

#else /* QUEUE_BASE == __LINKED_LIST */
/**
 * Linked List Base Queue
 */ 
myQueue_p ll_createQueue(void){

	myQueue_p ret = malloc(sizeof(myQueue));
	ret->root = mll_createNode(-1,NULL);
	ret->tail = mll_createNode(-1,NULL);
	ret->size = 0;

	return ret;
}

void ll_queuePrinter(myQueue_p queue){

	assert(queue);

	myNode* ptr = queue->root->next;

	printf("front--> [");
	while(ptr){
		printf("%d",ptr->data);
		if(ptr->next != NULL){
			printf(", ");
		}
		ptr = ptr->next;
	}
	printf("] <-- tail [size: %d]\n",queue->size);
}

void ll_enqueue(myQueue_p queue, int value){

	assert(queue);
	assert(queue->root);

	mll_insertNth2(&(queue->root->next),&(queue->tail->next),queue->size,value);
	++(queue->size);
}

int ll_dequeue(myQueue_p queue){

	assert(queue);
	assert(queue->root);

	if(ll_queueIsEmpty(queue)){
		printf("Queue is Empty!\n");
		return -1;
	}

	int ret = mll_popNode(&(queue->root->next));
	if(--(queue->size) == 0){
		queue->tail->next = NULL;
	}

	return ret;
}

int ll_queueGetFront(myQueue_p queue){

	assert(queue);
	assert(queue->root);

	if(ll_queueIsEmpty(queue)){
		printf("Queue is Empty!\n");
		return -1;
	}

	return queue->root->next->data;
}

int ll_queueGetSize(myQueue_p queue){

	assert(queue);

	return queue->size;
}

bool ll_queueIsEmpty(myQueue_p queue){

	assert(queue);

	return queue->size == 0;
}

void ll_freeQueue(myQueue_p* queue){

	assert(queue);

	mll_deleteList(&((*queue)->root));
	(*queue)->tail->next = NULL;
	free((*queue)->tail);
	free(*queue);
	*queue = NULL;
}

#endif /* QUEUE_BASE == __DYNAMIC_ARRAY */