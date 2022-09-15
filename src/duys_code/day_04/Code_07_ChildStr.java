package duys_code.day_04;

/**
 * @ClassName Code_07_ChildStr
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/interleaving-string/
 * @Date 2021/9/22 19:42
 **/
public class Code_07_ChildStr {
    /**
     * 字符串交错组成
     * dp[i][j] - > i 代表str1 0到i-1 全部字符 ； j代表str2 0到j-1 全部字符，
     * 整体含义就是 str1 拿前0到i-1的全部字符和str2拿前0到j-1的全部字符，能不能搞定 总字符串 前 0到 i+j-1的全部字符
     * <p>
     * 可能性分析：
     * 1.i+j-1位置的字符 = str1 的 i-1位置的字符，那么总的字符串剩下的位置是依赖 dp[i-1][j]
     * 2.i+j-1位置的字符 = str2 的 j-1位置的字符，那么总的字符串剩下的位置是依赖 dp[i][j-1](意思就是 0到i-1 字符来自str1，0到j-2字符来自str2)
     * 两种可能有一种满足，那么就说总字符串 满足str1和str2的交叉
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null || s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        //dp[0][0] s1种选-1位置的字符和s2选-1位置字符搞定 s3-1位置的字符
        dp[0][0] = true;
        for (int i = 1; i <= l1; i++) {
            // s2种不选，只选择s1中的
            // 那么只要有一个不等时候以后全部false
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= l2; i++) {
            // s1种不选，只选择s2中的
            // 那么只要有一个不等时候以后全部false
            if (str2[i - 1] != str3[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        // 普遍位置
        // dp[i][j]
        // 1.如果 str3的 i+j-1 位置的字符来源于 s1，那么s1的当前i的前一个位置就是当前i所依赖的答案，因为s1的当前位置依赖前一个位置，如果前一个位置是true，那么当前位置肯定为true，否则不满足
        // 2.如果 str3的 i+j-1 位置的字符来源于 s2，那么s2的当前j的前一个位置就是当前j所以来的答案，因为s2的当前位置依赖前一个位置，如果前一个位置是true，那么当前位置肯定满足
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                boolean p1 = str1[i - 1] == str3[i + j - 1] && dp[i - 1][j];
                boolean p2 = str2[j - 1] == str3[i + j - 1] && dp[i][j - 1];
                if (p1 || p2) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[l1][l2];
    }
}
