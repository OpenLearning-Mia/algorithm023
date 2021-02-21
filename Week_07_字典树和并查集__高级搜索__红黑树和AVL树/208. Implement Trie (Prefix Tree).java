// 2021年2月13日19:42:09









// 【Trie Node Struct】:Trie树的结点结构
class TrieNode {

    // R links to node children
    private TrieNode[] links;

    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}


// Trie 树中最常见的两个操作是键的插入和查找。
// Trie 是一颗非典型的多叉树模型，多叉好理解，即每个结点的分支数量可能为多个。
/* 
1.【Insert】: 向 Trie 树中插入键

    向 Trie 树中插入键
    我们通过搜索 Trie 树来插入一个键。我们从根开始搜索它对应于第一个键字符的链接。有两种情况：

    链接存在。沿着链接移动到树的下一个子层。算法继续搜索下一个键字符。
    链接不存在。创建一个新的节点，并将它与父节点的链接相连，该链接与当前的键字符相匹配。
    重复以上步骤，直到到达键的最后一个字符，然后将当前节点标记为结束节点，算法完成。
 */
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {  // word表示一个键值
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (!node.containsKey(currentChar)) { 
                //不包含当前字符，则创建新节点，插入当前字符；
                node.put(currentChar, new TrieNode());
            }

            // 到这里表示，接下来做下一个字符的插入；
            // 等式左边的node指向 当前字符currentChar；
            node = node.get(currentChar);
        } //for end

        // 到这里表示：打标记，给最后一个字符对应的节点打上end标记，表示到达键尾了 end of Key。
        node.setEnd();
    }
}
/* 
复杂度分析：

时间复杂度：O(m)，其中 m 为键长。在算法的每次迭代中，我们要么检查要么创建一个节点，直到到达键尾。只需要 mm 次操作。

空间复杂度：O(m)。最坏的情况下，新插入的键和 Trie 树中已有的键没有公共前缀。此时需要添加 m 个结点，使用 O(m)O(m) 空间。
*/





// 2.【Find】: 在 Trie 树中查找键
class Trie {
    ...

    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
           char curLetter = word.charAt(i);

           if (node.containsKey(curLetter)) {
                // found curLetter,then find it's next one;    
                node = node.get(curLetter);
           } else {   
                //no find，func end    
                return null;
           }
        } //for end

        // found this word(Key)
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        // 1.进入Trie树搜索
        TrieNode node = searchPrefix(word);

        // 2.找到键的条件：node不为空，并且node被标记为键尾(isEnd = true)
        return node != null && node.isEnd(); 
    }
}






// 3.【FindPrefix】: 查找 Trie 树中的键前缀
class Trie {
    ...

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}







// 2021年2月13日21:19:52 done first-time