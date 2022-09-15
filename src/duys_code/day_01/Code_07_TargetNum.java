package duys_code.day_01;

/**
 * @ClassName Code_07_TargetNum
 * @Author Duys
 * @Description TODO
 * @Date 2021/9/15 15:10
 **/
public class Code_07_TargetNum {
    /**
     * 力扣： https://leetcode-cn.com/problems/target-sum/
     */
    /**
     * 题意：给一个正数nums数组，再给一个target，可以在nums的每一个数前面加上 + 或者 - 然后搞定target这个数
     */

    // 1. 尝试每一个位置，从左到右的尝试模型
    public static int find1(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        return process1(nums, 0, target);
    }

    public static int process1(int[] arr, int index, int rest) {
        // 已经没有数了，index来到了越界的位置了
        if (index == arr.length) {
            // 如果还剩下的正好是0，那么说明之前找到了一个有效的方式
            return rest == 0 ? 1 : 0;
        }
        // 要当前位置，也就是说给当前位置 + 号
        int p1 = process1(arr, index + 1, rest - arr[index]);
        // 不要当前位置 ，也就是说当前位置 - 号
        int p2 = process1(arr, index + 1, rest + arr[index]);
        return p1 + p2;
    }

    // 2.dp的解答

    /**
     * 比如
     * nums 的和为sum
     * nums 中填+的数组和 是sum1
     * nums 中填-的数据和 是num2
     * target = sum1 - sum2
     * sum1+sum2 + target = sum1-sum2 +sum1 +sum2
     * sum + target = 2*sum1
     * sum1 = (sum+target )>> 1
     */
    public static int find2(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
            return Math.abs(nums[0]) == Math.abs(target) ? 1 : 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum < target || ((target & 1) ^ (sum & 1)) != 0 ? 0 : process2(nums, (target + sum) >> 1);
    }

    public static int process2(int[] nums, int rest) {
        int n = nums.length;
        // dp[i][j] : nums前缀长度为i的所有子集，有多少累加和是j？
        int[][] dp = new int[n + 1][rest + 1];
        // nums前缀长度为0的所有子集，有多少累加和是0？一个：空集
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= rest; j++) {
                //由递归可知，下一行都依赖上一行的值
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][rest];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        int t = 3;
        System.out.println(find2(arr, t));
    }
}
