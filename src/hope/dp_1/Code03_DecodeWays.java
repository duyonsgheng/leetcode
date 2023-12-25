package hope.dp_1;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code03_DecodeWays
 * @date 2023年12月04日 10:23
 */
// 解码方法
// 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）
// 例如，"11106" 可以映射为："AAJF"、"KJF"
// 注意，消息不能分组为(1 11 06)，因为 "06" 不能映射为 "F"
// 这是由于 "6" 和 "06" 在映射中并不等价
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数
// 题目数据保证答案肯定是一个 32位 的整数
// 测试链接 : https://leetcode.cn/problems/decode-ways/
public class Code03_DecodeWays {
    // 1.暴力尝试
    public static int numDecodings1(String s) {
        return f1(s.toCharArray(), 0);
    }

    // 当前来到i位置，求i及其以后能有多少转换方法数
    public static int f1(char[] arr, int i) {
        if (i == arr.length) {
            return 1; // 有效的方法
        }
        int ans = 0;
        // 如果单独面对0字符了，不能转
        if (arr[i] != '0') {
            ans = f1(arr, i + 1); // 当前字符单独一个位置
            // 当前字符和后面的字符合并
            if (i + 1 < arr.length && ((arr[i] - '0') * 10) + arr[i + 1] - '0' < 27) {
                ans += f1(arr, i + 2);
            }
        }
        return ans;
    }

    // 2.挂缓存
    public static int numDecodings2(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return f2(s.toCharArray(), 0, dp);
    }

    // 当前来到i位置，求i及其以后能有多少转换方法数
    public static int f2(char[] arr, int i, int[] dp) {
        if (i == arr.length) {
            return 1; // 有效的方法
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans = 0;
        // 如果单独面对0字符了，不能转
        if (arr[i] != '0') {
            ans = f2(arr, i + 1, dp); // 当前字符单独一个位置
            // 当前字符和后面的字符合并
            if (i + 1 < arr.length && ((arr[i] - '0') * 10) + arr[i + 1] - '0' < 27) {
                ans += f2(arr, i + 2, dp);
            }
        }
        dp[i] = ans;
        return ans;
    }

    // 3.严格位置依赖
    // 看看暴力尝试，依赖的是i+1 和 i+2的位置
    public static int numDecodings3(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] != '0') {
                dp[i] = dp[i + 1]; // 当前字符单独一个位置
                // 当前字符和后面的字符合并
                if (i + 1 < arr.length && ((arr[i] - '0') * 10) + arr[i + 1] - '0' < 27) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    // 4.位置依赖+空间压缩
    public static int numDecodings4(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int next = 1;
        int nextNext = 0;
        for (int i = n - 1, cur = 0; i >= 0; i--) {
            if (arr[i] != '0') {
                cur = next; // 当前字符单独一个位置
                // 当前字符和后面的字符合并
                if (i + 1 < arr.length && ((arr[i] - '0') * 10) + arr[i + 1] - '0' < 27) {
                    cur += nextNext;
                }
            } else cur = 0; // 单独面对0，那么没戏
            nextNext = next;
            next = cur;
        }
        return next;
    }
}
