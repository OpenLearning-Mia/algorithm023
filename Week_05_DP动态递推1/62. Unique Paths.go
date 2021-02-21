


// dp[i][j] 表示走到格子 (i, j) 的方法数。

class Solution {
	public:
		int uniquePaths(int m, int n) {
			vector<int> pre(n, 1), cur(n, 1);

			for (int i = 1; i < m; i++) {
				for (int j = 1; j < n; j++) {
					cur[j] = pre[j] + cur[j - 1];
				}
				swap(pre, cur);
			}
			return pre[n - 1];
		}
	};



// space，O(2n)
class Solution {
    public int uniquePaths(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre, 1);
		Arrays.fill(cur,1);
		
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){

				// 
				cur[j] = cur[j-1] + pre[j];			
			}
			
            pre = cur.clone();
        }
        return pre[n-1]; 
    }
}




// space，O(n)
class Solution {
    public int uniquePaths(int m, int n) {
		int[] cur = new int[n];
		
		Arrays.fill(cur,1);
		
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
				cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }
}





/* 
优化一：由于dp[i][j] = dp[i-1][j] + dp[i][j-1]，
		因此只需要保留当前行与上一行的数据 (在动态方程中，即pre[j] = dp[i-1][j])，两行，空间复杂度O(2n)； 

优化二：cur[j] += cur[j-1], 即cur[j] = cur[j] + cur[j-1] 等价于思路二-->> cur[j] = pre[j] + cur[j-1]，
		因此空间复杂度为O(n).

*/