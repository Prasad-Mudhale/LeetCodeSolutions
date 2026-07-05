package 001_100;

public class 11_Container_With_Most_Water {
 
    public int maxArea(int[] height) {
        int lefth = 0;
        int righth = height.length-1;

        int maxArea = 0;

        while(lefth<=righth){

            int area = Math.min(height[lefth], height[righth]) * (righth-lefth);

             maxArea = Math.max(area, maxArea);

            if(height[lefth]<height[righth]){
                lefth++;
            }else{
                righth--;
            }
            // int len = (righth-lefth);
        }

        return maxArea;

    }
}