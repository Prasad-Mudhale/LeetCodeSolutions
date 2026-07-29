public class 3518. Smallest Palindromic Rearrangement II {
  
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Count available characters for the first half
        int[] count = new int[26];
        char midChar = 0;
        int oddCount = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
            count[i] = freq[i] / 2;
        }

        // Validity check for palindrome
        if (oddCount > 1 || (n % 2 == 0 && oddCount > 0)) {
            return "";
        }

        int m = n / 2;
        
        // Total possible permutations for the first half
        long totalWays = calculateWays(count, k);
        if (totalWays < k) {
            return ""; // Not enough valid palindromic permutations
        }

        StringBuilder half = new StringBuilder();

        // Construct the first half greedily
        for (int i = 0; i < m; i++) {
            for (int c = 0; c < 26; c++) {
                if (count[c] > 0) {
                    count[c]--;
                    long ways = calculateWays(count, k);
                    
                    if (ways >= k) {
                        half.append((char) ('a' + c));
                        break; // Position i fixed, proceed to i + 1
                    } else {
                        k -= ways;
                        count[c]++; // Backtrack and try next character
                    }
                }
            }
        }

        // Build full palindrome
        StringBuilder result = new StringBuilder(half);
        if (n % 2 != 0) {
            result.append(midChar);
        }
        result.append(new StringBuilder(half).reverse());

        return result.toString();
    }

    // Computes unique permutations of remaining characters, capped at maxK + 1
    private long calculateWays(int[] count, long maxK) {
        long ways = 1;
        int currentLen = 0;

        for (int c = 0; c < 26; c++) {
            for (int j = 1; j <= count[c]; j++) {
                currentLen++;
                ways = ways * currentLen / j;
                if (ways > maxK) {
                    return maxK + 1; // Early exit on overflow past k
                }
            }
        }

        return ways;
    }
}
}
