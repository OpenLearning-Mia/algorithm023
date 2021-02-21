## Week_07_字典树和并查集__高级搜索__红黑树和AVL树



### 第13课 字典树和并查集



#### 1.应用场景？？



##### TBD: 

google，收藏夹







#### 2.Trie树的基本实现和特性、代码模板

- [二叉树的层次遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
- [实现 Trie](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/)
- [Tire 树代码模板](https://shimo.im/docs/DP53Y6rOwN8MTCQH)



字典树Trie



Trie 树中最常见的两个操作是键的插入和查找。

Trie 是一颗非典型的多叉树模型，多叉好理解，即每个结点的分支数量可能为多个。

为什么说非典型呢？因为它和一般的多叉树不一样，尤其在结点的数据结构设计上，比如一般的多叉树的结点是这样的：

```c++
C++
struct TreeNode {
    VALUETYPE value;    //结点值
    TreeNode* children[NUM];    //指向孩子结点
};


而 Trie 的结点是这样的(假设只包含'a'~'z'中的字符)：
C++
struct TrieNode {
    bool isEnd; //该结点是否是一个串的结束
    TrieNode* next[26]; //字母映射表
};
```



要想学会 Trie 就得先明白它的结点设计。我们可以看到TrieNode结点中并没有直接保存字符值的数据成员，那它是怎么保存字符的呢？

这时字母映射表next 的妙用就体现了，TrieNode* next[26]中保存了对当前结点而言下一个可能出现的所有字符的链接，因此我们可以通过一个父结点来预知它所有子结点的值



























腾讯一面，soeasy，刷过题的我只用了5分钟就写出来啦！

之前面试，面试官问了我一个问题，说如果用户在搜索引擎中搜索的内容是敏感内容，如何判别出来，然后不显示搜索结果？我的回答是，将敏感词放入Hash表中，然后对用户的搜索内容进行分词，将分出的词去Hash表中匹配，若有匹配的词，则表示是敏感内容。面试官说分词不一定准确，这个方法不可行，然后提示我用字典树。但是我现在看了字典树之后，发现使用字典树解决这个问题，也是需要对搜索内容进行分词，然后通过分出的词进行匹配吧。求解答！

7踩查看 13 条回复回复分享举报





由于这段时间在学校上课了，刷题也只有每天晚上刷两道题了，不能像暑假那样刷题一肝到底了。前缀树是上个星期开始刷的，leetcode**总共有17个前缀树的题目**（其中包括3个收费题，所以**我把14个免费题做完了），今天总结下，以后复习用。**











#### 2.Trie树实战题目解析：单词搜索2



#### 3.并查集的基本实现、特性和实战题目解析

##### 3.1.模板

- [并查集代码模板](https://shimo.im/docs/VtcxL0kyp04OBHak)



并查集的典型应用是有关连通分量的问题

并查集解决单个问题（添加，合并，查找）的时间复杂度都是O(1)



并查集跟树有些类似，只不过她跟树是相反的。在树这个数据结构里面，每个节点会记录它的子节点。在并查集里，每个节点会记录它的父节点。





这里有一个优化的点：如果我们树很深，比如说退化成链表，那么每次查询的效率都会非常低。所以我们要做一下路径压缩。也就是把树的深度固定为二。

这么做可行的原因是，并查集只是记录了节点之间的连通关系，而节点相互连通只需要有一个相同的祖先就可以了。



路径压缩的时间复杂度为O*(log∗*n*)，可以认为O(\log^*n) = O(1)；，即时间复杂度为 O(1)，常量级





**科普一下并查集各种情况下的时间复杂度**

不要再说加了xx 优化yy 优化之后并查集的时间复杂度是 O(1) 了，O(1) 是不可能的。

//说是常数级；



所有想深入了解下去的知识点都可以去看  [算法 4 - Union Find](https://algs4.cs.princeton.edu/15uf/)，[OI Wiki - 并查集](https://oi-wiki.org/ds/dsu/) 以及  [CF Edu Disjoint Sets Union](https://codeforces.com/edu/course/2/lesson/7/1)。



优化
并查集一般有两种优化，这里规范一下表达：

- 「路径压缩」：在并查集中查找代表元素时，会将经过的所有元素「直接」连接到代表元素，也就是将连通分量「压扁」。

- 「按秩合并」：在并查集中合并两个连通分量时，将「秩」小的连通分量合并到「秩」大的连通分量上面。这里「秩」可以定义为连通分量的大小（包含的节点数量），或者连通分量的高度（连通分量是树的结构，因此可以定义高度。不过在「路径压缩」优化的基础上，这个高度会不断减小，但我们不用去时刻维护它，这样也可以达到最优的时间复杂度是已经被证明的了）。




![学习笔记和总结-Week_07-并查集时间复杂度分析-原始+优化](D:\1-ruany-dir\3-算法训练营23期-1月至3月\algorithm023\Week_07_字典树和并查集__高级搜索__红黑树和AVL树\学习笔记和总结-Week_07-并查集时间复杂度分析-原始+优化.png)

**空间复杂度[¶](https://oi-wiki.org/ds/dsu/#_8)**

显然为 O(n)。节点个数n,n个存储空间。





**查找[¶](https://oi-wiki.org/ds/dsu/#_2)**

通俗地讲一个故事：几个家族进行宴会，但是家族普遍长寿，所以人数众多。由于长时间的分离以及年龄的增长，这些人逐渐忘掉了自己的亲人，只记得自己的爸爸是谁了，而最长者（称为「祖先」）的父亲已经去世，他只知道自己是祖先。为了确定自己是哪个家族，他们想出了一个办法，只要问自己的爸爸是不是祖先，一层一层的向上问，直到问到祖先。**如果要判断两人是否在同一家族，只要看两人的祖先是不是同一人就可以了。**

在这样的思想下，并查集的查找算法诞生了。



**路径压缩[¶](https://oi-wiki.org/ds/dsu/#_3)**

这样的确可以达成目的，但是显然效率实在太低。为什么呢？因为我们使用了太多没用的信息，我的祖先是谁与我父亲是谁没什么关系，这样一层一层找太浪费时间，不如我直接当祖先的儿子，问一次就可以出结果了。甚至祖先是谁都无所谓，只要这个人可以代表我们家族就能得到想要的效果。**把在路径上的每个节点都直接连接到根上，这就是路径压缩。**



**合并**[¶](https://oi-wiki.org/ds/dsu/#_4)

宴会上，一个家族的祖先突然对另一个家族说：我们两个家族交情这么好，不如合成一家好了。另一个家族也欣然接受了。
我们之前说过，并不在意祖先究竟是谁，所以只要其中一个祖先变成另一个祖先的儿子就可以了。

**启发式合并（按秩合并）**

一个祖先突然抖了个机灵：「你们家族人比较少，搬家到我们家族里比较方便，我们要是搬过去的话太费事了。」

由于需要我们支持的只有集合的合并、查询操作，当我们需要将两个集合合二为一时，无论将哪一个集合连接到另一个集合的下面，都能得到正确的结果。但不同的连接方法存在时间复杂度的差异。具体来说，如果我们将一棵点数与深度都较小的集合树连接到一棵更大的集合树下，显然相比于另一种连接方案，接下来执行查找操作的用时更小（也会带来更优的最坏时间复杂度）。





关于连通性问题，除了用DFS、BFS，并查集也是常用的数据结构。



并查集 是连通性的应用。



做题感受记录：  并查集处理的对象是集合（树形结构）



做题感受总结：增加【查看评论区】这一环节，能发现自己不知道的，没有理解清楚的；

/* 因为评论区有很多网友提问的，这些问题和讨论引发人思考，看到不同的见解和思维；

对自己是查漏补缺，看网友的提问和见解，看自己能否回答上来，验证我自己是否真的理解了这个题思想；

这是非常有价值的一个行动环节，因为交流讨论才能看到多角度多元化思维，一个人的认识有局限性，有时还理解得不到位，模模糊糊，难受；

我又没有人交流，而评论区有大量的网友在交流、提出和我类似的疑问、给出各自的见解；

真的让人长见识，对这个题会有进一步透彻的理解；

总结：要有【查看评论区】这一环节，是柏拉图吴军说的讨论交流环节，是增加见识和理解的重要环节；

练习掌握这个方法，能事半功倍(至少不会陷入经常犯的事倍功半)



如上是今早看评论区讨论 得出的感受体会 (也是因为对题解复杂度分析有疑问，才跑去看评论区，看是否有和我相同疑问的，及其解答和看法)



2021年2月20日08:41:54

*/











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







学友的学习总结：

547.省份数量

这道题是一个很典型的计算连通分量的问题。这类题一般可以用深度优先搜索和并查集来做。深度优先需要先给定一个is_visited数组来判断带搜索的元素是否已经搜索，如果没有搜索就对他进行深度优先搜索并且把返回的结果值+1 并查集的话思路就更简单了，直接对题目给定的二维数组进行上三角遍历即可。最后统计并查集中连通分量个数。

127 单词接龙

据说这是一道经典的广度优先搜索的题目。首先，为什么要用广度优先？先明确这个题需要我们得到的是单个单个单词变化后的最短路径，此时使用广度优先一层一层计算下去，得到的结果必然是最短的；如果使用深度优先，那么我们必须遍历所有路径并存储他们的所有路径长度，最后再选出最短的（或者每次得到一个路径就和当前最小的那个路径作比较并更新最小路径）。代码思路采用比较暴力所有字母遍历检查。不过看到答案中有用到虚拟结点的方法，可以说非常妙。







### 第15课 AVL树和红黑树

#### 1.AVL树和红黑树的实现和特性

- [维基百科：平衡树](https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree)



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

- [老乡群](https://u.geekbang.org/playground/exam/823?lesson=39&article=320041&question=7694&title=欢迎来到极客大学)
- [连带的麦子](https://u.geekbang.org/playground/exam/823?lesson=39&article=320041&question=7695&title=欢迎来到极客大学)
- [给小区建牛奶站](https://u.geekbang.org/playground/exam/823?lesson=39&article=320041&question=7696&title=欢迎来到极客大学)
- [滑动解谜](https://u.geekbang.org/playground/exam/823?lesson=39&article=320041&question=7697&title=欢迎来到极客大学)





