package custom.code_2022_06;

/**
 * @ClassName LeetCode_279
 * @Author Duys
 * @Description
 * @Date 2022/6/10 17:18
 **/
//279. 完全平方数
//给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
//完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
//链接：https://leetcode.cn/problems/perfect-squares
public class LeetCode_279 {

    // 老老实实的用dp吧，枚举 1到根号N
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        // 当前是9
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            // 比如6
            // 至少有一个1，然后看看剩下的6怎么分解
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(7));
    }
}
