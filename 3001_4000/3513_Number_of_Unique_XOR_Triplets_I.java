public class 3513_Number_of_Unique_XOR_Triplets_I {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if(n==1) return 1;
        if(n==2) return 2;

        return Integer.highestOneBit(n) << 1;
        
    }
}