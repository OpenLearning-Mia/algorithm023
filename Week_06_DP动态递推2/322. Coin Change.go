// 2021年2月9日19:26:19






	DP: 我们可以看到问题的答案是通过子问题的最优解得到的。
	腾讯二面面试题，希望大家好好做




题目需求：

输入：给定不同面额的硬币 coins 和一个总金额 amount


输出：编写一个函数来计算可以凑成总金额所需的最少的硬币个数；如果没有，返回 -1

附加条件：每种硬币的数量是无限的

分析：
要求最少硬币个数，则原则是：尽量用大面额的硬币


思路：


解法



做题感受总结：













/*
* 1.题目需求：
*	输入：n个台阶，n 为正整数(n > 0)；
*	输出：有多少种不同的方法 可以爬到楼顶？（计算出n个台阶的爬法数量）
*   
*	附加条件：每次只能爬 1 或 2 个台阶 
*
* 2.解法：DP (dfs、bfs怎么解？todo)
*
* 3.解题思路：
*	3.1.本问题分成多个子问题，爬第n阶楼梯的方法数量，等于两部分之和：
*		1.爬上n−1阶楼梯的方法数量。因为再爬1阶就能到第n阶;
*		2.爬上n−2阶楼梯的方法数量，因为再爬2阶就能到第n阶。
*
*	3.2.如此，问题转化为求斐波那切数列，f(n)=f(n−1)+f(n−2)
*
*	3.3.使用 滚动数组思想，来更新f(n)、f(n−1)、f(n−2)三个数的值
*
* 4.复杂度分析:
*   时间：O(amount*coins.size())；
*   空间：O(amount)；dp空间
 */





// 国际most vote; dp

/* coins的取值范围：
1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1

*/


class Solution {
	public:
		int coinChange(vector<int>& coins, int amount) {
			
			int Max = amount + 1;
			// key, 初始化,将dp元素初始化为Max，容易判断和识别，因为求的是dpMin;
			vector<int> dp(amount + 1, Max);
			dp[0] = 0;

			for (int i = 1; i <= amount; i++) {

				for (int j = 0; j < coins.size(); j++) {

					if (coins[j] <= i) {
						// 从头dp[0]走到底d[i]，d[i]是最终答案；
						// 等式右边的dp[i]是用coins[j-1]面额计算出来的硬币数；
						// 这里要计算出用【所有面额】所需的面额个数dp[i]，然后选出最小的为最终的dp[i]。
						dp[i] = min(dp[i], dp[i - coins[j]] + 1);
					}
				}

			}

			// dp[amount]表示最少硬币个数；
			// dp[amount] > amount 表示没有凑到总额amount的硬币；此时dp[amount]的值为初始值Max;
			return dp[amount] > amount ? -1 : dp[amount];
		}
	};







	// 国内精选题解



	class Solution {
		public int coinChange(int[] coins, int amount) {
			// 自底向上的动态规划
			if(coins.length == 0){
				return -1;
			}
	
			// memo[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
			int[] memo = new int[amount+1];

			// 给memo赋初值，最多的硬币数就是全部使用面值1的硬币进行换
			// amount + 1 是不可能达到的换取数量，于是使用其进行填充
			Arrays.fill(memo,amount+1);
			memo[0] = 0;

			for(int i = 1; i <= amount; i++){
				for(int j = 0; j < coins.length; j++){

					if(i - coins[j] >= 0){
						// memo[i]有两种实现的方式，
						// 一种是包含当前的coins[i],那么剩余钱就是 i-coins[i],
						//     这种操作要兑换的硬币数是 memo[i-coins[j]] + 1
						// 另一种就是不包含，要兑换的硬币数是memo[i]
						memo[i] = Math.min(memo[i], memo[i-coins[j]] + 1);
					}
				}
			}
	
			return memo[amount] == (amount+1) ? -1 : memo[amount];
		}
	}
	



				i - coins[j] 为剩下的总额
















	class Solution {
		public int coinChange(int[] coins, int amount) {
			// 自底向上的动态规划
			if(coins.length == 0){
				return -1;
			}
	
			// memo[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
			int[] memo = new int[amount+1];
			memo[0] = 0;

			for(int i = 1; i <= amount;i++){
				int min = Integer.MAX_VALUE;
				for(int j = 0;j < coins.length;j++){
					
					if(i - coins[j] >= 0 && memo[i-coins[j]] < min){
						min = memo[i-coins[j]] + 1;
					}
				}

				// memo[i] = (min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min);
				memo[i] = min;
			}
	
			return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
		}
	}
	





	想问下，你这个图用啥软件画的呢？
	mac上自带的keynote


// 2021年2月10日23:13:01 done fisrt time
