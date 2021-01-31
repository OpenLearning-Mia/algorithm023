










很长的花坛

一部分地块种植了花，另一部分却没有

花卉不能种植在相邻的地块上，
会争夺水源，两者都会死去


class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        // 每次跳两格
         for (int i = 0; i < flowerbed.size(); i += 2) {
             // 如果当前为空地
            if (flowerbed[i] == 0) {
                // 如果是最后一格或者下一格为空
                if (i == flowerbed.size() - 1 || flowerbed[i + 1] == 0) {
                    n--;
                } else {
                    i++;
                }
            }
        }
        return n <= 0;
    }
};




// most vote
// thinks of author:
// 首先这里我用的是连跳两格的方法，
// 因为如果遇到1,那么下一格子一定是0，这是毋庸置疑的（规则限定），
// 所以如果遇到最后一个格子，或者下个格子不是1，果断填充，代码如下：


class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {

        // 每次跳两格 (关键点, 通过演练分析 找到*跳两格*的规律)
         for (int i = 0; i < flowerbed.size(); i += 2) {
             // 如果当前为空地
            if (flowerbed[i] == 0) {
                // 如果是最后一格或者下一格为空,果断种下花
                if (i == flowerbed.size() - 1 || flowerbed[i + 1] == 0) {
                    //这个表示：种下一朵花了，总花朵数n减1
                    // 当n减到0时，表示n朵花都种完了；
                    // 这时func还没结束，把 地 遍历完了才结束，所以n存在小于0的情况；
                    n--;
                    // 我的优化:花都种完了，那就解决问题、找到答案了：
                    // n朵花都能种地(返回true)，结束func，不用再遍历后面是否有空地了，减少不必要的计算。
                    // 所以n只有0 和 > 0的情况，不要n < 0的情况了
                    // if (n == 0){
                    //     break; 改成 直接return true;
                    // }
                    // 最后return 就算 return n == 0;


                } else {
                    // 这里表示下一个是1(已种花)的情况，那要跳过：i++
                    // 再加上后面再接着跳两格的规律(i+=2)，一共跳三格。
                    i++;
                }
            }
        }

        // 到这里结束，返回种花的结果：
        // 1.都种完了，n <= 0, true  (n < 0表示花都种完了，后面还有很多空地)
        // 2.没种完，n > 0, false
        return n <= 0;
    }
};



class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        // 每次跳两格
         for (int i = 0; i < flowerbed.size(); i += 2) {
             // 如果当前为空地
            if (flowerbed[i] == 0) {
                // 如果是最后一格或者下一格为空
                if (i == flowerbed.size() - 1 || flowerbed[i + 1] == 0) {
                    n--;
                    if (n == 0){
                        break;
                    }
                } else {
                    i++;
                }
            }
        }
        return n == 0;
    }
};
