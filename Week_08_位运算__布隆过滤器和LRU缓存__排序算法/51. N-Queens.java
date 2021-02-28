// 2021年2月22日20:10:54


使皇后彼此之间不能相互攻击

// 怎样放 才算没有相互攻击？
提示：
1. 1 <= n <= 9
2. 皇后彼此不能相互攻击，
   也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。

输出应该是怎样的？看举例；
输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。

// 经验教训：先审题审清楚，搞清楚两点：1.题目是啥意思？输出应该是啥样的？
// 多看例子和提示信息，都是隐含的需求信息（解题需要的，少了就没法解题了）







// 用回溯算法来做 ，为啥用它？？理由是啥？？它比别的算法好在哪里？解决了什么问题？？



思路分析：以4皇后问题为例，它的「搜索」过程如下。大家可以在纸上模拟下面这个过程：






搜索的过程蕴含了 剪枝 的思想。
「剪枝」的依据是：题目中给出的 「N 皇后」 的摆放规则：
    1、不在同一行；2、不在同一列；3、不在同一主对角线方向上；4、不在同一副对角线方向上。



// 方法一：dfs+回溯
public class Solution {

    private int n;
    /**
     * 记录某一列是否放置了皇后
     */
    private boolean[] col;
    /**
     * 记录主对角线上的单元格是否放置了皇后
     */
    private boolean[] main;
    /**
     * 记录了副对角线上的单元格是否放置了皇后
     */
    private boolean[] sub;
    private List<List<String>> res;

    // 1.solveNQueens
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        // 设置成员变量，减少参数传递，具体作为方法参数还是作为成员变量，请参考团队开发规范
        this.n = n;
        this.col = new boolean[n];
        this.main = new boolean[2 * n - 1];
        this.sub = new boolean[2 * n - 1];
        Deque<Integer> path = new ArrayDeque<>();

        dfs(0, path);

        // 到这里表示，第一行的每一个节点都遍历完了（目的是找可能的位置存放第一个queen，题目要求找出所有可行的方案；）；
        // 摆放queen的顺序制约关系：先摆放第一行的，在摆放第二行的，第二行摆放；当前行摆放在哪，还得看上一行的quees在哪(因为当前queen不能和它产生冲突了)
        return res;  //最终答案
    }

    // 2.DFS过程：深度遍历行，遍历过程中对每一行做横向遍历(遍历列)，即遍历每一行中所有结点。
    private void dfs(int row, Deque<Integer> path) {

        // 1.递归结束条件：row下标越界（row最大为n-1）,表示已遍历完所有行row、遍历到底了
        if (row == n) {
            // 深度优先遍历到下标为 n，表示 [0.. n - 1] 已经填完，得到了一个结果board
            List<String> board = convert2board(path);
            res.add(board);
            return;
        }

// 每个单元格，被遍历不止一次；因为有试错重新再来的过程；

        // 2.有条件的进行DFS和回溯
        //   针对下标为 row 的每一列，尝试是否可以放置
        for (int j = 0; j < n; j++) {

            if (!col[j] && !main[(row - j) + (n - 1)] && !sub[row + j]) {

                //表示当前[row,j]可以放皇后；
                path.addLast(j); //这句干嘛？todo
                col[j] = true;
                main[row - j + n - 1] = true;
                sub[row + j] = true;

                // 继续向下一行row深度遍历:找出适合位置，放下一个皇后
                dfs(row + 1, path);

                // 到这表示回溯(从下一层试错失败，返回到当前层)
                // 回溯，则恢复数据（修改这些数据是为了进入下一层使用）
                sub[row + j] = false;
                main[row - j + n - 1] = false;
                col[j] = false;
                path.removeLast();
            }
        }
    }

    // 这个是干嘛？？
    private List<String> convert2board(Deque<Integer> path) {
        List<String> board = new ArrayList<>();

        for (Integer num : path) {
            StringBuilder row = new StringBuilder();
            row.append(".".repeat(Math.max(0, n)));
            row.replace(num, num + 1, "Q");

            // 将当前找到的一个放queen的字符串方案加入结果集合board里；
            board.add(row.toString());
        }
        return board;
    }
}

// 网友疑问：
// 方法一的时间复杂度怎么是N!呢？不应该是N^N吗？
/* 
第1行枚举N个, 第2行枚举N-1, ..., 第N行枚举1个 => O(N!)
照着递归式画棵树就比较好理解了  //key, 对照这个树图来理解过程和时间复杂度的分析
每个都枚举了, 但是只有一部分往下递归了
 */
// 2021年2月24日11:30:57 done-fisrt



// 方法二：基于位运算的回溯  （与方法一相比，就是实现细节不同：用位运算来表示指定位置能否放皇后，空间复杂度降低了些？todo）
// 2021年2月24日11:31:16 todo

var solutions [][]string

func solveNQueens(n int) [][]string {
    solutions = [][]string{}
    queens := make([]int, n)
    for i := 0; i < n; i++ {
        queens[i] = -1
    }
    solve(queens, n, 0, 0, 0, 0)
    return solutions
}

func solve(queens []int, n, row, columns, diagonals1, diagonals2 int) {
    if row == n {
        board := generateBoard(queens, n)
        solutions = append(solutions, board)
        return
    }

    availablePositions := ((1 << n) - 1) & (^(columns | diagonals1 | diagonals2))
    
    for availablePositions != 0 {
        position := availablePositions & (-availablePositions)
        availablePositions = availablePositions & (availablePositions - 1)
        column := bits.OnesCount(uint(position - 1))
        queens[row] = column
        solve(queens, n, row + 1, columns | position, (diagonals1 | position) >> 1, (diagonals2 | position) << 1)
        queens[row] = -1
    }
}

func generateBoard(queens []int, n int) []string {
    board := []string{}
    for i := 0; i < n; i++ {
        row := make([]byte, n)
        for j := 0; j < n; j++ {
            row[j] = '.'
        }
        row[queens[i]] = 'Q'
        board = append(board, string(row))
    }
    return board
}




































// 国际most vote

class Solution {
public:
    std::vector<std::vector<std::string> > solveNQueens(int n) {
        std::vector<std::vector<std::string> > res;
        std::vector<std::string> nQueens(n, std::string(n, '.'));
        solveNQueens(res, nQueens, 0, n);
        return res;
    }
private:
    void solveNQueens(std::vector<std::vector<std::string> > &res, std::vector<std::string> &nQueens, int row, int &n) {
        if (row == n) {
            res.push_back(nQueens);
            return;
        }

        for (int col = 0; col != n; ++col)
            if (isValid(nQueens, row, col, n)) {
                nQueens[row][col] = 'Q';
                solveNQueens(res, nQueens, row + 1, n);

                nQueens[row][col] = '.';
            }
    }
    bool isValid(std::vector<std::string> &nQueens, int row, int col, int &n) {
        //check if the column had a queen before.
        for (int i = 0; i != row; ++i)
            if (nQueens[i][col] == 'Q')
                return false;
        //check if the 45° diagonal had a queen before.
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j)
            if (nQueens[i][j] == 'Q')
                return false;
        //check if the 135° diagonal had a queen before.
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; --i, ++j)
            if (nQueens[i][j] == 'Q')
                return false;
        
        return true;
    }
};






















可以不用哈希表啊，用一个 int 数组速度更快
第几列有皇后就把下标为几的元素置 1，和主对角线平行的同理，把下标为行 + 列的元素置 1
和副对角线平行的，由于可能会出现负数，所以需要行 - 列 + n
用 unordered_set<int> 三十多毫秒，用 vector<int> 0 毫秒
反正行 + 列最多 2n .jpg