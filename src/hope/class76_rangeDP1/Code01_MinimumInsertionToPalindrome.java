package hope.class76_rangeDP1;

/**
 * @author Mr.Du
 * @ClassName Code01_MinimumInsertionToPalindrome
 * @date 2024年03月11日 11:41
 */
// 让字符串成为回文串的最少插入次数
// 给你一个字符串 s
// 每一次操作你都可以在字符串的任意位置插入任意字符
// 请你返回让s成为回文串的最少操作次数
// 测试链接 : https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/
public class Code01_MinimumInsertionToPalindrome {
    // 1.暴力尝试
    public static int minInsertions1(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        return f1(arr, 0, n - 1);
    }

    // arr[l...r]范围上的字符串，整体都变成回文
    // 返回至少插入几个字符串
    public static int f1(char[] arr, int l, int r) {
        if (l == r) { // 只有一个字符了
            return 0;
        }
        // 剩下两个字符，如果相等，则不需要查询，否则需要插入一个
        if (l + 1 == r) {
            return arr[l] == arr[r] ? 0 : 1;
        }
        // 还剩下多个字符
        if (arr[l] == arr[r]) {
            return f1(arr, l + 1, r - 1);
        } else {
            return Math.min(f1(arr, l + 1, r), f1(arr, l, r - 1)) + 1;
        }
    }

    // 2.严格位置依赖
    // l的变化范围和r的变化范围
    // 一个二维表格，l <= r部分有效
    public static int minInsertions(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        // 对角线是0，arr[l][l+1] 这条对角线
        for (int l = 0; l < n - 1; l++) {
            dp[l][l + 1] = arr[l] == arr[l + 1] ? 0 : 1;
        }
        // n-1 n-2都填了
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                if (arr[l] == arr[r]) {
                    dp[l][r] = dp[l + 1][r + 1];
                } else {
                    dp[l][r] = Math.min(dp[l + 1][r], dp[l][r - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
