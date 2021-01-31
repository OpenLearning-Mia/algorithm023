





























/*
* 题目需求：把字符串 s 中的每个空格替换成"%20"
*
* 1.解法：双指针倒序遍历，原地扩容
*   
* 2.思路分析：
*	2.1.先遍历出空格个数为count，然后对s扩容：新增2*count空间
*
*	2.2.然后倒序遍历字符串：双指针法 ；遇到字符往后挪，遇到空格填充；
*		2.2.1.倒序遍历优点：每个字符最多被挪动一次(与正序遍历相比)，降低时间复杂度；
*
*	2.3.遍历结束条件：i==j（两指针重合，表示左边已无空格，无需再向左遍历）
*
* 2.复杂度分析:
*   时间：O(n)，其中 n 为字符个数，每个字符最多被遍历和挪动一次
*   空间：O(2m)，等于O(m)，m表示空格个数；
*
*/ 






// c++
class Solution {
	public:
		string replaceSpace(string s) {
			
			int count = 0, len = s.size();

			// 1.统计空格数量
			for (char c : s) {
				if (c == ' ') count++;
			}

			// 2.扩容 数组s的长度
			s.resize(len + 2 * count);

			// 3.倒序遍历修改；秒点：倒序遍历，所以元素只用挪动一次(减少元素挪动次数，降低时间复杂度，和正序遍历相比)
			//	 初始情况下：s[i]指向原字符串的最后一个字符；s[j]指向新字符串空间的最后一个空间  
			for(int i = len - 1, j = s.size() - 1; i < j; i--, j--) {
				// 3.1.当前字符为 非空格，后移
				if (s[i] != ' ')
					s[j] = s[i];

				else {
					// 3.2.当前字符为 空格，替换成%20；然后j向左移动三步，指向下一个字符空间
					s[j - 2] = '%';
					s[j - 1] = '2';
					s[j] = '0';
					j -= 2;
				}
			}
			return s;
		}
	};
	