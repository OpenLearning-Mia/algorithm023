




// 2021年2月19日16:09:48
// 1.进入dfs的前提条件：
//   1.未超出网格边界；2.当前是陆地1；3.当前陆地未被访问过；其他情况都不进入dfs;

// 2.网格DFS重复遍历问题
//  网格结构的 DFS 与二叉树的 DFS 最大的不同之处在于，遍历中可能遇到遍历过的结点。这是因为，网格结构本质上是一个「图」，我们可以把每个格子看成图中的结点，每个结点有向上下左右的四条边。在图中遍历时，自然可能遇到重复遍历结点。
//  这时候，DFS 可能会不停地「兜圈子」，永远停不下来。
//  所以每遍历完一个节点，都要对它打上已访问标记，防止重复访问、兜圈子。
/* 
1.从一个点扩散开，找到与其连通的点：
其实就是从一个点开始，进行一次「深度优先遍历」 或者「广度优先遍历」，发现一片连着的区域。
2.对于这道题来说，就是从一个是「陆地」 的格子开始进行一次「深度优先遍历」 或者「广度优先遍历」，
把与之相连的所有陆地都标记上，视为发现了一个「岛屿」。

 */


/*
* 1.题目需求：
*	输入：一个由 '1'（陆地）和 '0'（水）组成的的二维网格（二维数组）
*	输出：计算出网格中岛屿的数量；
*   
*	附加信息：岛屿总是被水包围，每座岛屿只能由水平方向和/或竖直方向上相邻陆地 连接形成
*
*   信息翻译：网格中每个格子里的数字要么是0（海水），要么是1（陆地）；
*            （上下左右）相邻的陆地格子就构成了一个岛屿。（一个单独的陆地也算是一个岛屿？？答：算）
*
* 2.解法：DFS
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
*   时间：O(M*N)；其中M和N分别为网格的行数和列数；遍历网格所有节点；（DFS,BFS，并查集）
*   空间：O(M*N)；有几个陆地，就会dfs递归几次；最坏情况：整个网格全是陆地1，dfs递归深度(栈)达到M*N；（DFS，并查集所有节点空间parent）BFS的space是多少？
*   I think also O(mn) space for both recursive/stack DFS and Queue BFS.???TODO
*   BFS-space：O(min(M,N))，在最坏情况下，整个网格均为陆地，队列的大小可以达到min(M,N)？？why??? todo   2021年2月19日20:34:49
 */



// 解法总概：
// 1.用Flood fill算法(DFS, BFS)
// 2.用并查集



// ==========================================================================================================================
// 方法一：深度优先搜索

/* 
思路一：深度优先遍历DFS
    1.目标是找到矩阵中 “岛屿的数量” ，上下左右相连的 1 都被认为是连续岛屿。
    2.dfs方法： 设目前指针指向一个岛屿中的某一点 (i, j)，寻找包括此点的岛屿边界。
        i.从 (i, j) 向此点的上下左右 (i+1,j),(i-1,j),(i,j+1),(i,j-1) 做深度搜索。
        ii.终止条件：
            (i, j) 越过矩阵边界;
            grid[i][j] == 0，非陆地，不遍历。
        iii.搜索岛屿的同时，执行 grid[i][j] = '0'，即将岛屿所有节点删除，以免之后重复搜索相同岛屿。
    3.主循环：
        遍历整个矩阵，当遇到 grid[i][j] == '1' 时，从此点开始做深度优先搜索 dfs，岛屿数 count + 1 且在深度优先搜索中删除此岛屿。
        最终返回岛屿数 count 即可。

作者：jyd
链接：https://leetcode-cn.com/problems/number-of-islands/solution/number-of-islands-shen-du-you-xian-bian-li-dfs-or-/
 */


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
        /* 
            // 进入DFS条件：1如果不越界、2还是陆地、3没有被访问过visited

            if (inArea(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                dfs(newX, newY);
            }
         */

/*       // 1.递归结束条件：
        //   a.遍历超出网格边缘范围，数组越界;（相当于已遍历完(多叉树的)叶子节点，到底了，递归结束）
        //   b.当前格子不是(未被遍历的)陆地1；不是陆地1，不遍历。
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] != '1') {
            return;
        } 
*/
        // 2.处理当前层：打标记，给当前陆地打上已遍历标记2，防止重复遍历。
        grid[r][c] = '#'; //如果不允许【修改输入】，则新建一个二维数组，记录每个陆地是否被访问过visited[r][c]；
/* 
        3.继续深入DFS遍历的前提条件：1.不越界，2.是陆地1，3.没有被访问过visited
        如果不满足上述条件，不对当前节点dfs递归
        递归结束条件：网格越界、不是陆地1，不dfs遍历；
        继续深入DFS遍历时的children节点指：在网格中，为当前节点的相邻子节点（上下左右 四个子节点），相当于多叉树孩子节点的遍历；
*/
        // 【BFS过程】：这(上下左右)每个节点上的DFS，都会各自继续(上下左右)递归、深入到底(直到没有陆地才结束)，
        //  类似于遍历多叉树，每个分支各自DFS到叶子节点才结束
        if (r - 1 >= 0 && grid[r-1][c] == '1') dfs(grid, r - 1, c); // 上
        if (r + 1 < rows && grid[r+1][c] == '1') dfs(grid, r + 1, c); // 下
        if (c - 1 >= 0 && grid[r][c-1] == '1') dfs(grid, r, c - 1); // 左
        if (c + 1 < cols && grid[r][c+1] == '1') dfs(grid, r, c + 1); // 右
    }
}





// 2021年1月21日22:08:26 dfs第一遍理解 
// 2021年1月22日11:32:06 第二遍理解  done-first

//国内 most vote
// https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/

// 看过很多关于岛屿类问题的题解，觉得楼主写得最好，不仅通俗易懂，而且总结到位，
// 特别是从二叉树的深度优先延伸到图的深度优先遍历，很好的一个过渡作用，赞赞赞，我收藏了

// 2021年1月21日17:11:49

/* 
关于【修改输入值】的问题：
这里有一点需要注意：解决这个问题是可以不用建立 marked 数组，但有以下的知识点需要大家知道。

1.对于算法的输入而言，很多时候是介意修改输入的，除非题目就是要我们修改输入数据；
2.再者 marked 数组没有必要节约这个空间，现在空间越来越不值钱了，而且程序执行完成以后，空间可以回收。我们写算法的时候，应该力求时间复杂度最优，并且代码可读性强。
这里是 liuyubobobo 老师对这一类问题的解释：如果面试遇到这个问题，这一点最重要！。

*/



// ==========================================================================================================================
// 方法二：广度优先搜索

/* 
思路二：广度优先遍历 BFS
    1.主循环和思路一类似，不同点是在于搜索某岛屿边界的方法不同。
    2.bfs 方法：
        i.借用一个队列 queue，判断队列首部节点 (i, j) 是否未越界且为 1：
            * 若是则置零（删除岛屿节点），并将此节点上下左右节点 (i+1,j),(i-1,j),(i,j+1),(i,j-1) 加入队列；
            * 若不是则跳过此节点；
        ii.循环pop队列首节点，直到整个队列为空，表示此时已遍历完 当前岛屿；回到主循环，找下一个岛屿。

作者：jyd
链接：https://leetcode-cn.com/problems/number-of-islands/solution/number-of-islands-shen-du-you-xian-bian-li-dfs-or-/
 */

// c++
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int nr = grid.size();
        if (!nr) return 0;
        int nc = grid[0].size();
        int num_islands = 0;

        // 开始BFS广度优先遍历流程：遍历网格所有节点，由中心向周围扩散，类似洪水泛滥的过程；
        for (int r = 0; r < nr; ++r) { //行
            for (int c = 0; c < nc; ++c) { //列
                // 只遍历陆地，其他的跳过
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';  //对遍历过的陆地打上已访问标记，防止后面重复遍历该陆地；
                    queue<pair<int, int>> neighbors;
                    neighbors.push({r, c});  //当前陆地入队列

                    // 如下是BFS代码块（可以优化成BFS接口？）：
                    // BFS遍历当前陆地的相邻陆地(上下左右) ：
                    // 队列的先进先出，能达到BFS的效果：类似于洪水泛滥，由中心向外扩散
                    while (!neighbors.empty()) { // 循环结束条件：队列为空
                        // 1.陆地出队列
                        auto rc = neighbors.front();
                        neighbors.pop();
                        int row = rc.first, col = rc.second;

                        // 2.向【上下左右】继续dfs遍历相邻陆地
                        // 上
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            // 找到一个新陆地，对它进行BFS(陆地入队列);
                            neighbors.push({row-1, col});
                            grid[row-1][col] = '0'; // 特别注意：在放入队列以后，要马上标记成已访问过，反正只要进入了队列，迟早一定会遍历到它
                            // 而不是在出队列的时候再标记，如果是出队列的时候再标记，会造成很多重复的结点进入队列，造成重复的操作，这句话如果你没有写对地方，代码会严重超时的
 
                        }
                        // 下
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.push({row+1, col});
                            grid[row+1][col] = '0';
                        }
                        // 左
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.push({row, col-1});
                            grid[row][col-1] = '0';
                        }
                        // 右
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.push({row, col+1});
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }
};

// BFS代码块 优化成接口bfsFill，java：
public int numIslands(char[][] grid) {
    int count=0;
    for(int i=0;i<grid.length;i++)
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]=='1'){
                bfsFill(grid,i,j);
                count++;
            }
        }
    return count;
}
private void bfsFill(char[][] grid,int x, int y){
    grid[x][y]='0';
    int n = grid.length;
    int m = grid[0].length;
    LinkedList<Integer> queue = new LinkedList<Integer>();  
    int code = x*m+y;  
    queue.offer(code);  
    while(!queue.isEmpty())  
    {  
        code = queue.poll();  
        int i = code/m;  
        int j = code%m;  
        if(i>0 && grid[i-1][j]=='1')    //up；search upward and mark adjacent '1's as '0'.
        {  
            queue.offer((i-1)*m+j);  
            grid[i-1][j]='0';  
        }  
        if(i<n-1 && grid[i+1][j]=='1')  //down
        {  
            queue.offer((i+1)*m+j);  
            grid[i+1][j]='0';  
        }  
        if(j>0 && grid[i][j-1]=='1')  //left
        {  
            queue.offer(i*m+j-1);  
            grid[i][j-1]='0';  
        }  
        if(j<m-1 && grid[i][j+1]=='1')  //right
        {  
            queue.offer(i*m+j+1);  
            grid[i][j+1]='0';  
        }
    } 
}
// 2021年2月19日16:52:46 start
// 2021年2月19日20:15:02  done bfs



/* 
说明：1.DFS 和 BFS 都属于很常见的算法知识，也非常好理解，写法也相对固定，
    2.读者需要多写，发现并记录自己的问题。
    3.我也是在写了几遍甚至是在写本题解的过程中，才发现出自己的问题。

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/number-of-islands/solution/dfs-bfs-bing-cha-ji-python-dai-ma-java-dai-ma-by-l/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。 */











// ==========================================================================================================================
// 方法三：并查集

/* 
1.关于连通性问题，并查集也是常用的数据结构。
2.我们可以使用并查集代替搜索。
    为了求出岛屿的数量，我们可以扫描整个二维网格。
    如果当前节点是陆地1，则将其与相邻四个方向上的陆地在并查集中进行合并；
    合并结束条件：当前岛屿相邻处已没有陆地可合并了；
    此时结束合并，回到主循环，找下一块新岛屿；
    最终岛屿的数量就是并查集中连通分量的数目。
*/


/* 
* 3.并查集实现流程：先把每个陆地初始化成没有后代的祖宗（集合）；然后再合并相邻的祖宗（集合）
*
* 4.并查集复杂度分析:
*   时间：O(M*N*α(MN))；其中M和N分别为网格的行数和列数；遍历网格所有节点；
*        注意: 当使用路径压缩（见 find 函数）和按秩合并（见数组 rank）实现并查集时，并查集单次操作的时间复杂度为α(MN),其为常数时间复杂度；
*              所以最终时间复杂度可看作为O(M*N)；
*   空间：O(M*N)；为并查集需要的空间parent；
*
*  5.做题感受记录：并查集处理的对象是集合（树形结构）；岛屿本质就是一个【集合】，集合里的成员为陆地；
* 并查集就是连通性的应用
*
* 优化：You only need to do it for two directions, which are "right" and "down", because all "left" and "up" has already been seen if exists connection (undirected for Union Find).
*/
class Solution {

    // UnionFind方法集，代码模板
    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;  //记录祖先(集合)个数，也就是岛屿个数，最终答案；
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n]; //一维数组 存储节点的父亲parent[i]
            rank = new int[m * n];

            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    
                    // 只初始化所有陆地节点为祖宗；
                    if (grid[i][j] == '1') {
                        // 初始化祖宗：每个【陆地节点】都是没有后代的独立祖宗(将自己的父亲填为自己)；然后 后面再合并 那些相邻的祖宗们；
                        parent[i * n + j] = i * n + j;  //(i*n+j)表示将二维元素降为一维存储
                        ++count;  //记录祖宗个数（也就是集合个数）
                    }
                    // 初始化祖宗家族成员大小为0；（tips:这里家族成员个数 不包含祖宗本人）
                    rank[i * n + j] = 0;  //这rank用来计数祖宗i家族成员(集合里节点)个数，【按秩合并】中会用它比较哪个祖宗家庭大，谁大谁当祖宗；
                }
            }
        }

        public int find(int i) {
            // 1.递归法找祖宗；祖宗特点：祖宗的父亲是它自己(parent[i] == i)
            if (parent[i] != i) //如果i不是祖宗，则查找i的父亲，看i的父亲、爷爷是不是祖宗，一直往上挨个找，最终能找到祖宗(parent[i] = i)
                // 1.1.路径压缩：找到祖宗后，将所以后代直接指向该祖宗(parent[i] = root),降低后续查询的时间复杂度，查找为常数级；
                parent[i] = find(parent[i]);
            
            // 2.输出祖宗
            return parent[i];
        }

        public void union(int x, int y) { 
            // 1.找祖宗：找出x，y各自的祖宗root
            int rootx = find(x);
            int rooty = find(y);
            
            if (rootx != rooty) { // 如果rootx == rooty，表示x，y是同一祖宗、属于同一个集合，无需再合并；
                
                // i.按秩合并：谁大谁当祖宗；和路径压缩一样，目的是 降低并查集的时间复杂度
                // rank[rootx]记录着该祖宗的家庭成员个数 (集合里节点个数，不包含祖宗节点)
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;  //rootx祖宗大，则rooty 认rootx为祖宗
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else { //两祖宗一样大,则rooty的祖宗是rootx
                    parent[rooty] = rootx;
                    rank[rootx] += 1;  //对rootx祖宗的家庭成员个数进行计数，这计数用来做按秩合并时的比较，比较出谁大谁当祖宗
                }
                --count;  //两祖宗合并一次，就少一个祖宗；最终的合并结束后，cont就算最终的祖宗个数(集合个数)，也就是题目要求的岛屿个数；岛屿就是一个集合
            }
        }

        public int getCount() {
            return count;
        }
    } // UnionFind代码模板 end

    // 求岛屿数量
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);

        // 遍历网格所有节点，只找陆地，合并和它相邻的陆地
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                // 
                if (grid[r][c] == '1') {
                    grid[r][c] = '#'; //打上已访问标记
/*
                    // 上
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        // 合并grid[r][c]和grid[r-1][c]
                        uf.union(r * nc + c, (r-1) * nc + c);
                    } 
                    // 左
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        // 合并grid[r][c]和grid[r][c-1]
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
*/
                    // 注意，优化：不用遍历【上下左右】四个方向：这里只用向右、下两个方向遍历合并，因为左上两个方向的已经遍历合并过；可以推演看看
                    // You only need to do it for two directions, which are "right" and "down", 
                    // because all "left" and "up" has already been seen if exists connection (undirected for Union Find).
                    // 并查集 不具有方向性，不像DFS,BFS，得往四个方向遍历；
                    
                    // 右
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        // 合并grid[r][c]和grid[r][c+1]
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                    // 下
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        // 合并grid[r][c]和grid[r+1][c]
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                }
            }
        }

        return uf.getCount();
    }
}



// 2021年2月19日23:32:50 并查集 done-first
// todo,关于并查集的解法，只看了这一个，明天再看两个岛屿并查集代码思路实现，综合比较，取好的思路点，确定出它的最佳思路和代码实现；
// 2021年2月19日23:40:00





// 【网友讨论区】
/* 
1.rank数组在这道题里是多余的，直接把两个节点连接在一起就行了，不需要分情况。？？？

@SeptemberWard rank里存的是树的深度，意思是每次union都让矮树贴到高树上，但find里路径压缩操作也会改变树的深度，所以rank没有用。如果rank里存树的规模的话，每次union都让小树贴到大树上，会比较有意义。

rank是按秩合并 和这里find里面已经路径压缩了。 这是两种优方式，题解这种方法确实不需要rank。

rank用来降低并查集的高度,find的时候就更快.

并查集的优化有按秩合并和路径压缩两种，这里的rank数组就是秩，其实就是树的高度，也就是说每次合并的时候要把高度小的树插入到高度大的树上，这样除非两个树高度相等，否则插入后树的高度也不会变的。

秩，其实就是树的高度????搞清楚这里秩、按秩合并的确切概念；todo

那么个并查集里面，rank是做什么用的呢??

@TasiaTong rank 是并查集里「按秩合并」这个操作，表示以当前结点为根结点的子树的高度，但是这个定义不是很准确，所叫「秩」，而不是叫「高度」。具体您可以查看我投稿给「力扣」第 990 题的 视频题解 ，在 5 分 10 秒左右有解释什么是「按秩合并」。


节约空间其实一般来说不是优化的方向，空间成本是很低的。这里二维坐标和一维整数互相转换是常见的技巧，转换成一维的好处是判重比较容易。


2.bfs空间复杂度min（M，N）怎么得出来的？？？
    考虑矩阵全 1 的情况，neighbors 维护的是矩阵的对角线，从左上往右下遍历，所以最大为 min(M,N)

    自己画一个4*4的矩阵，用bfs方法去感染，发现确实是对角线感染时最多

    深度优先算法是 像一条蛇一样，不停的往前走。 所以时间复杂度是所有的格子的数量M*N 。 广度优先算法就是 二叉树中的按层遍历。每次扫描一层。在这个题目中，的按层遍历（广度优先）不是按行，也不是按列，而是按照斜率为45度的方向 斜着斜着的扫描。因为最长的斜线 斜着的格子的数量等于min（M, N） 所以，斜着扫描（广度优先）的空间复杂度等就是 min（M, N）

    从左上角往右下角,一条斜线斜着感染,斜线的长度就是min(n,m)



3. 修改输入的问题
不好意思，我今天才看到您的留言，解决这个问题是可以不用 mark 数组，但不建议。
通常而言的算法问题，是介意修改输入数据（除非题目就是要我们修改输入数据），但不介意使用额外空间的。
oj 通常只能判定代码对错和代码是否超时，但写程序的一些很细节的地方，例如方法名、变量名是否清晰、逻辑是否封装得好，还有一些程序设计方面，这些角度可能是面试的时候面试官在意的内容。


 */





//  我 做题感受总结：增加【查看评论区】这一环节，能发现自己不知道的，没有理解清楚的；
/* 因为评论区有很多网友提问的，这些问题和讨论引发人思考，看到不同的见解和思维；
对自己是查漏补缺，看网友的提问和见解，看自己能否回答上来，验证我自己是否真的理解了这个题思想；
这是非常有价值的一个行动环节，因为交流讨论才能看到多角度多元化思维，一个人的认识有局限性，有时还理解得不到位，模模糊糊，难受；
我又没有人交流，而评论区有大量的网友在交流、提出和我类似的疑问、给出各自的见解；
真的让人长见识，对这个题会有进一步透彻的理解；
总结：要有【查看评论区】这一环节，是柏拉图吴军说的讨论交流环节，是增加见识和理解的重要环节；
练习掌握这个方法，能事半功倍(至少不会陷入经常犯的事倍功半)

如上是今早看评论区讨论 得出的感受体会 (也是因为对题解复杂度分析有疑问，才跑去看评论区，看是否有和我相同疑问的，及其解答和看法)

把【查看评论区】加入做题的重要一环节；做题的重要环节、做题的流程是咋样的？？复习回顾一下？？？
TODO

2021年2月20日08:41:54
*/







// 并查集代码二
/* 
方法三：并查集
关于连通性问题，并查集也是常用的数据结构。

思路：
    并查集中维护连通分量的个数，在遍历的过程中：
    1.相邻的陆地（只需要向右看和向下看）合并，只要发生过合并，岛屿的数量就减少 11；
    2.在遍历的过程中，同时记录空地的数量；
    3.并查集中连通分量的个数 - 空地的个数，就是岛屿数量。
*/

public class Solution {

    private int rows;
    private int cols;

    public int numIslands(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;

        // 空地的数量
        int spaces = 0;
        UnionFind unionFind = new UnionFind(rows * cols);
        int[][] directions = {{1, 0}, {0, 1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0') {
                    spaces++;
                } else {
                    // 此时 grid[i][j] == '1'
                    for (int[] direction : directions) {
                        int newX = i + direction[0];
                        int newY = j + direction[1];
                        // 先判断坐标合法，再检查右边一格和下边一格是否是陆地
                        if (newX < rows && newY < cols && grid[newX][newY] == '1') {
                            unionFind.union(getIndex(i, j), getIndex(newX, newY));
                        }
                    }
                }
            }
        }
        return unionFind.getCount() - spaces;
    }

    private int getIndex(int i, int j) {
        return i * cols + j;
    }

    private class UnionFind {
        /**
         * 连通分量的个数
         */
        private int count;
        private int[] parent;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];  //路径压缩
                x = parent[x]; 
            }
            return x;
        }

        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
            if (xRoot == yRoot) {
                return;
            }

            parent[xRoot] = yRoot;
            count--;
        }
    }
}
/* 
作者：liweiwei1419
链接：https://leetcode-cn.com/problems/number-of-islands/solution/dfs-bfs-bing-cha-ji-python-dai-ma-java-dai-ma-by-l/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 */

//  2021年2月20日09:36:49