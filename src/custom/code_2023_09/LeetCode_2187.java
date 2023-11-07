package custom.code_2023_09;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2187
 * @date 2023年09月07日
 */
// 2187. 完成旅途的最少时间
// https://leetcode.cn/problems/minimum-time-to-complete-trips/
public class LeetCode_2187 {
    // 二分
    public long minimumTime(int[] time, int totalTrips) {
        Arrays.sort(time);
        long l = time[0], r = (long) time[0] * totalTrips;
        while (l < r) {
            long mid = l + ((r - l) >> 1);
            if (process(time, mid, totalTrips) < totalTrips) {
                l = mid + 1;
            } else r = mid;
        }
        return r;
    }

    public int process(int[] times, long cur, int total) {
        int ans = 0;
        for (int t : times) {
            if (cur < t || ans >= total) {
                break;
            }
            ans += (cur / t);
        }
        return ans;
    }
}
