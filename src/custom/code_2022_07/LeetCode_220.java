package custom.code_2022_07;

import java.util.TreeSet;

/**
 * @ClassName LeetCode_220
 * @Author Duys
 * @Description
 * @Date 2022/7/7 16:04
 **/
// 220. 存在重复元素 III
// 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
//如果存在则返回 true，不存在返回 false。
//链接：https://leetcode.cn/problems/contains-duplicate-iii
public class LeetCode_220 {

    // 滑动窗口 + 有序表
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            // 看看有没有 大于等于 得最小值
            Long limit = set.ceiling((long) nums[i] - (long) t);
            if (limit != null && limit <= ((long) t + (long) nums[i])) {
                return true;
            }
            set.add((long) nums[i]);
            // 固定窗口大小
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
