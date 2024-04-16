package hope.class77_rangeDP2;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code01_MinimumInsertionsToMatch
 * @date 2024年03月20日 16:36
 */
// 完成配对需要的最少字符数量
// 给定一个由'['、']'、'('，')'组成的字符串
// 请问最少插入多少个括号就能使这个字符串的所有括号正确配对
// 例如当前串是 "([[])"，那么插入一个']'即可满足
// 输出最少需要插入多少个字符
// 测试链接 : https://www.nowcoder.com/practice/e391767d80d942d29e6095a935a5b96b
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code01_MinimumInsertionsToMatch {

    public static int compute(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        return process(arr, 0, n - 1, dp);
    }

    // arr[l...r]配对至少需要几个字符
    public static int process(char[] arr, int l, int r, int[][] dp) {
        // 如果只有一个位置了，那么必定会需要再添加一个符号
        if (l == r) {
            return 1;
        }
        // 如果还有两个位置
        if (l == r - 1) {
            return ((arr[l] == '(' && arr[r] == ')') || (arr[l] == '[' && arr[r] == ']')) ? 0 : 2;
        }
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        // p1：l和r位置本来就配上了
        int p1 = Integer.MAX_VALUE;
        if ((arr[l] == '(' && arr[r] == ')') || (arr[l] == '[' && arr[r] == ']')) {
            p1 = process(arr, l + 1, r - 1, dp);
        }
        // p2：l和r位置不同，那么就在l到r之间划分
        int p2 = Integer.MAX_VALUE;
        for (int m = l; m < r; m++) {
            p2 = Math.min(p2, process(arr, l, m, dp) + process(arr, m + 1, r, dp));
        }
        int ans = Math.min(p1, p2);
        dp[l][r] = ans;
        return ans;
    }
}
