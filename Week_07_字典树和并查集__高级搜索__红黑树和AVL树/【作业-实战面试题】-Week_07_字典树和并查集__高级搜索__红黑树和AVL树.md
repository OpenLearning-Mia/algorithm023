## Week_07_字典树和并查集__高级搜索__AVL树和红黑树



### 第13课 字典树和并查集



#### 1.Trie树的基本实现和特性、代码模板

- [二叉树的层次遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
- [实现 Trie](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/)
- [Tire 树代码模板](https://shimo.im/docs/DP53Y6rOwN8MTCQH)



#### 2.Trie树实战题目解析：单词搜索2

##### 2.1.实战题目

- [实现 Trie (前缀树) ](https://leetcode-cn.com/problems/implement-trie-prefix-tree/#/description)（字节跳动、亚马逊、微软、谷歌在半年内面试中考过）
- [单词搜索 II ](https://leetcode-cn.com/problems/word-search-ii/)（亚马逊、微软、苹果在半年内面试中考过）
- 分析单词搜索 2 ，用 Tire 树方式实现的时间复杂度，请同学们提交在学习总结中。





#### 3.并查集的基本实现、特性和实战题目解析

##### 3.1.模板

- [并查集代码模板](https://shimo.im/docs/VtcxL0kyp04OBHak)



##### 3.2.实战题目

- [省份数量](https://leetcode-cn.com/problems/number-of-provinces/)（亚马逊、Facebook、字节跳动在半年内面试中考过）
- [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)（近半年内，亚马逊在面试中考查此题达到 361 次）
- [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)（亚马逊、eBay、谷歌在半年内面试中考过）





### 第14课 高级搜索



#### 1. 剪枝的实现和特性、代码模板

- [DFS 代码模板](https://shimo.im/docs/UdY2UUKtliYXmk8t)
- [BFS 代码模板](https://shimo.im/docs/ZBghMEZWix0Lc2jQ)
- [AlphaZero Explained](https://nikcheerla.github.io/deeplearningschool/2018/01/01/AlphaZero-Explained/)
- [棋类复杂度](https://en.wikipedia.org/wiki/Game_complexity)







#### 2. 剪枝实战题目解析：数独

##### 2.1.实战题目

- [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)（阿里巴巴、腾讯、字节跳动在半年内面试常考）
- [括号生成](https://leetcode-cn.com/problems/generate-parentheses/)（亚马逊、Facebook、字节跳动在半年内面试中考过）
- [N 皇后](https://leetcode-cn.com/problems/n-queens/)（亚马逊、苹果、字节跳动在半年内面试中考过）
- [有效的数独](https://leetcode-cn.com/problems/valid-sudoku/description/)（亚马逊、苹果、微软在半年内面试中考过）
- [解数独](https://leetcode-cn.com/problems/sudoku-solver/#/description)（亚马逊、华为、微软在半年内面试中考过）





常见的剪枝方式：
- **可行性剪枝**：指在当前情况与题意不符时，以及可以推导出后续情况都与题意不符，那么就进行剪枝，直接把这种情况及后续情况判负，返回。即：不可行，就返回。
- **排除等效冗余**：指当几个分支具有完全相同效果时，选择其中一个即可。即：都可以，选一个。
- **最优性剪枝**：指在用搜索方法解决最优化问题时的一种常用剪枝。就是搜索到一半时，发现相比已经搜索到的最优解，继续搜索不到更优解，那么停止搜索，进行回溯。即：有比较，选最优。
- **顺序剪枝**：普遍来说，搜索是不固定的，对于一个问题，算法可以进入搜索树的任意一个节点。假如需要搜索最小值，但非要从保存最大值的节点开始，那么可能要搜索到最后才有解；但是如果一开始从保存最小值的节点开始，那么可能立即得到解。这是顺序剪枝的一个应用。一般来说，有单调性存在的搜索问题，可以结合贪心算法，进行顺序剪枝。即：有顺序，按题意。
- **记忆化**：等同于记忆化搜索，搜索的一种分支。就是记录搜索的每一种状态，当重复搜索到相同状态时，直接返回已知解。即：搜重来，直接跳。









学友的学习总结：

547.省份数量

这道题是一个很典型的计算连通分量的问题。这类题一般可以用深度优先搜索和并查集来做。深度优先需要先给定一个is_visited数组来判断带搜索的元素是否已经搜索，如果没有搜索就对他进行深度优先搜索并且把返回的结果值+1 并查集的话思路就更简单了，直接对题目给定的二维数组进行上三角遍历即可。最后统计并查集中连通分量个数。

127 单词接龙

据说这是一道经典的广度优先搜索的题目。首先，为什么要用广度优先？先明确这个题需要我们得到的是单个单个单词变化后的最短路径，此时使用广度优先一层一层计算下去，得到的结果必然是最短的；如果使用深度优先，那么我们必须遍历所有路径并存储他们的所有路径长度，最后再选出最短的（或者每次得到一个路径就和当前最小的那个路径作比较并更新最小路径）。代码思路采用比较暴力所有字母遍历检查。不过看到答案中有用到虚拟结点的方法，可以说非常妙。







#### 3.双向BFS的实现、特性和题解

##### 3.1.实战题目

- [单词接龙](https://leetcode-cn.com/problems/word-ladder/)（亚马逊、Facebook、谷歌在半年内面试中考过）
- [最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/)（谷歌、Twitter、腾讯在半年内面试中考过）

##### 3.2.课后作业

- 总结双向 BFS 代码模版，请同学们提交在学习总结中。







#### 4.启发式搜索的实现、特性和题解

- [A* 代码模板](https://shimo.im/docs/8CzMlrcvbWwFXA8r)
- [相似度测量方法](https://dataaspirant.com/2015/04/11/five-most-popular-similarity-measures-implementation-in-python/)
- [二进制矩阵中的最短路径的 A* 解法](https://leetcode.com/problems/shortest-path-in-binary-matrix/discuss/313347/A*-search-in-Python)
- [8 puzzles 解法比较](https://zxi.mytechroad.com/blog/searching/8-puzzles-bidirectional-astar-vs-bidirectional-bfs/)

##### 4.1.实战题目

- [二进制矩阵中的最短路径](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/)（亚马逊、字节跳动、Facebook 在半年内面试中考过）
- [滑动谜题](https://leetcode-cn.com/problems/sliding-puzzle/)（微软、谷歌、Facebook 在半年内面试中考过）
- [解数独](https://leetcode-cn.com/problems/sudoku-solver/)（微软、华为、亚马逊在半年内面试中考过）







### 第15课 AVL树和红黑树



#### 1.AVL树和红黑树的实现和特性

- [维基百科：平衡树](https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree)



读操作非常非常多，写操作非常非常少的情况下，用AVL树，比如数据库，微博等，写操作非常多或者插入和删除操作一半一半，读操作非常少用红黑树比如map，set等。

2020-10-31** 评论** 3



前三条记住就行了，4、5两条就可以保证“任何一个结点的左右子树的高度差小于两倍”这个性质：
因为根据性质5，两条子树的黑色结点数量相同了，而根据性质4红色结点不能连续，
那么两个子树的深度差距最小的情况就是从根节点到叶子结点的路径上黑色结点和红色结点数目相同；
两个子树相差最大的情况就是就是一方全是黑结点，另一方有相同数目的黑结点，再加上和黑色结点数目相同的红色结点，此时相差两倍。

2021-01-13** 评论** 1



帮超哥补充一下，对于完整的树来说，应该是从底向上看，找第一个【左右子树高度差】绝对值大于1（也就是超出平衡因子）的节点开始操作。

2020-09-10** 评论** 0





### 本周作业及下周预习

#### 1.本周作业

##### 简单

- [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)（阿里巴巴、腾讯、字节跳动在半年内面试常考）

##### 中等

- [实现 Trie (前缀树) ](https://leetcode-cn.com/problems/implement-trie-prefix-tree/#/description)（亚马逊、微软、谷歌在半年内面试中考过）
- [省份数量](https://leetcode-cn.com/problems/number-of-provinces/)（亚马逊、Facebook、字节跳动在半年内面试中考过）
- [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)（近半年内，亚马逊在面试中考查此题达到 361 次）
- [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)（亚马逊、eBay、谷歌在半年内面试中考过）
- [有效的数独](https://leetcode-cn.com/problems/valid-sudoku/description/)（亚马逊、苹果、微软在半年内面试中考过）
- [括号生成](https://leetcode-cn.com/problems/generate-parentheses/)（亚马逊、Facebook、字节跳动在半年内面试中考过）
- [单词接龙](https://leetcode-cn.com/problems/word-ladder/)（亚马逊、Facebook、谷歌在半年内面试中考过）
- [最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/)（谷歌、Twitter、腾讯在半年内面试中考过）

##### 困难

- [单词搜索 II ](https://leetcode-cn.com/problems/word-search-ii/)（亚马逊、微软、苹果在半年内面试中考过）
- [N 皇后](https://leetcode-cn.com/problems/n-queens/)（亚马逊、苹果、字节跳动在半年内面试中考过）
- [解数独](https://leetcode-cn.com/problems/sudoku-solver/#/description)（亚马逊、华为、微软在半年内面试中考过）







#### 2.下周预习题目

- [LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/#/)
- [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/)





#### 3.训练场练习（选做）

学有余力的同学，可以挑战以下【训练场】模拟面试真题：









