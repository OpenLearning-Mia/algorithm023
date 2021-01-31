// 2021年1月13日11:37:48












题目需求：实现链表元素的倒序输出集合
// 国内most vote
// 方法一：递归
解题思路：
利用递归： 先走至链表末端，回溯时依次将节点值加入列表 ，这样就可以实现链表值的倒序输出

class Solution {
	ArrayList<Integer> tmp = new ArrayList<Integer>();
	
	// 输出 节点元素集合
    public int[] reversePrint(ListNode head) {
		// 1.递归：将链表元素 逆序存入结果集tmp
		recur(head);
		
		int[] res = new int[tmp.size()];
		
		// 2.输出结果集
        for(int i = 0; i < res.length; i++)
			res[i] = tmp.get(i);
			
        return res;
	}
	
	// 递归：将链表元素 逆序存入结果集tmp
    void recur(ListNode head) {

		// 1.递归结束条件：遍历完链表(走完链表尾部节点)
		if(head == null) return;
		
		// 2.分治：深入下一层
		recur(head.next);
		
		// 3.回溯：将当前节点元素 加入结果集tmp；
		// 	 3.1.这一步放在步骤2之后的原因：要倒序输出链表元素(题目需求)
		// 	 3.2.链表元素存入结果集tmp的顺序：从尾到头；		
        tmp.add(head.val);
    }
}









/* 
*  国内most vote
*
* 1.解法二：辅助栈   
*
* 2.解题思路：
*	2.1.链表特点： 只能从前至后 访问每个节点;
*	2.2.题目需求： 倒序输出节点值;
*	2.3.这种 先入后出 的需求可以用 栈 实现。
*
* 3.复杂度分析:
*   时间：O(n)，n 为链表节点个数，每个节点被入栈和出栈一次；
*   空间：O(n)，n为链表节点个数；额外使用一个栈 存储链表的节点值；
*/

class Solution {
    public int[] reversePrint(ListNode head) {

		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		// 1.链表节点值 入栈
        while(head != null) {
            stack.addLast(head.val);
            head = head.next;
		}
		
		// 2.出栈(先入后出)，元素存入结果集res，输出；
		int[] res = new int[stack.size()];
		
        for(int i = 0; i < res.length; i++)
			res[i] = stack.removeLast();
			
    return res;
    }
}




// 辅助栈 官方版 用的Stack结构，次之；与LinkedList相比
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
		ListNode temp = head;
		
		// 1.链表节点值 入栈
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }
}


// 为啥 LinkedList执行效率 比 Stack 高？ 如下网友解答：
// 我想问一下 如果用Stack<Integer> stack = new Stack<>(); 为什么执行用时比较高

Java 不太推荐用 Vector 和 Stack ~ 最好使用 LinkedList 实现链表、队列等数据结构~


从原理以及源码实现来看，个人认为原因： 1。Stack、Vector物理存储基于Array，因此： A> push/add 做插入时如果数组满了，就需要通过System.arraycopy(...) 做数组扩容 --- 原因1. B> pop/remove 做删除是，只要不是尾元素，都会发生数组拷贝System.arraycopy(...) ---- 主要原因？. 2。Stack、Vector里的方法是synchronized，执行效率低，这个和弹出元素时发生数组拷贝哪个更耗时 本人并未做实验对比。

LinkedList 作为对比： 1。物理存储结构采用链表，入栈和出栈都是对一个指针变量++/--的运算，效率显然高很多。 2。方法里 没有 synchronized。












