package week.week_2022_02_02;

import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_719
 * @Author Duys
 * @Description
 * @Date 2022/3/30 13:02
 **/
// 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
// https://leetcode-cn.com/problems/find-k-th-smallest-pair-distance/
public class Code_03_LeetCode_719 {

    public static int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int l = 0;
        // 全局最大的差值
        int r = nums[n - 1] - nums[0];
        int ans = 0;
        while (l <= r) {
            int dis = l + ((r - l) >> 1);
            int cnt = num(nums, dis);
            if (cnt >= k) {
                ans = dis;
                r = dis - 1;
            } else {
                l = dis + 1;
            }
        }
        return ans;
    }

    // 返回 差值 <= dis的数字对有几个
    public static int num(int[] arr, int dis) {
        int cnt = 0;
        for (int l = 0, r = 0; l < arr.length; l++) {
            while (r < arr.length && arr[r] - arr[l] <= dis) {
                r++;
            }
            cnt += r - l - 1;
        }
        return cnt;
    }
}
