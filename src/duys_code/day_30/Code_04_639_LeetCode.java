package duys_code.day_30;

/**
 * @ClassName Code_04_639_LeetCode
 * @Author Duys
 * @Description力扣：https://leetcode-cn.com/problems/decode-ways-ii/
 * @Date 2021/11/26 16:30
 **/
public class Code_04_639_LeetCode {

    // s中可能包含 * 号，*号可以代表1到9的数字
    public static int numDecodings(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        return process(s.toCharArray(), 0);
    }

    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        // 依然不能单独面对0
        if (str[index] == '0') {
            return 0;
        }
        // 当前index位置有字符，且不是0
        // 不是*号
        if (str[index] != '*') {
            // 单转
            int p1 = process(str, index + 1);
            if (index + 1 == str.length) {
                return p1;
            }
            // 和后面的一起
            int p2 = 0;
            // 后面位置不是 * 号
            if (str[index + 1] != '*') {
                int num = (str[index] - '0') * 10 + str[index + 1] - '0';
                if (num <= 26) {
                    p2 = process(str, index + 2);
                }
            }
            // 是 * 号呢
            else {
                // index位置是 1 2 才会有答案
                if (str[index] < '3') {
                    // index位置是 1的时候 9种
                    // index位置是 2的时候 6种
                    p2 = (str[index] == '1' ? 9 : 6) * process(str, index + 2);
                }
            }
            return (p1 + p2);
        }
        // 当前index 是 * 号
        else {
            // index位置单转
            int p1 = 9 * process(str, index + 1);
            if (index + 1 == str.length) {
                return p1;
            }
            int p2 = 0;
            // 和后面一起
            if (str[index + 1] != '*') {
                // 当index + 1 位置小于7的时候 0 1 2 3 4 5 6 前面可以是1 可以是2 两种
                // 当index + 1 位置大于6的时候  6 7 8 9 只有前面是1的时候才能一起 1种
                p2 = (str[index + 1] < '7' ? 2 : 1) * process(str, index + 2);
            }
            // 两个 * * 连在一起
            else {
                // 有哪些情况
                // 1x -> 9种
                // 2x -> 6种
                // 总共15种
                p2 = 15 * process(str, index + 2);
            }
            return (p1 + p2);
        }
    }

    public static int numDecodings1(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        long[] dp = new long[s.length()];
        return (int) process1(s.toCharArray(), 0, dp);
    }

    public static long mod = 1000000007;

    public static long process1(char[] str, int i, long[] dp) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        // 单转
        long ans = (str[i] == '*' ? 9 : 1) * process1(str, i + 1, dp);
        // 不单转，可以把后面的拉到一起
        if (i + 1 < str.length) {
            // 这样子才能和后面的一起
            if (str[i] == '1' || str[i] == '2' || str[i] == '*') {
                if (str[i + 1] == '*') {
                    ans += process1(str, i + 2, dp) * (str[i] == '*' ? 15 : (str[i] == '1' ? 9 : 6));
                }
                // i+1 不是*
                else {
                    if (str[i] == '*') {
                        ans += process1(str, i + 2, dp) * (str[i + 1] < '7' ? 2 : 1);
                    } else {
                        ans += (((str[i] - '0') * 10 + str[i + 1] - '0') < 27) ? process1(str, i + 2, dp) : 0;
                    }
                }
            }
        }
        ans %= mod;
        dp[i] = ans;
        return ans;
    }
}
