package hope.class81_status2;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code04_DistributeRepeatingIntegers
 * @date 2024年04月17日 下午 02:13
 */
// 分配重复整数
// 给你一个长度为n的整数数组nums，这个数组中至多有50个不同的值
// 同时你有m个顾客的订单quantity，其中整数quantity[i]是第i位顾客订单的数目
// 请你判断是否能将nums中的整数分配给这些顾客，且满足：
// 第i位顾客恰好有quantity[i]个整数、第i位顾客拿到的整数都是相同的
// 每位顾客都要满足上述两个要求，返回是否能都满足
// 测试链接 : https://leetcode.cn/problems/distribute-repeating-integers/
public class Code04_DistributeRepeatingIntegers {
    public static boolean canDistribute(int[] nums, int[] quantity) {
        Arrays.sort(nums);
        int n = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                n++;
            }
        }
        int[] cnt = new int[n];
        int c = 1;
        for (int i = 1, j = 0; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                cnt[j++] = c;
                c = 1;
            } else c++;
        }
        cnt[n - 1] = c;
        int m = quantity.length;
        int[] sum = new int[1 << m];
        for (int i = 0, v, h; i < quantity.length; i++) {
            v = quantity[i];
            h = 1 << i;
            for (int j = 0; j < n; j++) {
                sum[h | j] = sum[j] + v;
            }
        }
        int[][] dp = new int[1 << m][n];
        return f(cnt, sum, (1 << m) - 1, 0, dp);
    }

    // 当前来到的数字，编号是index，个数是cnt[index]
    // status：订单的状态，为1是还需要去满足的，为0表示已经满足了
    public static boolean f(int[] cnt, int[] nums, int status, int index, int[][] dp) {
        if (status == 0) {
            return true;
        }
        // 没有数字了，但是订单状态还有没满足的
        if (index == nums.length) {
            return false;
        }
        if (dp[status][index] != 0) {
            return dp[status][index] == 1;
        }
        boolean ans = false;
        int k = cnt[index];
        for (int j = status; j > 0; j = (j - 1) & status) {
            if (nums[j] <= k && f(cnt, nums, status ^ j, index + 1, dp)) {
                ans = true;
                break;
            }
        }
        if (!ans) {
            ans = f(cnt, nums, status, index + 1, dp);
        }
        dp[status][index] = ans ? 1 : -1;
        return ans;
    }
}
