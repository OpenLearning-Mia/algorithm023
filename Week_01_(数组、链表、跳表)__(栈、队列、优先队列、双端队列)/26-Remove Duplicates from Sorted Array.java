/*
* 解法：双指针
* 时间复杂度：O(n)
* 空间复杂度：O(1)
*
*/ 

class Solution {
public int removeDuplicates(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    int p = 0;
    int q = 1;
    while(q < nums.length){
        if(nums[p] != nums[q]){
            //当 q - p > 1 时，才进行复制 
            if(q - p > 1){
                nums[p + 1] = nums[q];
            }
            p++;
        }
        q++;
    }
    return p + 1;
}
}





