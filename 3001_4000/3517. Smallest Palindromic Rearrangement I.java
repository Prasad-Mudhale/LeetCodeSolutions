public import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int halfLen = n / 2;
        
        // Extract the first half of characters
        char[] half = s.substring(0, halfLen).toCharArray();
        
        // Sort the first half to get the lexicographically smallest arrangement
        Arrays.sort(half);
        
        String left = new String(half);
        
        // Middle character exists if length is odd
        String mid = (n % 2 != 0) ? String.valueOf(s.charAt(halfLen)) : "";
        
        // Reverse the sorted first half to get the right side
        String right = new StringBuilder(left).reverse().toString();
        
        return left + mid + right;
    }
} 3517. Smallest Palindromic Rearrangement I {
    
}
