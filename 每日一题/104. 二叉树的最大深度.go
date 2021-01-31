/*
* 1.解法：递归
*
* 2.思路分析：
* 	2.1.左子树和右子树的最大深度 l 和 r,那该二叉树的最大深度即为:max(l,r)+1
*	2.2.而左子树和右子树的最大深度又可以以同样的方式进行计算("重复的工作 用递归")，
*		因此在计算当前二叉树的最大深度时，就是先递归计算出其左子树和右子树的最大深度；
*	2.3.递归结束条件：访问到空节点时退出；
*
* 3.复杂度分析:
*   时间：O(n) ；		n 为二叉树节点的个数，每个节点在递归中只被遍历一次
*   空间：O(height) ；	height 表示二叉树的高度；递归需要栈空间，而栈空间取决于递归的深度。
*
 */

func maxDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return max(maxDepth(root.Left), maxDepth(root.Right)) + 1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
