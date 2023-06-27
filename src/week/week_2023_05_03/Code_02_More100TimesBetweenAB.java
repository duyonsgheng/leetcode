package week.week_2023_05_03;

import java.util.Arrays;

/**
 * @ClassName Code_02_More100TimesBetweenAB
 * @Author Duys
 * @Description
 * @Date 2023/5/18 9:32
 **/
// 假设每一次获得随机数的时候，这个数字大于100的概率是P
// 尝试N次，其中大于100的次数在A次~B次之间的概率是多少?
// 0 < P < 1, P是double类型
// 1 <= A <= B <= N <= 100
public class Code_02_More100TimesBetweenAB {

    // 概率dp问题
    // 很多时候这种概率dp的题有数学公式的解答，但是使用数学公式的话会超出数的表示范围
    // 所以我们就需要每一步计算得到答案，然后再汇总，dp能做到
    // 分析 大于100概率为p，那么小于等的100得概率就是1-p，
    // a到b次大于100得概率 = a+1次 + a+2次 + a+3次 .... + b次
    public static double probability(double P, int N, int A, int B) {
        double[][] dp = new double[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1d);
        }
        double ans = 0d;
        for (int i = A; i <= B; i++)
            ans += process(P, 1d - P, N, i, dp);
        return ans;
    }

    // 大于100得概率是 more，小于等于100得概率为less
    // 还有i次可以扔，大于100得次数还剩下j次
    public static double process(double more, double less, int i, int j, double[][] dp) {
        // 没有次数了，大于100得次数已经搞定了，或者次数不够了
        if (i < 0 || j < 0 || i < j) {
            return 0d;
        }
        // 搞定了
        if (i == 0 && j == 0) {
            return 1d;
        }
        if (dp[i][j] != -1d) {
            return dp[i][j];
        }
        // 当前扔出的大于100的，当前扔出的小于等于100的
        double ans = more * process(more, less, i - 1, j - 1, dp) + less * process(more, less, i - 1, j, dp);
        dp[i][j] = ans;
        return ans;
    }
}
