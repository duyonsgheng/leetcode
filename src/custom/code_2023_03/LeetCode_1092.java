package custom.code_2023_03;

/**
 * @ClassName LeetCode_1092
 * @Author Duys
 * @Description
 * @Date 2023/3/28 9:16
 **/
// 1092. 最短公共超序列
public class LeetCode_1092 {
    // 输入：str1 = "abac", str2 = "cab" 1000 长度
    // 动态规划
    // dp[i][j] 同时以 str1在i到n 和 str2 在j到m 作为最短的字符串长度，其中i和j表示 str1[i..j] 和str2[i..j] 下标的子串
    // 1.str1[i] =str[j] 则一定是以str1[i]开始位置为头的字符串最优
    // 2.str1[i] !=str[j] 则min(dp[i+1][j],dp[i][j+1])+1
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        // str2不够，则str1 剩余的补全在后面
        for (int i = 0; i < n; i++) {
            dp[i][m] = n - i;
        }
        // str1不够，str2的剩余添加在后面
        for (int i = 0; i < m; i++) {
            dp[n][i] = m - i;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // 以当前字符为头，继续往后
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }
        // 根据dp表信息，进行回溯
        StringBuffer buffer = new StringBuffer();
        int t1 = 0, t2 = 0;
        while (t1 < n && t2 < m) {
            if (str1.charAt(t1) == str2.charAt(t2)) {
                buffer.append(str1.charAt(t1));
                t1++;
                t2++;
            }
            // 如果当前选择的是str1中t1位置的字符，dp表中可以得到的关系
            else if (dp[t1 + 1][t2] == dp[t1][t2] - 1) {
                buffer.append(str1.charAt(t1));
                t1++;
            } else if (dp[t1][t2 + 1] == dp[t1][t2] - 1) {
                buffer.append(str2.charAt(t2));
                t2++;
            }
        }
        // 然后剩下的全部加在尾部
        while (t1 < n) {
            buffer.append(str1.charAt(t1));
            t1++;
        }
        while (t2 < m) {
            buffer.append(str2.charAt(t2));
            t2++;
        }
        return buffer.toString();
    }
}
