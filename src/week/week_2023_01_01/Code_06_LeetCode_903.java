package week.week_2023_01_01;

/**
 * @ClassName Code_06_LeetCode_903
 * @Author Duys
 * @Description
 * @Date 2023/1/5 13:06
 **/
// 903. DI 序列的有效排列
public class Code_06_LeetCode_903 {

    // 首先这是一个巧妙题目
    // 1.排列跟数字重复没关系
    // 2.D是下降 I是上升 来到一个位置看看小于自己的数有几个，那么大于自己的数就可以算出来
    public int numPermsDISequence1(String s) {
        // 开始的时候，默认是比-1位置小的数是全部
        return process(s.toCharArray(), 0, s.length() + 1, s.length() + 1);
    }

    // i  来到哪一个位置
    //less 比当前做的抉择的数小的有几个
    public int process(char[] arr, int i, int less, int n) {
        int ans = 0;
        if (i == n) { // 来到最后了。说明之前做的决定有效
            ans = 1;
        } else if (i == 0 || arr[i] == 'D') {
            //i=0表示 开始的时候可以随意选择
            // 如果当前是D，表示是下降，那么需要选择比之前小的
            for (int nextLess = 0; nextLess < less; nextLess++) {
                ans += process(arr, i + 1, nextLess, n);
            }
        } else {
            //  不是开始，且是上升，那么需要找比之前大的数
            for (int nextLess = less; nextLess < n - i; nextLess++) {
                ans += process(arr, i + 1, nextLess, n);
            }
        }
        return ans;
    }

    // 2.改dp
    public int numPermsDISequence2(String s) {
        int mod = 1_000_000_007;
        char[] arr = s.toCharArray();
        int n = s.length() + 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int less = 0; less <= n; less++) {
            dp[n][less] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int less = 0; less <= n; less++) {
                if (i == 0 || arr[i-1] == 'D') {
                    //i=0表示 开始的时候可以随意选择
                    // 如果当前是D，表示是下降，那么需要选择比之前小的
                    for (int nextLess = 0; nextLess < less; nextLess++) {
                        dp[i][less] = (dp[i + 1][nextLess] + dp[i][less]) % mod;
                    }
                } else {
                    //  不是开始，且是上升，那么需要找比之前大的数
                    for (int nextLess = less; nextLess < n - i; nextLess++) {
                        dp[i][less] = (dp[i + 1][nextLess] + dp[i][less]) % mod;
                    }
                }
            }
        }
        return dp[0][n];
    }

    // 3.观察位置，斜率优化枚举
    public int numPermsDISequence3(String s) {
        int mod = 1_000_000_007;
        char[] arr = s.toCharArray();
        int n = s.length() + 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int less = 0; less <= n; less++) {
            dp[n][less] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == 0 || arr[i-1] == 'D') {
                //i=0表示 开始的时候可以随意选择
                // 如果当前是D，表示是下降，那么需要选择比之前小的
                for (int less = 0; less <= n; less++) {
                    dp[i][less] = less - 1 >= 0 ? ((dp[i][less - 1] + dp[i + 1][less - 1]) % mod) : 0;
                }
            } else {
                //  不是开始，且是上升，那么需要找比之前大的数
                dp[i][n - i - 1] = dp[i + 1][n - i - 1];
                for (int less = n - i - 2; less >= 0; less--) {
                    dp[i][less] = (dp[i][less + 1] + dp[i + 1][less]) % mod;
                }
            }
        }
        return dp[0][n];
    }
}
