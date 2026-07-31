/*34. Find First and Last Position of Element in Sorted Array
Solved
        Medium
Topics
premium lock icon
        Companies
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.


Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]
 */

/*
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstPos = findPosition(nums, target, true);  // \U0001f50d Pehli baar target kaha mila
        int lastPos = findPosition(nums, target, false);  // \U0001f50d Aakhri baar target kaha mila
        return new int[]{firstPos, lastPos}; // ✅ Java me array return karne ka sahi tarika
    }

    public int findPosition(int[] nums, int target, boolean firstPosition) {
        int position = -1; // Agar nahi mila toh -1 return karna hai
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // \U0001f9ee Mid index nikalna (Binary Search formula)

            if (nums[mid] == target) {
                position = mid; // ✅ Target mil gaya, position update karo

                if (firstPosition) {
                    right = mid - 1; // \U0001f50d Pehli baar ke liye left side search karo
                } else {
                    left = mid + 1;  // \U0001f50d Last baar ke liye right side search karo
                }
            }
            else if (nums[mid] < target) {
                left = mid + 1; // \U0001f53c Target bada hai, right side jao
            }
            else {
                right = mid - 1; // \U0001f53d Target chhota hai, left side jao
            }
        }
        return position; // \U0001f51a Loop ke baad jo position mila, wahi return karna hai
    }
}
 */



/*
33. Search in Rotated Sorted Array
Solved
Medium
Topics
premium lock icon
Companies
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104



 class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;  // \U0001f525 Middle element nikal rahe hain
            if (nums[mid] == target) return mid;  // \U0001f3af Target mil gaya? Return index
            // ✅ Check kaun sa part sorted hai
            if (nums[low] <= nums[mid]) {  // \U0001f539 Left part sorted hai
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;  // \U0001f3af Target left part me hai
                } else {
                    low = mid + 1;  // \U0001f3af Target right part me hoga
                }
            } else {  // \U0001f539 Right part sorted hai
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;  // \U0001f3af Target right part me hai
                } else {
                    high = mid - 1;  // \U0001f3af Target left part me hoga
                }
            }
        }
        return -1; // ❌ Target nahi mila
    }
}


 */


81. Search in Rotated Sorted Array II



There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.



        Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false


Constraints:

        1 <= nums.length <= 5000
        -104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104


Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?

class Solution {
    public boolean search(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(nums[mid]==target) return true;
            //edge cases:
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left = left + 1;
                right = right - 1;
                continue;
            }
            //if half left part is sorted.
            if(nums[left]<=nums[mid]){
                if(nums[left]<=target&&target<=nums[mid]){
                    right=mid-1;
                }
                else{
                    left=mid+1;
                }
            }
            else{// if right half part is sorted.
                if(nums[mid]<=target&&target<=nums[right]){
                    left=mid+1;
                }
                else{
                    right=mid-1;
                }
            }

        }
        return false;
    }
}