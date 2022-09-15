package duys_code.day_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_01_1312_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 * @Date 2021/10/18 14:42
 **/
public class Code_01_1312_LeetCode {
    /**
     * 给定一个字符串s，每一次操作都可以在字符串得任意位置插入任意字符，返回插入字符最少次数能把s变成回文字符串
     */
    /**
     * 思路：显而易见这是一个范围上得尝试
     * i~j  范围上要是回文的话
     * 1.i~j-1位置上的答案加上1，我们把j位置插入到开头，整体变成回文
     * 2.i+1~j位置上的答案加上1，我们把i位置插入到最后，整体变成回文
     * 3.如果i位置字符和j位置字符相同，那么i~j的答案就是 i+1~j-1的答案
     */
    // 1. 返回最少的插入字符的次数
    public static int minInsertions(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int N = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[N][N];
        // base case是啥 i>j 区域是无效的
        // 如果i==j只剩下一个字符了，那么需要加0个字符变成回文，因为单独的字符就是回文
        // 如果i=j+1了，就是只有两个字符 i和j+1字符相同就是0，不同就需要增加一个字符整体变成回文
        for (int i = 1; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        // dp[N-1][N-1] = 0;
        // 普遍位置的依赖关系是三种可能性
        // 我们需要返回的dp[0][N-1] 那么我们就需要从下往上，从左往右填整个表
        // 第一条对角线全是0，第二条对角线，上面已经填了，所以从第三条对角线开始
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i + 1][j] + 1, dp[i][j - 1] + 1);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }

    // 2.返回其中的一种结果？？？？
    // 要返回结果，需要我们从dp反推回去
    public static String findAnsOnlyOne(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int[][] dp = findDp(s);
        char[] str = s.toCharArray();
        int N = str.length;
        // 开始反推 。
        // 比如 3~6范围上回文需要增加2个字符 -
        // 首先看看 3位置和6位置字符是否相等 再来 看一下3~5范围 看一下4~6范围
        int L = 0;
        int R = N - 1;
        char[] ans = new char[N + dp[0][N - 1]];
        int ansL = 0;
        int ansR = ans.length - 1;
        while (L < R) {
            // 答案是来自哪一个可能，来自我的左边+1得到的
            // 来3~5范围
            if (dp[L][R - 1] == dp[L][R] - 1) {
                ans[ansL++] = str[R];
                ans[ansR--] = str[R--];
            }
            // 来自4~6范围
            else if (dp[L + 1][R] == dp[L][R] - 1) {
                ans[ansL++] = str[L];
                ans[ansR--] = str[L++];
            }
            // 来自 4~5范围
            else {
                ans[ansL++] = str[L++];
                ans[ansR--] = str[R--];
            }
        }
        // 如果最后ans的中间剩下位置刚刚填完那么L>R
        // 如果最后ans的中间还剩下一个字符位置
        if (L == R) {
            ans[ansL] = str[R];
        }
        return String.valueOf(ans);
    }

    // 3.返回所有可能的结果？？？？
    // 要返回结果，需要我们从dp反推回去
    public static List<String> findAnsAll(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            int[][] dp = findDp(s);
            char[] str = s.toCharArray();
            int N = str.length;
            char[] path = new char[N + dp[0][N - 1]];
            process(str, dp, 0, N - 1, path, 0, path.length - 1, ans);
        }
        return ans;
    }

    // 当前来到dp的格子 L R
    //path的pl....pr
    public static void process(char[] str, int[][] dp, int L, int R, char[] path, int pl, int pr, List<String> ans) {
        if (L >= R) {
            if (L == R) {
                path[pl] = str[L];
            }
            ans.add(String.valueOf(path));
        } else {
            // 这里也是深度优先，但是我们的path不参与后续的比较，而且我们的path的位置还会被覆盖，所以还原不还原现场，没有区别
            // 分析是来自那种
            // 来自我的左边
            if (dp[L + 1][R] == dp[L][R] - 1) {
                path[pl] = str[L];
                path[pr] = str[L];
                process(str, dp, L + 1, R, path, pl + 1, pr - 1, ans);
            }
            // 右边
            if (dp[L][R - 1] == dp[L][R] - 1) {
                path[pl] = str[R];
                path[pr] = str[R];
                process(str, dp, L, R - 1, path, pl + 1, pr - 1, ans);
            }
            // 来自中间
            if (str[L] == str[R] && (L == R - 1 || dp[L + 1][R - 1] == dp[L][R])) {
                path[pl] = str[L];
                path[pr] = str[R];
                process(str, dp, L + 1, R - 1, path, pl + 1, pr - 1, ans);
            }
        }

    }


    public static int[][] findDp(String s) {
        int N = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[N][N];
        // base case是啥 i>j 区域是无效的
        // 如果i==j只剩下一个字符了，那么需要加0个字符变成回文，因为单独的字符就是回文
        // 如果i=j+1了，就是只有两个字符 i和j+1字符相同就是0，不同就需要增加一个字符整体变成回文
        for (int i = 1; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        // dp[N-1][N-1] = 0;
        // 普遍位置的依赖关系是三种可能性
        // 我们需要返回的dp[0][N-1] 那么我们就需要从下往上，从左往右填整个表
        // 第一条对角线全是0，第二条对角线，上面已经填了，所以从第三条对角线开始
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i + 1][j] + 1, dp[i][j - 1] + 1);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp;
    }
}
