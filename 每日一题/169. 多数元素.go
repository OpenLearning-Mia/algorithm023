
























// 
// 




/*
* 1.题目需求：
*	输入：
*	输出：
*   附加条件：
*   eg: 输入：[5,5,5,10,20]
*       输出：true
*
* 2.解法：哈希表
*
* 3.解题思路：
*	3.1.使用哈希映射（HashMap）来存储每个元素以及出现的次数。
*
*	3.2.对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。
*	
* 4.复杂度分析:
*   时间：O(n)；n为nums数组长度；
*   空间：O(n)；哈希表最多包含n-n/2个键值对
 */

class Solution {
    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }
}
