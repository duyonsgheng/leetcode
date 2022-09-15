package duys_code.day_49;

/**
 * @ClassName Code_01_377_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/combination-sum-iv/
 * @Date 2021/10/27 13:06
 **/
public class Code_01_377_LeetCode {

    public int combinationSum4(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }
        int[] dp = new int[target + 1];
        for (int i = 0; i <= target; i++) {
            dp[i] = -1;
        }
        return process1(nums, target, dp);
    }

    public static int process1(int[] nums, int rest, int[] dp) {
        if (rest < 0) {
            return 0;
        }
        if (dp[rest] != -1) {
            return dp[rest];
        }
        if (rest == 0) {
            return 1;
        }
        int ans = 0;
        for (int num : nums) {
            ans += process1(nums, rest - num, dp);
        }
        dp[rest] = ans;
        return ans;
    }
}
