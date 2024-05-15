package hope.class83_enum2;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code01_MaximumProfitInJobScheduling
 * @date 2024年05月15日 上午 09:27
 */
// 规划兼职工作
// 你打算利用空闲时间来做兼职工作赚些零花钱，这里有n份兼职工作
// 每份工作预计从startTime[i]开始、endTime[i]结束，报酬为profit[i]
// 返回可以获得的最大报酬
// 注意，时间上出现重叠的 2 份工作不能同时进行
// 如果你选择的工作在时间X结束，那么你可以立刻进行在时间X开始的下一份工作
// 测试链接 : https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
public class Code01_MaximumProfitInJobScheduling {
    public static int maxn = 50001;
    public static int[][] jobs = new int[maxn][3];

    public static int[] dp = new int[maxn];

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        // 所有的工作按照结束时间排序
        Arrays.sort(jobs, 0, n, (a, b) -> a[1] - b[1]);
        // 开始选择
        dp[0] = jobs[0][2];
        for (int i = 1, start; i < n; i++) {
            start = jobs[i][0];
            dp[i] = jobs[i][2];
            if (jobs[0][1] <= start) { // 如果最开始的任务的结束时间比当前开始的时间晚，那么就往左找
                dp[i] += dp[find(i - 1, start)];
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    // 在jod[0...i]范围上，找到结束时间 <= start，最右的下标
    public static int find(int i, int start) {
        int ans = 0;
        int l = 0;
        int r = i;
        int m = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (jobs[m][1] <= start) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        return ans;
    }
}
