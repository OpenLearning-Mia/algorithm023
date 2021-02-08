// 高频面试题（亚马逊、字节跳动在半年内面试常考）




/*
* 1.题目需求：
*	输入：一个数组；它的第i个元素是: 一支股票第i天的价格。
*	输出：尽可能完成更多的买卖股票交易，计算【获取最大利润】；
*   
*	附加条件：不能同时买卖多笔交易，只能一次次的来（必须在再次购买前出售掉之前的股票）
*
* 2.解法：贪心算法
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
*   时间：O(n)；遍历计算n次，n为股票数组长度；
*   空间：O(1)；常量空间大小
 */





/* 

思路：

	这道题用动态规划的思路并不难解决，比较难的是后文提出的用分治法求解，但由于其不是最优解法，所以先不列出来

	动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans

	如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字

	如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字

	每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果

	时间复杂度：O(n)
	空间：O(1)

	
解法：DP、滚动数组思想  
// DP方程：DP(i) = max{DP(i−1)+nums[i], nums[i]}

// 做题感受总结：关于DP, 重点是：找出重复子问题，确定出DP方程来；

*/

// 国际 most vote  感觉这个逻辑更好理解：如果之前的sum小于0,需要舍弃，因为对当前的新和相加没有增加效益，没有最大值效益，只会变更小；
public int maxSubArray(int[] A) {
	int n = A.length;
	int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
	dp[0] = A[0];
	int max = dp[0];
	
	for(int i = 1; i < n; i++){
		// KEY:dp方程：DP(i) = max{DP(i−1)+nums[i], nums[i]}
		dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);

		//空间优化O(1)：dp[i]-->sum    
		// sum = A[i] + (sum > 0 ? sum : 0);

		max = Math.max(max, dp[i]);
	}
	
	return max;
}








输入：
 	整数数组 nums 


	找到一个具有最大和的、连续的 子数组


	子数组最少包含一个元素


输出：返回其最大和



解法：


思路；


// 官网题解：
// 题目需求是：连续子数组的最大和，需求关键字：要连续的、要最大和的
// 输出：求maxSum；

// DP方程：f(i)=max{f(i−1)+nums[i],nums[i]}
class Solution {
    public int maxSubArray(int[] nums) {
        int currMaxSum = 0, //存储当前计算出的最大和（dp[i]）
		int maxSum = nums[0]; //存储结果值：最终的最大和

        for (int cur : nums) {
			// 1.遍历元素，求出当前的最大和；
			// 	 currMaxSum + cur 小于cur，说明currMaxSum小于0，负数，丢弃这个和值currMaxSum，定位下一个；
            currMaxSum = Math.max(currMaxSum + cur, cur);
			// 2.比较当前最大和currMaxSum和maxSum的大小;保证maxSum一直最大；
            maxSum = Math.max(maxSum, currMaxSum);
        }

        return maxSum;
    }
}






//  2021年2月7日10:48:54
// 2021年2月7日17:57:04