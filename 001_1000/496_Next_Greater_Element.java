import java.util.HashMap;
import java.util.Stack;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
       
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> st = new Stack<>();

   
        for (int i = 0; i < nums2.length; i++) {
            while (!st.isEmpty() && nums2[i] > nums2[st.peek()]) {
                map.put(nums2[st.pop()], nums2[i]);
            }
            st.push(i);
        }

   
        while (!st.isEmpty()) {
            map.put(nums2[st.pop()], -1);
        }

   
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}