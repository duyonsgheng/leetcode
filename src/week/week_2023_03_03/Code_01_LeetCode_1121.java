package week.week_2023_03_03;

/**
 * @ClassName Code_01_LeetCode_1121
 * @Author Duys
 * @Description
 * @Date 2023/3/23 11:10
 **/
// 给你一个 非递减 的正整数数组 nums 和整数 K
// 判断该数组是否可以被分成一个或几个 长度至少 为 K 的 不相交的递增子序列
// 测试链接 : https://leetcode.cn/problems/divide-array-into-increasing-sequences/
public class Code_01_LeetCode_1121 {
    // 看看相同的元素可以分为多少组。
    public static boolean canDivideIntoSubsequences(int[] nums, int k) {
        int cnt = 1;
        int maxCnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                maxCnt = Math.max(cnt, maxCnt);
                cnt = 1;
            } else {
                cnt++;
            }
        }
        maxCnt = Math.max(maxCnt, cnt);
        return nums.length / maxCnt >= k;
    }
}
