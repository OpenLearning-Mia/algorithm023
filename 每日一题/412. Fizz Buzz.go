


// 1网友问：
// 像这样的解法和用%有什么区别吗，哪个的复杂度更低一点喃？
// 小白，望不吝赐教

// 2回答:一般来说，对于CPU取余数的运算相对来说效率很低，
// 上面解法避免了使用大量的求余数操作，提升了程序的性能
// todo：如上意思是说：取余数的方法是最次的。。？

// 审题，分析

需求：输出从 1 到 n 数字的字符串表示
输出：将结果存入 字符串数组, 输出数组；


// 1.二维数组
new array[n][32]

// 2.
for 遍历 i from n {
	if (i % 3 == 0 && i % 5 == 0){ //或者直接 if(i % 15 == 0)
		array[i] = “FizzBuzz”
	}
	else if (i % 3 == 0){
		array[i] =  “Fizz”
	}
	else if (i % 5 == 0){
		array[i] =  “Buzz”
	}
	else{
		array[i] =  i
	}
}


时间: O(n) 遍历n个数
空间: O(n) 数组存储n个字符串 //官方答案怎么是O(1)?







/*
* 1.解法：遍历
*   
* 2.复杂度分析:
*   时间：O(n)
*   空间：O(n)
*
*/ 
// 国内官方版
class Solution {
	public List<String> fizzBuzz(int n) {
  
	  // ans list
	  List<String> ans = new ArrayList<String>();
  
	  for (int num = 1; num <= n; num++) {
  
		boolean divisibleBy3 = (num % 3 == 0);
		boolean divisibleBy5 = (num % 5 == 0);
  
		if (divisibleBy3 && divisibleBy5) {
		  ans.add("FizzBuzz");
		} else if (divisibleBy3) {
		  ans.add("Fizz");
		} else if (divisibleBy5) {
		  ans.add("Buzz");
		} else {
		  ans.add(Integer.toString(num));
		}
	  }
  
	  return ans;
	}
  }
  




//most vote  not using "%" operation
public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<String>(n);
        for(int i=1,fizz=0,buzz=0;i<=n ;i++){
            fizz++;
            buzz++;
            if(fizz==3 && buzz==5){
                ret.add("FizzBuzz");
                fizz=0;
                buzz=0;
            }else if(fizz==3){
                ret.add("Fizz");
                fizz=0;
            }else if(buzz==5){
                ret.add("Buzz");
                buzz=0;
            }else{
                ret.add(String.valueOf(i));
            }
        } 
        return ret;
    }
}