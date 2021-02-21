// 2021年2月11日08:45:56








给定一个代表每个房屋存放金额的非负整数数组



不触动警报装置的情况下  //不能同时取相邻两元素值


一夜之内能够偷窃到的最高金额。 //又是求MAX，考虑用DP;




// 做题感受总结：
//  DP解题流程：思考是自顶向下分治，整理出子问题， 实现时就是自底向上递推累加；
// 解题流程：搞清楚子问题是什么？？如何分治理？？








/* 
	本问题分成多个子问题，爬第n阶楼梯的方法数量，等于 2 部分之和
	1.爬上n−1阶楼梯的方法数量。因为再爬1阶就能到第n阶
	2.爬上n−2阶楼梯的方法数量，因为再爬2阶就能到第n阶

*/

/*
* 1.题目需求：
*	输入：n个台阶，n 为正整数(n > 0)；
*	输出：有多少种不同的方法 可以爬到楼顶？（计算出n个台阶的爬法数量）
*   
*	附加条件：每次只能爬 1 或 2 个台阶 
*
* 2.解法：斐波那切数列  滚动数组思想
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
*   时间：O(n)；遍历计算n次
*   空间：O(1)；常量空间大小
 */





// 解法二：动态规划 + 滚动数组(for空间优化)
// space优化 O(1)

/* the order is: first, second, num[i]  */
func rob(nums []int) int {
    if len(nums) == 0 {
        return 0
    }

    if len(nums) == 1 {
        return nums[0]
    }

    first := nums[0]
    second := max(nums[0], nums[1])

    for i := 2; i < len(nums); i++ {
        first, second = second, max(first + nums[i], second)
    }

    return second
}

func max(x, y int) int {
    if x > y {
        return x
    }
    return y
}


// most vote great 极简代码；
/* the order is: prev2, prev1, num  */
public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    int prev1 = 0;
    int prev2 = 0;

    for (int num : nums) {
        int tmp = prev1;
        prev1 = Math.max(prev2 + num, prev1);
        prev2 = tmp;
    }
    return prev1;
}




// 解法二：动态规划 + DP一维数组
 func rob(nums []int) int {
    if len(nums) == 0 {
        return 0
    }

    if len(nums) == 1 {
        return nums[0]
    }

    dp := make([]int, len(nums))
    dp[0] = nums[0]
    dp[1] = max(nums[0], nums[1])

    for i := 2; i < len(nums); i++ {
        dp[i] = max(dp[i-2] + nums[i], dp[i-1])
    }

    return dp[len(nums)-1]
}

func max(x, y int) int {
    if x > y {
        return x
    }
    return y
}



// 2021年2月12日22:03:09  done  first-time

如果你对于动态规划还不是很了解，或者没怎么做过动态规划的题目的话，那么 House Robber （小偷问题）这道题是一个非常好的入门题目。本文会以 House Robber 题目为例子，讲解动态规划题目的四个基本步骤。

动态规划的的[四个解题步骤]是：
1定义子问题
2写出子问题的递推关系
3确定 DP 数组的计算顺序
4空间优化（可选）


步骤一：定义子问题
动态规划实际上就是通过求这一堆子问题的解，来求出原问题的解。这要求子问题需要具备两个性质：

原问题要能由子问题表示。例如这道小偷问题中，k=nk=n 时实际上就是原问题。否则，解了半天子问题还是解不出原问题，那子问题岂不是白解了。
一个子问题的解要能通过其他子问题的解求出。例如这道小偷问题中，f(k)f(k) 可以由 f(k-1)f(k−1) 和 f(k-2)f(k−2) 求出，具体原理后面会解释。这个性质就是教科书中所说的“最优子结构”。如果定义不出这样的子问题，那么这道题实际上没法用动态规划解。
小偷问题由于比较简单，定义子问题实际上是很直观的。一些比较难的动态规划题目可能需要一些定义子问题的技巧。



步骤二：写出子问题的递推关系
这一步是求解动态规划问题最关键的一步。
然而，这一步也是最无法在代码中体现出来的一步。
在做题的时候，最好把这一步的思路用注释的形式写下来。
做动态规划题目不要求快，而要确保无误。
否则，写代码五分钟，找 bug 半小时，岂不美哉？


这样我们可以保证，计算一个子问题的时候，它所依赖的那些子问题已经计算出来了。


public int rob(int[] nums) {
    if (nums.length == 0) {
        return 0;
    }
    // 子问题：
    // f(k) = 偷 [0..k) 房间中的最大金额

    // f(0) = 0
    // f(1) = nums[0]
    // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }

    int N = nums.length;
    int[] dp = new int[N+1];
    dp[0] = 0;
    dp[1] = nums[0];
    for (int k = 2; k <= N; k++) {
        dp[k] = Math.max(dp[k-1], nums[k-1] + dp[k-2]);
    }
    return dp[N];
}


// 空间复杂度也从 O(n) 降到了 O(1)
public int rob(int[] nums) {
    int prev = 0;
    int curr = 0;

    // 每次循环，计算“偷到当前房子为止的最大金额”
    for (int i : nums) {
        // 循环开始时，curr 表示 dp[k-1]，prev 表示 dp[k-2]
        // dp[k] = max{ dp[k-1], dp[k-2] + i }
        int temp = Math.max(curr, prev + i);
        prev = curr;
        curr = temp;
        // 循环结束时，curr 表示 dp[k]，prev 表示 dp[k-1]
    }

    return curr;
}

作者：nettee
链接：https://leetcode-cn.com/problems/house-robber/solution/dong-tai-gui-hua-jie-ti-si-bu-zou-xiang-jie-cjavap/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

/* 

解法迭代过程：
Recursive (top-down)

Recursive + memo (top-down)

Iterative + memo (bottom-up)

Iterative + 2 variables (bottom-up) 

*/