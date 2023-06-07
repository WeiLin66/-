#include "bsearch.h"

int binarySearch(int* nums, int target, int left, int right){

	while(left <= right){
		int mid = left + (right - left) / 2;
		if(target == nums[mid]){
			return mid;
		}else if(target < nums[mid]){
			right = mid - 1;
		}else{
			left = mid + 1;
		}
	}

	return -1;
}

int upper(int* nums, int target, int left, int right){
    
    while(left <= right){
        int mid = left + (right - left) / 2;
        if(target >= nums[mid]){
            left = mid + 1;
        }else{
            right = mid - 1;
        }
    }
    
    return left;
}

int lower(int* nums, int target, int left, int right){
    
    while(left <= right){
        int mid = left + (right - left) / 2;
        if(target <= nums[mid]){
            right = mid - 1;
        }else{
            left = mid + 1;
        }
    }
    
    return right;
}

int upper_lower(int* nums, int target, int left, int right){

    while(left <= right){
        int mid = left + (right - left) / 2;
        if(target >= nums[mid]){
            left = mid + 1;
        }else{
            right = mid - 1;
        }
    }
    
    return right;
}

int upper_ceil(int* nums, int target, int left, int right){

    int u = upper(nums,target,left,right);
    
    return u > 0 && nums[u-1] == target ? u-1 : u;
}

int lower_floor(int* nums, int target, int left, int right){
    
    int l = lower(nums,target,left,right);
    
    return l < right && nums[l+1] == target ? l+1 : l;
}

int lower_ceil(int* nums, int target, int left, int right){
    
    while(left <= right){
        int mid = left + (right - left) / 2;
        if(target <= nums[mid]){
            right = mid - 1;
        }else{
            left = mid + 1;
        }
    }
    
    return left;
}