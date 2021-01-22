/*
* 做题感受总结：先搞清楚基本概念问题啊，比如【什么叫x的平方根】？？搞清楚后再找思路和解决方案。不要一上来就找答案，基本概念都没弄懂啊，这会导致舍本求末，事倍功半。
*
* 1.题目需求：
*	输入：x，一个非负整数。
*	输出：计算并返回 x 的平方根（实现 int sqrt(int x) 函数），结果返回整数（小数部分舍去）
*
* 2.解法：二分查找
*
* 3.解题思路：
*
*	3.1.什么叫x的平方根？存在整数a满足a^2 <= x < (a+1)^2，则称a为x的整数平方根。
*
*	3.2.由于x平方根的整数部分a是满足a^2 <= x,因此我们可以对a进行二分查找，从而得到答案；
*
*	3.3.二分查找的下界为0，上界可以粗略地设定为x（x优化：可以直接定为x/2（推导出a <=根号x <= x/2）,缩小二分查找范围）;(二分查找的思路和代码模板)
*
*	3.4.在二分查找的每一步中，我们只需要比较中间元素mid的平方与x的大小关系，通过比较的结果不断缩小上下界的范围，直到找到答案.
*
*	3.5.直到计算出dp[k-1]的数值，输出，func end；
*
*
* 4.复杂度分析:
*
*   时间：O(logx)；二分查找需要的次数
*
*   空间：O(1)；常量空间
*
 */

func mySqrt(x int) int {
	if x == 1 {
		return 1
	}

	left, right := 0, x/2 //优化迭代：right=x改成=x/2,进一步缩小查找范围（可以推导出：a <=根号x <= x/2（x!=1）, 所以right可以取x/2）
	ans := -1             //ans用来存储结果（x的平方根）

	for left <= right {
		mid := left + (right-left)/2

		if mid <= x/mid { //mid小了，往右找； mid^2小于x，则继续找右边：找更大的，使mid^2值接近x
			ans = mid      //记录当前小于x的mid值
			left = mid + 1 //向右查找

		} else { //mid大了，往左找； mid^2大于x，则继续找左边，找更小的，使mid^2值小于等于x
			right = mid - 1
		}
	}

	return ans
}

// 2021年1月22日16:37:33




// 第二遍 		2021年1月22日19:31:16

// 2021年1月22日21:55:08


if (x <= 1) {
	return x;
}
int left = 1, right = x / 2;


Initialize right = x/2
Square root of x is always lesser than or equal to x/2.


Nice process to prevent the integer overflow:

int mid = left + (right - left)/2;
mid>x/mid instead of mid*mid>x









// 国际most vote1
public int sqrt(int x) {
    if (x == 0)
		return 0;
		
	int left = 1, right = Integer.MAX_VALUE;
	
    while (true) {
        int mid = left + (right - left)/2;
        if (mid > x/mid) { /* same as mid * mid > x but prevents overflow */
            right = mid - 1;
        } else {
            if (mid + 1 > x/(mid + 1))
                return mid;
            left = mid + 1;
        }
    }
}


// 国际most vote2
class Solution {
	public:
		int sqrt(int x) {
			if (0 == x) return 0;
			int left = 1, right = x, ans;
			while (left <= right) {
				int mid = left + (right - left) / 2;
				if (mid <= x / mid) {
					left = mid + 1;
					ans = mid;
				} else {
					right = mid - 1;
				}
			}
			return ans;
		}
	};
