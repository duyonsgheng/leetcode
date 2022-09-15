package duys_code.day_51;

/**
 * @ClassName Code_05_1035_LeetCode
 * @Author Duys
 * @Description 力扣: https://leetcode-cn.com/problems/uncrossed-lines/
 * @Date 2021/11/8 14:04
 **/
public class Code_05_1035_LeetCode {
    /**
     * 两个数组中,相等的数连成一条线,求最多的不相交的线
     * 其实就是求两个数组的最长公共子序列
     */

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length < 1 || nums2.length < 1) {
            return 0;
        }
        int N = nums1.length;
        int M = nums2.length;
        int[][] dp = new int[N][M];
        //  0 0 位置
        dp[0][0] = nums1[0] == nums2[0] ? 1 : 0;
        // 第一列
        for (int i = 1; i < N; i++) {
            dp[i][0] = nums1[i] == nums2[0] ? 1 : dp[i - 1][0];
        }
        // 第一行
        for (int j = 1; j < M; j++) {
            dp[0][j] = nums1[0] == nums2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                // i j 位置讨论
                // 1.[i] [j]位置字符相等,就是dp[i-1][j-1]+1 不能就是dp[i-1][j-1]
                // 2.[i] [j]位置字符不相等,0~i-1 能搞定 0~j
                // 3.[i] [j]位置字符不相等,0~i 能搞定 0~j-1
                int p1 = nums1[i] == nums2[j] ? dp[i - 1][j - 1] + 1 : dp[i - 1][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = dp[i][j - 1];
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }
}
