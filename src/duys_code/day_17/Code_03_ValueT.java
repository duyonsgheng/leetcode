package duys_code.day_17;

/**
 * @ClassName Code_03_ValueT
 * @Author Duys
 * @Description
 * @Date 2021/10/28 15:55
 **/
public class Code_03_ValueT {
    /**
     * 给定两个字符串S和T
     * 返回S的所有子序列中
     * 有多少个子序列的字面值等于T
     */
    // 一看就是样本对应模型
    // 样本对应模型就是讨论结束位置如何如何
    public static int numDistinct1(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        return process(s, t, s.length, t.length);
    }

    /**
     * @param str1 s字符串
     * @param str2 T字符串
     * @param s1   s字符串0~s1位置所有的字符
     * @param s2   t字符串0~s2的前缀字符
     * @return
     */
    public static int process(char[] str1, char[] str2, int s1, int s2) {
        // t字符都来到最后了
        if (s2 == 0) {
            return 1;
        }
        // t还没到最后，而s到最后了，没搞定
        if (s1 == 0) {
            return 0;
        }
        // 就是当前的s1位置不参与，t的0~s2 被s的0~s1-1搞定了
        int res = process(str1, str2, s1 - 1, s2);
        if (str1[s1] == str2[s2]) {
            res += process(str1, str2, s1 - 1, s2 - 1);
        }
        return res;
    }

    public static int numDistinct2(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int N = s.length;
        int M = s.length;
        int[][] dp = new int[N + 1][M + 1];
        // 两个位置字符相等就是1，不等就是0
        dp[0][0] = s[0] == t[0] ? 1 : 0;
        // 第一列的怎么填？如果当前位置的字符和t[0]的字符相同就是1
        for (int i = 1; i < N; i++) {
            // 比如
            // s -> seafaaaddss
            // t -> a
            // 不同的位置不同的答案，还要算上之前的
            dp[i][0] = s[i] == t[0] ? (dp[i - 1][0] + 1) : dp[i - 1][0];
        }
        // 第0行除了[0,0] 位置,其他位置都是0
        // 普遍位置
        for (int i = 1; i < N; i++) {
            // 左上半部分区域是无效的 我的t 有20个字符，s只有两个字符，没有意义
            for (int j = 1; j <= Math.min(i, M - 1); j++) {
                dp[i][j] = dp[i - 1][j];
                if (s[i] == t[j]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[N - 1][M - 1];
    }
}
