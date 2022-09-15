package custom.code_2022_08;

import java.util.Arrays;

/**
 * @ClassName LeetCode_435
 * @Author Duys
 * @Description
 * @Date 2022/8/10 17:01
 **/
// 435. 无重叠区间
public class LeetCode_435 {
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = intervals.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][0] < intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return n - Arrays.stream(dp).max().getAsInt();
    }

    public static int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int n = intervals.length;
        int r = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; i++) {
            // 当前的开始大于之前的结束
            if (intervals[i][0] >= r) {
                ans++;
                r = intervals[i][1];
            }
        }
        return n - ans;
    }

    public static int eraseOverlapIntervals1(int[][] intervals) {
        // 首先根据 开始位置排序，开始位置相同的，谁大谁在前面
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int n = intervals.length;
        int res = 0;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                ++res;
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);//缩减当前的区间，相当于删除重叠区间
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}};
        System.out.println(eraseOverlapIntervals(arr));
    }
}
