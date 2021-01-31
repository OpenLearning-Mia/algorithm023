
/*
1 <= g.length <= 3 * 104      // 小孩人数  >= 1个
0 <= s.length <= 3 * 104     // 饼干个数 >=0
1 <= g[i], s[j] <= 231 - 1      // 每个小孩胃口值 >= 1， 每个饼干尺寸 >= 1；


*/  


// 命名：驼峰

#include <stdio.h>
#include <string.h>



int findContentChildren(int *g, int gSize, int *s, int sSize){

    int i;
    int j;


    // 0.防错处理
    if (g == NULL || s == NULL || gSize <=0 || sSize < 0)
    {
        return 0;
    }

    int max_feedchild_num = 0;

    // 1.
    if (sSize == 0)
    {
        return max_feedchild_num;
    }

    // 2.find the max feed-child num 
    for (i = 0; i <= gSize; i++) {
        for (j = 0; j <= sSize; j++) {
            if (s[j] >= g[i]) {  //满足分发条件，分发and下一个孩子；        
                max_feedchild_num++;
            }
            //到这里，表示不满足分发条件，找下一个饼干来试试；
        }
    }

    return max_feedchild_num;
}



int main(){

    int max = 0;
    int g[] = {1,2,3};
    int s[] = {1,1};

    max = findContentChildren(g, sizeof(g)/sizeof(int), s, sizeof(s)/sizeof(int));
    printf("max is %d\n", max);

    return 0
}






// 方法一：排序 + 贪心算法   
// todo: 这题怎么用到 贪心算法 了？  贪心算法是什么？？

// 2020年12月29日10:42:27   官方

int cmp(int* a, int* b) {
    return *a - *b;
}

int findContentChildren(int* g, int gSize, int* s, int sSize) {

    qsort(g, gSize, sizeof(int), cmp);
    qsort(s, sSize, sizeof(int), cmp);


    int numOfChildren = gSize, numOfCookies = sSize;
    int count = 0;


    // i，g(胃口)表示小孩；j,s(bg尺寸)表示饼干
    // todo:这个for循环的时间复杂度是多少？
    for (int i = 0, j = 0; i < numOfChildren && j < numOfCookies; i++, j++) {

        while (j < numOfCookies &&  s[j] < g[i]) {  //表示 饼干不够当前小孩吃，
            j++; //找下一个饼干来试试
        }

        if (j < numOfCookies) {
            // 1.找到了；到这里表示：为当前小孩 找到适合的饼干了
            count++;  //count表示 已被分配饼干的小孩数量
        }
        //else 2.没找到；表示为当前小孩，没找到合适饼干; 
        // 接下来：轮询下一个小孩和下一个饼干(如果当前bg不够当前小孩吃，那这块bg也不够下一个小孩吃(有序)，所以要同时轮询下一块bg)

    }//end for


    return count;
}


// 复杂度分析，m: children   n: cookies

// 时间复杂度：O(m \log m + n \log n)O(mlogm+nlogn)，其中 mm 和 nn 分别是数组 gg 和 ss 的长度。对两个数组排序的时间复杂度是 O(m \log m + n \log n)O(mlogm+nlogn)，遍历数组的时间复杂度是 O(m+n)O(m+n)，因此总时间复杂度是 O(m \log m + n \log n)O(mlogm+nlogn)。

// 空间复杂度：O(\log m + \log n)O(logm+logn)，其中 mm 和 nn 分别是数组 gg 和 ss 的长度。空间复杂度主要是排序的额外空间开销。


// 作者：LeetCode-Solution
// 链接：https://leetcode-cn.com/problems/assign-cookies/solution/fen-fa-bing-gan-by-leetcode-solution-50se/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。




Arrays.sort(g);
Arrays.sort(s);
int i = 0;

for(int j=0;i<g.length && j<s.length;j++) {
	if(g[i]<=s[j]) i++;  //get cookie
}
return i;



int cmp(int* a, int* b) {
    return *a - *b;
}

int findContentChildren(int* g, int gSize, int* s, int sSize) {

    // g:child    s:cookie

    //1. sort
    qsort(g, gSize, sizeof(int), cmp);
    qsort(s, sSize, sizeof(int), cmp);

    // 2. find the feed-children
    int i = 0;
    for(int j = 0; i < gSize && j < sSize; j++) {
	    if(g[i] <= s[j])  //get cookie
            i++;  //i means  the max feed-children;
    }

    return i;

}




// 总结：时间和空间复杂度 忘了如何分析了！
// 这是重要紧急的事情！todo：看覃超和王争的这块学习方法和思路

