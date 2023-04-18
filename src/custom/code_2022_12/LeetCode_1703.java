package custom.code_2022_12;

// 1703. 得到连续 K 个 1 的最少相邻交换次数
public class LeetCode_1703 {
    public int minMoves(int[] nums, int k) {
        int n = nums.length, m = 0;
        for (int i = 0; i < n; ++i)
            if (nums[i] != 0) {
                nums[m] = i - m;
                ++m;
            }
        if (m == n) return 0; // 全部都是 1
        int[] p = nums;
        int sl = 0, sm = 0, sr = 0; // s[i] s[i+k/2] s[i+k]
        for (int i = 0; i < k; ++i) {
            if (i < k / 2) sm += p[i];
            sr += p[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= m - k; ++i) {
            ans = Math.min(ans, sl + sr - sm * 2 - p[i + k / 2] * (k % 2));
            sl += p[i];
            sm += p[i + k / 2];
            sr += p[i + k];
        }
        return ans;
    }
}
