package custom.code_2022_07;

/**
 * @ClassName LeetCode_264
 * @Author Duys
 * @Description
 * @Date 2022/7/13 15:13
 **/
// 264. 丑数 II
// 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
public class LeetCode_264 {


    public static int nthUglyNumber(int n) {
        if (n < 1) {
            return 0;
        }
        //dp[i] 第i个最小的质数
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        for (int i = 2; i <= n; i++) {
            int next2 = dp[p2] * 2;
            int next3 = dp[p3] * 3;
            int next5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(next2, next3), next5);
            if (dp[i] == next2) {
                p2++;
            }
            if (dp[i] == next3) {
                p3++;
            }
            if (dp[i] == next5) {
                p5++;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(1352));
    }
}
