package hope.dp_1;

/**
 * @author Mr.Du
 * @ClassName Code06_LongestValidParentheses
 * @date 2023年12月05日 9:21
 */
// 最长有效括号
// 给你一个只包含 '(' 和 ')' 的字符串
// 找出最长有效（格式正确且连续）括号子串的长度。
// 测试链接 : https://leetcode.cn/problems/longest-valid-parentheses/
public class Code06_LongestValidParentheses {
    public static int longestValidParentheses(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        // dp的含义
        // 必须以i位置字符结尾的情况下，往左能扩多远
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 1, p; i < n; i++) {
            if (arr[i] == '(') {
                dp[i] = 0;
            } else {
                // 左边扩到的最远的位置的前一个
                p = i - 1 - dp[i - 1];
                // 如果满足，那么我当前位置至少是前一个位置最远+2(考虑括号嵌套)
                if (p >= 0 && arr[p] == '(') {
                    // 再看看p的前面能扩多远(考虑括号并列)
                    dp[i] = dp[i - 1] + 2 + (p - 1 >= 0 ? dp[p - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
