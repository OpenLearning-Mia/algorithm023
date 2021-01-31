



// 国内官方
// go



/*
* 1.题目需求：
*	输入：一个数组；其元素为n个顾客买一杯柠檬水（价值$5），向你付的钱（只可能是$5或$10或$20）
*	输出：必须给每个顾客正确找零，如果能给每位顾客正确找零，返回 true ，否则返回 false。
*   附加条件：一开始你手头没有任何零钱
*   eg: 输入：[5,5,5,10,20]
*       输出：true
*
* 2.解法：模拟 + 贪心
*
* 3.解题思路：
*	3.1.由于顾客只可能给三个面值的钞票($5或$10或$20)，并且我们一开始没有任何钞票；因此分三种情况讨论：
*
*	3.2. 5美元，柠檬水价格为5美元，所以直接收下,不用找；
*
*	3.3. 10美元，需要找回5美元，如果没有5美元钞票，则无法正确找零，return false;
*	
*	3.4. 20美元，需要找回15美元；此时有两种找零方式：
*           a.一张10美元和5美元的钞票
*           b.三张5美元的钞票
*   3.5.如果这两种组合方式都没有，则无法正确找零，return false；
*    
*   3.6.如果可以正确找零，优先用第一种方式找零，因为需要使用5美元的找零场景比需要使用10美元的找零场景多，（10元能用来找零付款20元，5元则适用找零10元、20元的）
*        需要尽可能保留5美元钞票；(贪心思想，保证局部最优推出全局最优)
*   
*   3.7.如此，维护两个变量five和ten表示当前手中拥有的5美元和10美元钞票的张数,从前往后遍历数组分类讨论.
*
*	
* 4.复杂度分析:
*   时间：O(n)；遍历计算n次，n为bills数组长度；
*   空间：O(1)；常量空间大小
 */
func lemonadeChange(bills []int) bool {
    five, ten := 0, 0

    for _, bill := range bills {
        if bill == 5 {
            five++
        } else if bill == 10 {
            if five == 0 {
                return false
            }
            five--
            ten++
        } else { //bill == 20
            if five > 0 && ten > 0 {  //先把10元的找出去；5元太宝贵、找零用处更大,能留则留(贪心算法思想)
                five--
                ten--
            } else if five >= 3 {
                five -= 3
            } else {
                return false
            }
        }
    }
    return true
}


// java
class Solution {
    public boolean lemonadeChange(int[] bills) {

        int five = 0, ten = 0;

        for (int bill : bills) {

            if (bill == 5) {
                five++;

            } else if (bill == 10) {

                if (five == 0) {
                    return false;
                }
                five--;
                ten++;

            } else {

                if (five > 0 && ten > 0) {
                    five--;
                    ten--;

                } else if (five >= 3) {

                    five -= 3;

                } else {

                    return false;
                }
            }
        }

        return true;
    }
}
