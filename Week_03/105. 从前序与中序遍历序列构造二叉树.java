

/* 
*
* 1.题目需求：
*	输入：前序遍历，中序遍历数组
*	输出：一颗二叉树；输出结果：[3,9,20,null,null,15,7]
*
* 2.解法二：迭代栈   （迭代效率比递归 要高，递归 有函数调用和函数调用堆栈的开销）
*
* 4.复杂度分析:
*   时间：O(n)，
*   空间：O(n)，
*
* 5.做题感受：先搞清楚【输出】应该是怎样的，然后再找解决方案。
*   做题经历：第一次做时，都没搞清楚【输出结果】应该是怎样的，就着急学习题解，结果学的云里雾里，然后重头再来。
*/
public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0) {
        return null;
    }
    Stack<TreeNode> roots = new Stack<TreeNode>();
    int pre = 0;
	int in = 0;
	
    //先序遍历第一个值作为根节点
    TreeNode curRoot = new TreeNode(preorder[pre]);
    TreeNode root = curRoot;
    roots.push(curRoot);
	pre++;
	
	//遍历前序遍历的数组
	// pre = preorder.length表示已遍历完最后一个节点。
    while (pre < preorder.length) {

        if (curRoot.val == inorder[in]) {

			while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
                curRoot = roots.peek();
                roots.pop();
                in++;
			}
			
			// 构造右子树
            curRoot.right = new TreeNode(preorder[pre]);
			curRoot = curRoot.right;
            roots.push(curRoot);
			pre++;
			
        } else {

			// 构造左子树
            curRoot.left = new TreeNode(preorder[pre]);
            curRoot = curRoot.left;
            roots.push(curRoot);
            pre++;
        }
    }
    return root;
}


// 2021年1月16日18:15:42