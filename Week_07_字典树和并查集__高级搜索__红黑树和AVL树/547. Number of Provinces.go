// 2021年2月16日14:32:34



// ==============================================================================================================
// 方法一：深度优先搜索（先竖向到底再横向）

// 计算省份总数，等价于计算图中的连通分量数，
// 可以通过深度优先搜索或广度优先搜索实现，也可以通过并查集实现。
/* 
* 4.【复杂度分析】:
*   时间：O(n^2)；其中n为城市数量；需要遍历(该二维矩阵n*n中的)每个元素；    
*   空间：O(n)；其中n为城市数量; 需要使用数组visited记录每个城市是否被访问过，数组长度是n；
*              dfs递归调用栈的深度不会超过 n个城市。
*/

class Solution {
    public int findCircleNum(int[][] isConnected) {

        // 1.初始化；
        int cities = isConnected.length; // 1.1.获取城市数量
        boolean[] visited = new boolean[cities]; // 1.2.visited用来标记城市i是否被访问过visited[i]，防止城市被重复访问；
        int provinces = 0; // 1.3.省份个数，最终答案；

        // 2.开始进入dfs(深度遍历)城市节点流程
        for (int i = 0; i < cities; i++) {
            // 进入深度遍历的前提条件：该城市未被访问过：visited[i] = false;
            // 若当前结点i未被访问，说明又是一个新的连通域，则dfs遍历新的连通域且provinces+=1.
            if (!visited[i]) {
                // 对当前结点i进行深度遍历；将城市i传入，与其他城市比较是否相连：isConnected；
                dfs(isConnected, visited, cities, i);
                // 此轮dfs遍历结束，省份个数+1;(tips:单独一个城市也算是一个省份)
                provinces++;
            }
        }

        // 3.到这里表示遍历完所有城市了，输出省份个数；
        return provinces;
    }


    public void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
        // 深度遍历流程：
        // 深度遍历的前提条件：1.当前两城市i,j是连通的，2.并且当前城市j未被访问过；
        // 
        for (int j = 0; j < cities; j++) {
            // 进入dfs的条件：当前两城市连通,不连通不继续dfs,当前城市j被访问过，也不dfs,防止重复访问；
            if (isConnected[i][j] == 1 && !visited[j]) {
                // 给当前城市j打上已访问标记，防止下面dfs里再重复访问；
                visited[j] = true;
                // 进入dfs：对当前城市j继续深度遍历；
                dfs(isConnected, visited, cities, j);
            }
        }
    }
}


// 国际most vote   DFS java solution

public class Solution {
    public void dfs(int[][] M, int[] visited, int i) {

        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
}


// ==============================================================================================================
// 解法二：广度优先搜索（先横向到底再竖向）
/* 
* 4.【复杂度分析】:
*   时间：O(n^2)；其中n为城市数量；需要遍历(该二维矩阵n*n中的)每个元素；    
*   空间：O(n)；其中n为城市数量; 需要使用数组visited记录每个城市是否被访问过，数组长度是n；广度优先搜索bfs 使用的队列元素个数 不会超过n。
*/
class Solution {
    public int findCircleNum(int[][] isConnected) {
        // int[][] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
        int n = isConnected.length;
        // 定义 boolean 数组标识顶点是否被访问
        boolean[] visited = new boolean[n];
        
        // 定义 cnt 来累计遍历过的连通域的数量
        int cnt = 0;  
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 若当前顶点 i 未被访问，说明又是一个新的连通域，则bfs新的连通域且cnt+=1.
            if (!visited[i]) {
                cnt++;
                queue.offer(i);  //城市i入队列
                visited[i] = true;
                
                //循环终止条件：队列为空
                while (!queue.isEmpty()) {  
                    int v = queue.poll(); //城市出队列
                    // 横向广度优先遍历城市，将当前的v城市与横向遍历的城市w进行比较是否相连；
                    // 广度优先遍历的前提条件：1.当前两城市连通；2.当前城市w未被访问过
                    for (int w = 0; w < n; w++) {
                        if (isConnected[v][w] == 1 && !visited[w]) {
                            visited[w] = true;
                            queue.offer(w);
                        }
                    }
                }
            }
        }
        return cnt;
    }
} 




// ==============================================================================================================

// 方法三：并查集
/* 
* 4.【复杂度分析】:
*   时间：O(n^2*logn)；其中n为城市数量；需要遍历(该二维矩阵n*n中的)每个元素；
*                     最坏情况下的时间复杂度是 O(n^2*logn);平均情况下的时间复杂度依然是 O(n^2*α(n))，其中α为阿克曼函数的反函数，α(n) 可以认为是一个很小的常数;
    
*   空间：O(n)；其中n为城市数量; 需要使用数组parent存储记录每个城市的父亲(祖先)，find递归最深不超过n;
*/
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        // 初始化并查集
        UnionFind uf = new UnionFind(n);

        // 遍历每个顶点，将当前顶点与其邻接点进行合并
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        // 返回最终合并后的集合的数量
        return uf.size;
    }
}

// 并查集
class UnionFind {
    int[] parent;


    int size; // 集合数量
    
    public UnionFind(int n) {

		// 初始化n个独立的祖先；
        parent = new int[n];
        for (int i = 0; i < n; i++) {
			// 初始化祖先：自己的父亲是自己
			// 含义：i的父亲parent[i]是它自己i;
            parent[i] = i; 
        }
        size = n;  //size表示祖先（集合）个数，初始情况下，祖先个数为n
    }

	// find: 找出祖先root
	// 祖先的特点：祖先的父亲是它自己，即：parent[root] = root
    public int find(int i) { 
		// 找出祖先:第i个节点的父亲是它自己，则它是祖宗节点
        if (parent[i] != i) {
			//递归法 找出祖先root
	   		parent[i] = find(parent[i]);	
        }
        return parent[i];  //找到祖先了,递归结束后parent[i]就是被找到的祖先root；
    }
/* 
	// 迭代法：查找祖先root
	public int find(int i) {  
		root = i;
		// 1.找出祖先root
		// 祖先的特点：祖先的父亲是它自己，即：parent[root] = root
		while (parent[root] != root) { //表示当前root不是祖宗
			// 如下parent[root]表示当前root的父亲；表示接下来找他父亲是不是祖宗
			root = parent[root];   
		}

        // 2.路径压缩：把i的父亲直接填为祖宗root
        parent[i] = root;

		// 3.走到这里，表示找到祖先了
		return root; 
	}

	// 精简版：
	public int find(int i) {
		while (path[i] != i) 
			i = path[i];
		return i;
	}	

*/

    public void union(int p, int q) {

		// 1.找出两节点各自的祖先root
        int pRoot = find(p);
        int qRoot = find(q);
	
 		// 如果两祖先相同，表示是同一个祖先，不用合并，return；
		if (rootP == rootQ) return; 
		
		//union：到这里表示两祖先不同，合并它们；
		parent[rootP] = rootQ; 
		size--;  //合并两个祖先，就少了一个祖先（少一个集合）

/*      
		// 另一种简便写法：
		if (pRoot != qRoot) {
            parent[pRoot] = qRoot;
            size--;
        } */
    }
}





// 并查集模板1
// Java
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) {  
		root = p;
		// 找出祖先root
		// 祖先的特点：祖先的父亲是它自己，即：parent[root] = root
		while (parent[root] != root) { 
			root = parent[root]; 
		}
		
		return root; //找到祖先了，
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 

		// union：两根节点不同，合并他们；
		if (rootP != rootQ) {  // 如果两根节点相同，表示是同一个节点，不用合并；
			parent[rootP] = rootQ; 
			count--;  //合并一个根节点，就少一个根节点（少一个集合）
		}

	}
}



// 并查集模板2
class UnionFind {
    private Map<Integer,Integer> father;
    
    public UnionFind() {
        father = new HashMap<Integer,Integer>();
    }
    
    public void add(int x) {
        if (!father.containsKey(x)) {
            father.put(x, null);
        }
    }
    
    public void merge(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY){
            father.put(rootX,rootY);
        }
    }
    
	// 找出祖先root
	// 祖先的特点：祖先的父亲是它自己，即：parent[root] = root
    public int find(int x) {
        int root = x;
        
        while(father.get(root) != null){
            root = father.get(root);
        }
        
        while(x != root){
            int original_father = father.get(x);
            father.put(x,root);
            x = original_father;
        }
        
        return root;
    }
    
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
} 


// 2021年2月17日21:23:13 第三遍，看花花酱的视频讲解，给理解上了，找到原因是：没理解题目的意思和附加条件等情况，对题目含义现状认识没到位。
// todo:接下来写出自己的注释理解（如上三种解法的注释理解）；便于后续复习巩固，也验证下自己是否真的理解了；
// todo2:还有这三种解法的复杂度分析是啥情况？done
// 2021年2月19日09:46:09 done first-time;















/* 
并查集的典型应用是有关连通分量的问题
并查集解决单个问题（添加，合并，查找）的时间复杂度都是O(1)

并查集跟树有些类似，只不过她跟树是相反的。在树这个数据结构里面，每个节点会记录它的子节点。在并查集里，每个节点会记录它的父节点。


这里有一个优化的点：如果我们树很深，比如说退化成链表，那么每次查询的效率都会非常低。所以我们要做一下路径压缩。也就是把树的深度固定为二。






1.审题，分析

输入：

输出：

附加条件：


隐含条件，翻译：
	1.自己和自己，isConnected[i][i] 一定是一个连通域、一个省份；count：+1

	2.visited[i]表示第i个城市是否被访问过；

	3.第i个城市与第j个城市相邻，则第j个城市一定与第i个城市相邻,	
	即：isConnected[i][j] = isConnected[j][i]

	4.搞清楚dfs、bfs的遍历条件和遍历轨迹、流程 应该是怎么样的？关键点：写出来，确定出来；
	  4.1.能写出来(输出)，才能证明自己是真清楚了、搞懂了；
	  4.2.我常犯误区：觉得自己懂了、觉得自己清楚了，觉得在脑子里懂了，不用写出来或表达说出来；
	  4.3.写出来！写出来！写出来！
	  4.4.【输出】是王道！输出是王道！输出是王道！
	  4.5.重要的事说三遍！
	  4.6.上帝喜欢笨人，上帝喜欢踏实勤奋的人（笨人），
	  4.7.偷懒是恶果，偷懒是阻碍自我发展和成功的巨大障碍！

分析：



 */





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


