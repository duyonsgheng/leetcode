package custom.code_2022_09;

/**
 * @ClassName LeetCode_837
 * @Author Duys
 * @Description
 * @Date 2022/9/27 19:23
 **/
//爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
//爱丽丝以 0 分开始，并在她的得分少于 k 分时抽取数字。 抽取时，她从 [1, maxPts] 的范围中随机获得一个整数作为分数进行累计，
//其中 maxPts 是一个整数。 每次抽取都是独立的，其结果具有相同的概率。
//当爱丽丝获得 k 分 或更多分 时，她就停止抽取数字。
//爱丽丝的分数不超过 n 的概率是多少？
//与实际答案误差不超过10^-5 的答案将被视为正确答案。
//链接：https://leetcode.cn/problems/new-21-game
public class LeetCode_837 {
    // 当分数达到或超过 k 时游戏结束，游戏结束时
    // 如果分数不超过 n 则获胜
    // 如果分数超过 n 则失败
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1.0;
        }
        double[] dp = new double[k + maxPts];
        for (int i = k; i <= n && i < k + maxPts; i++) {
            dp[i] = 1.0;
        }
        /*dp[k - 1] = 1.0 * Math.min(n - k + 1, maxPts) / maxPts;
        for (int i = k - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + maxPts + 1] - dp[i + 1]) / maxPts;
        }*/
        for (int i = k - 1; i >= 0; i--) {
            for (int j = 1; j <= maxPts; j++) {
                dp[i] += dp[i + j] / maxPts;
            }
        }
        return dp[0];
    }

    public double new21Game1(int n, int k, int maxPts) {
        double dp[] = new double[k + 1];
        return process(n, k, maxPts, 0, dp);
    }

    public double process(int n, int k, int maxPts, int cur, double[] dp) {
        if (cur >= k && cur <= n) {
            return 1.0;
        }
        if (cur > n) {
            return 0;
        }
        if (dp[cur] != 0) return dp[cur];
        double ans = 0;
        for (int i = 1; i <= maxPts; i++)
            ans += process(n, k, maxPts, cur + i, dp);
        dp[cur] = ans / maxPts;
        return dp[cur];
    }

    public double new21Game2(int n, int k, int maxPts) {
        double dp[] = new double[k + 1 + maxPts];
        for (int i = k; i <= n; i++) dp[i] = 1;
        double sum = Math.min(n - k + 1, maxPts);
        for (int i = k - 1; i >= 0; i--) {
            dp[i] = sum / maxPts;
            sum += dp[i] - dp[i + maxPts];
        }
        return dp[0];
    }
}
