




// Obstacles：障碍物
// java
class Solution {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		// 1.特判：起点就是障碍物
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}
	
		int width = obstacleGrid[0].length;
		int[] dp = new int[width];
		dp[0] = 1;
	
		// 秒点：以行为单位遍历每个元素，如下for遍历所示，尤其是第一个for遍历
		for (int[] row : obstacleGrid) {
			for (int j = 0; j < width; j++) {  //j从0开始计算，因为下标为0的位置可能为障碍物，需要检测
				if (row[j] == 1) //障碍物
					dp[j] = 0;
					
				else if (j > 0) //因为下面的j-1下标必须j>0
					dp[j] += dp[j - 1];  
			}
		}
		return dp[width - 1];
	}
}

// 疑问：第一个就是障碍物呢？那就结束了，没法走了，对不？
// 答：这个情况包含在算法里了，最终结果是0，还是会最晚整个遍历流程，
// 所以做优化，一开始就特判：终止这种情况；


// go
//todo:怎么遍历这个二维array,go语言
func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	// 1.
	column := len(obstacleGrid[0])
	dp := make([]int, column)
	row int[]  //??

	// 2.特判：起点就是障碍物，则0条路径，func end;
	if obstacleGrid[0][0] == 1 {
		return 0
	}

	// 3.遍历二维数组：以行row为单位遍历每个元素
	for row range obstacleGrid { //todo:怎么遍历这个二维array,go语言
		for j := 0; j < column; j++ {
			// 3.1.略过障碍物
			if row[j] == 1 {
				dp[j] = 0;
			}
			else if j > 0 {
				// dp[j] = pre[j] + dp[j-1];
				// pre[j]为上一行的计算结果dp[j],即pre[j] = dp[j](该dp为上一行的结果dp[j]-last)；
				// 等式左边dp[j]用来存储当前的计算结果；
				// 数组滚动思想
				dp[j] += dp[j-1]
			}
		}
	}

	// 4.到这里表示已遍历到达终点，dp[column-1]为最终路径数；
	return dp[column-1]

}






func uniquePathsWithObstacles(obstacleGrid [][]int) int {
    row, column := len(obstacleGrid), len(obstacleGrid[0])
    f := make([]int, column)
    if obstacleGrid[0][0] == 0 {
        f[0] = 1
    }
    for i := 0; i < n; i++ {
        for j := 0; j < m; j++ {
            if obstacleGrid[i][j] == 1 {
                f[j] = 0
                continue
            }
            if j - 1 >= 0 && obstacleGrid[i][j-1] == 0 {
                f[j] += f[j-1]
            }
        }
    }
    return f[len(f)-1]
}







// 解题笔记：
/* 「滚动数组思想」是一种常见的动态规划优化方法，在我们的题目中已经多次使用到，
	例如「剑指 Offer 46. 把数字翻译成字符串」、「70. 爬楼梯」等，
	当我们定义的状态在动态规划的转移方程中只和某几个状态相关的时候，
	就可以考虑这种优化方法，目的是给空间复杂度「降维」。
	如果你还不知道什么是「滚动数组思想」，一定要查阅相关资料进行学习哦。
 */



// 2021年2月3日08:41:44