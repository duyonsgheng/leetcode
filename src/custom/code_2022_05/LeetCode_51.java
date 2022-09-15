package custom.code_2022_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_51
 * @Author Duys
 * @Description
 * @Date 2022/5/5 17:44
 **/
// n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
//每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//链接：https://leetcode-cn.com/problems/n-queens
public class LeetCode_51 {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        int[] re = new int[n];
        process(0, re, n, res);
        return res;
    }

    // 来到index行上放皇后
    // record前一行放在哪里的
    public static void process(int index, int[] record, int n, List<List<String>> res) {
        if (index == n) {// 有效的
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < record.length; i++) {
                if (record[i] == 0) {
                    ans.add(".");
                } else {
                    ans.add("Q");
                }
            }
            res.add(ans);
        }
        // 把index行的皇后放在哪一个位置
        for (int i = 0; i < n; i++) {
            if (isOk(record, index, i)) {
                record[index] = i;
                process(index + 1, record, n, res);
            }
        }
    }

    public static boolean isOk(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }

    public static void toStr(int[][] dp, int n, List<List<String>> res) {
        if (dp != null) {
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] dp1 = dp[i];
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < n; j++) {
                    if (dp1[j] != 0) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                ans.add(sb.toString());
            }
            res.add(ans);
        }
    }

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 1) {
            return res;
        }
        int[][] dp = new int[n][n];
        process(dp, n, 0, res);
        return res;
    }

    public static void process(int[][] dp, int n, int row, List<List<String>> res) {
        if (row == n) {
            toStr(dp, n, res);
            return;
        }
        // 每一列取搞
        for (int i = 0; i < n; i++) {
            if (!ok2(dp, row, i, n)) {
                continue;
            }
            dp[row][i] = 1;
            process(dp, n, row + 1, res);
            dp[row][i] = 0;
        }
    }

    public static boolean ok2(int[][] dp, int row, int col, int n) {
        // 这一列已经存在了
        for (int i = 0; i < n; i++) {
            if (dp[i][col] != 0) {
                return false;
            }
        }
        // 右上方是否冲突
        for (int i = row - 1, j = col + 1; i > 0 && j < n; i--, j++) {
            if (dp[i][j] != 0) {
                return false;
            }
        }
        // 左上方
        for (int i = row - 1, j = col - 1; i > 0 && j > 0; i--, j--) {
            if (dp[i][j] != 0) {
                return false;
            }
        }
        return true;
    }
}
