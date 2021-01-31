







整数数组 nums


大小为 k 的滑动窗口


滑动窗口从数组的最左侧移动到数组的最右侧 


只可以看到在滑动窗口内的 k 个数字


滑动窗口每次只向右移动一位


返回滑动窗口中的最大值



// 我的想法：max为k? 有这么简单？
// 2021年1月2日14:26:46
// 看了题解，发现没那么简单


vector<int> maxSlidingWindow(vector<int>& nums, int k) {
    MonotonicQueue window;
    vector<int> res; //数组res-->表示result，用来存储 结果值；

    for (int i = 0; i < nums.size(); i++) {
        if (i < k - 1) { 
            //1.先把窗口的前 k - 1 填满
            //这意思是：
            // 先把窗口填满k-1个，还剩下一个(总共能填充k个元素)
            window.push(nums[i]);
        } 
        else { 
            //2.窗口开始向前滑动

            // 入队列push；
            // 入完后，这时窗口就是满的(k个元素)
            window.push(nums[i]);

            //3.获取当前窗口里的max值，存入res数组里；
            res.push_back(window.max());

            // 4.出队列(窗口头部) 吐出一个元素来，为下一个元素 入队列(窗口尾部) 做准备
            window.pop(nums[i - (k - 1)]);        
            // nums[i - k + 1] 就是窗口最后的元素
            // todo:这个(i - k + 1)理解不了啊，啥意思？？没分析出来；

        }
    }
    return res;
}



/*
*
*https://leetcode-cn.com/problems/sliding-window-maximum/solution/dan-diao-dui-lie-by-labuladong/
* 国内站最优题解: 单调队列
*  
* 在一堆数字中，已知最值，如果给这堆数添加一个数，那么比较一下就可以很快算出最值；但如果减少一个数，就不一定能很快得到最值了，而要遍历所有数重新找最值。
* 回到这道题的场景，每个窗口前进的时候，要添加一个数同时减少一个数，所以想在 O(1)O(1) 的时间得出新的最值，就需要「单调队列」这种特殊的数据结构来辅助了。
*  
* 单调队列：
* 「单调队列」的核心思路和「单调栈」类似。单调队列的 push 方法依然在队尾添加元素，但是要把前面比新元素小的元素都删掉：
*
* 复杂度分析：
* 队列操作(push、pop、getmax)的复杂度都是 O(1)；
*
三、算法复杂度分析：
读者可能疑惑，push 操作中含有 while 循环，时间复杂度不是 O(1) 呀，那么本算法的时间复杂度应该不是线性时间吧？ 

单独看 push 操作的复杂度确实不是 O(1)，但是*算法整体的复杂度*依然是 O(N)线性时间。 
要这样想，【nums 中的每个元素最多被 push_back 和 pop_back 一次，没有任何多余操作】，所以整体的复杂度还是 O(N)。

空间复杂度就很简单了，就是窗口的大小 O(k)。

复杂度分析：
时间复杂度 O(n) ： 其中 nn 为数组 numsnums 长度；线性遍历 numsnums 占用 O(N) ；每个元素最多仅入队和出队一次，因此单调队列 dequedeque 占用 O(2N) 。
空间复杂度 O(k) ： 双端队列 dequedeque 中最多同时存储 k 个元素（即窗口大小）。

*/ 


/*
* 1.解法：单调队列 
*   
* 2.复杂度分析:
*   时间：O(n)
*   空间：O(k)
*  
*/ 

// most vote  c++
class MonotonicQueue {
private:
    deque<int> data;
public:
    //1.入队列，从队列尾部 入
    void push(int n) {
        while (!data.empty() && data.back() < n) 
            // 删光 队列里所有比n小的元素，然后再插入n元素（可以删掉无用的小元素，因为题目需要的是max值）；
            // 最终单调队列中的元素大小就会保持一个单调递减(头->尾)的顺序

            // 1.1.尾部 出队列  删除队所有比n小的元素
            data.pop_back();

        // 1.2.尾部 入队列
        data.push_back(n);
    }
    
    // 单调队列里：头部元素最大；
    int max() { return data.front(); }
    
    //头部 出队列 （队列特点，只能在头尾插入和删除节点(头结点)）
    void pop(int n) {
        // 当要删除的元素n与头部元素不一样，就不用删了(因为头部元素是最大值，是被挪到头部来的)
        // 如果一样，则删掉(说明当前最大的节点就算要删除的节点n)
        if (!data.empty() && data.front() == n)
            data.pop_front();
    }
};

vector<int> maxSlidingWindow(vector<int>& nums, int k) {
    MonotonicQueue window;
    vector<int> res;
    for (int i = 0; i < nums.size(); i++) {
        if (i < k - 1) { 
            //1.先把窗口填满k-1个，还剩下一个；(窗口大小：k)
            window.push(nums[i]);
        } else { 
            //2. 窗口向前滑动

            //2.1.入队列push；
            // 入完后，这时窗口就是满的(k个元素)
            window.push(nums[i]);

            //3.获取当前窗口里的max值，存入res数组里；
            res.push_back(window.max());

            // 4.出队列，为下一个元素入队列(窗口尾部) 做准备
            window.pop(nums[i - k + 1]);
        }
    }
    return res;
}





// most vote  python
def maxSlidingWindow(self, nums, k):
    win = [] # 在window里的元素的index
    ret = []
    for i, v in enumerate(nums):
        if i >= k and win[0] <= i - k: win.pop(0)
        while win and nums[win[-1]] <= v: win.pop()
        win.append(i)
        if i >= k - 1: ret.append(nums[win[0]])
    return ret