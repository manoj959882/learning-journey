/*162. Find Peak Element
https://leetcode.com/problems/find-peak-element/description/
A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.



Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.

*/


class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // upward slope -> peak right side mein hai
                left = mid + 1;
            } else {
                // downward slope (ya peak khud mid hai) -> peak mid ya left mein hai
                right = mid;
            }
        }

        // jab left == right, wahi peak element hai
        return left;
    }
}


/*
540. Single Element in a Sorted Array
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.



Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10


Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105



https://leetcode.com/problems/single-element-in-a-sorted-array/description/

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Step 1: mid ko hamesha EVEN banao
            // Kyunki humari saari logic even index ke pattern pe based hai
            if (mid % 2 == 1) {
                mid--;  // odd hai to ek peeche kar do, ab even ho jaayega
            }

            // Step 2: check karo pattern normal hai ya shift ho gaya
            if (nums[mid] == nums[mid + 1]) {
                // Pattern normal hai -> single element right side mein hai
                // mid aur mid+1 dono discard, kyunki ye pura pair hai
                left = mid + 2;
            } else {
                // Pattern shift ho gaya -> single element mid ya left mein hai
                // mid ko discard nahi karte, kyunki khud answer ho sakta hai
                right = mid;
            }
        }

        // Jab left == right, wahi single element hai
        return nums[left];
    }
}


 */