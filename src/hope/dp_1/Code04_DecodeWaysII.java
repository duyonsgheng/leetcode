package hope.dp_1;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code04_DecodeWaysII
 * @date 2023年12月04日 10:38
 */
// 解码方法 II
// 一条包含字母 A-Z 的消息通过以下的方式进行了 编码 ：
// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// 要 解码 一条已编码的消息，所有的数字都必须分组
// 然后按原来的编码方案反向映射回字母（可能存在多种方式）
// 例如，"11106" 可以映射为："AAJF"、"KJF"
// 注意，像 (1 11 06) 这样的分组是无效的，"06"不可以映射为'F'
// 除了上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符
// 可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）
// 例如，"1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19"
// 对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息
// 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目
// 由于答案数目可能非常大，返回10^9 + 7的模
// 测试链接 : https://leetcode.cn/problems/decode-ways-ii/
public class Code04_DecodeWaysII {
    // 1.暴力尝试
    public static int numDecodings1(String str) {
        return f1(str.toCharArray(), 0);
    }

    public static int f1(char[] s, int i) {
        if (i == s.length) {
            return 1;
        }
        if (s[i] == '0') {
            return 0;
        }
        // i位置单独转
        // 如果i位置是* 那么就可以是1-9中任意的
        int ans = f1(s, i + 1) * (s[i] == '*' ? 9 : 1);
        // i位置和i+1位置合到一起转
        if (i + 1 < s.length) {
            if (s[i] != '*') {
                // i是数字 ，i+1也是数字
                if (s[i + 1] != '*') {
                    if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                        ans += f1(s, i + 2);
                    }
                } else {
                    // i位置数数字，i+1位置是 *
                    if (s[i] == '1') {
                        ans += f1(s, i + 2) * 9;
                    }
                    if (s[i] == '2') {
                        ans += f1(s, i + 2) * 6;
                    }
                }
            } else { // i位置是 *
                if (s[i + 1] != '*') { // i+1位置是数字
                    if (s[i + 1] <= '6') {
                        ans += f1(s, i + 2) * 2;
                    } else {
                        ans += f1(s, i + 2);
                    }
                } else { // i+1位置也是 *
                    // *  *
                    // i  i+1
                    // 11 12 ... 19 21 22 ... 26 -> 一共15种可能
                    // 没有10、20，因为*只能变1~9，并不包括0
                    ans += f1(s, i + 2) * 15;
                }
            }
        }
        return ans;
    }

    // 2.挂缓存
    public static long mod = 1000000007;

    public static int numDecodings2(String str) {
        char[] s = str.toCharArray();
        long[] dp = new long[s.length];
        Arrays.fill(dp, -1);
        return (int) f2(s, 0, dp);
    }

    public static long f2(char[] s, int i, long[] dp) {
        if (i == s.length) {
            return 1;
        }
        if (s[i] == '0') {
            return 0;
        }
        // i位置单独转
        // 如果i位置是* 那么就可以是1-9中任意的
        long ans = f2(s, i + 1, dp) * (s[i] == '*' ? 9 : 1);
        // i位置和i+1位置合到一起转
        if (i + 1 < s.length) {
            if (s[i] != '*') {
                // i是数字 ，i+1也是数字
                if (s[i + 1] != '*') {
                    if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                        ans += f2(s, i + 2, dp);
                    }
                } else {
                    // i位置数数字，i+1位置是 *
                    if (s[i] == '1') {
                        ans += f2(s, i + 2, dp) * 9;
                    }
                    if (s[i] == '2') {
                        ans += f2(s, i + 2, dp) * 6;
                    }
                }
            } else { // i位置是 *
                if (s[i + 1] != '*') { // i+1位置是数字
                    if (s[i + 1] <= '6') {
                        ans += f2(s, i + 2, dp) * 2;
                    } else {
                        ans += f2(s, i + 2, dp);
                    }
                } else { // i+1位置也是 *
                    // *  *
                    // i  i+1
                    // 11 12 ... 19 21 22 ... 26 -> 一共15种可能
                    // 没有10、20，因为*只能变1~9，并不包括0
                    ans += f2(s, i + 2, dp) * 15;
                }
            }
        }
        ans %= mod;
        dp[i] = ans;
        return ans;
    }

    // 3.严格位置依赖
    public static int numDecodings3(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long[] dp = new long[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] == '0') {
                continue;
            }
            // i位置单独转
            // 如果i位置是* 那么就可以是1-9中任意的
            dp[i] = dp[i + 1] * (s[i] == '*' ? 9 : 1);
            // i位置和i+1位置合到一起转
            if (i + 1 < s.length) {
                if (s[i] != '*') {
                    // i是数字 ，i+1也是数字
                    if (s[i + 1] != '*') {
                        if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                            dp[i] += dp[i + 2];
                        }
                    } else {
                        // i位置数数字，i+1位置是 *
                        if (s[i] == '1') {
                            dp[i] += dp[i + 2] * 9;
                        }
                        if (s[i] == '2') {
                            dp[i] += dp[i + 2] * 6;
                        }
                    }
                } else { // i位置是 *
                    if (s[i + 1] != '*') { // i+1位置是数字
                        if (s[i + 1] <= '6') {
                            dp[i] += dp[i + 2] * 2;
                        } else {
                            dp[i] += dp[i + 2];
                        }
                    } else { // i+1位置也是 *
                        // *  *
                        // i  i+1
                        // 11 12 ... 19 21 22 ... 26 -> 一共15种可能
                        // 没有10、20，因为*只能变1~9，并不包括0
                        dp[i] += dp[i + 2] * 15;
                    }
                }
            }
            dp[i] %= mod;
        }
        return (int) dp[0];
    }

    // 4.严格位置依赖+空间压缩
    public static int numDecodings4(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long cur = 0, next = 1, nextNext = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                // i位置单独转
                // 如果i位置是* 那么就可以是1-9中任意的
                cur = next * (s[i] == '*' ? 9 : 1);
                // i位置和i+1位置合到一起转
                if (i + 1 < s.length) {
                    if (s[i] != '*') {
                        // i是数字 ，i+1也是数字
                        if (s[i + 1] != '*') {
                            if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                                cur += nextNext;
                            }
                        } else {
                            // i位置数数字，i+1位置是 *
                            if (s[i] == '1') {
                                cur += nextNext * 9;
                            }
                            if (s[i] == '2') {
                                cur += nextNext * 6;
                            }
                        }
                    } else { // i位置是 *
                        if (s[i + 1] != '*') { // i+1位置是数字
                            if (s[i + 1] <= '6') {
                                cur += nextNext * 2;
                            } else {
                                cur += nextNext;
                            }
                        } else { // i+1位置也是 *
                            // *  *
                            // i  i+1
                            // 11 12 ... 19 21 22 ... 26 -> 一共15种可能
                            // 没有10、20，因为*只能变1~9，并不包括0
                            cur += nextNext * 15;
                        }
                    }
                }
                cur %= mod;
            }
            nextNext = next;
            next = cur;
            cur = 0;
        }
        return (int) next;
    }
}
