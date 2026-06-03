Divisors of a Number


You are given an integer n. You need to find all the divisors of n. Return all the divisors of n as an array or list in a sorted order.

A number which completely divides another number is called it's divisor.
Example 1
Input: n = 6
Output = [1, 2, 3, 6]
Explanation: The divisors of 6 are 1, 2, 3, 6.
Example 2

Aapko ek integer n diya gaya hai. Aapko n ke saare divisors dhundne hain aur unhe sorted order mein return karna hai.
Divisor kya hota hai?
Jo number n ko poori tarah se divide kar de (remainder 0 ho), wahi divisor hota hai.
Brute Force Approach:

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        
        // Step 1: 1 se lekar n tak saare numbers check karo
        for (int i = 1; i <= n; i++) {
            // Step 2: Agar i, n ko completely divide kar raha hai
            if (n % i == 0) {
                divisors.add(i);  // Divisor mil gaya
            }
        }
        
        // Step 3: Sorted list return karo (already sorted because loop 1 to n)
        return divisors;
    }
}


Time Complexity: O(n)
Space Complexity: O(number of divisors)


 Optimized Approach (Better)
Math ka magic:
Divisors pairs mein aate hain!
• Agar i divisor hai, toh n/i bhi divisor hai
• Example: n = 36
• i = 2 → divisor, 36/2 = 18 → bhi divisor
• i = 3 → divisor, 36/3 = 12 → bhi divisor
• i = 4 → divisor, 36/4 = 9 → bhi divisor
• i = 6 → divisor, 36/6 = 6 → same (special case)
Sirf √n tak check karna hai!

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<Integer> findDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        
        // Step 1: Sirf 1 se lekar √n tak loop chalao
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                // Step 2: Agar i divisor hai
                divisors.add(i);
                
                // Step 3: Dusra divisor (n/i) bhi add karo
                // Lekin agar i*i == n hai toh same divisor ko duplicate mat add karo
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }
        
        // Step 4: Sorting karni padegi kyunki pairs unordered aate hain
        Collections.sort(divisors);
        
        return divisors;
    }
}


Time Complexity: O(√n) + O(k log k) where k = number of divisors
Space Complexity: O(k)

After sorting: [1, 2, 3, 4, 6, 9, 12, 18, 36] ✅




Check if a number is Palindrome or Not

Given an integer x, return true if x is a palindrome, and false otherwise.
 
Example 1:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
Example 2:
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 

Constraints:
	• -231 <= x <= 231 - 1


class Solution {
    public boolean isPalindrome(int n) {
        if(n<0) return false;
        int originalNumber=n;
        int reverseNumber=0;
        while(n>0){
            reverseNumber=10*reverseNumber+n%10;
            n=n/10;
        }
        return originalNumber==reverseNumber;
    }
}



Find GCD of two numbers
Example 1:
Input: N1 = 9, N2 = 12
Output: 3
Explanation:
Factors of 9: 1, 3, 9
Factors of 12: 1, 2, 3, 4, 6, 12
Common Factors: 1, 3
Greatest common factor: 3 (GCD)
Example 2:
Input: N1 = 20, N2 = 15
Output: 5
Explanation:
Factors of 20: 1, 2, 4, 5, 10, 20
Factors of 15: 1, 3, 5, 15
Common Factors: 1, 5
Greatest common factor: 5 (GCD)
    

Brute Force Approach


Intuition:
The GCD of two numbers is the largest number that divides both of them without leaving a remainder. We iterate through all numbers from 1 up to the minimum of the two input numbers, checking if each number is a common factor of both input numbers.
If a number is a common factor, we update our gcd variable to that number. This process continues until we have iterated through all possible common factors. Finally, we return the gcd variable, which will hold the greatest common divisor of the two input numbers.
Algorithm:
Step 1: Initialise a variable gcd to 1. This variable will store the greatest common divisor of the input numbers n1 and n2.
Step 2: Iterate from 1 to the minimum of n1 and n2.
	1. We start from 1 because the GCD of any two numbers is at least 1, and it cannot be greater than the smaller of the two numbers.
Step 3: At each iteration, if i is a common factor of both n1 and n2 update the gcd variable to i. We keep updating gcd as long as we find common factors.
Step 4: After the iteration, the gcd variable will store the greatest common divisor of n1 and n2. Return this value as the output of the function.


public class Main {
    public static int findGcd(int n1, int n2) {
        // Initialize gcd to 1
        int gcd = 1;

        // Iterate from 1 up to
        // the minimum of n1 and n2
        for (int i = 1; i <= Math.min(n1, n2); i++) {
            // Check if i is a common
            // factor of both n1 and n2
            if (n1 % i == 0 && n2 % i == 0) {
                // Update gcd to the
                // current common factor i
                gcd = i;
            }
        }

        // Return the greatest
        // common divisor (gcd)
        return gcd;
    }

    public static void main(String[] args) {
        int n1 = 20, n2 = 15;

        // Find the GCD of n1 and n2
        int gcd = findGcd(n1, n2);

        System.out.println("GCD of " + n1 + " and " + n2 + " is: " + gcd);
    }
}
                                


Complexity: 
Time Complexity: O(min(N1, N2)) where N1 and N2 is the input number. The algorithm iterates from 1 to the minimum of N1 and N2 and each iteration checks whether both the numbers are divisible by the current number (constant time operations).
Space Complexity: O(1)as the space complexity remains constant and independent of the input size. Only a fixed amount of memory is required to store the integer variables.



Optimal Approach



Euclidean Algorithm:
The Euclidean Algorithm is a method for finding the greatest common divisor (GCD)
of two numbers. It operates on the principle that the GCD of two numbers remains
the same even if the smaller number is subtracted from the larger number.
To find the GCD of n1 and n2 where n1 > n2:
1. Repeatedly subtract the smaller number from the larger number until one of them becomes 0.
2. Once one becomes 0, the other is the GCD of the original numbers.
Example:
n1 = 20, n2 = 15
gcd(20, 15) = gcd(20 - 15, 15) = gcd(5, 15)
gcd(5, 15)  = gcd(15 - 5, 5)  = gcd(10, 5)
gcd(10, 5)  = gcd(10 - 5, 5) = gcd(5, 5)
gcd(5, 5)   = gcd(5 - 5, 5)  = gcd(0, 5)
Hence, return 5 as the GCD.


public class Main {
    // Continue loop as long as both
    // a and b are greater than 0
    public static int findGcd(int a, int b) {
        while(a > 0 && b > 0) {
            // If a is greater than b,
            // subtract b from a and update a
            if(a > b) {
                // Update a to the remainder
                // of a divided by b
                a = a % b;
            }
            // If b is greater than or equal
            // to a, subtract a from b and update b
            else {
                // Update b to the remainder
                // of b divided by a
                b = b % a;
            }
        }
        // Check if a becomes 0,
        // if so, return b as the GCD
        if(a == 0) {
            return b;
        }
        // If a is not 0,
        // return a as the GCD
        return a;
    }

    public static void main(String[] args) {
        int n1 = 20, n2 = 15;

        // Find the GCD of n1 and n2
        int gcd = findGcd(n1, n2);

        System.out.println("GCD of " + n1 + " and " + n2 + " is: " + gcd);
    }
}
    
                                


Complexity:
Time Complexity: O(min(N1, N2)) where N1 and N2 is the input number. The algorithm iterates from the minimum of N1 and N2 to 1 and each iteration checks whether both the numbers are divisible by the current number (constant time operations).
Space Complexity: O(1) as the space complexity remains constant and independent of the input size. Only a fixed amount of memory is required to store the integer variable





Reverse Integer
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

 

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21

Constraints:

-231 <= x <= 231 - 1

class Solution {
    public int reverse(int x) {
        int rev = 0;  // Reverse number store karne ke liye
        
        while (x != 0) {
            // Step 1: Last digit nikal lo
            int digit = x % 10;
            
            // Step 2: Pehle check karo ki overflow toh nahi ho raha
            // Agar rev already INT_MAX/10 se bada hai aur ek digit aur add karte ho toh overflow ho jayega
            if (rev > Integer.MAX_VALUE / 10) {
                return 0;  // Positive overflow
            }
            if (rev < Integer.MIN_VALUE / 10) {
                return 0;  // Negative overflow
            }
            
            // Step 3: Reverse number build karo
            rev = rev * 10 + digit;
            
            // Step 4: Original number se last digit hata do
            x = x / 10;
        }
        
        return rev;
    }
}

####
A Number After a Double Reversal

Reversing an integer means to reverse all its digits.

For example, reversing 2021 gives 1202. Reversing 12300 gives 321 as the leading zeros are not retained.
Given an integer num, reverse num to get reversed1, then reverse reversed1 to get reversed2. Return true if reversed2 equals num. Otherwise return false.

Example 1:

Input: num = 526
Output: true
Explanation: Reverse num to get 625, then reverse 625 to get 526, which equals num.
Example 2:

Input: num = 1800
Output: false
Explanation: Reverse num to get 81, then reverse 81 to get 18, which does not equal num.
Example 3:

Input: num = 0
Output: true
Explanation: Reverse num to get 0, then reverse 0 to get 0, which equals num.
 
 Constraints:

0 <= num <= 106


Intuition
This problem asks us to determine if a number remains the same after being reversed twice. The key insight is that reversing a number twice should return the original number unless trailing zeros are present. Numbers with trailing zeros will lose those zeros after the first reversal (e.g., 120 → 021 → 21), so they won't match the original.

Approach
We'll use a double reversal comparison strategy:

Reversal function: Create helper to reverse digits using modulo and division
Digit extraction: Extract rightmost digit with num % 10
Build reversed: Multiply running result by 10 and add new digit
Double application: Apply reversal twice: reverse(reverse(num))
Equality check: Compare double-reversed result with original
This approach directly implements the problem statement to check the property.

Complexity
Time complexity: O(d)
Where d is the number of digits, as we process each digit twice (two reversals).

Space complexity: O(1)
We use only constant extra space for variables during reversal operations.

Code

class Solution {
    
    // This method reverses the digits of a positive integer
    // Note: This only works for POSITIVE numbers (doesn't handle negatives)
    int reverse(int num) {
        
        // Store the original number in a temporary variable
        // We'll modify 'temp' during reversal
        int temp = num;
        
        // 'rem' will store the reversed number
        // Initially it's 0
        int rem = 0;
        
        // Loop until all digits are processed
        // Condition: temp > 0 means we stop at 0
        // PROBLEM: If num is 0, loop won't run and returns 0 (correct)
        // PROBLEM: If num is negative, loop won't run at all (returns 0) - BUG!
        while (temp > 0) {
            
            // Step 1: Get last digit using modulo operator (temp % 10)
            // Step 2: Add it to reversed number after shifting left by multiplying by 10
            // Example: rem was 32, now rem = 32 * 10 + 5 = 325
            rem = rem * 10 + temp % 10;
            
            // Remove the last digit from temp by integer division by 10
            // Example: temp was 123, now temp = 123 / 10 = 12
            temp /= 10;
        }
        
        // Return the completely reversed number
        return rem;
    }

    // This method checks if reversing a number TWICE gives back the original number
    public boolean isSameAfterReversals(int num) {
        
        // Step 1: Reverse the number once -> get rev1
        // Step 2: Reverse rev1 again -> get rev2
        // Step 3: Check if rev2 equals original number 'num'
        // If yes, return true; otherwise false
        
        // Example: num = 123
        // reverse(123) = 321
        // reverse(321) = 123
        // 123 == 123 -> returns true
        
        // Example: num = 120
        // reverse(120) = 21 (trailing zero is dropped)
        // reverse(21) = 12
        // 12 != 120 -> returns false
        
        return reverse(reverse(num)) == num;
    }
}




**********Print all Divisors of a given Number   ****************
/*
Given an integer N, return all divisors of N.
A divisor of an integer N is a positive integer that divides N without leaving a remainder.
In other words, if N is divisible by another integer without any remainder, then that integer is considered a divisor of N.


We can optimise the previous approach by using the property that for any non-negative integer n, if d is a divisor of n then n/d is also a divisor of n. 
This property is symmetric about the square root of N. Thus, by traversing just the first half we can avoid redundant iteration and computations improving the efficiency of the algorithm.

Iterate from 1 to sqrt(N) and for every divisor found, if N/divisor is distinct, add that to the list of divisors as well.
*/


import java.util.*;

class Solution {
    // Function to get all divisors
    public List<Integer> getDivisors(int N) {
        // Create a list to store divisors
        List<Integer> res = new ArrayList<>();

        // Loop from 1 to square root of N
        for (int i = 1; i * i <= N; i++) {
            // Check if i divides N
            if (N % i == 0) {
                // Add i to result
                res.add(i);

                // If N / i is different from i, add N / i too
                if (i != N / i) {
                    res.add(N / i);
                }
            }
        }

        // Return the list of divisors
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create object of Solution class
        Solution sol = new Solution();

        // Input number
        int N = 36;

        // Get divisors
        List<Integer> result = sol.getDivisors(N);

        // Print the result
        System.out.print("Divisors of " + N + ": ");
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}


Complexity Analysis
Time Complexity: O(sqrt(N)), we check for every number between 1 and sqaure root of N.
Space Complexity: O(2*sqrt(N)), extra space used for storing divisors.



*************************Check for Prime Number****************
सबसे छोटा प्राइम नंबर: 2 है。एकमात्र सम (Even) प्राइम नंबर: 2 है。 इसके अलावा सभी प्राइम नंबर विषम (Odd) होते हैं。
संख्या 1: 1 न तो एक प्राइम नंबर है और न ही भाज्य संख्या, क्योंकि इसका केवल एक ही गुणनखंड होता है
उदाहरण: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31.

public class Solution {
    public static boolean isPrime(int n) {
        // Prime numbers are greater than 1
        if (n <= 1) {
            return false;
        }
        
        // Check divisibility from 2 to sqrt(n)
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;  // Found a divisor, not prime
            }
        }
        
        return true;  // No divisors found, prime number
    }

    public static void main(String[] args) {
        // Example 1
        int n1 = 5;
        System.out.println("Is " + n1 + " prime? " + isPrime(n1));  // Output: true
        
        // Example 2
        int n2 = 8;
        System.out.println("Is " + n2 + " prime? " + isPrime(n2));  // Output: false
        
        // Additional test cases
        System.out.println("Is 1 prime? " + isPrime(1));   // false
        System.out.println("Is 2 prime? " + isPrime(2));   // true
        System.out.println("Is 17 prime? " + isPrime(17)); // true
        System.out.println("Is 25 prime? " + isPrime(25)); // false
    }
}

Note: Kyon sirf √n tak check karna kaafi hai?

Maan lo `n = a × b`

Agar dono factors `sqrt(n)` se bade hon:

```text
a > sqrt(n)
b > sqrt(n)
```

To:

```text
a × b > n
```

Lekin `a × b = n` hona chahiye. Ye possible nahi hai.

Isliye agar kisi number ka factor hai, to kam se kam **ek factor `sqrt(n)` se chhota ya barabar** zarur hoga.

Isliye prime check karte waqt:

```java
for(int i = 2; i <= Math.sqrt(n); i++)
```

tak check karna hi kaafi hai.

Example:

* `36 = 4 × 9`
* `sqrt(36) = 6`

Yahan 4 factor hai jo 6 se chhota hai, isliye 9 tak check karne ki zarurat nahi padti.

**Interview answer (1 line):**

> Factors pair me aate hain, aur kisi composite number ka kam se kam ek factor `√n` se chhota ya barabar hota hai, isliye `2` se `√n` tak check karna sufficient hai.




************ find the meadian in 2d array ************

import java.util.Arrays;

public class Median2DArray {
    
    public static double findMedian(int[][] matrix) {
        // Step 1: Count total elements
        int rows = matrix.length;
        int cols = matrix[0].length;
        int totalElements = rows * cols;
        
        // Step 2: Copy all elements to 1D array
        int[] flatArray = new int[totalElements];
        int index = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatArray[index++] = matrix[i][j];
            }
        }
        
        // Step 3: Sort the 1D array
        Arrays.sort(flatArray);
        
        // Step 4: Find median
        if (totalElements % 2 == 1) {
            // Odd number of elements
            return flatArray[totalElements / 2];
        } else {
            // Even number of elements
            int mid1 = flatArray[totalElements / 2 - 1];
            int mid2 = flatArray[totalElements / 2];
            return (mid1 + mid2) / 2.0;
        }
    }
    
    public static void main(String[] args) {
        // Example 2D array
        int[][] arr = {
            {5, 2, 8},
            {1, 9, 3},
            {7, 4, 6}
        };
        
        double median = findMedian(arr);
        System.out.println("Median is: " + median);
        
        // Another example
        int[][] arr2 = {
            {1, 3, 5},
            {2, 4, 6}
        };
        
        double median2 = findMedian(arr2);
        System.out.println("Median is: " + median2);
    }
}
