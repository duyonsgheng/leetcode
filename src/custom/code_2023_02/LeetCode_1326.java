package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1326
 * @Author Duys
 * @Description
 * @Date 2023/2/21 17:40
 **/
// 1326. 灌溉花园的最少水龙头数目
public class LeetCode_1326 {
    /*public int minTaps(int n, int[] ranges) {
        int inf = (int) 1e5;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            int range = ranges[i];
            int l = i - range;
            int r = i + range;
            int min = inf;
            for (int j = Math.max(l, 0); j <= Math.min(r, n); j++) {
                min = Math.min(min, dp[j] + 1);
            }
            dp[Math.min(r, n)] = Math.min(dp[Math.min(r, n)], min);
        }
        return dp[n] == inf ? -1 : dp[n];
    }*/

    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            intervals[i] = new int[]{start, end};
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            // 如果当前的start已经不能满足了，没戏了。
            if (dp[start] == Integer.MAX_VALUE) {
                return -1;
            }
            // 枚举当前区域，设置好每个位置上的值
            for (int j = start; j <= end; j++) {
                dp[j] = Math.min(dp[j], dp[start] + 1);
            }
        }
        return dp[n];
    }
}
