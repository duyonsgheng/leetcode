package week.week_2021_11_04;

/**
 * @ClassName Code_02_375_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
 * @Date 2022/1/25 17:01
 **/
public class Code_02_375_LeetCode {
    //我从1到 n 之间选择一个数字。
    //你来猜我选了哪个数字。
    //如果你猜到正确的数字，就会 赢得游戏 。
    //如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
    //每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
    //问题：给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。

    // 先来一个暴力的
    public static int getMoneyAmount1(int n) {
        return process(1, n);
    }

    // 在L到R范围上猜数字
    // 本题中首先能想到的就是对手绝顶聪明，每次留给你的都是最差情况
    public static int process(int L, int R) {
        if (L == R) { // 只有一个数字，不用猜了，就是它了
            return 0;
        }

        // 假如猜错了
        // 只剩下两个数字了，那么我只需要猜小的哪个，哪怕猜错了，也只需要支付小的钱
        // 说明L~R范围，只剩下两个数字了
        // 比如： 5 6
        // 假设永远会遇到最差情况，
        // 那么当然猜5，因为最差情况下，也只需要耗费5的代价，然后就知道了答案是6
        // 不能猜6，因为最差情况下，需要耗费6的代价，然后才知道答案是5
        // 所以当然选代价低的！请深刻理解：每次永远面临猜错的最差情况！
        if (L == R - 1) {
            return L;
        }
        // 我们现在就是先猜 两边，然后往中间走
        // 如果说明L~R范围，不仅仅两个数字
        // 比如：5 6 7 8 9
        // 首先尝试5，如果最差情况出现，代价为：5 + 6~9范围上的尝试
        // 最后尝试9，如果最差情况出现，代价为：9 + 5~8范围上的尝试
        int ans = Math.min(L + process(L + 1, R), R + process(L, R - 1));
        // 进而尝试6，如果最差情况出现，代价为：6 + Max { 5~5范围上的尝试 ，7~9范围上的尝试}
        // 这是因为猜了6，会告诉你，猜高了还是猜低了，所以左右两侧的待定范围，一定会只走一侧
        // 又因为永远会遇到最差情况，所以，一定会走最难受的那一侧，所以是 Max { 5~5范围上的尝试 ，7~9范围上的尝试}
        // 进而尝试7，如果最差情况出现，代价为：7 + Max { 5~6范围上的尝试 ，8~9范围上的尝试}
        // 进而尝试8，如果最差情况出现，代价为：8 + Max { 5~7范围上的尝试 ，9~9范围上的尝试}
        // 所有尝试中，取代价最小值
        for (int M = L + 1; M < R; M++) {
            ans = Math.min(ans, M + Math.max(process(L, M - 1), process(M + 1, R)));
        }
        return ans;
    }

    // 两个可变参数，做动态规划，二维表
    public static int getMoneyAmount2(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n; i++) {
            dp[i][i + 1] = i;
        }
        // dp[i][i] = 0
        // 返回是第1行 n列
        // 从下往上，从左往右
        // 对角线以下的没意义
        for (int L = n - 2; L >= 1; L--) {
            for (int R = L + 1; R <= n; R++) {
                dp[L][R] = Math.min(L + dp[L + 1][R], R + dp[L][R - 1]);
                for (int M = L + 1; M < R; M++) {
                    dp[L][R] = Math.min(dp[L][R], M + Math.max(dp[L][M - 1], dp[M + 1][R]));
                }
            }
        }
        return dp[1][n];
    }
}
