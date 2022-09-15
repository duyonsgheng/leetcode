package duys_code.day_13;

/**
 * @ClassName Code_01_NCard
 * @Author Duys
 * @Description
 * @Date 2021/10/20 17:26
 **/
public class Code_01_NCard {
    /**
     * 谷歌面试题扩展版
     * 面值为1~N的牌组成一组，
     * 每次你从组里等概率的抽出1~N中的一张
     * 下次抽会换一个新的组，有无限组
     * 当累加和<a时，你将一直抽牌
     * 当累加和>=a且<b时，你将获胜
     * 当累加和>=b时，你将失败
     * 返回获胜的概率，给定的参数为N，a，b
     */

    // 纯暴力
    public static double f1(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        // 比如我的 a 是 100，b=200，n只有10
        // 小于10的一直拿，大于200了就返回0
        if (b - a >= N) {
            return 1.0;
        }
        return p1(0, N, a, b);
    }

    // N 次相互独立的事件
    public static double p1(int curSum, int N, int a, int b) {
        if (curSum >= b) {
            return 0.0;
        }
        if (curSum >= a && curSum < b) {
            return 1.0;
        }
        // 枚举所有的可能
        double ans = 0.0;
        for (int i = 1; i <= N; i++) {
            // 这里都是独立事件，结果相加即可
            ans += p1(curSum + i, N, a, b);
        }
        //
        return ans / N;
    }

    // 使用临近位置进行替换枚举操作
    public static double f2(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        // 比如我的 a 是 100，b=200，n只有10
        // 小于10的一直拿，大于200了就返回0
        if (b - a >= N) {
            return 1.0;
        }
        return p2(0, N, a, b);
    }

    public static double p2(int curSum, int N, int a, int b) {
        if (curSum >= b) {
            return 0.0;
        }
        if (curSum >= a && curSum < b) {
            return 1.0;
        }
        // 比如 N = 3 a = 10 b = 12
        /**
         * curSum = 5 依赖哪些位置 6 7 8 (三者相加)/N
         * curSum = 4 依赖哪些位置 5 6 7 (三者相加)/N
         * 5的位置就是 6的概率+7的概率+8的概率 之和 除以N
         * 4的位置就是 5的概率+6的概率+7的概率 之和 除以N
         * 普遍的位置就是 f(i) = [f(i+1) + f(i+1)*N - f(i+1+N)]/N
         * 当我们来带 cur= a-1 = 9位置的时候，情况是 (10 + 11)/N
         */
        if (curSum == a - 1) {
            return 1.0 * (b - a) / N;
        }
        double ans = 0.0;
        ans = p2(curSum + 1, N, a, b) + p2(curSum + 1, N, a, b) * N;
        if (curSum + 1 + N < b) {
            ans -= p2(curSum + 1 + N, N, a, b);
        }
        //
        return ans / N;
    }

    // 使用临近位置进行替换枚举操作-dp
    public static double f3(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        // 比如我的 a 是 100，b=200，n只有10
        // 小于10的一直拿，大于200了就返回0
        if (b - a >= N) {
            return 1.0;
        }
        double[] dp = new double[b];
        for (int i = a; i < b; i++) {
            dp[i] = 1.0;
        }
        if (a - 1 >= 0) {
            dp[a - 1] = 1.0 * (b - a) / N;
        }
        for (int i = a - 2; i >= 0; i--) {
            double ans = dp[i + 1] + dp[i + 1] * N;
            if (i + 1 + N < b) {
                ans -= dp[i + 1 + N];
            }
            dp[i] = ans / N;
        }
        return dp[0];
    }

}
