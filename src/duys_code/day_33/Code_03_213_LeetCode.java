package duys_code.day_33;

/**
 * @ClassName Code_03_213_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/house-robber-ii/
 * @Date 2021/12/6 13:31
 **/
public class Code_03_213_LeetCode {
    // 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
    // 意思就是 0和N-1相连
    // 分两种情况
    // 1. 0 到 n-2 选一个最大
    // 2. 1 到 n-1 选一个最大
    // 相邻位置不能选
    // 比如当前来到i位置
    // p1. 完全不考虑i 就是 0到i-1 的答案
    // p2. 只考虑i位置情况，那就只有i
    // p3. 我要i和i之前的 那就是 i位置+0到i-2位置
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 0到 n-2
        int p1 = nums[0];
        int p2 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            // 不能相邻
            int tmp = Math.max(nums[i] + p1, p2);
            tmp = Math.max(tmp, nums[i]);
            p1 = p2;
            p2 = tmp;
        }
        int ans1 = p2;

        // 1到 n-1
        int p3 = nums[1];
        int p4 = Math.max(nums[1], nums[2]);
        for (int i = 3; i < nums.length; i++) {
            // 不能相邻
            int tmp = Math.max(nums[i] + p3, p4);
            tmp = Math.max(tmp, nums[i]);
            p3 = p4;
            p4 = tmp;
        }
        int ans2 = p4;
        return Math.max(ans1, ans2);
    }
}
