package custom.code_2023_06;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1681
 * @date 2023年06月28日
 */
// 1681. 最小不兼容性
public class LeetCode_1681 {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length, group = n / k, max = Integer.MAX_VALUE;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, max);
        dp[0] = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int mask = 1; mask < (1 << n); mask++) {
            // 当前的mask二进制位信息，满足需求
            if (Integer.bitCount(mask) != group) {
                continue;
            }
            int minn = 20, maxn = 0;
            Set<Integer> set = new HashSet<>();
            // 看看那些数可以满足需求
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) > 0) {
                    if (set.contains(nums[i])) {
                        break;
                    }
                    set.add(nums[i]);
                    minn = Math.min(minn, nums[i]);
                    maxn = Math.max(maxn, nums[i]);
                }
            }
            if (set.size() == group) {
                map.put(mask, maxn - minn);
            }
        }
        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == max) {
                continue;
            }
            Map<Integer, Integer> seen = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    seen.put(nums[i], i);
                }
            }
            if (seen.size() < group) {
                continue;
            }
            int cur = 0;
            for (int v : seen.values()) {
                cur |= (1 << v);
            }
            int next = cur;
            while (next > 0) {
                if (map.containsKey(next)) {
                    dp[mask | next] = Math.min(dp[mask | next], dp[mask] + map.get(next));
                }
                next = (next - 1) & cur;
            }
        }
        return dp[(1 << n) - 1] < max ? dp[(1 << n) - 1] : -1;
    }
}
