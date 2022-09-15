package custom.code_2022_09;

/**
 * @ClassName LeetCode_718
 * @Author Duys
 * @Description
 * @Date 2022/9/13 16:00
 **/
// 718. 最长重复子数组
public class LeetCode_718 {
    public static void main(String[] args) {
        // [1,2,3,2,8]
        //[5,6,1,4,7]
        int[] nums1 = {1, 2, 3, 2, 8}, nums2 = {5, 6, 1, 4, 7};
        System.out.println(findLength(nums1, nums2));
    }

    public static int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        /*for (int i = 0; i < n; i++) {
            dp[i][0] = nums1[i] == nums2[0] ? 1 : 0;
        }
        for (int i = 0; i < m; i++) {
            dp[0][i] = nums1[0] == nums2[i] ? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = nums1[i] == nums2[j] ? dp[i - 1][j - 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }*/
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = nums1[i] == nums2[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
