











// 斐波那契数，用 F(n) 表示

// 该数列由 0 和 1 开始；

// 后面的每一项数字 都是 前面两项数字的和

// F(0) = 0，F(1) = 1
// F(n) = F(n - 1) + F(n - 2)，其中 n >= 2

// 需求：给你 n ，请计算 F(n);
// 伪代码：
// 输入：n
// 输出：F(n)



int fib(int n){

    //1.递归终止条件；
    if (n == 0){
        return 0;
    }
    else if (n == 1){
        return 1；
    }
    
    //2.重复做同一件事；
    f(n) = f(n - 1) + fib(n-2);

    return f(n);


}


/*
* 1.解法：动态规划
*
* 2.复杂度分析:
*   时间：O(n)
*   空间：O(1)
*  
*  F(n)=F(n−1)+F(n−2)
*  (sum = prev + curr)
*/ 

// 官方题解
func fib(n int) int {
    if n < 2 {
        return n
    }
    prev, curr, sum := 0, 0, 1
    for i := 2; i <= n; i++ {
        prev = curr
        curr = sum
        // F(n)=F(n−1)+F(n−2)
        sum = prev + curr
    }
    return sum
}

// 2021年1月4日16:46:46 +1

