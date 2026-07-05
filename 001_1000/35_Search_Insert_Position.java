package 001_100;

public class 35_Search_Insert_Position {

    public int searchInsert(int[] nums, int target) {
       int n = nums.length;
        int left = 0;
        int right = n-1;

        int mid = 0;

        for(int i=0;i<n;i++){

            mid = (left+right)/2;

            if(nums[mid]==target){
                return mid;
            }
            else if(nums[mid]<target){
               left = mid+1;
            }else{
                right=mid-1;
            }
        }

        return left;
        
    }
}