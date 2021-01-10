





























// 1.审题，分析
1.1.输入：
	1.一个整数数组 nums
	2.一个整数目标值 target


1.2.输出：
	1.在nums中找出和为target的那两个整数，并返回它们的数组下标(result)


1.3.理解举的例子：
	示例 1：
	输入：nums = [2,7,11,15], target = 9
	输出：[0,1]
	解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。


1.4.注意:
	1.每种输入只会对应一个答案(输出)，
	  其意思是：只有一个结果，找到了func就返回；



// 我个人的解题思路：
// 用哈希表 存num；然后遍历数组num，用target-num[i]的值来查找哈希表，找到就返回结果
// 时间O(n)； 最差情况：遍历完 数组num所有元素n个
// 空间O(n)； 哈希表存储数组里的所有元素n








// 国内 官网
func twoSum(nums []int, target int) []int {
    hashTable := map[int]int{}
    for i, x := range nums {
        if p, ok := hashTable[target-x]; ok {
            return []int{p, i}
        }
        hashTable[x] = i
    }
    return nil
}
