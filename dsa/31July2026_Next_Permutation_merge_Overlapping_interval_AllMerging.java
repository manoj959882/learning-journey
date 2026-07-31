//Next Permutation :
class Solution {
    public void nextPermutation(int[] nums) {
        int breakPoint=-1;
        int n=nums.length;
        for(int i=n-2;i>=0;i--){// Step 1: Find the break point:
            if(nums[i]<nums[i+1]){
                breakPoint=i;//index i is the break point
                break;
            }
        }
        if(breakPoint==-1){//If break point does not exist:
            reverseArray(nums,0,n-1);//reverse the whole array
            return;
        }
//Find the next greater element and swap it with arr[ind]
        for(int i=n-1;i>breakPoint;i--){
            if(nums[i]>nums[breakPoint]){
                swap(nums,i,breakPoint);
                break;
            }
        }
        //reverse the right half:
        reverseArray(nums,breakPoint+1,n-1);
    }
    public void swap(int []nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    public void reverseArray (int []nums,int i,int j){
        while(i<j){
            swap(nums,i++,j--);
        }
    }

    Maan lijiye aapko ek number diya gaya hai, jaise `[1, 3, 5, 4, 2]`. Aapko isse thoda bada number banana hai, lekin **sabse chhota** possible bada number.

*   **Left side ki digits** (jaise '1' aur '3') number ki value par sabse zyada asar daalti hain. Isliye, hum right side se kuch badal kar dekhte hain taaki number bahut bada na ho jaye.
            *   Humein ek aisi jagah (breakpoint) dhundhni hai, jahaan right side se aate hue, pehli baar koi digit apne right wali digit se **chhoti** ho. Is example mein, 3 se right mein 5, 4, 2 hain. 3, 5 se chhota hai, toh 3 hi breakpoint hai (index 1). Iska matlab hai ki `[5,4,2]` wala hissa apni maximum arrangement (descending order) mein hai.
            *   Ab, is 3 ko badal kar kuch **aur bada** karna hai, lekin sabse chhota bada. Right side mein 3 se bade numbers hain: 5 aur 4. Inmein sabse chhota bada number hai **4**.
            *   3 aur 4 ko swap karo. Ab array hai `[1,4,5,3,2]`. Abhi bhi number ka left part (`1,4`) apni sahi jagah par hai.
            *   Ab jo right side bacha hai (`5,3,2`), use **chhota se chhota** karna hai taaki yeh 'next' permutation ho. Isko chhota karne ka sabse fast tarika hai isse **reverse** kar dena. Kyunki `5,3,2` descending order mein hai, isko reverse karke `2,3,5` ascending order mein la do.
            *   Final answer: `[1,4,2,3,5]`.

    Yeh sochne ka tarika aapke steps se bilkul milta hai, bas shabdon mein alag hai.

            ### Aapke Code ka Visualization

    Aapka code isi logic ko perfectly follow karta hai. Main isko ek chart ke through samjha raha hoon taaki har step clear ho jaye:

            **Example:** `nums = [1, 3, 5, 4, 2]`

            | Step | Action | Code Line (Approx.) | Array State | Explanation |
            | :--- | :--- | :--- | :--- | :--- |
            | 1 | **Breakpoint dhundho** | `for(int i=n-2; i>=0; i--)` | `[1, 3, 5, 4, 2]` | Right se chalo. `2<4?` No. `4<5?` No. `5<3?` No. `3<5?` **Haan!** Toh `breakPoint = 1` (value 3). |
            | 2 | **Check karo ki koi breakpoint hai bhi** | `if(breakPoint == -1)` | - | Agar nahi hota (jaise `[5,4,3,2,1]`), toh poora array reverse karo. |
            | 3 | **Swap karne ke liye element dhundho** | `for(int i=n-1; i>breakPoint; i--)` | `[1, 3, 5, 4, 2]` | Right se chalo. `2>3?` No. `4>3?` **Haan!** Toh `i=3` (value 4) breakpoint se swap ke liye best hai. |
            | 4 | **Swap karo** | `swap(nums, breakPoint, i)` | `[1, 4, 5, 3, 2]` | 3 aur 4 ki position badal do. |
            | 5 | **Right part ko reverse karo** | `reverseArray(nums, breakPoint+1, n-1)` | `[1, 4, 2, 3, 5]` | Breakpoint ke baad wala hissa (`[5,3,2]`) reverse karke `[2,3,5]` karo. |

            ### Yaad Rakhne Layak Baatein (Key Takeaways)

1.  **Yehi Standard Hai:** Aapne jo code likha hai, woh precisely LeetCode ka accepted aur most optimized solution hai. Ismein koi kami nahi hai.
2.  **Samajhna Zaroori hai:** Algorithm ke steps yaad karne se behtar hai ki aapko yeh pata ho ki **'breakpoint' kyun** aur **'reverse' kyun** karte hain. Aapka explanation (example ke saath) isko bahut achhe se cover karta hai.
            3.  **Edge Cases:** Aapka code last permutation (jaise `[3,2,1]`) ko sambhal leta hai, jo bahut zaroori hai.

    Aapka solution aur explanation dono hi ekdum clear aur sahi hain. Isse zyada 'best' kuch nahi hai. Agar aap isko aur robust dekhna chahein, toh aap `reverseArray` function ko `while (i < j)` ki jagah `for` loop se bhi likh sakte hain, par aapka tarika perfectly readable aur efficient hai.

    Aapne problem ko sahi se samjha aur implement kiya hai. Shabash! 😊


/*
88. Merge Sorted Array
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.



Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109


Follow up: Can you come up with an algorithm that runs in O(m + n) time?


 class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer for the end of nums1
        int j = n - 1; // Pointer for the end of nums2
        int k = m + n - 1; // Pointer for the end of merged array
        // Merge the two arrays from the end to the beginning
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        // Copy any remaining elements from nums2 to nums1 (if any)
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
        /*
        int nums1Index = m - 1;
    int nums2Index = n - 1;
    int insertAt = m + n - 1;
    while (nums2Index >= 0)
        if (nums1Index >= 0 && nums1[nums1Index] >= nums2[nums2Index])
            nums1[insertAt--] = nums1[nums1Index--];  // insert value from a, then shift indexes for a and insert
        else  // b < a
            nums1[insertAt--] = nums2[nums2Index--];
    /*2.

    int i = m - 1, j = n - 1, k = m + n - 1;
        while(i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while(j >= 0) {
            nums1[k--] = nums2[j--];
        }

       /*3. 1ms faster
        for(int i=m;i<nums1.length;++i){
            nums1[i]=nums2[i-m];
        }
        Arrays.sort(nums1);
        */
        /*
        for (int j = 0, i = m; j < n; j++) {
            nums1[i] = nums2[j];
            i++;
        }
        Arrays.sort(nums1);
        */
}
}


 */

/*
56. Merge Intervals
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
Example 3:

Input: intervals = [[4,7],[1,4]]
Output: [[1,7]]
Explanation: Intervals [1,4] and [4,7] are considered overlapping.


Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104


class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals; // Agar ek ya ek se kam intervals hain toh directly return kar do
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Step 1: Intervals ko unke start time ke basis pe sort karna
        List<int[]> ans = new ArrayList<>(); // Final merged intervals store karne ke liye list
        int[] currentInterval = intervals[0]; // Pehla interval pakad lo
        ans.add(currentInterval); // List me pehla interval add kar diya
        for (int[] ele : intervals) {// Step 2: Baaki intervals ko check karo aur merge karo
            int currentStart = currentInterval[0]; // Current interval ka start
            int currentEnd = currentInterval[1]; // Current interval ka end
            int nextStart = ele[0]; // Next interval ka start
            int nextEnd = ele[1]; // Next interval ka end
            if (currentEnd >= nextStart) {// Agar overlap ho raha hai toh merge karna hai
                currentInterval[1] = Math.max(currentEnd, nextEnd); // Max end update karna
            } else {// Agar overlap nahi ho raha toh naye interval ko add karna
                currentInterval = ele; // Current interval ko update karo
                ans.add(currentInterval); // List me naya interval add karna
            }
        }
        return ans.toArray(new int[ans.size()][]); // Final merged intervals ko 2D array me convert karke return karna
    }
}


 */

/*

insert interval wala problem kar lena hai leetcode se .meeting in room wala bhi
.
yeh

https://takeuforward.org/plus/dsa/problems/count-inversions?source=strivers-a2z-dsa-track


nahi ho paya hai par important hai iska video dekhna padega.
 */