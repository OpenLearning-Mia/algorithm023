

/*
* 1.解法：二分查找
*
* 2.复杂度分析:
*   时间：O(logn)；n为数组大小；二分查找的时间复杂度
*   空间：O(1)；常量空间大小
*/
class Solution {
public:
    int search(vector<int>& nums, int target) {
		int n = (int)nums.size();
		// 1.特判：n等于0，1的情况
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
		}
		
		// 2.开始二分查找流程，该数组特性：元素呈【局部有序性】，就是在mid左右，至少有一边是有序的；
		// 	查找策略：哪边有序，就先往哪边找：去找target是否在它里面，不在的话就在另一边里找。
        int l = 0, r = n - 1;
        while (l <= r) {
			int mid = (l + r) / 2;
			
			// 2.1.找到了
			if (nums[mid] == target) return mid;
			
			// 2.2.查找策略：哪边有序，就先往哪边找（nums[0] <=？ nums[mid]）
			// 	   nums[mid]【左边有序】，则查找target值是否在mid左边？
            if (nums[0] <= nums[mid]) { 
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;  //target在mid左边，去左边子集里继续找；
                } else {
                    l = mid + 1; //target在mid右边，去右边子集里继续找；
                }
            } else {
				// 2.3.nums[mid]【右边有序】，则查找target值是否在mid右边？
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;  //target在mid右边，去右边子集里继续找；
                } else {
                    r = mid - 1;  //target在mid左边，去左边子集里继续找；
                }
            }
		}

		// 到这里表示没找到，返回-1;
        return -1;
    }
};



// 2021年2月6日23:25:47 second-time