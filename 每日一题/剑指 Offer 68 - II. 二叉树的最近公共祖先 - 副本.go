// 2021年1月14日19:15:20








class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if(root == null || root == p || root == q) return root;
		

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		

		if(left == null) return right;
		
		if(right == null) return left;
		
        return root;
    }
}



class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if(root == null || root == p || root == q) return root;
		

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		

		if(left == null && right == null) return null; // 1.
		
		if(left == null) return right; // 3.
		
		if(right == null) return left; // 4.
		
        return root; // 2. if(left != null and right != null)
    }
}


若 rootroot 是 p, q的 最近公共祖先 ，则只可能为以下情况之一：






/* 
* 1.题目需求：
*	输入：二叉树，两个指定节点p、q
*	输出：找出 两个指定节点p、q 的【最近公共祖先root】
*
* 2.解法：递归 后序遍历
*
* 3.解题思路： 
*	若root是 p, q的最近公共祖先 ，则存在以下三种情况：
*	3.1. p 和 q 在root的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
*	3.2. p = root，且 q 在 root 的左或右子树中；
*	3.3. q = root，且 p 在 root 的左或右子树中；
*
* 3.复杂度分析:
*   时间：O(n)，其中n为二叉树节点数;最差情况下，需要遍历递归 该树的所有节点；
*   空间：O(n)，最差情况下，递归深度达到 n，所以栈空间为O(n)；
*
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		
		// 1.递归结束条件
		if(root == null) return null; // 如果树为空，直接返回null
		
		if(root == p || root == q) return root; // 如果 p和q中有等于 root的，那么它们的最近公共祖先即为root（一个节点也可以是它自己的祖先）
		

		// 2.深入下一层(分治)
		TreeNode left = lowestCommonAncestor(root.left, p, q); // 递归遍历左子树，只要在左子树中找到了p或q，则先找到谁就返回谁
		
		TreeNode right = lowestCommonAncestor(root.right, p, q); // 递归遍历右子树，只要在右子树中找到了p或q，则先找到谁就返回谁
		
		// 3.处理回溯值
		if(left == null) return right; // 如果在左子树中 p和 q都找不到，则 p和 q一定都在右子树中，右子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
		
        else if(right == null) return left; // 否则，如果 left不为空(right == null)，则 p和 q一定都在左子树中，左子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
	   
		else return root; //如果left和 right均不为空时，说明 p、q节点分别在 root异侧, 最近公共祖先即为 root
    }
}
// 做题感受:
// 首先要搞明白：【最近公共祖先】的概念定义什么？问题本质是什么？