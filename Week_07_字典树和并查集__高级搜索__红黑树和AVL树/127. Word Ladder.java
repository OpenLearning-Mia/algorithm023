// 2021年2月20日10:02:22
// 单词接龙，谷歌面试 考得非常频繁



// 国内most votes

// 时间复杂度：
// I think the time complexity is length(beginWord)*length(wordList), 
// space complexity is length(wordList) 
/* 
方法二：双向广度优先遍历
    已知目标顶点的情况下，可以分别从起点和目标顶点（终点）执行广度优先遍历，直到遍历的部分有交集。这种方式搜索的单词数量会更小一些；
    更合理的做法是，每次从单词数量小的集合开始扩散；
    这里 beginVisited 和 endVisited 交替使用，等价于单向 BFS 里使用队列，每次扩散都要加到总的 visited 里。

 */
public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        // 题目附加信息里有给出：字典不为空；
        Set<String> wordSet = new HashSet<>(wordList);

        // 特判：endWord不在字典中，则无法进行转换，func end;
        if (!wordSet.contains(endWord)) {  
            return 0;
        }

        // 第 2 步：已经访问过的 word 添加到 visited 哈希表里
        Set<String> visited = new HashSet<>();

        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;  //why 1 ？？？
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少（指：在changeWordEveryOneLetter里字符的遍历修改次数少）
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;  //小的哈希表作为beginVisited，大的作为endVisited
                endVisited = temp;
            }


            // 逻辑到这里，保证 beginVisited 是相对较小的集合，nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            // 
            for (String word : beginVisited) { // 输入：word, wordSet, endVisited；  输出：visited, nextLevelVisited
                if (changeWordEveryOneLetter(word, wordSet, endVisited, visited, nextLevelVisited)) {
                    return step + 1;  //最终答案；表示找到最后一个单词了，func end;
                }
            }



            // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
            beginVisited = nextLevelVisited;
            step++;
        }

        // 到这里表示beginVisited或endVisited为空，单词转换失败
        return 0;
    }


    /**
     * 尝试对 word 修改每一个字符，看看是不是能落在 endVisited 中，扩展得到的新的 word 添加到 nextLevelVisited 里
     *
     * @param word
     * @param endVisited
     * @param visited
     * @param wordSet
     * @param nextLevelVisited
     * @return
     */
    private boolean changeWordEveryOneLetter(String word, Set<String> endVisited,
                                             Set<String> visited,
                                             Set<String> wordSet,
                                             Set<String> nextLevelVisited) {
        char[] charArray = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {  // word.length()<=10
            char originChar = charArray[i];

            for (char c = 'a'; c <= 'z'; c++) { //遍历26个字母
                if (originChar == c) { //跳过与自己相同的字符
                    continue;
                }
                
                charArray[i] = c;  //当前字符替换为新字符c
                // 当前新单词nextWord
                String nextWord = String.valueOf(charArray);

                // 查看新单词是否在字典中，如果在，则？？
                if (wordSet.contains(nextWord)) {
                    // 查看新单词是否在endVisited中，在 则表示什么？？啥意思？？
                    if (endVisited.contains(nextWord)) {
                        return true; //表示找到最后一个单词了，程序即将结束？
                    }
                    // 到这表示：新单词nextWord不在endVisited，也未被访问过(visited)
                    // 则：
                    if (!visited.contains(nextWord)) {
                        // 这是干啥？有啥用？？todo
                        nextLevelVisited.add(nextWord);
                        visited.add(nextWord);  //给新单词打上已访问标记，防止重复访问？？？
                    }
                }
            } //for2 end
            
            charArray[i] = originChar; // 恢复，下次再用
        } //for1 end

        return false;
    }
}









// 单向BFS
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);
        
        // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
        int step = 1;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                // 依次遍历当前队列中的单词
                String currentWord = queue.poll();
                // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                if (changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordSet)) {
                    return step + 1;
                }
            }
            step++;
        }
        return 0;
    }

    /**
     * 尝试对 currentWord 修改每一个字符，看看是不是能与 endWord 匹配
     *
     * @param currentWord
     * @param endWord
     * @param queue
     * @param visited
     * @param wordSet
     * @return
     */
    private boolean changeWordEveryOneLetter(String currentWord, String endWord,
                                             Queue<String> queue, Set<String> visited, Set<String> wordSet) {
        char[] charArray = currentWord.toCharArray();
        for (int i = 0; i < endWord.length(); i++) {
            // originChar备份，后面要恢复回来；
            char originChar = charArray[i];
            for (char k = 'a'; k <= 'z'; k++) {
                if (k == originChar) {
                    continue;
                }
                charArray[i] = k;
                String nextWord = String.valueOf(charArray);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) {
                        return true;
                    }
                    if (!visited.contains(nextWord)) {
                        queue.add(nextWord);
                        // 注意：添加到队列以后，必须马上标记为已经访问
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复
            charArray[i] = originChar;
        }
        return false;
    }
}




// 2021年2月21日19:48:07  理解  first-time
















做题流程:

1.理解题目意思和需求;


2.审题,分析,确认它的问题模型是什么???,  找它的解决方案模型

3.给出解题方法和思路流程 (找多个最优解：国内3个和国外3个，确定最终的最优解)

4.代码实现


5.复杂度分析


6.做题感受总结:


7.网友讨论区:提问、思考问题、回答问题，摘抄记录好的想法见解


8.疑问记录区;






1.题目意思和需求是什么？？



1输入：
    i.字典 wordList，里面装着单词，单词之间没有重复的；
    ii.两个单词：单词 beginWord 和 endWord

2输出：
    实现一个最短转换序列，（单词接龙）
    该序列中：第一个单词是beginWord，最后一个单词是 endWord；

3具体要求：
    1.从beginWord转换到endWord的最短距离（最少单词个数）；（单词接龙）
    2.每次单词转换，从wordA到wordA，只能改变一个字母；
    3.转换过程中的中间单词必须是字典 wordList 中的单词；

4.隐含客观条件 翻译：
    根据实例可知：endWord也属于被转换出的中间单词，
    也就是endWord也必须是字典中的单词(beginWord可以不是)
    (如果endWord不在字典中，则转换失败)
    //题目举例里 已明确说明这一点： endWord不在字典中，所以无法进行转换，func end;


5.附加信息：
    beginWord != endWord；// 首尾单词不同，单词的长度相同 
    //所有单词长度一样
    endWord.length == beginWord.length，wordList[i].length == beginWord.length 
    1 <= beginWord.length <= 10； // 单词长度>=1，也就是单词不为空；
    1 <= wordList.length <= 5000  //字典中的单词个数>=1, 也就是字典不为空





// ladder:阶梯，梯子



