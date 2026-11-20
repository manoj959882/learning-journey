Merge Sort: aur merge sort based top 10 problem

Divide: Bade array ko chhote-chhote arrays mein divide karna.
Conquer: Chhote arrays ko sort karna.
Merge: Sorted arrays ko combine karke ek sorted array banana.


Requirement:
1. Array ko middle se divide karo.
2. Jab tak single element na bach jaye, divide karte raho.
3. Ab adjacent single elements ko compare karo.
4. Chhote element ko pehle rakho.
5. Sorted parts ko dobara merge karo.
6. Final sorted array return karo.

# Merge Sort in Java – Complete Beginner Tutorial

## Part 1: Introduction (0:00–1:30)

Hello everyone!

Welcome back to the channel.

Today, we are going to learn one of the most important sorting algorithms in Data Structures and Algorithms: **Merge Sort**.

In this tutorial, we will not just learn how to write the code. We will understand how to think about the problem, how to break it into smaller parts, and how to convert our thinking into Java code.

By the end of this video, you will understand:

1. What Merge Sort is.
2. How the Divide and Conquer approach works.
3. How to divide an array into smaller parts.
4. How to merge two sorted arrays.
5. How to implement Merge Sort in Java.
6. Its time and space complexity.

So, let's get started!

## Part 2: Understand the Problem (1:30–3:00)

First, let's look at our input array.

Our array is:

`[7, 4, 1, 5, 3, 8, 2, 6]`

Our task is to sort this array in ascending order.

That means the smallest element should come first and the largest element should come last.

The expected output is:

`[1, 2, 3, 4, 5, 6, 7, 8]`

Now, the question is:

How can we sort this array efficiently?

Instead of trying to sort all the elements at once, we will divide the array into smaller parts.

This approach is called Divide and Conquer.

## Part 3: What is Divide and Conquer? (3:00–5:00)

Divide and Conquer has three main steps.

First: Divide.

We divide a big problem into smaller subproblems.

Second: Conquer.

We solve those smaller subproblems.

Third: Combine.

We combine the solutions of smaller problems to get the final solution.

Merge Sort follows exactly this approach.

Let's understand it visually.

[Show the division diagram.]

Our original array is:

`[7, 4, 1, 5, 3, 8, 2, 6]`

We divide it into two halves:

Left half: `[7, 4, 1, 5]`

Right half: `[3, 8, 2, 6]`

But we don't stop here.

We continue dividing each half until every part contains only one element.

Why?

Because an array containing just one element is already sorted.

This is a very important observation.

## Part 4: Divide the Array (5:00–8:00)

Let's divide the array step by step.

First:

`[7, 4, 1, 5, 3, 8, 2, 6]`

Becomes:

`[7, 4, 1, 5]` and `[3, 8, 2, 6]`

Next:

`[7, 4]`, `[1, 5]`, `[3, 8]`, `[2, 6]`

Next:

`[7]`, `[4]`, `[1]`, `[5]`, `[3]`, `[8]`, `[2]`, `[6]`

Now every part contains one element.

Our dividing process is complete.

Notice that we have not sorted anything yet.

We have only divided the array.

Now comes the most interesting part: merging.

## Part 5: How Does Merging Work? (8:00–13:00)

Let's understand merging with a very simple example.

Suppose we have two sorted arrays:

Left: `[7]`

Right: `[4]`

We compare both elements.

Which one is smaller?

Four is smaller than seven.

So we place four first, followed by seven.

Our result becomes:

`[4, 7]`

Now let's take another example.

Left: `[1]`

Right: `[5]`

One is smaller than five.

So our result is:

`[1, 5]`

Similarly, we merge the remaining pairs.

We get:

`[4, 7]`, `[1, 5]`, `[3, 8]`, `[2, 6]`

Now we have four sorted arrays.

Let's merge them again.

Take:

Left: `[4, 7]`

Right: `[1, 5]`

Compare four and one.

One is smaller, so take one.

Now compare four and five.

Four is smaller, so take four.

Now compare seven and five.

Five is smaller, so take five.

Seven is remaining, so take seven.

Our result is:

`[1, 4, 5, 7]`

Similarly:

`[3, 8]` and `[2, 6]`

Give us:

`[2, 3, 6, 8]`

Finally, we have two sorted arrays:

`[1, 4, 5, 7]`

`[2, 3, 6, 8]`

We compare their elements and merge them.

The final sorted array is:

`[1, 2, 3, 4, 5, 6, 7, 8]`

And that's how Merge Sort works!

## Part 6: Convert Our Thinking into Java Code (13:00–19:00)

Now let's convert our understanding into Java code.

First, we need a main method.

We create our array and call the mergeSort method.

Next, ask yourself:

How do we divide the array?

We need three things:

A starting index, an ending index, and a middle index.

We calculate the middle index using:

`mid = start + (end - start) / 2`

Now, what happens when only one element remains?

We should stop dividing.

This is called the base case.

In Java:

`if (start >= end) return;`

Now we need to sort both halves.

For the left half:

`mergeSort(arr, start, mid);`

For the right half:

`mergeSort(arr, mid + 1, end);`

After both halves are sorted, we call:

`merge(arr, start, mid, end);`

Now let's understand the merge method.

We need two pointers.

The first pointer starts at the beginning of the left half.

The second pointer starts at the beginning of the right half.

So:

`int left = start;`

`int right = mid + 1;`

We also need a temporary array to store the sorted result.

Now, while both halves have elements, we compare their current elements.

If the left element is smaller, we copy it into the temporary array.

Otherwise, we copy the right element.

We continue until one half becomes empty.

But what if some elements are still remaining?

We copy all the remaining elements from the left or right half.

Finally, we copy the temporary array back into the original array.

Why do we use `start + l`?

Because we are updating only the current section of the original array, not necessarily the entire array from index zero.

And that's it!

We have successfully implemented Merge Sort.

## Part 7: Time and Space Complexity (19:00–21:00)

Now let's understand the complexity.

Merge Sort divides the array into two halves at every level.

The number of division levels is approximately log n.

At each level, we process all n elements during merging.

Therefore:

Time Complexity: O(n log n)

Auxiliary Space Complexity: O(n)

Merge Sort is also a stable sorting algorithm when equal elements are handled in their original order.

## Part 8: Conclusion (21:00–22:00)

Let's quickly revise what we learned today.

Merge Sort follows Divide and Conquer.

First, we divide the array into smaller parts.

Then, we consider single elements as sorted.

Finally, we merge the sorted parts to create the final sorted array.

Remember these three words:

Divide.

Conquer.

Merge.

Thank you for watching!

If you found this tutorial helpful, please like the video, subscribe to the channel, and share it with your friends.

See you in the next video!



Problem1:  88. Merge Sorted Array

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

  

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1;
        int j=n-1;
        int k=m+n-1;
        while(i>=0 && j>=0) {

            if(nums1[i]>nums2[j]) {
                nums1[k--]=nums1[i--];
            }

            else {
                nums1[k--]=nums2[j--];
            }
        }
        while(j>=0) {//Ab yeh while loop kyun lagaya?
            nums1[k--]=nums2[j--];
        }
    }
/*
Ab 3 pointers ki zaroorat kyun padi?
Ab humne decide kar liya ki right se fill karenge.

Toh humein kya chahiye?

nums1 ka last actual element check karna hai.

nums2 ka last element check karna hai.

Dono mein jo bada ho, use last empty position par rakhna hai.
 
Ab second while loop kyun lagaya?
 Maan lo nums1 mein saare elements chhote hain:
nums1 = [1, 2, 3, 0, 0, 0]
nums2 = [4, 5, 6]
 Comparison karte karte i = -1 ho jayega.

Lekin nums2 mein abhi bhi [4, 5, 6] bache hain.

Toh unhe nums1 mein copy karna padega.

Isliye second while loop lagaya hai.

Ek interesting baat: while(i >= 0) lagane ki zaroorat nahi hai, kyunki nums1 ke remaining elements already correct position par hote hain.
 Sabse important: Right se left kyun ja rahe hain?
Socho agar tum left se right merge karte:
nums1 = [1, 4, 5, 0, 0, 0]
 Agar tum index 1 par 2 daal doge, toh existing 4 overwrite ho sakta hai.
Lekin right se left jaane par hum pehle empty spaces fill karte hain. Isliye existing elements safe rehte hain.
Bas ye 3 baatein yaad rakhna:
i aur j se dono arrays ke last elements compare karo.
Jo bada ho, use k position par daalo.
Pointer ko peeche move karte jao.



 
Problem 2: leetcode 148. Sort List

  Given the head of a linked list, return the list after sorting it in ascending order.
 Input: head = [4,2,1,3]
Output: [1,2,3,4]


 Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
         if (head == null || head.next == null) {//base case jaha pe ruk jaye matlab single element par.
            return head;
        }
        ListNode slow=head;//pointer for mid
        ListNode fast=head;
        while (fast.next != null && fast.next.next != null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        //now making the left half and right half.
        //second half is
        ListNode temp=slow.next;
        slow.next=null;//made head as first half.
        ListNode left=sortList(head);
       ListNode right =sortList(temp);
        return merge(left,right);

    }
    public static ListNode merge(ListNode left,ListNode right){
        //ek dummy Node banaya maine kyoki mujhe temp Node me sort karke element ko rakhna hai sort karke.
        ListNode head=new ListNode(0);
        ListNode temp=head;
        while(left!=null&&right!=null){
            if (left.val<right.val){
                temp.next=left;
                left=left.next;
            }
            else{
                temp.next=right;
                right=right.next;
            }
            temp=temp.next;
        }
        while (left!=null){
            temp.next=left;
            left=left.next;
            temp=temp.next;
        }
        while (right!=null){
            temp.next=right;
            right=right.next;
            temp=temp.next;
        }
        return head.next;
    }

}



  


