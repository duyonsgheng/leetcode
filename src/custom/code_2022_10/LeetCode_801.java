package custom.code_2022_10;

/**
 * @ClassName LeetCode_801
 * @Author Duys
 * @Description
 * @Date 2022/10/10 9:01
 **/
// 801. 使序列递增的最小交换次数
public class LeetCode_801 {
    // 动态规划
    // 使得两个数组都严格递增
    // nums1 = [1,3,5,4],
    // nums2 = [1,2,3,7]
    // nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1]
    // nums1[i] > nums2[i-1] && nums2[i] > nums1[i-1]
    // 两种情况可以交换，使得两个数组都递增
    public int minSwap1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int pre0 = 0; // 不需要交换
        int pre1 = 1; // 需要交换
        for (int i = 1; i < n; i++) {
            int cur0 = pre0;
            int cur1 = pre1;
            pre0 = Integer.MAX_VALUE;
            pre1 = Integer.MAX_VALUE;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                // 继续保持不交换
                pre0 = Math.min(pre0, cur0);
                // 可以交换
                pre1 = Math.min(pre1, cur1 + 1);
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                pre0 = Math.min(pre0, cur1);
                pre1 = Math.min(pre1, cur0 + 1);
            }
        }
        return Math.min(pre0, pre1);
    }

    public int minSwap(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0; // 0位置不交换
        dp[0][1] = 1; // 0位置交换
        for (int i = 1; i < n; i++) {
            // 三种情况
            int a1 = arr1[i - 1];
            int a2 = arr1[i];
            int b1 = arr2[i - 1];
            int b2 = arr2[i];
            // 1 2
            // 1 3
            // 可以交换，也可以不交换
            if (a1 < a2 && b1 < b2 && b1 < a2 && a1 < b2) {
                // 可以交换，也可以不交换，取小的
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i][0] + 1; // 再不交换的基础上+1。表示交换了
            }
            // 6 7
            // 3 4
            // 满足两个数组递增。
            else if (a1 < a2 && b1 < b2) {
                // 不交换，就直接i-1位置也不能交换
                dp[i][0] = dp[i - 1][0];
                // 要交换的话，那么i-1位置也必须交换
                dp[i][1] = dp[i - 1][1] + 1;
            }
            // 都不是有序的了
            else {
                // i 位置不交换，那么i-1位置必须交换
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
}
