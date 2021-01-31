// 2021年1月23日15:39:41








































升序排列的 整数 数组nums   //单调有序 用二分查找。。






nums 在预先未知的某个点上进行了【旋转】

例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7, 0,1,2]


在数组中搜索 target（输入值）

如果数组中存在这个目标值target，则返回它的索引，否则返回 -1



// 解题思路：


搜索区域，为局部有序性

怎么去搜索？？

先确定局部有序的范围？？？

用目标值去确定？


二分果然是魔鬼 都在细节里···一个等号要我老命
有序就想到二分！不过细节就是问题了。手是我的，脑子不这么想。脑子说它是人体最聪明的器官，可是这是脑子告诉你的。





// 国内 官方
class Solution {
    public int search(int[] nums, int target) {
		int len = nums.length;
		// 1.特判：数组只有0、1个元素的情况
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
		}
		

        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

