

public class Solution {

    public int maxArea(int[] height) {

        int l = 0, r = height.length - 1;
        int ans = 0;

        while (l < r) {

            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }
}



// most vote
int maxArea(int* heights, int n) {

    int water = 0, *i = heights, *j = i + n - 1;

    while (i < j) {
        //1.算面积
        int h = *i < *j ? *i : *j;
        int w = (j - i) * h;

        //2.更新最大面积maxArea的值
        if (w > water) 
            water = w;

        //3.继续挪动短边，过滤掉更短的边，找出更长的边(为了后面找更大的面积)
        // 巧妙点：这里用while来过滤掉下一条边更短(那总面积更小)的情况，减少不必要的计算，提高效率；
        while (*i <= h && i < j) i++; 
        while (*j <= h && i < j) j--;
    }
    return water;
}

// most vote
int maxArea(int* heights, int heightSize) {

    // 0.防错处理
    if (heights == NULL || heightSize <= 0){
        return -1;
    }

    int maxArea = 0;
    int *left = heights;
    int *right = left + heightSize - 1;

    while (left < right) {
        //1.算面积
        int minHigh = *left < *right ? *left : *right;
        int area = (right - left) * minHigh;

        //2.更新 最大面积maxArea的值
        maxArea = (area > maxArea) ? area : maxArea;


        //3.继续挪动短边，过滤掉更短的边，找出更长的边(为了后面找更大的面积)
        // 巧妙点：这里用while来过滤掉所有下一条更短的边(那总面积更小)的情况，减少不必要的计算，提高效率.
        while (*left <= minHigh && left < right) {
            left++; 
        } 
        while (*right <= minHigh && left < right) {
            right--; 
        } 
    }

    return maxArea;
}



int maxArea(int* heights, int heightSize) {

    int maxArea = 0;
    int *left = heights;
    int *right = left + heightSize - 1;
    int minHigh = 0;
    int area = 0;


    while (left < right) {
        //1.算面积
        minHigh = *left < *right ? *left : *right;
        area = (right - left) * minHigh;

        //2.更新最大面积maxArea的值
        maxArea = (area > maxArea) ? area : maxArea;

        //3.继续挪动短边，过滤掉更短的边，找出更长的边(为了后面找更大的面积)
        // 巧妙点：这里用while来过滤掉下一条边更短(那总面积更小)的情况，减少不必要的计算，提高效率；
        while (*left <= minHigh && left < right){
            left++; 
        } 
        while (*right <= minHigh && left < right){
            right--; 
        } 
    }
    return maxArea;
}





// 我在上面基础上做的优化，提高阅读体验
int maxArea(int* heights, int heightSize) {
    
    // 0.防错处理
    if (heights == NULL || heightSize <= 0){
        return -1;
    }

    int *left = heights;
    int *right = left + (heightSize - 1);
    int minHigh = 0;
    int maxArea = 0;   
    int area = 0;


    while (left < right){
        //1.算面积
        minHigh = *left < *right ? *left : *right;
        area = (right - left) * minHigh;

        //2.更新面积maxArea的值
        maxArea = (area > maxArea) ? area : maxArea;

        //3.继续挪动短边，过滤掉更短的边，找出更长的边(为了后面找更大的面积)
        while (*left <= minHigh && left < right){
            left++;
        }
        while (*right <= minHigh && left < right){
            right--;
        }
    }

    //4.到这里表示 找到了最大面积值maxArea，返回；
    return maxArea;

}




// 我自己写的；

int maxArea(int* heights, int n) {


// 思路：
// 1. 求x,y围起来的最大面积
// 2. x轴两条边越越远越好 
// 3. y的长度越长越好；(计算取决于短板的长度，所以尽量增大短板的长度)
/

// 伪代码：

// 数组名：height 
// 数组长度len >=2
// 数组元素值 >= 0


// 双指针法，左右指针往中间遍历；
// 左右指针，向中间移动；谁短就移谁;
// 计算出所有的面积值，只要面积最大的 max；
// 两指针相遇时,停止，结束；返回最大面积max值；


height[left],    height[right]


if (height[left] < height[right]){
    left++;
}
else{
    right++;    
}


// 计算面积的公式
// x*y
// area = (right - left) * Min(height[left], height[right]);

int min(int a, int b){
    return a < b ? a: b;
}

// 我第一次的解法，卡在如下todo那
int maxArea(int* height, int heightSize){

    // 0.防错处理
    if (height == NULL || heightSize <= 0){
        return -1;
    }

    int left = heightSize;
    int right = heightSize - 1;
    int area = 0;
    int max = 0;

    while (left < right){
        //1.算面积
        area = (right - left) * min(height[left], height[right]);

        //2.更新面积最大值max；
        max = (max >= area ? max : area);

        //3.继续挪动短边，计算新的面积是否比现有的更大； 
        if (height[left] < height[right]){
            left++;
            //todo：下一条边比当前这个还短的情况(那面积肯定更小，因为x，y都变小了)：怎么跳过下一个，直接下下一个边，这种处理；优化；
        }
        else{
            right++;    
        }
    }

    // 到这里表示 找到了最大面积值max，返回；
    return max;
}

// 测试：输出结果有问题；输出为0；是right++; 导致的问题，应该是right--； 
// 小傻子，反复一点点的改好几遍才发现是这块问题，细节决定成败呀，真是眼睛看不到；跟瞎了似的，
// 主要原因是 自己就做错了,在这点上, 考虑错, 我想的是right++, 应该是right--才是合理；是我想当然了；没又过脑子思考。
// 2020年12月30日14:33:34
// 2020年12月30日18:27:40




// 复杂度：
// 时间：







}