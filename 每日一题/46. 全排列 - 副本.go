







 输入：数组nums；没有重复 数字的序列 
 输出：全排列；返回其所有可能的情况

 输入: [1,2,3]
 输出:
 [
   [1,2,3],
   [1,3,2],
   [2,1,3],
   [2,3,1],
   [3,1,2],
   [3,2,1]
 ]
 
//  这有啥规律吗？有规律的穷举？



class Solution {
public:		
	
	// 递归函数backtrack
	void backtrack(vector<vector<int>>& res, vector<int>& input, int first, int len){

		// 1.递归结束条件：
		// 	 所有数都填完了
		if (first == len) {
			res.emplace_back(input); //得到了想要的结果input，加入结果集合res里
			return;
		}

		// 2.
		for (int i = first; i < len; ++i) {

			// 1.swap
			swap(input[i], input[first]);

			// 2.继续递归填下一个数
			backtrack(res, input, first + 1, len);

			// 3.还原input；撤销swap操作
			swap(input[i], output[first]);

		}//for end
	}

	
	vector<vector<int>> permute(vector<int>& nums) {
		vector<vector<int> > res;
		backtrack(res, nums, 0, (int)nums.size());
		return res;
	}
};





/* 
* 1.题目需求：
*	输入：数组nums （没有重复数字的序列）
*	输出：全排列 （返回其所有可能的情况）
*
* 2.解法：回溯  深度优先遍历
*
* 3.解题思路： 
*
*	3.1.为了找到解题规律和方法，先手写出 排列过程(画出推演分析过程，重要关键的头一步)
*		- 先写以1开头的全排列，它们是：[1, 2, 3], [1, 3, 2]， 即 1 + [2, 3] 的全排列（ 递归思想体现在这了）
*		- 再写以2开头的全排列，它们是：[2, 1, 3], [2, 3, 1]， 即 2 + [1, 3] 的全排列；
*		- 最后写以3开头的全排列，它们是：[3, 1, 2], [3, 2, 1]， 即 3 + [1, 2] 的全排列。
*
*	3.2.如上，按顺序枚举每一位可能出现的情况，这样的思路，可以用一个树形结构表示；
*
*	3.3.尝试自己亲手画出「全排列」问题的树形结构,增加感受体会，过一遍或多遍脑子，能更高效的、更好的 理解树，回溯，递归算法思想。
*	   （做题感受：需要画出和推演这个树图 来分析理解和编码，凭空想象无解，云里雾里，费脑不讨好）
*
*	3.4.使用编程的方法得到全排列，就是在这样的一个树形结构中完成遍历，从树的根结点到叶子结点形成的路径就是其中一个全排列。
*
*	3.5.执行深度优先遍历，从较深层的结点返回到较浅层结点的时候，需要做「状态重置」，即「深入下一层」、「恢复现场」
*
*	3.6.树的最后一层的所有叶子节点(全排列的所有情况)，就是最终的答案集合。
*	
*	3.7.使用一个数组res来保存 所有可能的全排列结果。
*
* 3.复杂度分析:
*   时间：O(n)； 一共n!个叶子节点(全排列的所有情况，结果集)，
*		  每个叶子节点，需要O(n) 的时间复制到答案数组中，所以时间复杂度为O(n∗n!)，n为序列长度。
*
*   空间：O(n)；除答案数组(O(n))以外，递归函数在递归过程中需要为每一层递归函数分配栈空间，其需要额外的空间取决于递归的深度(deep = n)，所以空间复杂度为O(n)，n为序列长度。
*
* 5.做这题的感受总结：
*	做题的时候，需要动手画出推演过程，找出规律(没有看出来的过程和结果，只有亲自动手尝试体会才能理解透彻，明白问题本质)，
*	比如像回溯，递归，dfs等算法思想的题,先画树形图 ，画图能帮助我们想清楚递归结构，看清楚回溯，dfs流程，理解其思想精髓。
*/


class Solution {
	public:
		// 递归函数backtrack
		void backtrack(vector<vector<int>>& res, vector<int>& output, int first, int len){
			//1.递归结束条件：序列里的元素都遍历完了 
			if (first == len) {
				res.emplace_back(output);  //取得一个全排列结果，存入结果集res
				return;
			}

			// for循环(i)意义：横向(i)找结果； 而first用于纵向（dfs）找出结果
			for (int i = first; i < len; ++i) {
				
				//1.交换元素位置 
				swap(output[i], output[first]);

				//深入下一层，直到得出叶子节点(全排列结果)，再回溯
				backtrack(res, output, first + 1, len);

				// 回溯，撤销操作(恢复现场)
				swap(output[i], output[first]);
			}
		}

		// 全排列permute
		vector<vector<int>> permute(vector<int>& nums) {
			
			// 使用数组res来保存 所有可能的全排列结果
			vector<vector<int> > res;

			// 调用递归来获取全排列结果
			backtrack(res, nums, 0, (int)nums.size());
			return res;
		}
	};
	
