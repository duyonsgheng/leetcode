package custom.code_2023_06;

/**
 * @ClassName LeetCode_1855
 * @Author Duys
 * @Description
 * @Date 2023/6/6 9:36
 **/
// 1855. 下标对中的最大距离
public class LeetCode_1855 {
    // 窗口 ，存在单调性
    public int maxDistance(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int r = 0;
        int ans = 0;
        for (int l = 0; l < n; l++) {
            // 如果nums2中数字一直大于，对于当前窗口来说是有效的，不满足的时候，左边就要缩一个
            while (r < m && nums2[r] >= nums1[l]) {
                r++;
            }
            // r 来到不满足的位置，那么满足的位置一定是r-1
            ans = Math.max(ans, r - l - 1);
        }
        return ans;
    }
}
