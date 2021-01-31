






















public class Solution {

    // 方向向量DIRECTIONS，{上下，左右} ==》：{下，左，上，右}
    // 用来干啥？？
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private boolean[][] visited;
    private int rows;
    private int cols;
    private char[][] grid;


    public int numIslands(char[][] grid) {

        rows = grid.length;  //二维数组grid的行数
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;  //grid第一行元素个数，就是grid二维数组的列数

        this.grid = grid;
        visited = new boolean[rows][cols];  //行 列
        int count = 0;  //记录岛屿个数

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // 如果是岛屿中的一个点1，并且没有被访问过，就进行深度优先遍历
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }

        return count;
    }


    /**
     * 从坐标为 (i, j) 的点开始进行深度优先遍历
     *
     * @param i
     * @param j
     */
    private void dfs(int i, int j) {

        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {

            // i + DIRECTIONS[k][0]相加 是啥意思？？
            // 表示接下来向哪个方向移动，下左上右，按按这个顺序来移动和查找陆地
            int newX = i + DIRECTIONS[k][0]; 
            int newY = j + DIRECTIONS[k][1];

            // 如果不越界、还是陆地、没有被访问过；就再次递归 深入下一层
            if (inArea(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {

                dfs(newX, newY);
            }
        }
    }





    /**
     * 封装成 inArea 方法语义更清晰
     *
     * @param x
     * @param y
     * @return
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}









/* 
这里有一点需要注意：解决这个问题是可以不用建立 marked 数组，但有以下的知识点需要大家知道。

1.对于算法的输入而言，很多时候是介意修改输入的，除非题目就是要我们修改输入数据；
2.再者 marked 数组没有必要节约这个空间，现在空间越来越不值钱了，而且程序执行完成以后，空间可以回收。我们写算法的时候，应该力求时间复杂度最优，并且代码可读性强。
这里是 liuyubobobo 老师对这一类问题的解释：如果面试遇到这个问题，这一点最重要！。

*/





// 岛屿遍历dfs代码：

void dfs(int[][] grid, int r, int c) {
    // 判断 base case
    if (!inArea(grid, r, c)) {
        return;
    }
    // 如果这个格子不是岛屿，直接返回
    if (grid[r][c] != 1) {
        return;
    }
    grid[r][c] = 2; // 将格子标记为「已遍历过」,防止重复遍历；
    
    // 访问上、下、左、右四个相邻结点（网格、图dfs遍历），类似于dfs遍历树根节点的childrens
    dfs(grid, r - 1, c);
    dfs(grid, r + 1, c);
    dfs(grid, r, c - 1);
    dfs(grid, r, c + 1);
}

// 判断坐标 (r, c) 是否在网格中
boolean inArea(int[][] grid, int r, int c) {
    return 0 <= r && r < grid.length 
        	&& 0 <= c && c < grid[0].length;
}










// 国际 most vote
public class Solution {

    private int n;
    private int m;
    
    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
        }    
        return count;
    }
    
    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }
}




// ============================================================================================================
// ============================================================================================================


我们可以将二维网格看成一个无向图，竖直或水平相邻的 11 之间有边相连。

为了求出岛屿的数量，我们可以扫描整个二维网格。
如果一个位置为1，则以其为起始节点开始进行深度优先搜索。
在深度优先搜索的过程中，每个搜索到的1都会被标记为2，表示已遍历过，防止重复遍历。
最终岛屿的数量就是我们进行深度优先搜索的次数。



/*
* 1.题目需求：
*	输入：一个由 '1'（陆地）和 '0'（水）组成的的二维网格（二维数组）
*	输出：计算出网格中岛屿的数量；
*   
*	附加信息：岛屿总是被水包围，每座岛屿只能由水平方向和/或竖直方向上相邻陆地 连接形成
*
*   信息翻译：网格中每个格子里的数字要么是0（海水），要么是1（陆地）；
*            （上下左右）相邻的陆地格子就构成了一个岛屿。（一个单独的陆地也算是一个岛屿？？对吗？？todo）
*
* 2.解法：贪心算法
*
* 3.解题思路：
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
* 4.复杂度分析:
*   时间：O(M*N)；最坏情况：for遍历计算M*N次；其中M和N分别为网格的行数和列数；
*   空间：O(M*N)；有几个陆地，就会dfs递归几次；最坏情况：整个网格全是陆地1，dfs遍历深度达到M*N；
 */

// 第二遍理解    2021年1月22日11:32:06
public class Solution {

    private int rows;
    private int cols;
    
    public int numIslands(char[][] grid) {

        int count = 0;  //记录岛屿数量（答案）
        rows = grid.length; //网格(二维数组)的行数
        if (rows == 0) return 0; //特判：网格(行数)为空，则岛屿为0。
        cols = grid[0].length; //网格的列数

        // 扫描整个网格，遇到陆地就进入DFS
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);  //dfs结束返回，表示找到了一个岛屿，count加一；
                    ++count;  
                }
            }
        }    
        return count;  //到这里表示已遍历完整个网格，输出最终的岛屿数量
    }
    
    private void dfs(char[][] grid, int r, int c) {
        // 1.递归结束条件：
        //   a.遍历超出网格边缘范围，数组越界;（相当于已遍历完(多叉树的)叶子节点，到底了，递归结束）
        //   b.当前格子不是(未被遍历的)陆地1；不是陆地1，不遍历。
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] != '1') {
            return;
        }
        // 2.处理当前层：给当前陆地打上已遍历标记2，防止重复遍历。
        grid[r][c] = 2; 
        // 3.继续深入DFS遍历children节点：在网格中，为当前节点的相邻子节点（上下左右 四个子节点）
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}



// 第一遍理解
public class Solution {

    private int rows;
    private int cols;
    
    public int numIslands(char[][] grid) {

        int count = 0;  //记录岛屿数量（答案）
        
        rows = grid.length; //网格(二维数组)的行数
        if (rows == 0) return 0; //特判：二维数组(行数)为空，则岛屿为0。
        cols = grid[0].length; //网格(二维数组)的列数

        // 扫描整个网格，遇到陆地格子，就进行DFS遍历；遍历完就表示找到一个岛屿，加一？？
        // todo dfs结束怎么就判断出表示找到一个岛屿的？？
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ++count;  //???逻辑怎么得来的？
                }
            }
        }    
        return count;
    }
    
    private void dfs(char[][] grid, int r, int c) {

        // 1.DFS递归结束条件：
        //   a.遍历超出网格边缘范围了，数组越界，递归结束；（判断格子 (r, c) 是否在网格中）
        //   b.这个格子不是陆地1，不遍历它，结束。
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] != '1') {
            return;
        }

        // 2.处理当前层：给已遍历过的陆地 打上标记，防止被重复遍历。
        grid[r][c] = 2; 
        
        // 3.深入下一层递归；
        // 访问当前节点的 【上、下、左、右四个相邻结点】（网格、图dfs遍历），类似于dfs遍历树根节点的childrens
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}
// 2021年1月21日22:08:26






//国内 most vote
// https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/

// 看过很多关于岛屿类问题的题解，觉得楼主写得最好，不仅通俗易懂，而且总结到位，
// 特别是从二叉树的深度优先延伸到图的深度优先遍历，很好的一个过渡作用，赞赞赞，我收藏了

// 2021年1月21日17:11:49








public class Solution {

    private int n;
    private int m;
    
    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    ++count;
                }
            }
        }    
        return count;
    }
    
    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }
}







