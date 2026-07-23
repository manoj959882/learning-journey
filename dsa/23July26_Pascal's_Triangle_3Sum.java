//pascal's triangle i ka solution niche hai:

/*
Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]
*/
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans=new ArrayList<>();//result list ke liye bana diya hai
        for (int i=0;i<numRows;i++){//i ko less than numRows chalana hai kyoki 0 se start hai
            List<Integer> currentRows=new ArrayList<>();//jisme current data add karke last me result me add kar denge.
            for (int j=0;j<=i;j++){ //j ko i tak hi chalayenge to hi satisfy karega ye.
                if (j==0||j==i){
                    currentRows.add(1);//current list me first aur last me 1 add kar denge.
                }
                else {
                    List<Integer> prevRow  =new ArrayList<>(ans.get(i-1));//dhyan se yeh main result list ka previous row ka element layega jitna bhi hoga.
                    currentRows.add(prevRow.get(j-1)+prevRow.get(j));//previous element ko yaha pe add kar denge
                }
            }
            ans.add(currentRows);//result list me current list ka element add kar denge.
        }
        return ans;
    }
}





//niche pascal's triangle ii ka solution hai.
/*
Example 1:

Input: rowIndex = 3
Output: [1,3,3,1]
Example 2:

Input: rowIndex = 0
Output: [1]
Example 3:

Input: rowIndex = 1
Output: [1,1]
*/

import java.util.*;

class Solution {
    public List<Integer> getRow(int rowIndex) {
        // result list banaya jisme final answer store hoga
        List<Integer> row = new ArrayList<>();

        // Row 0 hamesha [1] se start hoti hai, isliye pehla element 1 daal diya
        row.add(1);

        // i loop chalega 1 se rowIndex tak, kyunki row 0 already ban gayi hai upar
        for (int i = 1; i <= rowIndex; i++) {

            // Har nayi row banane se pehle, list ke end mein 1 add kar do
            // kyunki har row ka last element hamesha 1 hota hai (Pascal's triangle property)
            row.add(1);

            // j loop right se left chalega (i-1 se 1 tak)
            // right se left chalne ka reason: agar left se chalte, toh row.get(j-1)
            // already update ho chuki hoti current row ki value se, jo galat hoga
            for (int j = i - 1; j > 0; j--) {

                // Current position ki value = (upar wali row ka current element) + (upar wali row ka pehle wala element)
                // row.get(j) abhi bhi purani (upar wali) row ki value hai kyunki hum right se left ja rahe hain
                // row.get(j-1) bhi abhi purani row ki value hai (update nahi hui abhi tak)
                row.set(j, row.get(j) + row.get(j - 1));
            }

            // j=0 wala element kabhi change nahi hota, wo hamesha 1 rehta hai
            // (Pascal's triangle mein har row ka first element 1 hota hai), isliye loop j>0 tak hi chalaya
        }

        // Poori ban chuki rowIndex-th row return kar do
        return row;
    }

    // Testing ke liye main method
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test case 1: rowIndex = 3
        System.out.println(sol.getRow(3)); // Expected: [1, 3, 3, 1]

        // Test case 2: rowIndex = 0
        System.out.println(sol.getRow(0)); // Expected: [1]

        // Test case 3: rowIndex = 1
        System.out.println(sol.getRow(1)); // Expected: [1, 1]

        // Test case 4: rowIndex = 4
        System.out.println(sol.getRow(4)); // Expected: [1, 4, 6, 4, 1]
    }
}

/*3 sum
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.


import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Sort the array
        Arrays.sort(nums);
        int n = nums.length;
        
        // Step 2: Fix one element, find two others
        for (int i = 0; i < n - 2; i++) {
            // Skip duplicate 'i' values
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1;
            int right = n - 1;
            int target = -nums[i]; // We need left + right = -nums[i]
            
            // Step 3: Two-pointer 2Sum
            while (left < right) {
                int sum = nums[left] + nums[right];
                
                if (sum == target) {
                    // Found a triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } 
                else if (sum < target) {
                    left++; // Need bigger sum
                } 
                else {
                    right--; // Need smaller sum
                }
            }
        }
        return result;
    }
}

*/