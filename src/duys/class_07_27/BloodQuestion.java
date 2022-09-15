package duys.class_07_27;

/**
 * @ClassName BloodQuestion
 * @Author Duys
 * @Description 怪兽血量问题 - 斜率优化，观察临近位置，观察位置包含关系
 * @Date 2021/7/27 17:09
 **/
public class BloodQuestion {
    /**
     * 给定3个参数，N，M，K
     * 怪兽有N滴血，等着英雄来砍自己
     * 英雄每一次打击，都会让怪兽流失[0~M]的血量
     * 到底流失多少？每一次在[0~M]上等概率的获得一个值
     * 求K次打击之后，英雄把怪兽砍死的概率
     */
    // 1 次打击 0 - m
    // 2 次打击 0 - m
    // k 次打击 0 - m
    // 总共 (m+1)^k
    public static double daji(int N, int K, int M) {
        if (N < 1 || K < 1 || M < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long process = process(N, K, M);
        return (double) process / (double) all;
    }

    // 怪兽还剩下N点血量，每次砍了M点血，还剩下K次没砍
    private static long process(int N, int K, int M) {
        // 如果血量已经小于=0了。那么还是要展开，所以剩余的生存点就是这么多
        if (N <= 0) {
            return (long) Math.pow(M + 1, K);
        }
        if (K == 0) {
            // 不剪枝，血量小于0，可以继续砍，直到次数用完
            return N <= 0 ? 1 : 0;
        }
        long ans = 0;
        for (int i = 0; i <= M; i++) {
            ans += process(N - i, K - 1, M);
        }
        return ans;
    }

    // hp - 血量
    // times - 剩余次数
    public static double dp1(int hp, int times, int M) {
        if (hp < 1 || times < 1 || M < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, times);

        /**
         *   0------N+1
         * 0
         *
         * k
         */
        // 行是剩余次数 列是血量
        long dp[][] = new long[times + 1][hp + 1];
        // 根据递归 当K =0的时候 N<=0 为 1，其他位置都是0
        dp[0][0] = 1;
        // 位置依赖
        // 剩余步数
        for (int i = 1; i <= times; i++) {
            // 剩余血量
            dp[i][0] = (long) Math.pow(M + 1, i);
            for (int j = 1; j <= hp; j++) {
                long ans = 0;
                // 每次砍 0 - m 血量
                for (int l = 0; i <= M; i++) {
                    // 当血量小于等于了0，那么直接获取次数
                    if (j - l >= 0) {
                        ans += dp[i - 1][j - l];
                    } else {
                        ans += (long) Math.pow(M + 1, i - 1);
                    }
                }
                dp[i][j] = ans;
            }
        }
        return (double) dp[times][hp] / (double) all;
    }

    // 上面的dp中，存在枚举行为 也就是73 到80行

    /**
     * 找严格位置依赖
     * 比如 dp[5][10] 意思是 还剩下 5次可以砍，还剩下10滴血，每次砍 M (0 -3) 范围血量
     * 依赖关系dp[5][10] = dp[5][9] + dp[4][10] - dp[4][10-(M)]
     * 那么dp[5][10] 依赖 dp[4][10], dp[4][9], dp[4][8], dp[4][7] 四个位置的累加
     * 那么dp[4][10] 依赖 dp[3][10], dp[3][9], dp[3][8], dp[3][7]
     * 那么dp[4][9] 依赖 dp[3][9], dp[3][8], dp[3][7], dp[3][6]
     * 那么dp[4][8] 依赖 dp[3][8], dp[3][7], dp[3][6], dp[3][5]
     * 那么dp[4][7] 依赖 dp[3][7], dp[3][6], dp[3][5], dp[3][4]
     * 依次依赖 血量不小于0，那么依赖的是上一行的(血量-（0-m）),当血量小于等于0的时候 直接就等于 (M+1)^k这么多
     * -----------------------------
     * 例如
     * m - (0-7)
     * dp[5][10] = dp[4][10.....3]
     * dp[5][11] = dp[4][11.....4] -> dp[5][10] + dp[4][11] - dp[4][3]
     * 普遍位置 dp[i][j] = dp[i][j-1]+dp[i-1][j]-dp[i-1][j-(m+1)]
     * 解释：因为dp[5][11] 的原解是dp[4][11.....4]相比dp[5][10] 的解 dp[4][10.....3]
     * 多了一个dp[4][11] 但是少了一个dp[4][3]所以表达式是 dp[i][j] = dp[i][j-1]+dp[i-1][j]-dp[i-1][j-(m+1)]
     */
    public static double dp2(int hp, int times, int M) {
        if (hp < 1 || times < 1 || M < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, times);

        /**
         *   0------N+1
         * 0
         *
         * k
         */
        // 行是剩余次数 列是血量
        long dp[][] = new long[times + 1][hp + 1];
        // 根据递归 当K =0的时候 N<=0 为 1，其他位置都是0
        dp[0][0] = 1;
        // 位置依赖
        // 剩余步数
        for (int i = 1; i <= times; i++) {
            // 剩余血量
            dp[i][0] = (long) Math.pow(M + 1, i);
            for (int j = 1; j <= hp; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                // 如果剩余的血量大于=0 说明格子存在，直接减去格子里的值
                if (j - 1 - M >= 0) {
                    dp[i][j] -= dp[i - 1][j - 1 - M];
                }
                // 如果剩余的血量小于0了，说明格子不存在，但是根据实际逻辑情况，可以把血量小于0的情况算出来，需要减去
                else {
                    dp[i][j] -= dp[i - 1][0]; // dp[i][j] -=  Math.pow(M + 1, i-1)
                }
            }
        }
        return (double) dp[times][hp] / (double) all;
    }
}
