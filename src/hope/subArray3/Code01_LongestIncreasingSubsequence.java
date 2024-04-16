package hope.subArray3;

/**
 * @author Mr.Du
 * @ClassName Code01_LongestIncreasingSubsequence
 * @date 2023年12月28日 10:19
 */
// 最长递增子序列和最长不下降子序列
// 给定一个整数数组nums
// 找到其中最长严格递增子序列长度、最长不下降子序列长度
// 测试链接 : https://leetcode.cn/problems/longest-increasing-subsequence/
public class Code01_LongestIncreasingSubsequence {
    // 普通的解，动态规划
    public static int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] ends = new int[n];
        // len表示ends数组目前的有效区域长度
        // ends[0....len-1]是有效区，有效区里的数组是严格递增的
        int len = 0;
        for (int i = 0, find; i < n; i++) {
            find = find(ends, len, nums[i]);
            if (find == -1) {
                ends[len++] = nums[i];
            } else {
                ends[find] = nums[i];
            }
        }
        return len;
    }

    public static int find(int[] ends, int len, int num) {
        int l = 0, r = len - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (ends[m] >= num) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }
}
