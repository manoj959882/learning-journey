/*
48. Rotate Image
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
//Transpose the matrix → Matrix ka row & column interchange kar do.
        for(int i=0;i<n;i++){//
            for(int j=i;j<n;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
       // Reverse every row → Har row ko reverse kar do.
        for(int i=0;i<n;i++){
            for(int j=0;j<n/2;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[i][n-j-1];
                matrix[i][n-j-1]=temp;
            }
        }
    }
}



agar Anti Clock wise bolta tab

Step 1: Transpose the matrix

Step 2: Reverse each row

Anti clock wise ka step alag hai bas code same hai donor ka  niche hai

Step 1: Reverse each row

Step 2:Transpose the matrix


class Solution {
    public void rotateAntiClockwise(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Reverse each row
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }

        // Step 2: Transpose the matrix
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
 */



/*
73. Set Matrix Zeroes
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.
A straightforward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

 */


public class HelloDSA {
    public static void main(String[] args) {

    }
        public void setZeroes(int[][] matrix) {
            int row=matrix.length,col=matrix[0].length;
            boolean[] zeroRows=new boolean[row];
            boolean[] zeroCol=new boolean[col];

            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(matrix[i][j]==0){
                        zeroRows[i]=true;
                        zeroCol[j]=true;
                    }
                }
            }
            //mark row as 0
            for(int i=0;i<row;i++){
                if(zeroRows[i]){
                    for(int j=0;j<col;j++){
                        matrix[i][j]=0;
                    }
                }
            }
            //mark col as 0;
            for(int j=0;j<col;j++){
                if(zeroCol[j]){
                    for(int i=0;i<row;i++){
                        matrix[i][j]=0;
                    }
                }
            }
    }
}

/*
54. Spiral Matrix
Given an m x n matrix, return all elements of the matrix in spiral order.
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
         int top=0,left=0,right=matrix[0].length-1,bottom=matrix.length-1;
        List<Integer> ans=new ArrayList<>();
        while (top<=bottom&&left<=right){//setting boundary to execute our code.
            for (int i=left;i<=right;i++){
                ans.add(matrix[top][i]);//top liya hun Qki top will not be changed as row will be same here
            }
            top++;//increase bcoz we will now go for second row.
            for(int i=top;i<=bottom;i++){//top se bottom
                ans.add(matrix[i][right]);
            }
            right--;//decrease QKi 9 tak cover kar gaye hai ab 8 se number suru hoga.
            if(top<=bottom){//right se left ki taraf
                for(int i=right;i>=left;i--){
                    ans.add(matrix[bottom][i]);
                }
            }
            bottom--;
            if(left<=right){//bottom se top ki taraf
                for(int i=bottom;i>=top;i--){
                    ans.add(matrix[i][left]);
                }
            }
            left++;
        }
        return ans;
    }
}
 */