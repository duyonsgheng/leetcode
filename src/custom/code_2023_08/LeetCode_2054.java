package custom.code_2023_08;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2054
 * @date 2023年08月03日
 */
// 2054. 两个最好的不重叠活动
// https://leetcode.cn/problems/two-best-non-overlapping-events/
public class LeetCode_2054 {
    // 按照 结束时间排序
    // 每次二分，一直到最后，抓出最大的结果
    public int maxTwoEvents(int[][] events) {
        int n = events.length, m = 2;
        int[][] dp = new int[n][m + 1];
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        for (int i = 0; i < n; i++) {
            int l = 0, r = i - 1;
            while (l <= r) {
                int mid = (r + l) / 2;
                // 往左试试
                // 当前的结束时大于了之前的开始的
                if (events[mid][1] >= events[i][0]) {
                    r = mid - 1;
                } else l = mid + 1;
            }
            // 选择
            for (int k = 1; k <= m; k++) {
                dp[i][k] = events[i][2]; // 当前选择一个
                if (r >= 0) { // 左边选k-1个
                    dp[i][k] += dp[r][k - 1];
                }
                if (i > 0) {
                    dp[i][k] = Math.max(dp[i][k], dp[i - 1][k]);
                }
            }
        }
        return dp[n - 1][m];
    }
}
