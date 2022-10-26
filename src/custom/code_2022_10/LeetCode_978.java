package custom.code_2022_10;

/**
 * @ClassName LeetCode_978
 * @Author Duys
 * @Description
 * @Date 2022/10/26 10:35
 **/
// 978. 最长湍流子数组
public class LeetCode_978 {
    // 1.窗口
    public int maxTurbulenceSize1(int[] arr) {
        int n = arr.length;
        int ans = 1;
        int l = 0;
        int r = 0;
        while (r < n - 1) {
            if (l == r) {
                if (arr[l] == arr[l + 1]) {
                    l++;
                }
                r++;
            } else {
                if (arr[r - 1] < arr[r] && arr[r] > arr[r + 1]) {
                    r++;
                } else if (arr[r - 1] > arr[r] && arr[r] < arr[r + 1]) {
                    r++;
                } else {
                    l = r;
                }
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    // 2.动态规划
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        // dp[i][0] : 表示arr[i-1] > arr[i] 的最长子数组
        // dp[i][1] : 表示arr[i-1] < arr[i] 的最长子数组
        int[][] dp = new int[n][2];
        dp[0][0] = dp[0][1] = 1; // 只有一个数的时候都满足
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i][1] = 1;
            if (arr[i - 1] > arr[i]) {
                // 需要符号交替，那么当前就需要拿到前一个位置小于的情况
                dp[i][0] = dp[i - 1][1] + 1;
            } else if (arr[i - 1] < arr[i]) {
                // 需要符号交替，那么当前就需要拿到前一个位置大于的情况
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        int ans = 1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
        }
        return ans;
    }
}
