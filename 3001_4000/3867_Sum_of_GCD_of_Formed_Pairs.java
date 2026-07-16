/**
 * 3867_Sum_of_GCD_of_Formed_Pairs
 */
public class 3867_Sum_of_GCD_of_Formed_Pairs {


    public long gcdSum(int[] nums) {

        int n = nums.length;

        int[] prefixGcd = new int[n];

        int max = 0;

        // Construct prefixGcd
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            prefixGcd[i] = gcd(nums[i], max);
        }

        // Sort the array
        Arrays.sort(prefixGcd);

        long sum = 0;

        int left = 0;
        int right = n - 1;

        // Pair smallest with largest
        while (left < right) {
            sum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }

        return sum;
    }

    // GCD Function
    public int gcd(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}