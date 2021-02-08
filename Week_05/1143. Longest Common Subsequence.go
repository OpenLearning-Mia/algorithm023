

最长公共子序列（Longest Common Subsequence，简称 LCS）是一道非常经典的面试题目，
因为它的解法是典型的二维动态规划，大部分比较困难的字符串问题都和这个问题一个套路，比如说编辑距离。
而且，这个算法稍加改造就可以用于解决其他问题，所以说 LCS 算法是值得掌握的。


作者：labuladong
链接：https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。





Memory Optimization
You may notice that we are only looking one row up in the solution above. 
So, we just need to store two rows.






// spcace优化为O(n)，一维数组存储状态值、数组滚动思想递推出最终答案；
// time O(mn)
// space O(min(m,n))
class Solution {
	public:
		int longestCommonSubsequence(string text1, string text2) {
			int cur_val, pre_val;
			int len1 = text1.length(), len2 = text2.length();
			// 1.特判：有空串；则最长公共子串为0
			if (len1 == 0 || len2 == 0) {
				return 0;
			}

			// 2.保证进来的text2长度len2更小，因为后面把它作为列数来开辟状态存储空间（降低space复杂度）
			if (len1 < len2) {
				return longestCommonSubsequence(text2, text1);
			}

			// 3.初始化dp元素为0, 长度为len2+1;
			vector<int> dp(len2+1, 0);

			// 遍历方向：先横向j遍历完列column，在竖向i遍历行row
			for(int i=1; i<=len1; ++i) {
				// 竖向遍历
				pre_val = 0; //左上角值 初始花
				for(int j=1; j<=len2; ++j) {
					// 横向遍历
					cur_val = dp[j];  //这里的dp[j]为old-dp[j]表示d[i-1][j]：上一行的同一列的值；

					if (text1[i-1]==text2[j-1]) {  //1.当前字符相同 DP方程:dp[j] = dp[i-1][j-1] +1
						// 当前dp[j] = 左上角dp[i-1][j-1] +1
						dp[j] = pre_val +1;
					}
					else {
						// 1.当前字符不同, 则DP方程:dp[j] = max(d[i-1][j], dp[i][j-1])
						// 当前dp[j] = max[上一行d[j](也就是d[i-1][j]), 左边d[j-1](也就是d[i][j-1])]
						dp[j] = max(dp[j], dp[j-1]);
					}
					// 等式左边的dp[j]表示【当前行新的】，用来存储当前行第j个元素的值；
					// 等式右边的dp[j]表示【上一行的】第j个元素的值，与当前的新dp[j]上下相邻；

					// 3.数组滚动思想：将上一行d[i-1][j]的值赋给左上角值[i-1][j-1]；
					pre_val = cur_val; //pre用来存储当前dp[j]的左上角的值；
				}
			}
			return dp[len2];
		}
	
};






// time O(mn)
// space O(mn)
 class Solution {
    public int  longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int length1 = t1.length;
        int length2 = t2.length;
		int[][] dp = new int[length1+1][length2+1];
		
        for (int i = 1; i < length1 +1; i++) {
            for (int j = 1; j < length2 +1; j++) {

                if (t1[i-1] == t2[j-1]){  //字符串的下标是从0开始的；dp的下标是从1开始的；为什么？因为要初始化；不这样就得另外写代码；
                    // 这边找到一个 lcs 的元素，继续往前找
                    dp[i][j] = 1+ dp[i-1][j-1];
                }else {
                    //谁能让 lcs 最长，就听谁的
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[length1][length2];
    }
}
