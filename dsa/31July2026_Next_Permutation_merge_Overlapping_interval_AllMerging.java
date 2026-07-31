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

insert interval wala problem kar lena hai leetcode se .meeting in room wala bhi
.
yeh

https://takeuforward.org/plus/dsa/problems/count-inversions?source=strivers-a2z-dsa-track


nahi ho paya hai par important hai iska video dekhna padega.
 */