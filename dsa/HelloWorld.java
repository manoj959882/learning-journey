
// Day 1 — Mera pehla DSA commit
// Topic: Array ka basic traversal

public class HelloDSA {
    public static void main(String[] args) {
        
        int[] numbers = {10, 20, 30, 40, 50};
        
        // Array traverse karo
        System.out.println("Array elements:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Index " + i + " → " + numbers[i]);
        }
        
        // Max element dhundho
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        System.out.println("Max element: " + max);
    }
}