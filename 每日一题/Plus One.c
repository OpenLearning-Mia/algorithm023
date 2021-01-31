

// 1.审题，思路：

情况一： a != 9

123--》124
421--》422
0--》1

情况二： a = 9
129--》130
199-->200

情况三： 数组扩容一位
9 --》1，0
9，9，9 --》1，0，0，0


// 思路：
a[n] = {……}

最后一个元素是 a[n-1]；

从最后一个元素开始处理，

从后往前 遍历元素是否为9？ 



// 伪代码：

if (a[n-1] != 9){
    a[n-1] = a[n-1]+1;
}
else { //产生了进位
    a[n-1] = 0;
    a[n-2] = a[n-2] + 1;
}

printf(a[]);





// 我自己写的；
int plusOne(int *array, int arrLen){

    // 0.防错处理

    // 1.
    for(i = arrLen-1; i >= 1; i--){
        
        //1.无进位
        if (array[i] != 9){ 
            a[n-1] = a[n-1] + 1;
            break;  //end
        }
        else{
            //2.产生进位
            a[n-1] = 0;
            if (a[n-2] != 9) {
                //无进位
                a[n-2] = a[n-2] + 1;  
            }
            else{
                // 不会做了，越想越复杂，千丝万缕的，像毛线；用五毒神掌去了。
            }
        }
    


        //3.产生进位并扩容新位


    }



}











// most votes
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                //1.无进位情况，eg: 123 0  198
                digits[i]++;
                return digits;  //得到了想要的，so return and end.
            }
            
            //2.有进位 eg:189 199 999
            digits[i] = 0; //接下来轮询判断digits[i-1]的值和进位情况；妙在digits[i-1]的进位情况包含在上面if里来判断了
        }

        //到了这里，表示数组元素全是999的情况，比如999 --》1000，三位数变成四位数了，所以要对数组扩容一位。 
        
        int[] newNumber = new int [n+1];
        newNumber[0] = 1;
        
        return newNumber;
    }
}



todo: 用C、go语言怎么写？？？
todo2: 时间复杂度和空间复杂度是多少？



今日做题感受：
总是考虑不周全，看完答案才知道：哦，对，还有这种情况。

和自己对话：接受自己的现状，一点点来，不能放弃。这是在学习，不是失败，是在学习，这有利于达到我的最终目标。

