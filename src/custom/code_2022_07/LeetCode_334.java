package custom.code_2022_07;

/**
 * @ClassName LeetCode_334
 * @Author Duys
 * @Description
 * @Date 2022/7/19 17:17
 **/
// 334. 递增的三元子序列
// 给你一个整数数组nums ，判断这个数组中是否存在长度为 3 的递增子序列。
//如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
//链接：https://leetcode.cn/problems/increasing-triplet-subsequence
public class LeetCode_334 {

    // 递增子序列问题
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return false;
        }
        int n = nums.length;
        // ends数组，记录了递增子序列结尾位置，长度就是endSize
        int[] ends = new int[n + 1];
        ends[0] = nums[0];
        int endSize = 1;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < n; i++) {
            if (endSize >= 3) {
                return true;
            }
            l = 0;
            r = right;
            while (l <= r) {
                // 长度
                m = l + ((r - l) >> 1);
                if (ends[m] < nums[i]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            endSize = Math.max(endSize, l + 1);
        }
        return endSize >= 3;
    }
}
