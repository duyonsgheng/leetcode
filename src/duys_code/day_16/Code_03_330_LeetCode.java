package duys_code.day_16;

/**
 * @ClassName Code_03_330_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/patching-array/
 * @Date 2021/10/26 15:43
 **/
public class Code_03_330_LeetCode {
    /**
     * 给定一个已排序的正整数数组 nums，和一个正整数n 。从[1, n]区间内选取任意个数字补充到nums中，
     * 使得[1, n]区间内的任何数字都可以用nums中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
     */
    public int minPatches(int[] nums, int n) {
        int pathces = 0;// 缺少多少数字
        long range = 0; // 已经已完成了 1~range
        for (int i = 0; i != nums.length; i++) {
            // 比如已经搞了1~100 range = 100
            // arr[i] = 101 能不能搞定 可以
            // arr[i] = 102 不行了 1~100 拿出最大的100 搞不定102
            while (nums[i] - 1 > range) {
                range += range + 1;
                pathces++;
                if (range >= n) {
                    return pathces;
                }
            }
            // 当前可以搞定，直接推高range
            range += nums[i];
            if (range >= n) {
                return pathces;
            }
        }
        // 整个数组都遍历完了，发现离n还有距离
        while (n >= range + 1) {
            range += range + 1;
            pathces++;
        }
        return pathces;
    }
}
