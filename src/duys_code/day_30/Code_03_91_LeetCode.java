package duys_code.day_30;

/**
 * @ClassName Code_03_91_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/decode-ways/
 * @Date 2021/11/26 13:35
 **/
public class Code_03_91_LeetCode {

    // 大体思路。当前位置单转，和当前位置和下一个位置一起转
    public int numDecodings(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return process(chars, 0);
    }

    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        // 不能让我单独面对一个0字符，我转不了
        if (str[index] == '0') {
            return 0;
        }
        // 我单转
        int ways = process(str, index + 1);
        // 如果我单转了，后面还剩下一个字符，对不起，没有方法了
        if (index + 1 == str.length) {
            return ways;
        }
        // 和后面的一起
        int next = (str[index] - '0') * 10 + (str[index + 1] - '0');
        if (next > 26) {
            return ways;
        }
        return ways + process(str, index + 2);
    }

    // 傻缓存
    public static int numDecodings1(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        return process1(chars, 0, dp);
    }

    public static int process1(char[] str, int index, int[] dp) {
        if (dp[index] != 0) {
            return dp[index];
        }
        int ways = 0;
        if (index == str.length) {
            ways = 1;
        } else {
            // 不能让我单独面对一个0字符，我转不了
            if (str[index] == '0') {
                ways = 0;
            } else {
                // 我单转
                ways = process1(str, index + 1, dp);
                // 如果我单转了，后面还剩下一个字符，对不起，没有方法了
                if (index + 1 != str.length) {
                    // 和后面的一起
                    int next = (str[index] - '0') * 10 + (str[index + 1] - '0');
                    if (next < 27) {
                        ways += process1(str, index + 2, dp);
                    }
                }
            }
        }
        dp[index] = ways;
        return ways;
    }

    // 分析位置依赖
    // 当前位置都依赖 i+1 或者i+2 位置，所以从后往前填
    // 当来到最后的位置时候，有一种返回
    public static int numDecodings2(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[chars.length] = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                continue;
            }
            dp[i] = dp[i + 1];
            // 后面没元素了
            if (i + 1 == chars.length) {
                continue;
            }
            int num = (chars[i] - '0') * 10 + chars[i + 1] - '0';
            if (num <= 26) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings1("1112"));
    }
}
