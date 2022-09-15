package duys.class_08_26;

/**
 * @ClassName DP_OPT_05
 * @Author Duys
 * @Description 四边形不等式-邮局划分问题-从左往右尝试模型
 * @Date 2021/8/31 10:16
 **/
public class DP_OPT_05 {
    /**
     * 一条直线上有居民点，邮局只能建在居民点上。给定一个有序正数数组arr，每个值表示 居民点的一维坐标，再给定一个正数 num，
     * 表示邮局数量。选择num个居民点建立num个 邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离
     * 【举例】
     * arr=[1,2,3,4,5,1000]，num=2。
     * 第一个邮局建立在 3 位置，第二个邮局建立在 1000 位置。
     * 那么 1 位置到邮局的距离 为 2， 2 位置到邮局距离为 1，
     * 3 位置到邮局的距离为 0，4 位置到邮局的距离为 1，
     * 5 位置到邮局的距 离为 2，
     * 1000 位置到邮局的距离为 0。这种方案下的总距离为 6，
     * 其他任何方案的总距离都不会 比该方案的总距离更短，所以返回6
     */
    /**
     * 以最后一个邮局负责哪些位置来进行可能性分析
     * 那么先来L - R范围上只有一个邮局的时候，最佳划分点在哪里，先的出来
     */
    public static int min1(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        // 自己到自己的距离是0，所以对角线全部是0，L > R的位置是无效的
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                // 如果L - R这之间有奇数个居民点，那么中点位置就是最优划分，
                // 如果L - R这之间有偶数个居民点，那么上中点喝下中点是一样的，都是最优的
                // 比如当前是 (1,4) 1号到4号居民点上有一个邮局，
                // 对角线是0 ，那么每一行从左往右填，
                // 0 - 0 = 0 ，0 - 1 就是0 - 0的加上 0 - 1的
                // 0 - 2 的就是前一个 0 - 1 的最优，加上当前2 到 前一个中点的距离之差
                // 这里面有一个巧妙之处
                // 0 - 0 建在0号点  0-1 建在0号喝1号一样的 0 - 2 建在1号最优。而上一步中在 0号和1号同时最优，所以在0-2范围求解的时候，
                // 就认为上一步的最优就在1，不用动，0-3范围最优是 1号和2号，而在0-4范围中最优是2号，那么上一步得出的答案直接可用，
                // 位置增加只需要加上当前增加的位置 到 前一个重点的距离之差就可以了。
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        // dp[i][j] - 表示 0到i号居民点 j个邮局的最佳划分的最短距离是多少
        /**
         * 这里的划分，我们还是和画家问题一样，以最后一个邮局负责哪些居民点来进行分析
         */
        // 只有一个邮局，直接从w拿，意思是0到i号居民点 1个邮局
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
        }
        // 无论多少个邮局，只有一个居民点，那么依然是0，所以 i从1开始 j从2开始
        for (int i = 1; i < N; i++) {
            // 这里有一个问题，那就是当居民点小于邮局的时候，都是0，没有意义
            //for (int j = 2; j <= num; j++) {
            for (int j = 2; j <= Math.min(num, i); j++) {
                int ans = Integer.MAX_VALUE;
                // 枚举最后一个邮局负责哪些居民点
                // 最后一个邮局负责
                for (int k = 0; k <= i; k++) {
                    // 前 0到k的居民点由 j-1个邮局负责，最后一个邮局负责的是 k+1 到 i位置的，直接从w里面拿
                    ans = Math.min(ans, dp[k][j - 1] + w[k + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][num];
    }

    // 使用四边形不等式优化枚举行为
    public static int min2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        int dp[][] = new int[N][num + 1];
        int best[][] = new int[N][num + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
            // 只有一个邮局
            best[i][1] = -1;
        }
        // 位置依赖，比如[7,4] 依赖哪些位置 [7,3]表示 0-7居民点由三个邮局负责，最后一个闲着，[6,2],[5,2],[4,2],[3,2],[2,2],[1,2]
        // 依赖左侧的上方的左右位置
        // 那么此时我左边格子给我一个枚举下限，下面格子给我一个上限的指导
        for (int j = 2; j <= num; j++) {
            // 这里之所以不是 i>=0 因为要规避邮局过剩的问题
            for (int i = N - 1; i >= j; i--) {
                int down = best[i][j - 1];
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestIndex = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    // 如果leftEnd 是=-1，说明，最后一个邮局搞定所有的
                    int leftAns = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    // 如果leftEnd = i了，说明，最后一个邮局闲着，否则，就是leftEnd 到 i号居民点由 最后一个邮局负责，直接从w拿
                    int rightAns = leftEnd == i ? 0 : w[leftEnd + 1][i];
                    int cur = leftAns + rightAns;
                    if (cur <= ans) {
                        ans = cur;
                        bestIndex = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestIndex;
            }

        }
        return dp[N - 1][num];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 7, 9, 11, 14, 15};
        int num = 6;
        System.out.println(min1(arr, num));
        System.out.println(min2(arr, num));
    }
}
