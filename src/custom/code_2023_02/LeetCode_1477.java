package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1477
 * @Author Duys
 * @Description
 * @Date 2023/2/1 15:13
 **/
// 1477. 找两个和为目标值且不重叠的子数组
public class LeetCode_1477 {
    int max = 1_000_01;

    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, max);
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0, sum = 0; r < n; r++) {
            sum += arr[r];
            // 如果窗口被破坏，需要收紧窗口
            while (l <= r && sum > target) {
                sum -= arr[l++];
            }
            if (sum == target) {
                dp[r] = r - l + 1;
                // 当前窗口是 l到r，上一个满足的是 l-1时候的答案
                ans = Math.min(ans, (l > 0 ? dp[l - 1] : max) + r - l + 1);
            }
            // 如果窗口内只有一个数，那么没有答案
            dp[r] = Math.min(dp[r], r > 0 ? dp[r - 1] : max);
        }
        return ans > n ? -1 : ans;
    }
}
