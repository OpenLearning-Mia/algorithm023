
// 递归的话涉及到方法调用，比出入栈消耗的时间大多了。

// 

优化进阶: 递归算法很简单，你可以通过迭代算法完成吗？


// 官方题解中介绍了三种方法来完成树的中序遍历，包括：
// 递归 O(n)  O(n)
// 借助栈的迭代方法 O(n)  O(n)
// 莫里斯遍历 O(n)  O(1)



在树的深度优先遍历中（包括前序、中序、后序遍历），递归方法最为直观易懂，但考虑到效率，我们通常不推荐使用递归。
// why ???



// 题目需求是：输出该树的[中序遍历]结果


// 递归
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
	



// 题目需求是：输出该树的[中序遍历]结果



/*
* 题目需求：输出二叉树的[中序遍历]结果
*
* 1.解法：递归
*   
* 2.思路分析：
*	1.中序遍历：左中右
*	2.遍历流程：先遍历完左子树，然后根节点，最后遍历完右子树，then func end.   
*
* 2.复杂度分析:
*   时间：O(n)，其中 n 为二叉树节点的个数，二叉树的遍历中每个节点都只会被访问一次;
*   空间：O(n)，每个节点都被递归过，所以栈深度为n
*
*/ 

class Solution {
	public:
		void inorder(TreeNode* root, vector<int>& res) {
			if (!root) {
				return;
			}

			// 中序遍历：左中右
			inorder(root->left, res);
			res.push_back(root->val);
			inorder(root->right, res);
		}
		vector<int> inorderTraversal(TreeNode* root) {
			vector<int> res;
			inorder(root, res);
			return res;
		}
};

/*
* 1.解法：递归； 递归缺点是啥？是节点数太多，造成栈溢出？？就这？为什么高票题解里说：递归效率不好？？哪里不好？？
*
* 复杂度分析:
*   时间：O(n) ；		n 为二叉树的节点个数，每个节点在递归中只被遍历一次
*   空间：O(height) ？？n吧？；	height 表示二叉树的高度；递归需要栈空间，而栈空间取决于递归的深度。
*   
 */

 复杂度分析

时间复杂度：O(n)O(n)，其中 nn 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
空间复杂度：O(n)O(n)。空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n)O(n) 的级别。






// 方法二：栈
// 栈特点：先进后出

class Solution {
	public:
		vector<int> inorderTraversal(TreeNode* root) {

			vector<int> res;
			stack<TreeNode*> stk;

			// 【中序遍历流程：左中右】
			// 	stk.empty()如果节点为null并且栈为空，表示遍历结束了；
			while (root != nullptr || !stk.empty()) {

				// 1.节点入栈
				//   遍历左子树，将左子树节点入栈
				//   遍历结束条件：遍历完左子树(叶子的左右子节点为null)
				while (root != nullptr) {
					// 入栈
					stk.push(root);
					root = root->left;
				}

				// 2.节点出栈： (栈：先进后出)
				//   弹出一个节点root(该节点的右子树节点还没遍历)
				root = stk.top();

				// 3.将弹出的节点值 存入结果集合res里
				res.push_back(root->val);

				// 4.根节点root的左子节点都遍历完，接下来遍历右节点right
				root = root->right;
			}

			return res;
		}
};



class Solution {
	public:
		vector<int> inorderTraversal(TreeNode* root) {
			vector<int> res;
			stack<TreeNode*> stk;

			while (root != nullptr || !stk.empty()) {

				while (root != nullptr) {
					stk.push(root);
					root = root->left;
				}

				root = stk.top();
				res.push_back(root->val);
				root = root->right;
			}
			return res;
		}
};
















// 方法三：Morris 中序遍历

class Solution {
	public:
		vector<int> inorderTraversal(TreeNode* root) {
			vector<int> res;
			TreeNode *predecessor = nullptr;
	
			while (root != nullptr) {

				if (root->left != nullptr) {

					// predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
					predecessor = root->left;

					while (predecessor->right != nullptr && predecessor->right != root) {
						predecessor = predecessor->right;
					}
					
					// 让 predecessor 的右指针指向 root，继续遍历左子树
					if (predecessor->right == nullptr) {
						predecessor->right = root;
						root = root->left;
					}else {
						res.push_back(root->val);
						predecessor->right = nullptr;
						root = root->right;
					}
				}
				// 如果没有左孩子，则直接访问右孩子
				else {
					res.push_back(root->val);
					root = root->right;
				}
			}
			return res;
		}
};
	


复杂度分析

时间复杂度：O(n)O(n)，其中 nn 为二叉搜索树的节点个数。Morris 遍历中每个节点会被访问两次，因此总时间复杂度为 O(2n)=O(n)O(2n)=O(n)。
空间复杂度：O(1)O(1)。
// todo：Morris 遍历中，每个节点会被访问两次？？？really？推演分析试试






// 国内 most vote

class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        WHITE, GRAY = 0, 1
        res = []
		stack = [(WHITE, root)]
		
        while stack:
			color, node = stack.pop()
			
            if node is None: continue
			
			if color == WHITE:
                stack.append((WHITE, node.right))
                stack.append((GRAY, node))
                stack.append((WHITE, node.left))
            else:
                res.append(node.val)
        return res
































