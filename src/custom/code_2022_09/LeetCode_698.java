package custom.code_2022_09;

import java.util.Arrays;

/**
 * @ClassName LeetCode_698
 * @Author Duys
 * @Description
 * @Date 2022/9/8 16:26
 **/
//
public class LeetCode_698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int len = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (nums[len - 1] > target) {
            return false;
        }
        int size = 1 << len;
        boolean[] dp = new boolean[size];
        dp[0] = true;
        int[] curSum = new int[size];
        for (int i = 0; i < size; i++) {
            if (!dp[i]) {
                continue;
            }
            for (int j = 0; j < len; j++) {
                // 当前选择过了
                if ((i & (1 << j)) != 0) {
                    continue;
                }
                int next = i | (1 << j);
                if (dp[next]) {
                    continue;
                }
                if ((curSum[i] % target) + nums[j] <= target) {
                    curSum[next] = curSum[i] + nums[j];
                    dp[next] = true;
                }
            }
        }
        return dp[size - 1];
    }
}
