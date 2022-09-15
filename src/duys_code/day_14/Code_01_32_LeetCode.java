package duys_code.day_14;

/**
 * @ClassName Code_01_32_LeetCode
 * @Author Duys
 * @Description
 * @Date 2021/10/21 15:53
 **/
public class Code_01_32_LeetCode {

    // 字符串只有 ( ) 两种，求最长的合法字符串

    // 一看就是范围尝试，以每一个位置结尾看看能忘左边推多大长度
    // 以 ( 结尾的是0，是 ) 的时候进行讨论
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        // dp[i] 意思就是以i结尾 看看最多能往左边推多大长度
        int[] dp = new int[str.length];
        int pre = 0;
        int ans = 0;
        for (int i = 1; i <= str.length - 1; i++) {
            // ( ( ) ( ) )
            //           i
            // 只有当前字符串时 ) 才有意义
            if (str[i] == ')') {
                // 意思就是 看看我前面的位置推到了哪里
                pre = i - dp[i - 1] - 1;
                // 我之前的位置有效的，并且与我i位置配对的 是 ( 才行
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
   /* //  pre 是之前的
    public static int process(char[] str, int end, int pre) {

    }*/
}
