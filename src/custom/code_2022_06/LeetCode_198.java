package custom.code_2022_06;

/**
 * @ClassName LeetCode_198
 * @Author Duys
 * @Description
 * @Date 2022/6/9 17:20
 **/
// 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
//如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//链接：https://leetcode.cn/problems/house-robber
public class LeetCode_198 {

    // dp[i] = arr[i] + dp[i-2] or dp[i] = dp[i-1]
    public static int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 1];
    }


    public static int rob1(int[] nums) {
        return process(nums, 0, 0);
    }

    public static int process(int[] arr, int index, int pre) {
        if (index == arr.length) {
            return 0;
        }
        if (pre == 0) {
            int p1 = arr[index] + process(arr, index + 1, 1);
            int p2 = process(arr, index + 1, 0);
            return Math.max(p1, p2);
        } else {
            return process(arr, index + 1, 0);
        }
    }

    public static void main(String[] args) {
        int[] arr = {114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240};
        System.out.println(rob(arr));
        System.out.println(rob1(arr));
    }
}
