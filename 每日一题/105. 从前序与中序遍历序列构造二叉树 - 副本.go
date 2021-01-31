
/* 
*  国内most vote
*
* 1.题目需求：
*	输入：前序遍历，中序遍历数组
*	输出：一颗二叉树
*
* 2.解法二：迭代栈   
*
* 4.复杂度分析:
*   时间：O(n)，
*   空间：O(n)，
*
* 5.做题感受总结：先搞清楚【输出】应该是怎样的，然后再找解决方案。
*   做题经历：第一次做时，都没搞清楚【输出结果】应该是怎样的，就着急学习题解，结果学的云里雾里，然后重头再来。
*/

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder.length == 0) {
			return null;
		}

		Stack<TreeNode> roots = new Stack<TreeNode>();

		int pre = 0; //前序节点元素的下标
		int in = 0;  //中序节点元素的下标

		//1.将【前序遍历】第一个值：根节点 入栈
		TreeNode curRoot = new TreeNode(preorder[pre]);
		TreeNode root = curRoot;
		roots.push(curRoot); // 入栈
		pre++;


		//2.遍历【前序数组】
		while (pre < preorder.length) {

			// 2.1.找【右子树】(可以找的条件：如果当前序根节点值等于当前中序节点值(curRoot.val == inorder[in])，表示接下来找的是右子树)
			if (curRoot.val == inorder[in]) {  //出现了当前【根节点】值和中序数组的值相等，寻找是谁的右子树

				// (!roots.isEmpty()) == true 表示栈不为空
				while (!roots.isEmpty() && roots.peek().val == inorder[in]) {//每次进行出栈，实现倒着遍历
					// 出栈
					curRoot = roots.peek();
					roots.pop();  
					in++; in=2，inorder=15  in=3 inorder=20
				}

				// 开始找 当前根的右子树
				curRoot.right = new TreeNode(preorder[pre]); //设为当前的右孩子,20, pre ==2
				
				curRoot = curRoot.right; //更新 curRoot=20
				roots.push(curRoot);
				pre++; 

			} else {  

				// 2.2.找【左子树】(中序遍历中 根节点的右边)
				// 	   到这里来的，全是左子树节点(curRoot.left)；(站在中序数组的角度 来去前序数组的值)
				curRoot.left = new TreeNode(preorder[pre]);

				curRoot = curRoot.left;  //将左子树节点curRoot.left又作为新的根节点curRoot，并入栈
				roots.push(curRoot); //9
				pre++;
			}
		}
		return root;
	}
	

/* 

综上所述，我们用一个栈保存已经遍历过的节点，

遍历 前序数组，一直作为当前根节点的左子树，

直到当前节点和中序数组的节点相等了，
// 那么我们正序遍历 中序遍历的数组，倒着遍历已经遍历过的根节点（用栈的 pop 实现）??

找到最后一次相等的位置，把它作为该节点的右子树。


*/


// 最后输出结果：[3,9,20,null,null,15,7]



/* 
*  国内most vote
*
* 1.题目需求：
*	输入：前序遍历，中序遍历数组
*	输出：一颗二叉树
*
* 2.解法二：迭代栈   
*
* 4.复杂度分析:
*   时间：O(n)，
*   空间：O(n)，
*
* 5.做题感受总结：先搞清楚【输出】应该是怎样的，然后再找解决方案。
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

