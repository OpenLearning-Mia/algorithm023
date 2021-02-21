/*
* 1.解法：递归； 
*
* 复杂度分析:
*   时间：O(n) ；n 为二叉树的节点个数，每个节点在递归中只被遍历一次
*   空间：O(n) ；	
*   
 */


// 题目需求是：输出该树的[中序遍历]结果
class Solution {
	public:
		// 递归函数inorder：遍历树；
		void inorder(TreeNode* root, vector<int>& res) {

			// 1.递归结束条件：遇到叶子节点的儿子节点为空(当前root == null)
			if (!root) {
				return;
			}

			// 如下开始【中序遍历】(左中右)，遍历子树；
			// 流程：先遍历完左子树，然后根节点，最后遍历完右子树，then func end.

			// 1.先左子树：左子树的递归；左子树递归完了，才会进入下面的第三、四步骤；
			inorder(root->left, res);

			// 2.然后是 根节点：根节点值 存入数组res
			res.push_back(root->val);  //（【演练分析】发现：每个节点都是根节点，包括叶子节点,也就是【递归会遍历每个节点】，时间复杂度：O(n)，n为树节点数）

			// 3.最后右子树：右子树的递归
			inorder(root->right, res);
		}

		// 功能函数的总入口(main)：调用inorder遍历树；遍历完了之后，输出【中序遍历】结果
		vector<int> inorderTraversal(TreeNode* root) {
			vector<int> res;
			inorder(root, res);
			return res;
		}
	};
	


 