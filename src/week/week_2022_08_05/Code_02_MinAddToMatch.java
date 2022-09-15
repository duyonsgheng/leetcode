package week.week_2022_08_05;

import java.util.Arrays;

/**
 * @ClassName Code_02_MinAddToMatch
 * @Author Duys
 * @Description
 * @Date 2022/9/1 8:58
 **/
// 给定一个由 '[' ，']'，'('，‘)’ 组成的字符串
// 请问最少插入多少个括号就能使这个字符串的所有括号左右配对
// 例如当前串是 "([[])"，那么插入一个']'即可满足
// 输出最少插入多少个括号
// 测试链接 : https://www.nowcoder.com/practice/e391767d80d942d29e6095a935a5b96b
// 提交如下代码，把主类名改成Main，可以直接通过
public class Code_02_MinAddToMatch {

    public static int minAdd(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(arr, 0, n - 1, dp);
    }

    // l 到 r 范围上都要完全匹配
    public static int process(char[] arr, int l, int r, int[][] dp) {
        // 当只剩下一个字符不管是什么，都需要另外一个来配对
        if (l == r) {
            return 1;
        }
        // 当还剩下两个的时候，需要讨乱一下
        if (l == r - 1) {
            if ((arr[l] == '(' && arr[r] == ')') || (arr[l] == '[' && arr[r] == ']')) {
                return 0;
            }
            // 否则需要配两个才能满足
            return 2;
        }
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        // 嵌套关系的合法：
        // 可能性1：先把l+1 到r上搞定，然后单独来配l位置
        int p1 = 1 + process(arr, l + 1, r, dp);
        // 可能性2：先把l到r-1位置搞定，然后单独来配r位置
        int p2 = 1 + process(arr, l, r - 1, dp);
        // 可能性3：l和r位置已经配对了，那么需要l+1 到 r-1
        int p3 = Integer.MAX_VALUE;
        if ((arr[l] == '(' && arr[r] == ')') || (arr[l] == '[' && arr[r] == ']')) {
            p3 = process(arr, l + 1, r - 1, dp);
        }
        int ans = Math.min(p1, Math.min(p2, p3));
        // 并列关系的合法：
        for (int i = l; i < r; i++) {
            ans = Math.min(ans, process(arr, l, i, dp) + process(arr, i + 1, r, dp));
        }
        dp[l][r] = ans;
        return ans;
    }
}
