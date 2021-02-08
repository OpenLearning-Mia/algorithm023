
/* 
	本题是一道非常经典且历史悠久的动态规划题，
	其作为算法题出现，最早可以追溯到 1994 年的 IOI（国际信息学奥林匹克竞赛）的 The Triangle。
	时光飞逝，经过 20 多年的沉淀，往日的国际竞赛题如今已经变成了动态规划的入门必做题，
	不断督促着我们学习和巩固算法。
*/


/* 
解法：

triangle的【DP方程】：

版本一：DP[i][j] 二维数组
	minpath[k][i] = min( minpath[k+1][i], minpath[k+1][i+1]) + triangle[k][i];

版本二：迭代优化
	优化空间复杂度：DP[j] 一维数组  For the kth level: 
	minpath[j] = min( minpath[j], minpath[j+1]) + triangle[k][j]; 
 */




// 国际版most vote1    java
// 这个更好理解，代码更简洁；
// 思想：求 【从头dp[i][j]走到底】的路径数，则使用自底向上思维：从底递推到头d[0][0]，d[0][0]为最终路径数
// time:O(N*N)，N为三角形的行数.	
// space:O(N)，DP为一维数组空间大小；

public int minimumTotal(List<List<Integer>> triangle) {
	// DP初始化为0；DP大小为啥是size+1，不是size吗？size就够用？
	// 回答：size表示数组行数rows;所以列数最大值为rows（这个题目等腰三角形的特性:j <= i）；
	// key: DP(A)的大小为【size+1】;因为后面计算有A[j+1]的情况，也就是存在对size+1空间的判断,所以DP空间大小初始化为size+1
	int[] A = new int[triangle.size()+1];
	
	//自底向上思想DP： 从最后一行开始往回计算;
    for(int i = triangle.size() - 1; i >= 0; i--){
        for(int j = 0; j <= i; j++){
            A[j] = Math.min(A[j], A[j+1]) + triangle.get(i).get(j);
        }
	}
	
    return A[0];
}









// 国际版most vote2    c++
// 思想：求 从头dp[i][j]走到底的路径数，则使用自底向上思维：从底递推到头d[0][0]，d[0][0]为最终路径数
int minimumTotal(vector<vector<int> > &triangle) {
	int n = triangle.size();
	
	// key1：将倒数第一行的值初始化到minlen，作为后面计算的基础值； ？？TODO这块是干啥，google确认下
	vector<int> minlen(triangle.back());
	
	// key2:i = n-2,从倒数第二层开始计算；倒数第一行的值都被初始化到minlen里了(minlen(triangle.back()))
    for (int i = n-2; i >= 0; i--) // For each i
    {
		// key3：j <= i
        for (int j = 0; j <= i; j++) // Check its every 'node'
        {
            // Find the lesser of its two children, and sum the current value in the triangle with it.
            minlen[j] = min(minlen[j], minlen[j+1]) + triangle[i][j]; 
        }
	}
	
	// minlen[0]为最终答案：从头走到底的路径数(采用自底向上思维来计算：从底【递推计算】到头部)
    return minlen[0];
}






// 网友写的注释理解：
class Solution {
	public:
		int minimumTotal(vector<vector<int>>& triangle) {
	
			//很经典的dp题目
			int col = triangle.size();   //多少行
	
			vector<int> dp(col+1,0);    //根据多少行，我们来确定需要多大的辅助空间  这里将里面的元素都初始化为0
	
			for (int i = col - 1; i >= 0; i--)  //从最后一行开始向第一行走  即从下到上
			{
				for (int j = 0; j <triangle[i].size(); j++)    //从第一列向最后一列走， 从左到右
				{
					dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]; //先再选择最小的元素 然后再加上要计算的元素
				}
			}
			return dp[0];
		}
	};


























// 方法一：从头走到底 (则用自底向上思维：从底递推到头d[0][0]，得出最终路径数)

//【从头d[0][0]走到底】的路径数：然后用自底向上思维：从底递推到头d[0][0]求解

// space: n*n, 二维数组dp[i][j]，存储每行的dp[i][j]
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 【到底边】的最小路径和：因为是【从头走底边】的路径数，所以从底【递推】到头(自底向上DP思维)
        int[][] dp = new int[n + 1][n + 1];
		
		// 从三角形的最后一行开始递推：因为求的是从头走到尾的路径数，所以从底【递推】到头d[0][0]；
		// d[0][0]就是最终的答案；
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}


// 优化空间复杂度DP 一维数组；
// space n,一维数组dp[j]，存储每行的dp[j]
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1]; //dp初始化为0; n+1个空间的目的，为下面下标j + 1的计算做铺垫；相当于在底部在加一行一列（值为0）
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {

				// 等式左边的dp[j]用来存储 当前行（i）计算的新值；
				// 等式右边的dp[j], dp[j + 1]，为旧值：在上一行（i+1）就计算出的值；
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}






// 方法二：从底走到头 (则用自底向上思维：从头递推到底d[j][j]，得出最终路径数)

// 【从底d[j][j]走到头】的路径数：然后自底向上思维：从头递推到底d[j][j]求解
// 怎么求解？？得分三部分来写，官网有题解；

// 官网答案：

class Solution {
	public:
		int minimumTotal(vector<vector<int>>& triangle) {
			int n = triangle.size();
			vector<vector<int>> f(n, vector<int>(n));
			f[0][0] = triangle[0][0];

			for (int i = 1; i < n; ++i) {  //n为行数rows

				f[i][0] = f[i - 1][0] + triangle[i][0];  //第一列(每一行头部节点)的路径值；

				for (int j = 1; j < i; ++j) {
					f[i][j] = min(f[i - 1][j - 1], f[i - 1][j]) + triangle[i][j]; //每一行中间【非头和尾部】节点f[i][i]的路径值
				}

				f[i][i] = f[i - 1][i - 1] + triangle[i][i];  //每一行最后一位节点f[i][i]的路径值
			}

			return *min_element(f[n - 1].begin(), f[n - 1].end());
		}
	};
	



// 优化空间复杂度DP 一维数组；
class Solution {
	public:
		int minimumTotal(vector<vector<int>>& triangle) {
			int n = triangle.size();
			vector<int> f(n);
			f[0] = triangle[0][0];

			for (int i = 1; i < n; ++i) {

				f[i] = f[i - 1] + triangle[i][i]; //第一列

				for (int j = i - 1; j > 0; --j) {
					f[j] = min(f[j - 1], f[j]) + triangle[i][j];
				}
				f[0] += triangle[i][0];
			}

			return *min_element(f.begin(), f.end());
		}
	};






// 2021年2月6日21:01:06 done first-time