
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
public class Solution {
	public int maxProfit(int[] prices) {
		int len = prices.length;
		// 1.只有0天、1天的股票价格，还没法交易，利润为0；
		if (len < 2) {
			return 0;
		}

		// 2.遍历len天的价格，累计出最大利润；
		int res = 0;
		for (int i = 1; i < len; i++) {
			// 只累计交易大于0的利润
			res += Math.max(prices[i] - prices[i - 1], 0);
		}

		// 3.到这表示已算出最大利润res，func end
		return res;
	}
}




// “等价于每天都买卖”，这种理解好，把可能跨越多天的买卖都化解成相邻两天的买卖，多谢大佬。

// 国内 most vote  （有股票问题通解总结）
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/
