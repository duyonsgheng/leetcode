package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1498
 * @Author Duys
 * @Description
 * @Date 2023/2/2 16:40
 **/
// 1498. 满足条件的子序列数目
public class LeetCode_1498 {
    int mod = 1_000_000_007;

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] > target) {
            return -1;
        }
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[1] = 2;
        int l = 0, r = n - 1, t = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int cur = nums[mid];
            if (cur <= target) {
                t = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        l = 0;
        r = t;
        long ans = 0;
        while (l <= r) {
            while (l <= r && nums[l] + nums[r] <= target) {
                l++;
            }
            if (l <= r) {
                ans += (pow(l, dp) - 1) * pow(r - l, dp) % mod;
                r--;
            }
        }
        if (r >= 0) {
            ans += pow(r + 1, dp) - 1;
            ans %= mod;
        }
        return (int) (ans % mod);
    }

    public long pow(int x, long[] dp) {
        if (dp[x] != -1) {
            return dp[x];
        }
        long ans = pow(x >> 1, dp) % mod;
        ans = (ans * ans) % mod;
        ans = (x & 1) == 1 ? ans * 2 % mod : ans;
        dp[x] = ans;
        return ans;
    }
}
