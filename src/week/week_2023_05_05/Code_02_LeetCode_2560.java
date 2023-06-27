package week.week_2023_05_05;

import java.util.Arrays;

/**
 * @ClassName Code_02_LeetCode_2560
 * @Author Duys
 * @Description
 * @Date 2023/6/2 13:48
 **/
// 2560. 打家劫舍 IV
public class Code_02_LeetCode_2560 {
    // 复习：之前的打家劫舍问题
    // 不能相邻
    // 那么dp[i] 就存在两种情况
    // 1.我不要当前位置，那么就是dp[i-1]
    // 2.我要当前位置，那么就是dp[i-2]+arr[i]
    // 最后两个取较大的
    // 本题只是加了一个限制，至少要窃取 k家。
    // 返回小偷的最小能力要达到多少
    // 分析，能力我们可以二分，从0到max二分就好了。每一次看看能偷多少家
    public static int minCapability(int[] nums, int k) {
        int l = 1;
        int r = Arrays.stream(nums).max().getAsInt();
        int m, ans = 0;
        while (l <= r) {
            // 能力
            m = l + (r - l) / 2;
            // 说明当前能力够大，看看能不能更小
            if (process(nums, m) >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // 盗贼的能力为limit的时候，可以盗取多少房屋
    public static int process(int[] arr, int limit) {
        // 房屋的值小于等于能力，表示可以动手
        int lastLast = arr[0] <= limit ? 1 : 0;
        int n = arr.length;
        if (n == 1) {
            return lastLast;
        }
        int last = (arr[0] <= limit || arr[1] <= limit) ? 1 : 0;
        int ans = Math.max(last, lastLast);
        for (int i = 2; i < n; i++) {
            // 可能性1，当前不要，就是i-1位置的
            int p1 = last;
            // 可能性2：当前要，那么就i-2 + 当前
            int p2 = 0;
            if (arr[i] <= limit) {
                p2 = lastLast + 1;
            }
            int cur = Math.max(p1, p2);
            ans = Math.max(ans, cur);
            lastLast = last;
            last = cur;
        }
        return ans;
    }
}
