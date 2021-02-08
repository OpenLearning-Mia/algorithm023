
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        
        while (!stack.empty()) {
            root = stack.pop();
            list.add(root.val);
            for (int i = root.children.size() - 1; i >= 0; i--)
                stack.add(root.children.get(i));
        }
        
        return list;
    }
}




/* 
前序遍历是中左右，每次先处理的是中间节点，那么先将跟节点放入栈中，然后将右孩子加入栈，再加入左孩子。

为什么要先加入 右孩子，再加入左孩子呢？ 因为这样出栈的时候才是中左右的顺序。

* 1.解法：迭代、栈先进后出
*
* 3.思路：使用栈的先进后出 来得到前序遍历，需要保证【栈顶节点】就是当前遍历到的顺序节点，所以要逆序入栈孩子节点；
*
* 2.复杂度分析:
*   时间：O(n)，其中 n 为节点个数，每个节点只会入栈和出栈各一次
*
*   空间：O(n-1)，n为树节点个数；最坏情况，根节点出栈，剩下节点都在栈里，
*        这种情况是：该N叉树只有两层，第二层全是根节点的孩子(n-1)
 */
class Solution {
public:
    vector<int> preorder(Node* root) {
        vector<int> result;
        if (root == NULL) return result;
        stack<Node*> st;
        st.push(root);
        while (!st.empty()) {
            Node* node = st.top();
            st.pop();
            result.push_back(node->val);
            // 因为栈先进后出，所以孩子节点要【倒序入栈】(右到左)，这样出栈 才能达到前序（根左右）效果
            for (int i = node->children.size() - 1; i >= 0; i--) {
                if (node->children[i] != NULL) {
                    st.push(node->children[i]);
                }
            }
        }
        return result;
    }
};
