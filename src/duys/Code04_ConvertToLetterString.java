package duys;

public class Code04_ConvertToLetterString {

    /**
     * 1-A  2-B  3-C .... 26-Z
     * 例如 111  -> AAA AK KA  3种
     *
     * @param str 只含有数字 0 -9
     * @return 有多少种转化方案
     */

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static int process(char[] str, int i) {
        // 空的字符串。算一种 比如 111 当i=3的时候，说明转化成功，算一种，比如305 就不能转 0转化种方法
        if (i == str.length) {
            return 1;
        }
        // 中间位置或者第一位遇到0，那么就是0种，没有对应的，说明之前的决定有问题
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        return process(str, i + 1);
    }

    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
           /* if (str[i] == '0') {
                dp[i] = 0;
            } else if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < N) {
                    dp[i] += dp[i + 2];
                }
            } else if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }*/
            if (str[i] == '0') {
                continue;
            }
            int ways = dp[i + 1];
            if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                ways += dp[i + 2];
            }
            dp[i] = ways;

        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("2132082"));
        System.out.println(dp("2132082"));
    }

}
