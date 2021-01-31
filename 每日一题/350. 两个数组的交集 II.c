







// 350. Intersection of Two Arrays II

// 给定 两个数组，
// 编写一个函数来 计算它们的交集。

// emmm...，看不懂题目的说明


方法一：哈希表

同一个数字在两个数组中都可能出现多次，
因此需要用哈希表存储每个数字出现的次数； //为啥？干啥用？


对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
// 比如在arrayA中出现2次，在arrayB中出现5次, 那交集里的次数就是2




首先遍历第一个数组，
并在哈希表中记录：
第一个数组中的每个数字 以及对应出现的次数，

然后遍历第二个数组，对于第二个数组中的每个数字：

如果在哈希表中存在这个数字，则将该数字添加到答案，
并减少哈希表中该数字出现的次数。



为了降低空间复杂度，
首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，
然后遍历较长的数组得到交集。
// 1.因为数组越短，哈希表的存储空间越小；
// 2.再一个，短的数组遍历完了，func就结束了；
    //2不对，长的遍历完了func才结束





// 复杂度分析：
    // 时间：O(m+n)
    // 空间：O(min(m,n)) (哈希表空间)
    // 哈希表操作的时间复杂度是 O(1)


// 方法一：哈希表
// map：无序的键值对集合
// Map 最重要的一点是通过 key 来快速检索数据，key 类似于索引，指向数据的值
// intersection：交集

/*
* 1.解法：哈希表  
*   
* 2.复杂度分析:
*   时间：O(m+n)
*   空间：O(min(m,n))
*  
*/ 
func intersect(nums1 []int, nums2 []int) []int {

    // 1.这段code目的是：保证进来处理的nums1一定是最短的数组，后面的nums1都表示短数组
    // 这个想法真妙……第一遍没看懂；
    if len(nums1) > len(nums2) {
        // 如果输入的两个数组，nums1比nums2长，
        // 那就再回到调用func intersect，保证输入的数组是nums1比nums2更短；
        // (这个函数，保证传入参数nums1比nums2更小，因为后面是先处理短数组，再处理长数组)
        return intersect(nums2, nums1)
    }

    // 2.先处理短数组nums1；
    // 遍历数组nums1，将该数组里的数字及其出现的次数加入map
    m := map[int]int{}
    for _, num := range nums1 {
        // num(数组里的数字)为map的key,用来做map的索引；
        // m[num]++ 为数字出现的次数
        m[num]++
    }

    // 3. 再处理长数组nums2, 
    // 遍历nums2，在map中查找是否存在该数组里的元素；
    // 存在：那就表示两数组元素(数字)有一个交集，将该数字加入交集数组(最终的结果集合)
    // 如此，遍历完nums2，找到所有可能的交集数字。
    // intersection is the results of func；
    intersection := []int{}
    for _, num := range nums2 {
        if m[num] > 0 {
            intersection = append(intersection, num)
            m[num]--
        }
    }
    return intersection
}



// 版本迭代，优化 ？如果排序时间不是O(logn)，那就没有优化，与哈希表解法比较；
// 方法二：排序

如果两个数组是有序的，则可以便捷地计算两个数组的交集。
// 怎么便捷地计算？

首先对两个数组进行排序，然后使用两个指针遍历两个数组。

初始时，两个指针分别指向两个数组的头部。

每次比较两个指针指向的两个数组中的数字，

如果两个数字不相等，则将指向较小数字的指针右移一位，
// 不等，就移动小的数字

如果两个数字相等，将该数字添加到答案，
并将两个指针都右移一位。
当至少有一个指针超出数组范围时，遍历结束。

// me的理解、翻译：
// 相等，就表示找到了一个交集，加入结果集合里；
// 然后两指针接着比较各自下一个数字是否相同(两个指针同时右移一位)
// 结束条件：有一个指针向右 指到头了(已遍历完最后一元素)，则func结束；




/*
* 1.解法：排序   todo：排序的时间复杂度是O(nlogn)吗？？没有O(logn)的排序吗？？
*   
*   迭代、优化
*
* 2.复杂度分析:
*   时间：O(mlogm+nlogn)
*   空间：O(min(m,n))
*  
*/ 
func intersect(nums1 []int, nums2 []int) []int {
    //1. 先给两个数组 排序
    sort.Ints(nums1)
    sort.Ints(nums2)

    length1, length2 := len(nums1), len(nums2)
    index1, index2 := 0, 0

    //2.使用两个指针遍历这两个数组，比较数字大小:
    // 相等，就表示找到了一个交集，加入结果集合intersection，然后两指针同时右移一位，接着比较下一位
    // 不相等，就挪数字小的那个指针，再来比较数字大小；
    // 结束条件：index1 = length1或index2 = length2，表示指针已走到头了，func end;
    intersection := []int{}
    for index1 < length1 && index2 < length2 {
        if nums1[index1] < nums2[index2] {
            index1++
        } else if nums1[index1] > nums2[index2] {
            index2++
        } else {
            intersection = append(intersection, nums1[index1])
            index1++
            index2++
        }
    }
    return intersection
}




// 结语
// 如果 \textit{nums}_2nums的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中。那么就无法高效地对 \textit{nums}_2nums 
// 2
// ​	
//   进行排序，因此推荐使用方法一而不是方法二。在方法一中，\textit{nums}_2nums 
// 2
// ​	
//   只关系到查询操作，因此每次读取 \textit{nums}_2nums 
// 2
// ​	
//   中的一部分数据，并进行处理即可。
