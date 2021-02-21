// 152. 乘积最大子数组
// product：乘积












/*
* 1.题目需求：
*	输入：一个数组；它的第i个元素是: 一支股票第i天的价格。
*	输出：尽可能完成更多的买卖股票交易，计算【获取最大利润】；
*   
*	附加条件：不能同时买卖多笔交易，只能一次次的来（必须在再次购买前出售掉之前的股票）
*
* 2.解法：DP 
*	DP方程：DP[j] = max(DP[j] * nums[i], nums[i])
*
* 3.解题思路：
*	3.1.由于不限制交易次数，所以只要今天股价比昨天高，就交易(贪心思想)：
*
*	3.2.贪心算法：每一步总是做出 在当前看来最好的选择；
*
*	3.3.对于「今天的股价 - 昨天的股价」，得到的结果有3种可能：1.正数，2.零，3.负数。
*	
*	3.4.为获得最大利润，贪心算法的决策是：只加正数；
*	
* 4.复杂度分析:
*   时间：O(n)；遍历计算n次，n为数组长度；
*   空间：O(1)；常量空间大小
 */



// 网友精彩评论；

//  关键点就是这句话：“由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin。”

//  imax表示以当前节点为终结节点的最大连续子序列乘积 imin表示以当前节点为终结节点的最小连续子序列乘积


/* 
	Math.max(imax*nums[i], nums[i])的意思是：
	是当前累计乘积大些，还是当前元素大些，最终取这两者中的最大值；
 */


// 关键思路分析：分成当前元素是正数还是负数 的情况；
// 乘积结果的特征：正负=负，负负=正；
// imax、imin与一个负数相乘：会导致最大的变最小，最小的变最大。key...
// 需要同时维护当前乘积的最大值imax和最小值imin，因为最小值与负数相乘就是最大值（题目需要的是最大值）

// DP方程：DP[j] = max(DP[j] * nums[i], nums[i])
// 		   DP[j] = min(DP[j] * nums[i], nums[i])

// 推演分析: {5, 6, −3, 4, −3}
// 国内most vote1
class Solution {
    public int maxProduct(int[] nums) {

		// 1.初始化
		// imax为当前乘积出的最大值
		// imin为当前乘积出的最小值
		// max为所有imax结果集中的最大值，为输出值；
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;

		// 2.DP，向右遍历计算元素
        for(int i=0; i<nums.length; i++) {

			// 2.1.先确认当前元素nums[i]的正负：
			// 	   如果nums[i]为负数，则互换imax和imin的值；因为正负=负、负负=正
			// 	   也就是：与一个负数相乘：会导致最大的变最小，最小的变最大。
            if(nums[i] < 0) { 
				// 互换swap(imax, imin)的值，因为题目需求是【求最大值】，一切往最大值上靠;
				int tmp = imax;
				imax = imin; //因为当前待计算的元素为负数，所以把imin赋给imax，目的是保证imax一直是最大的；
				imin = tmp;
            }

			// 2.2.算出当前乘积最大值
            imax = Math.max(imax*nums[i], nums[i]);

			// 2.3.算出当前乘积最小值
			// 	   计算维护imin的目的：如果下一个nums[i]为负，则希望它之前的乘积最小，这样能得到当前的imax，然后后面还有nums[i]为负的，则能得出更大的imax(负负得正)
			imin = Math.min(imin*nums[i], nums[i]);
            
			// max为所有imax结果集中的最大值，
			// 如果当前imax比max更大，则将imax值更新为max；
            max = Math.max(max, imax);
        }

        return max;
    }
}

// 做题感受记录：
// 1.多看别人的题解描述，这样才能发现优质的题解描述，这样才能理解题解的意思和思维；
// 2.多看评论区，网友的进一步评论解说，能点播我，是我能更理解的深刻到位，对解题思维方法；
// 3.多看图解过程，一图胜过千言万语；多看，才能找到优质的图解，能更深刻的更好的理解解题方法和思想；

// 2021年2月8日22:08:14



整数数组 nums


找出数组中乘积最大的连续子数组


返回该子数组所对应的乘积



// 感受记录：
// 求最大值，那【负数】就是拖后腿的(弱化最大值)，所以对其能丢则丢；
// 先分析、搞清楚乘积出现的结果 会有哪些情况：负*正=负，负*负=正；





// 国际most vote1
int maxProduct(int A[], int n) {
    // store the result that is the max we have found so far
    int r = A[0];

    // imax/imin stores the max/min product of
    // subarray that ends with the current number A[i]
    for (int i = 1, imax = r, imin = r; i < n; i++) {
        // multiplied by a negative makes big number smaller, small number bigger
        // so we redefine the extremums by swapping them
        if (A[i] < 0)
            swap(imax, imin);

        // max/min product for the current number is either the current number itself
        // or the max/min by the previous number times the current one
        imax = max(A[i], imax * A[i]);
        imin = min(A[i], imin * A[i]);

        // the newly computed max value is a candidate for our global result
        r = max(r, imax);
    }
    return r;
}






// 2021年2月8日21:45:13 done  3个番茄