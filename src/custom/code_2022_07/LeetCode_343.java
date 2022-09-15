package custom.code_2022_07;

import java.util.List;

/**
 * @ClassName LeetCode_343
 * @Author Duys
 * @Description
 * @Date 2022/7/19 17:50
 **/
// 343. 整数拆分
// 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
//返回 你可以获得的最大乘积 。
public class LeetCode_343 {

    // 简单的动态规划。
    // i 可以拆分成 j 和 i-j两部分，i-j 又可以拆分，所以转移方程
    // i *(i-j) 和 i*dp[i-j] 取最大
    public static int integerBreak(int n) {
        if (n < 2) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int cur = 0;
            for (int j = 1; j < i; j++) {
                // 当前i 可以拆分成j 和 i-j两部分
                // i -j 也可以继续拆分
                cur = Math.max(cur, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = cur;
        }
        return dp[n];
    }


    public static void main(String[] args) {
        int n = 10;
        System.out.println(integerBreak(n));
    }
}
