package week.week_2022_12_01;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_03_LeetCode_2463
 * @Author Duys
 * @Description
 * @Date 2022/12/8 10:27
 **/
// 2463. 最小移动总距离
public class Code_03_LeetCode_2463 {
    // 动态规划
    // dp[i][j] 含义 0到i号机器人到0到j号工厂去的最小移动距离总和
    // 可能性分析
    // 第i号机器人直接就不去j号工厂 dp[i][j-1]
    // 第i号机器人去j号工厂，不仅如此，i-1 i-2 都可以去j号工厂，直到j号工厂容量填满
    public long minimumTotalDistance1(List<Integer> robot, int[][] factory) {
        int n = robot.size();
        int m = factory.length;
        // 机器人根据位置排个序
        robot.sort((a, b) -> a - b);
        // 工厂也根据位置排个序
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        long[][] dp = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long ans = j - 1 >= 0 ? dp[i][j - 1] : Long.MAX_VALUE;
                long dist = 0;
                // 枚举第j号工厂可以哪些机器人来
                for (int l = i, num = 1; l >= 0 && num <= factory[j][1]; l--, num++) {
                    long cur = l - 1 < 0 ? 0 : (j - 1 < 0 ? Long.MAX_VALUE : dp[l - 1][j - 1]);
                    dist += Math.abs(robot.get(l) - factory[j][0]);
                    if (cur != Long.MAX_VALUE) {
                        ans = Math.min(ans, cur + dist);
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[n - 1][m - 1];
    }

    // 最优解O(N*M)，
    // 比较复杂，大体的思路是利用了窗口内最小值的更新结构，来保存最小值。然后往下递推
    public static long minimumTotalDistance2(List<Integer> robot, int[][] factory) {
        int n = robot.size();
        int m = factory.length;
        robot.sort((a, b) -> a - b);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        long[][] dp = new long[n][m];
        long[][] deque = new long[n + 1][2];
        int l = 0;
        int r = 0;
        // 最外的for循环一定是枚举工厂
        for (int j = 0; j < m; j++) {
            long add = 0;
            long limit = factory[j][1];
            l = 0;
            r = 1;
            // deque[l][0] : 加入时的下标，用来判断过期
            deque[l][0] = -1;
            // deque[l][1] : 加入时的指标，窗口选出最小指标 去加上当前add！
            deque[l][1] = 0;
            for (int i = 0; i < n; i++) {
                long p1 = j - 1 >= 0 ? dp[i][j - 1] : Long.MAX_VALUE;
                add += Math.abs((long) robot.get(i) - (long) factory[j][0]);
                if (deque[l][0] == i - limit - 1) {
                    l++;
                }
                long p2 = Long.MAX_VALUE;
                if (l < r) {
                    long best = deque[l][1];
                    if (best != Long.MAX_VALUE) {
                        p2 = add + best;
                    }
                }
                dp[i][j] = Math.min(p1, p2);
                long fill = p1 == Long.MAX_VALUE ? p1 : (p1 - add);
                while (l < r && deque[r - 1][1] >= fill) {
                    r--;
                }
                deque[r][0] = i;
                deque[r++][1] = fill;
            }
        }
        return dp[n - 1][m - 1];
    }
}
