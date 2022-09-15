package custom.code_2022_05;

/**
 * @ClassName LeetCode_523
 * @Author Duys
 * @Description
 * @Date 2022/5/6 10:48
 **/
// n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
//链接：https://leetcode-cn.com/problems/n-queens-ii
public class LeetCode_52 {
    public static int totalNQueens(int n) {
        int ans = 0;
        if (n < 1) {
            return ans;
        }
        int[][] dp = new int[n][n];
        return process(dp, n, 0);
    }

    public static int process(int[][] dp, int n, int row) {
        if (row == n) {
            return 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!isOk(dp, row, i, n)) {
                continue;
            }
            dp[row][i] = 1;
            ans += process(dp, n, row + 1);
            dp[row][i] = 0;
        }
        return ans;
    }

    public static boolean isOk(int[][] dp, int row, int col, int n) {
        // 检查这一列是否已经存在了
        for (int i = 0; i < n; i++) {
            if (dp[i][col] == 1) return false;
        }
        // 右上方
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (dp[i][j] == 1) return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (dp[i][j] == 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(totalNQueens(5));
    }
}
