#ifndef __BSEARCH_H_
#define __BSEARCH_H_

#include <stdio.h>
#include <stdlib.h>

int binarySearch(int* nums, int target, int left, int right);
int upper(int* nums, int target, int left, int right);
int lower(int* nums, int target, int left, int right);
int upper_lower(int* nums, int target, int left, int right);
int upper_ceil(int* nums, int target, int left, int right);
int lower_floor(int* nums, int target, int left, int right);
int lower_ceil(int* nums, int target, int left, int right);

#endif /* __BSEARCH_H_ */