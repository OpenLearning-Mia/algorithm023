// 2021年2月14日21:39:49




// 解法：前缀树和回溯  Trie +  Backtracking (dfs)  

public List<String> findWords(char[][] board, String[] words) {
    List<String> res = new ArrayList<>();
    TrieNode root = buildTrie(words);
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            dfs (board, i, j, root, res);
        }
    }
    return res;
}

public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
    char c = board[i][j];
    if (c == '#' || p.next[c - 'a'] == null) return;
    p = p.next[c - 'a'];
    if (p.word != null) {   // found one
        res.add(p.word);
        p.word = null;     // de-duplicate
    }

    board[i][j] = '#';

    // 这里符合对应条件 才能进入dfs递归;（相当于把递归结束条件放在这里了）
    if (i > 0) dfs(board, i - 1, j ,p, res); 
    if (j > 0) dfs(board, i, j - 1, p, res);
    if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
    if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
    board[i][j] = c;
}

public TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for (String w : words) {
        TrieNode p = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (p.next[i] == null) p.next[i] = new TrieNode();
            p = p.next[i];
       }
       p.word = w;
    }
    return root;
}

class TrieNode {
    TrieNode[] next = new TrieNode[26];
    String word;
}











// 解法：前缀树和回溯  Trie +  Backtracking (dfs)  
/* 
复杂度分析：

时间复杂度：O(x*y)，x，y为网格大小。遍历每个网格单元，最坏情况下，在每个网格单元下dfs递归次数为4*3^（L−1）(对于搜索一个单词第一步至多可选4个方向,后续至多可选3个方向(题目说格子不能重复利用,即无法后退)。比如 前一步→，那么下一步不能是←)
                                                L为单词长度；

假设单词的最大长度是L，从一个单元格开始，最初我们最多可以探索 4 个方向。假设每个方向都是有效的（即最坏情况），在接下来的探索中，我们最多有 3 个相邻的单元（不包括我们来的单元）要探索。
因此，在回溯探索期间，我们最多遍历 4\cdot3^{L-1}4⋅3 
L−1个单元格。

//时间复杂度分析：O(x*y)；遍历了网格的所有单元格，对每个单元格dfs递归,
                在每个单元格下dfs递归的次数，最坏情况就是一个单词的长度

空间复杂度：O(N)。其中 N 是字典树trie中的字母总数。算法消耗的主要空间是我们构建的 Trie 数据结构。在最坏的情况下，如果单词之间没有前缀重叠，则 Trie 将拥有与所有单词的字母一样多的节点。
 */

// todo疑问：dfs,回溯的时间空间复杂度怎么分析来着？？？递归次数和递归深度？？

//改造前缀树节点
class TrieNode {
    public TrieNode[] children;
    public String word; //节点直接存当前的单词

    public TrieNode() {
        children = new TrieNode[26];
        word = null;
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}
class Trie {
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    // time: array.length
    // spcae: array.length
    public void insert(String word) {
        char[] array = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < array.length; i++) {
            // 当前孩子是否存在
            if (cur.children[array[i] - 'a'] == null) {
                cur.children[array[i] - 'a'] = new TrieNode();
            }
            cur = cur.children[array[i] - 'a'];
        }
        // 当前节点结束，存入当前单词
        cur.word = word;
    }
};


class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        //将所有单词存入前缀树中
        List<String> res = new ArrayList<>();
        for (String word : words) {
            trie.insert(word);
        }
        int rows = board.length;
        if (rows == 0) {
            return res;
        }
        int cols = board[0].length;

        //从每个位置开始遍历
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                existRecursive(board, i, j, trie.root, res);
            }
        }
        return res;
    }

    private void existRecursive(char[][] board, int row, int col, TrieNode node, List<String> res) {

        // 1.递归结束条件：遍历超出二维网格范围
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }

        char cur = board[row][col];//将要遍历的字母

        //当前节点遍历过 或者 将要遍历的字母在前缀树中不存在 (剪枝pruning)
        if (cur == '$' || node.children[cur - 'a'] == null) {
            return;
        }
        node = node.children[cur - 'a'];
        //判断当前节点是否是一个单词的结束，如果是，则表明找到了一个目标单词，加入结果集res,
        if (node.word != null) {
            //加入到结果中
            res.add(node.word);
            //将当前单词置为 null，防止重复加入
            node.word = null; //（剪枝pruning）

            // todo??这里就应该return？？ 因为找到了一个？
            // 答：继续顺着当前trie树路径往下走，看能否在找到一个？在这条trie树路径上找到底，再结束；
            // 比如 oath 和oaths两个单词，都在trie树的一条路径上
        }
        
        char temp = board[row][col];
        board[row][col] = '$';  //打标记，表示在当前dfs过程中已被遍历过，防止在深入下一层dfs(如下所示)时重复遍历该节点；
        
        //上下左右去遍历
        existRecursive(board, row - 1, col, node, res);
        existRecursive(board, row + 1, col, node, res);
        existRecursive(board, row, col - 1, node, res);
        existRecursive(board, row, col + 1, node, res);

        // 回溯算法流程的最后一步：恢复现场(数据还原)
        // todo:为啥要恢复？？目的是啥？ 需要恢复吗？
        board[row][col] = temp;  //也可以直接等于cur;
    }
}

/* 
总结：
结合代码就很好懂了，就是【从每个位置对图做深度优先搜索】，然后路径生成的字符串如果没有在前缀树中出现就提前结束，
如果到了前缀树中某个单词的结束，就将当前单词加入即可。



作者：windliang
链接：https://leetcode-cn.com/problems/word-search-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-44/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。





和dfs+回溯比，前缀树优化的点在哪呢？？


不再是单纯的去挨个遍历，
前缀树相当于一个限制，让dfs跟着前缀树走（就是在前缀树上进行回溯，不知道这么说你理解没），
这个想法真的很厉害

 */







// 字典树
class TrieNode {
private:

   TrieNode* next[26];     // 孩子列表[0-25]表示['a'-'z']，nullptr表示无
   TrieNode* parent;       // 该结点的父结点/双亲结点
   char flag;              // 控制标记。-1 逻辑已删除； 0：作为中间链路；1：有效单词结束
   int numsOfNext;         // 当前结点的孩子个数，叶结点为0

public:
   // 构造函数
   // param: [bool] isRoot - 初始化的根结点需要置true
   TrieNode(bool isRoot = false) {
       for (int i = 0; i < 26; ++i) next[i] = nullptr;
       flag = isRoot;
       parent = nullptr;
       numsOfNext = 0;
   }

   // 在字典树的根结点插入单词记录 (若存在则跳过，默认在单词结尾的字符结点置其控制标记flag为1)
   // param: [TrieNode*] root - 一般传字典树根结点
   // param: [string] word - 单词
   // return: [TrieNode*] - 返回插入的单词末尾字符结点
   static TrieNode* insert(TrieNode* root, string word) {
       auto ptr = root;
       for (char c : word) {
           if (ptr->next[c-'a'] == nullptr) {
               ptr->next[c-'a'] = new TrieNode();
               ptr->next[c-'a']->parent = ptr;
               ++ptr->numsOfNext;
           }
           ptr = ptr->next[c-'a'];
       }
       ptr->flag = true;
       return ptr;
   }

   // 在字典树的某个结点插入下一个字符结点 (若存在则跳过，需要指定其是否是单词结尾)
   // param: [TrieNode*] root - 字典树的某个结点
   // param: [char] c - 字符
   // param: [bool] isEnd - 是否以此为结尾够成新单词
   // return: [TrieNode*] - 返回插入的新字符结点
   static TrieNode* insert(TrieNode* root, char c, bool isEnd) {
       if (root->next[c-'a'] == nullptr) {
           root->next[c-'a'] = new TrieNode();
           root->next[c-'a']->parent = root;
           ++root->numsOfNext;
       }
       root->next[c-'a']->flag = isEnd;
       return root->next[c-'a'];
   }

   // 在字典树的根个结点查找单词记录 (需满足单词结尾的字符结点其控制标记flag为1)
   // param: [TrieNode*] root - 一般传字典树根结点
   // param: [string] word - 单词
   // return: [bool] - 是否存在
   static bool has(TrieNode* root, string word) {
       if (!root) return false;
       auto ptr = root;
       for (char c : word) {
           if (ptr->next[c-'a'] == nullptr || ptr->next[c-'a']->flag == -1) {
               return false;
           }
           ptr = ptr->next[c-'a'];
       }
       return ptr->flag == 1;
   }

   // 在字典树的某结点查找是否存在下一个指定的字符
   // param: [TrieNode*] root - 字典树的某结点
   // param: [char] c - 查找的字符
   // return: [bool] - 是否存在
   static bool has(TrieNode* root, char c) {
       if (!root) return false;
       if (root->next[c-'a'] == nullptr || root->next[c-'a']->flag == -1) {
           return false;
       }
       return true;
   }   

   // 逻辑上删除(flag置-1)，在字典树的某子结点开始向上追溯父结点/双亲结点并删除，若父结点/双亲结点有其他分叉或其flag为1(作为单词结尾)则停止删除
   // param: [TrieNode*] root - 字典树的某结点
   // return: [TrieNode*] - 返回停止删除的父结点
   static TrieNode* del_up_logicly(TrieNode* root) {
       auto cur = root;
       do {
           cur->flag = -1;
           cur = cur->parent;
           --cur->numsOfNext;

       } while (cur->flag <= 0 && cur->numsOfNext == 0);
       return cur;
   }

   //=========================================================
   // setter & getter 不做详细注释

   // 返回当前结点的下一个指定字符结点
   static TrieNode* getNextNode(TrieNode* root, char c) {
       return root->next[c-'a'];
   }

   // 返回当前结点的父结点/双亲结点
   static TrieNode* getParentNode(TrieNode* root) {
       return root->parent;
   }   

   // 返回当前结点是否是单词结尾
   static bool isEnd(TrieNode* root) {
       return root->flag == 1;
   }   

   // 返回当前结点的分支next数量
   static int getNumsOfNext(TrieNode* root) {
       return root->numsOfNext;
   }   

   // 设置当前结点是否是单词结尾
   static void setEnd(TrieNode* root, bool isEnd) {
       root->flag = isEnd;
   }   
};

// 字典树方法
class Solution {
public:
   
   int R, C;           // 最大行列值
   TrieNode* trie;     // 字典树根结点
   vector<string> ans; // 答案数组
   int need;           // 还需要多少答案数量

   vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
       R = board.size();
       C = board[0].size();

       need = words.size();        // 设置答案需求数量
       
       trie = new TrieNode(true);  // 创建字典树根节点

       // 创建需要单词的字典树
       for (string& word : words) {
           trie->insert(trie, word);
       }
       
       // DFS回溯搜索
       string temp;
       for (int row = 0; row < R; ++row) {
           for (int col = 0; col < C; ++col) {
               DFS(board, row, col, 0, trie, temp);
           }
       }

       return ans;
   }

   // 深度回溯字符矩阵
   // 试探回溯字符是否够成答案需要的单词
   // param: [int] depth - 递归/回溯/DFS 深度，因为单词长度最多10，因此我们只需要递归深度到10即可返回
   // param: [TrieNode*] curNode - 当前字典树字符结点，初始化传入根结点
   // param: [string&] word - 回溯单词，临时存储当前组成的单词
   void DFS(vector<vector<char>>& board, int row, int col, int depth, TrieNode* curNode, string& word) {

       // DFS深度10
       if (depth >= 10) return;

       // 不需要再寻找了，因为答案已经找完了
       if (need == 0) return;

       // 坐标超出范围
       if (row < 0 || row >= R || col < 0 || col >= C) return;

       // 已被访问过(标记过)
       if (board[row][col] < 'a') return;

       // 在字典树中，要么该字符结点已被删除，或者没有/不存在
       if (!trie->has(curNode, board[row][col])) return;

       // 当前能够构成的单词
       word += board[row][col];

       // 取出下一个字符结点
       curNode = trie->getNextNode(curNode, board[row][col]);

       // 如果这个字符结点可以组成单词的话
       if (trie->isEnd(curNode)) {
           // 修改当前字符结点不能组成单词
           trie->setEnd(curNode, false);
           // 加入答案数组
           ans.push_back(word);
           // 减少需求数量
           --need;
           if (!need) return;

           // 如果该字符节点为叶结点
           if (trie->getNumsOfNext(curNode) == 0) {
               // 逻辑地向上回溯删除该分叉
               trie->del_up_logicly(curNode);
               // 回溯// 回溯单词
               word.pop_back();
               return;
           }
       }

       // 标记访问
       board[row][col] -= 26;
   
       DFS(board, row+1, col, depth+1, curNode, word);
       DFS(board, row-1, col, depth+1, curNode, word);
       DFS(board, row, col+1, depth+1, curNode, word);
       DFS(board, row, col-1, depth+1, curNode, word);

       // 回溯单词
       word.pop_back();
       // 回溯标记/访问
       board[row][col] += 26;
       return;
   }

};















// 我是易安，努力去写最清晰易懂的code.
class TrieNode{
public:
    string word = "";
    vector<TrieNode*> nodes;
    TrieNode():nodes(26, 0){}
};

class Solution {
    int rows, cols;
    vector<string> res;
public:
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        rows = board.size();
        cols = rows ? board[0].size():0;
        if(rows==0 || cols==0) return res;

        //建立字典树的模板
        TrieNode* root = new TrieNode();
        for(string word:words){
            TrieNode *cur = root;
            for(int i=0; i<word.size(); ++i){
                int idx = word[i]-'a';
                if(cur->nodes[idx]==0) cur->nodes[idx] = new TrieNode();
                cur = cur->nodes[idx];
            }
            cur->word = word;
        }

        //DFS模板
        for(int i=0; i<rows; ++i){
            for(int j=0; j<cols; ++j){
                dfs(board, root, i, j);
            }
        }
        return res;
    }

    void dfs(vector<vector<char>>& board, TrieNode* root, int x, int y){
        char c = board[x][y];
        //递归边界
        if(c=='.' || root->nodes[c-'a']==0) return;

        root = root->nodes[c-'a'];
        if(root->word!=""){
            res.push_back(root->word);
            root->word = "";
        }
        
        board[x][y] = '.';
        if(x>0) dfs(board, root, x-1, y);
        if(y>0) dfs(board, root, x, y-1);
        if(x+1<rows) dfs(board, root, x+1, y);
        if(y+1<cols) dfs(board, root, x, y+1);
        board[x][y] = c;
    }  
};



// 做题感受记录：
// 第一遍看，懵逼，看不懂，有点不想看；
// 每次都得精神状态好的时候来看，比如睡一觉再来看，白天看(晚上看，一天精力耗得没剩多少，做题更是会看不懂，不想看)
// 第二遍看，知道用什么思路来解题，比如这题用回溯+trie来做
// 第三遍看，看文字真难受，干巴巴的，不知道它在讲什么？难受
// 第四遍看，看代码，看好几个题解、most vote；看代码更容易理解些了
// 第五遍看，代码里的实现思路有些看不明白，有bug,为什么这么做？记录下我的疑问；
// 第六遍再来看，看多个题解和评论，明白了代码那么实现的目的和意思，记录下我的回答；
// 第七遍，回过头，整体捋一捋解题思想，实现流程。感受一下整体过程；
// 思考时间空间的复杂度分析，记录下来；



// 2021年2月15日18:21:30