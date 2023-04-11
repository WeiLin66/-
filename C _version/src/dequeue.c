#include "dequeue.h"

#if (DEQUEUE_BASE == __DYNAMIC_ARRAY)
/**
 * Dynamic Array Base Dequeue
 */
static void adq_resize(myQueue_p queue, int capacity){

	assert(queue);
	assert(queue->buffer);

	int size = adq_queueGetSize(queue);
	int* temp = malloc(sizeof(int)*size);
	for(int i=0; i<size; ++i){
		temp[i] = queue->buffer[(queue->front+i)%queue->capacity];
	}
	for(int i=0; i<size; ++i){
		queue->buffer[i]=temp[i];
	}

	queue->buffer = realloc(queue->buffer,sizeof(int)*capacity);
	queue->front = 0;
	queue->tail = queue->size-1;
	queue->capacity = capacity;
}

myQueue_p adq_createQueue(void){

	myQueue_p ret = malloc(sizeof(myQueue));
	ret->buffer = malloc(sizeof(int)*MAX_DEQUEUE_SIZE);
	ret->front = 0;
	ret->tail = -1;
	ret->size = 0;
	ret->capacity = MAX_DEQUEUE_SIZE;

	return ret;
}

void adq_queuePrinter(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	printf("front --> [");
	int size = adq_queueGetSize(queue);

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

void adq_addFront(myQueue_p queue, int value){

	assert(queue);
	assert(queue->buffer);

	int size = adq_queueGetSize(queue);
	if(size >= queue->capacity){
		adq_resize(queue,queue->capacity<<1);
	}

	if(queue->front != 0){
		--(queue->front);
	}else{
		queue->front = queue->capacity-1;
	}

	queue->buffer[queue->front] = value;
	++(queue->size);
}

void adq_addLast(myQueue_p queue, int value){

	assert(queue);
	assert(queue->buffer);

	int size = adq_queueGetSize(queue);
	if(size >= queue->capacity){
		adq_resize(queue,queue->capacity<<1);
	}

	if(queue->tail != queue->capacity-1){
		++(queue->tail);
	}else{
		queue->tail = 0;
	}

	queue->buffer[queue->tail] = value;
	++(queue->size);	
}

int adq_removeFront(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	if(adq_queueIsEmpty(queue)){
		printf("Queue is Empty!\n");
		return -1;
	}

	int size = adq_queueGetSize(queue);
	if(size >= MAX_DEQUEUE_SIZE && size == queue->capacity>>1){
		adq_resize(queue,queue->capacity>>1);
	}

	int ret = queue->buffer[queue->front];

	if(queue->front != queue->capacity-1){
		++(queue->front);
	}else{
		queue->front = 0;
	}
	--(queue->size);

	return ret;
}

int adq_removeLast(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	assert(queue);
	assert(queue->buffer);

	if(adq_queueIsEmpty(queue)){
		printf("Queue is Empty!\n");
		return -1;
	}

	int size = adq_queueGetSize(queue);
	if(size >= MAX_DEQUEUE_SIZE && size == queue->capacity>>1){
		adq_resize(queue,queue->capacity>>1);
	}

	int ret = queue->buffer[queue->tail];

	if(queue->tail != 0){
		--(queue->tail);
	}else{
		queue->tail = queue->capacity-1;
	}
	--(queue->size);

	return ret;
}

int adq_queueGetFront(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	return queue->buffer[queue->front];
}

int adq_queueGetLast(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	return queue->buffer[queue->tail];
}

int adq_queueGetSize(myQueue_p queue){

	assert(queue);
	assert(queue->buffer);

	return queue->size;
}

bool adq_queueIsEmpty(myQueue_p queue){

	assert(queue);

	return queue->size == 0;
}

void adq_freeQueue(myQueue_p* queue){

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
#else /* DEQUEUE_BASE == __LINKED_LIST */
myQueue_p ldq_createQueue(void){

	myQueue_p ret = malloc(sizeof(myQueue));
	ret->root = mll_createNode(-1,NULL);
	ret->tail = mll_createNode(-1,NULL);
	ret->size = 0;

	return ret;
}

void ldq_queuePrinter(myQueue_p queue){

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

void ldq_addFront(myQueue_p queue, int value){

	assert(queue);
	assert(queue->root);
	assert(queue->tail);

	mll_insertNth2(&(queue->root->next),&(queue->tail->next),0,value);
	++(queue->size);
}

void ldq_addLast(myQueue_p queue, int value){

	assert(queue);
	assert(queue->root);
	assert(queue->tail);

	mll_insertNth2(&(queue->root->next),&(queue->tail->next),queue->size,value);
	++(queue->size);
}

int ldq_removeFront(myQueue_p queue){

	assert(queue);
	assert(queue->root);
	assert(queue->tail);

	if(ldq_queueIsEmpty(queue)){
		printf("Queue is Empty!\n");
		return -1;
	}	

	myNode* node = queue->root->next;
	queue->root->next = node->next;
	int ret = node->data;

	if(--(queue->size) == 0){
		queue->tail->next = NULL;
	}
	node->next = NULL;
	free(node);

	return ret;
}

int ldq_removeLast(myQueue_p queue){

	assert(queue);
	assert(queue->root);
	assert(queue->tail);

	if(ldq_queueIsEmpty(queue)){
		printf("Queue is Empty!\n");
		return -1;
	}

	myNode* ptr=queue->root->next;
	myNode* pre=NULL;
	myNode* endNode = queue->tail->next;

	while(ptr){

		if(ptr == endNode){
			break;
		}
		pre = ptr;
		ptr = ptr->next;
	}

	if(pre){
		pre->next = NULL;
	}
	queue->tail->next = pre;
	if(--(queue->size) == 0){
		queue->root->next = NULL;
	}

	int ret = ptr->data;
	ptr->next = NULL;
	free(ptr);

	return ret;
}

int ldq_queueGetFront(myQueue_p queue){

	assert(queue);
	assert(queue->root);

	if(ldq_queueIsEmpty(queue)){
		printf("Queue is Empty!\n");
		return -1;
	}

	return queue->root->next->data;	
}

int ldq_queueGetLast(myQueue_p queue){

	assert(queue);
	assert(queue->root);

	if(ldq_queueIsEmpty(queue)){
		printf("Queue is Empty!\n");
		return -1;
	}

	return queue->tail->next->data;	
}

int ldq_queueGetSize(myQueue_p queue){

	assert(queue);

	return queue->size;
}


bool ldq_queueIsEmpty(myQueue_p queue){

	assert(queue);

	return queue->size == 0;
}

void ldq_freeQueue(myQueue_p* queue){

	assert(queue);

	mll_deleteList(&((*queue)->root));
	(*queue)->tail->next = NULL;
	free((*queue)->tail);
	free(*queue);
	*queue = NULL;
}

#endif /* DEQUEUE_BASE == __DYNAMIC_ARRAY */