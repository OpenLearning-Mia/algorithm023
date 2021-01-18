

/*
* 1.题目需求：
*	输入：二叉树，其代码特点：最多只有左右两个儿子；不会同时右三个以上的儿子(多叉树) ；
*	输出：返回其按 层序遍历 得到的节点值
*
*   输出需求 翻译：
*		1.层序遍历就是 把二叉树分层，然后每一层从左到右遍历
*		2.与BFS算法思想很像，但是两者输出不一样：
*		3.层序遍历要求我们区分每一层，也就是返回一个二维数组 [[1],[2,3]]
*		4.BFS的遍历结果是一个一维数组，无法区分每一层[1,2,3]
*
* 2.解法： 层序遍历 ,也就是定制版(结果分层输出)的 BFS
*		
* 3.解题思路：
*
*	3.1.由上面的输出结果分析可知，使用BFS的遍历思想，但如何给BFS遍历的结果分层？
*
*	3.2.稍微修改一下BFS模板代码，在每一层遍历开始前，先记录队列中的结点数量n（也就是这一层的结点数量）
*
*	3.3.然后一口气处理完这一层的n个结点(for循环n次)，
*		其中每个节点的被处理流程：1.出队列、2.该节点值存入层集合level中、3.将该节点的左右儿子入队列
*
* 4.复杂度分析:
*
*   时间：O(k)；n为二叉树节点个数，每个点进队出队各一次
*
*   空间：O(n)；队列、数组空间的元素个数不超过n个，所以空间复杂度为O(n)，n为二叉树节点个数
*
 */
public List<List<Integer>> levelOrder(TreeNode root) {

	List<List<Integer>> res = new ArrayList<>();  //res二维数组，用来装二叉树的层序遍历结果集
	Queue<TreeNode> queue = new ArrayDeque<>();  //使用队列【先进先出】的特点，用来 BFS遍历树节点

	if (root != null) {
		queue.add(root);  //初始化：根节点入队列
	}

	/*  在while循环的每一轮中，都是将当前层的所有结点出队列，
	 	再将下一层的所有结点入队列，这样就实现了【层序遍历】。
	 */
	while (!queue.isEmpty()) {  //队列为空，表示树节点都遍历完了，循环结束
		
		int n = queue.size(); //n表示树当前层的节点个数(这些同一层节点都在队列里)
		List<Integer> level = new ArrayList<>(); //level用来存 层节点元素

		// for循环之前，队列里装的都是同一层level的节点；for循环n次后：这同一层的所有节点都出队列，同时这些节点各自的左右儿子入队列
		
		// 处理同一层的n个节点：1.节点出队列、2.该节点值存入层集合level中、3.将该节点的左右儿子入队列
		for (int i = 0; i < n; i++) {  

			// 1.【出队列】
			// 	 将当前节点出队列
			TreeNode node = queue.poll();  

			// 2.将当前节点的值，存入当前层level；
			level.add(node.val); 

			// 3.【入队列】；
			// 	 将当前节点node的左右儿子节点入队列
			if (node.left != null) { //如果当前节点的左儿子不为空，则其左儿子加入队列
				queue.add(node.left); 
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}

		// 4.到这，表示当前层的节点都出队列了，其值也加入level了，并且他们的下一层左右儿子节点也都入队列了；
		// 将当前层结果level 存入结果集
		res.add(level);	
	}

	// 5.到这里，表示 树每一层都遍历完了，输出结果，func end
	return res;  //结果集：存储所有层结果的集合
}


// 2021年1月18日21:14:13







// go语言版
func levelOrder(root *TreeNode) [][]int {
	ret := [][]int{}
	
    if root == nil {
        return ret
	}
	
	q := []*TreeNode{root}
	
    for i := 0; len(q) > 0; i++ {

        ret = append(ret, []int{})
		p := []*TreeNode{}
		
        for j := 0; j < len(q); j++ {

			node := q[j]
			
			ret[i] = append(ret[i], node.Val)
			
            if node.Left != nil {
                p = append(p, node.Left)
            }
            if node.Right != nil {
                p = append(p, node.Right)
            }
        }
        q = p
    }
    return ret
}



// ####################################################################################################################
// 第一次做这题时，我的解题过程：
/*
* 
*算法的【代码模板】 是算法的【基石】，
*由算法实现的【功能是大厦】。大厦需要牢固的基石(算法(代码模板))，才能巍然不倒；
*
 */

//  bfs【代码模板】  C/C++
void bfs(Node* root) {
	  map<int, int> visited;
	
	  if(!root) return ;
	
	  queue<Node*> queueNode;
	  queueNode.push(root);
	
	  while (!queueNode.empty()) {
	
	    Node* node = queueNode.top();
	
	    queueNode.pop();
	
	    if (visited.count(node->val)) continue;
	    visited[node->val] = 1;
	
	    for (int i = 0; i < node->children.size(); ++i) {
	        queueNode.push(node->children[i]);
	    }
	
	  }
	  return ;
	}



// 二叉树的【层序遍历】   java
void bfs(TreeNode root) {
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int n = queue.size();
        for (int i = 0; i < n; i++) { 
            // 变量 i 无实际意义，只是为了循环 n 次
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}


