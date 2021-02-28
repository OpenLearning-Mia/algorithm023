












// 2021年2月22日08:43:21




/* 


编写一个函数


输入 
    是一个无符号整数（以二进制串的形式）

输出
    返回其二进制表达式中数字位数为 '1' 的个数


提示：
输入必须是长度为 32 的 二进制串 。
 */

/* 
算法思路：

这个方法比较直接。我们遍历数字的 32 位。如果某一位是 1 ，将计数器加一。

我们使用 位掩码 来检查数字的第i位。一开始，掩码 m=1因为 1 的二进制表示是

0000 0000 0000 0000 0000 0000 0000 0001
0000 0000 0000 0000 0000 0000 0000 0001

显然，任何数字跟掩码 11 进行逻辑与运算，都可以让我们获得这个数字的最低位。检查下一位时，我们将掩码左移一位。

0000 0000 0000 0000 0000 0000 0000 0010
0000 0000 0000 0000 0000 0000 0000 0010

并重复此过程。


 */

public int hammingWeight(int n) {
    int bits = 0;
    int mask = 1;

    // 1.一个十进制数n，只有32位比特位，所以循环32次，每次通过相与来得知当前比特位是1还是0。
    //   从最低位(右边)开始：获取十进制n(32个bit位)的每一比特位是多少；是1的，计数bits+1；
    for (int i = 0; i < 32; i++) {
        // 1.1.n & mask，为1，表示当前第i的位置为比特1; 为0，表示当前第i的位置为比特0
        if ((n & mask) == 1) { 
            bits++;  //1.2.当前比特位为1的，累计加1
        }
        mask <<= 1;  //1.3.当前n的比特位与完后，掩码mask每次向高位（左）移动一位,与n的下一位比特位相与。
    }

    // 2. 32位遍历计算结束，返回结果。
    return bits;
}
/* 
复杂度分析

时间复杂度：O(1) 。运行时间依赖于数字n的位数。由于这题中n是一个 32 位数，所以运行时间是O(1) 的。
空间复杂度：O(1)。没有使用额外空间。

 */





/* 
方法 2：位操作的小技巧
算法思路：

我们可以把前面的算法进行优化。我们不再检查数字的每一个位，而是不断把数字最后一个 11 反转，并把答案加一。当数字变成 00 的时候偶，我们就知道它没有 11 的位了，此时返回答案。

这里关键的想法是对于任意数字 nn ，将 nn 和 n - 1n−1 做与运算，会把n最后一个 11 的位变成 00 。为什么？考虑 nn 和 n - 1n−1 的二进制表示。

这里通过手工画图、举例推演，理解感受它的过程规律和原理
光看代码和文字描述，再费劲也不知道它在讲什么，没啥感觉；一自己动手试一遍，就知道它什么意思？为什么这么讲和写


 */

public int hammingWeight(int n) {
    int bitCount = 0; //用来存储n中比特位为1的个数, 答案;

    //循环结束条件：n=0；表示n的值已被&为0了(n中没有为1的比特位，全是0)，fun end.  
    while (n != 0) {
        // 1.相与，n最低位的1变为0，bitCount+1；
        n &= (n - 1);  //每&(与)一次，n最低位的1就少一位；
        bitCount++; 
    }
    // 2.计算完毕，返回结果
    return bitCount;
}

// n = n & (n - 1); 
// 1. &与一次，最低位的1就少一位，
// 2. time&space: O(1); 循环计算次数 就是 n中比特位为1的个数，题目指出：n最大位数为32bit；所以最差情况：循环32次，常数级。
// 3. 做题感受：
//    1.通过手工举例推演，就能明白它的过程规律和原理；
//    2.通过手工画图、举例推演，去理解感受它的过程规律和原理
//    3.光看代码和文字描述，费牛劲也不知道它在讲什么，没啥感觉；
//    4.一但自己动手试一遍，就知道它什么意思？为什么这么讲和写



// 2021年2月22日09:50:33 done-fisrt













































/*
* 1.【题目需求】：
*	【输入】：一个由 '1'（陆地）和 '0'（水）组成的的二维网格（二维数组）
*	【输出】：计算出网格中岛屿的数量；
*   
*	【附加信息】：岛屿总是被水包围，每座岛屿只能由水平方向和/或竖直方向上相邻陆地 连接形成
*
*   【信息翻译】：网格中每个格子里的数字要么是0（海水），要么是1（陆地）；
*            （上下左右）相邻的陆地格子就构成了一个岛屿。（一个单独的陆地也算是一个岛屿？？对吗？？todo）
*
* 2.【解法】：贪心算法
*
* 3.【解题思路】：
*	3.1.这岛屿数量问题属于【网格遍历通用问题】，可以用网格DFS或BFS实现；
*
*	3.2.网格问题的基本概念：网格问题是由m*n个小方格组成一个网格，
*       每个小方格与其上下左右四个方格认为是相邻的，要在这样的网格上进行某种搜索。
*
*	3.3.网格上的DFS，完全可以参考二叉树的DFS，二叉树的 DFS 有两个要素：「访问相邻结点」和「递归结束条件」
*
*   3.4.由此写出网格DFS的两个要素：
*       首先，网格结构中的格子有多少相邻结点要访问？（等价于多叉树的节点）
*             答案是：上下左右 共四个。对于格子 (r, c) 来说（r 和 c 分别代表行坐标和列坐标），
*             四个相邻的格子分别是 (r-1, c)、(r+1, c)、(r, c-1)、(r, c+1);
*             换句话说，网格结构是「四叉」的。 （类似于四叉树的四个儿子）
*       其次，网格 DFS 中递归结束条件是什么？从二叉树的递归结束条件 类比过来，应该是：
*             网格中不需要继续递归遍历、grid[r][c] 会出现数组下标越界异常的格子，就是超出网格边缘范围的格子；
*             一旦遍历完网格边缘的格子，递归就结束；网格边缘的格子相当于二叉树的叶子节点，遍历完叶子节点就递归结束，再没有可递归遍历了，到底了、到边缘了。
*
*	3.4.为求出岛屿数量，【需要在所有值为1的陆地格子上做 DFS 遍历】；具体为：
*       扫描整个二维网格，只DFS搜索格子为1的节点(非1的格子不搜)，并以其为起始节点继续DFS搜索它的上下左右四个相邻格子)
*
*   3.5.网格DFS重复遍历问题。网格结构的 DFS 与二叉树的 DFS 最大的不同之处在于，遍历中可能遇到遍历过的结点；这是因为网格结构本质上是一个「图」，可以把每个格子看成图中的结点
*	    这时候，DFS可能会不停地「兜圈子」，永远走不到网格边缘而停下来。
*   
*   每一次进行「深度优先遍历」的前提条件是：
*       1.这个格子是陆地 1；
*       2.这个陆地没有被遍历过。  
*
*   3.6.如何避免这样的重复遍历？答案是标记已被遍历过的陆地格子(标记为2)；
*       这样当我们遇到格子等于2的时候，就知道这是已遍历过的格子，直接跳过它。同时也说明，每个格子可能取三个值：
*        0 —— 海洋格子
*        1 —— 陆地格子（未遍历过）
*        2 —— 陆地格子（已遍历过）
*
* 4.【复杂度分析】:
*   时间：O(M*N)；最坏情况：for遍历计算M*N次；其中M和N分别为网格的行数和列数；
*   空间：O(M*N)；有几个陆地，就会dfs递归几次；最坏情况：整个网格全是陆地1，dfs遍历深度达到M*N；
* 
* 5.【做题 经历感受记录】：
*	TBD:
*/


