// 2021年2月8日22:15:34







做题流程：

审请题目条件和需求，分析，找规律，找它的【问题模型】是啥：

用什么思维和方法模型 来解这题？？






输入：一个m x n 矩阵；
	 矩阵特点：1.每行中的整数从左到右按升序排列。2.每行的第一个整数大于前一行的最后一个整数。


输出：该矩阵中，是否存在目标值target，存在输出true; 否则输出false;


// 思路：给定目标值target，在有序目标集合里【查找】该目标值，找到输出true; 否则输出false;
// 因为是有序的目标集合，所有考虑用【二分查找】模型；





// key:Don't treat it as a 2D matrix, just treat it as a sorted list
// 重点：将该二维有序集合 转化为 一维有序集合，然后用二分查找来求解。
// 问题转为：如何转化？如何在二维有序数组里定位出mid？？
// mid在二维有序数组如何表示？？？
// 答：row = mid / col_num; col = mid % col_num; 
// 	   mid_value = matrix[row][col]，即matrix[mid/col_num][mid%col_num]
		
// key：将二维有序集合 转化为 一维有序集合来进行【二分查找】；



/*
* 1.题目需求：
*	输入：一个数组；它的第i个元素是: 一支股票第i天的价格。
*	输出：尽可能完成更多的买卖股票交易，计算【获取最大利润】；
*   
*	附加条件：不能同时买卖多笔交易，只能一次次的来（必须在再次购买前出售掉之前的股票）
*
* 2.解法：二分查找
*
* 3.解题思路：
*	3.1.由于不限制交易次数，所以只要今天股价比昨天高，就交易(贪心思想)：
*
*	3.2.贪心算法：每一步总是做出 在当前看来最好的选择；
*
*	3.3.对于「今天的股价 - 昨天的股价」，得到的结果有3种可能：1.正数，2.零，3.负数。
*	
*	3.4.为获得最大利润，贪心算法的决策是：只加正数；
*	
* 4.复杂度分析:
*   时间：O(log(m*n))；二分查找复杂度，m*n为矩阵大小；
*   空间：O(1)；常量空间大小。
*
* 5.做题感受总结：
*	5.1.这题看到有序集合查找目标值，则可以考虑用【二分查找】来做；
*	5.2.因为给的是二维数组(不是一维的)，所以关键是得知道如何在二维数组中定位到(一维数组的)mid值,
*		也就是将二维转化为一维来进行二分查找；
* 	5.3.即，能推导出mid_value = matrix[mid/col_num][mid%col_num]，然后剩下的就是二分查找思想和其模板实现流程；
 */
class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
		// 1.特判：matrix为null或内容为空； 
		if (matrix == null || matrix.length == 0) {
				return false;
			}

		// 2.初始化：left、right左右指针
		int row_num = matrix.length; // 行数
		int col_num = matrix[0].length; // 列数
		int left = 0;
		int right = row_num * col_num - 1;
	
		/* 			
		   3.开始二分查找流程：
			3.1.这里关键是将mid下标定位到二维矩阵的具体元素上，即：mid_value = matrix[mid/col_num][mid % col_num];
			3.2.循环结束条件：left > right,表示各自走过头了也没找到，return false; 
		*/
		while (left <= right) {

			// 1.求出mid；
			int mid = left+ ((right-left) >> 1);

			// 将一维坐标[mid]转化为二维坐标[row,col]，定位到二维数组中的对应元素；
			// (如下标公式，可通过具体推演得到,光肉眼看可能不太明白，一推演就清楚些了)
			int row = mid / col_num; // 行下标 = (mid / 列数）
			int col = mid % col_num; // 列下标 = (mid % 列数)

			// 2.将target与中间值比较，来确定target在左内还是右内；
			// 这里matrix[row][col]就是中间值mid_value
			if (target ==matrix[row][col]) { // 1.找到了
				return true;
			} else if (target < matrix[row][col]) { // 2.target小于mid，则往左找：right = mid - 1;
				right = mid - 1;
			} else if (target > matrix[row][col]) { // 3.target大于mid，则往右找：left = mid + 1;
				left = mid + 1;
			}
		}

		// 到这表示没找到, return false;
		return false;
	}
}






// 2021年2月9日17:23:46, done, first time













