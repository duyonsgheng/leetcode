package custom.code_2023_07;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1981
 * @date 2023年07月20日
 */
// 1981. 最小化目标值与所选元素的差
// https://leetcode.cn/problems/minimize-the-difference-between-target-and-chosen-elements/
public class LeetCode_1981 {
    // 背包类型的dp
    public int minimizeTheDifference(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;
        boolean[][] dp = new boolean[n + 1][4900];
        Arrays.fill(dp[0], false);
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 4900; j++) {
                for (int k = 0; k < m; k++) {
                    if (j - mat[i - 1][k] >= 0 && dp[i - 1][j - mat[i - 1][k]] == true) {
                        dp[i][j] = true;
                        break;
                    }
                    dp[i][j] = false;
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < 4900; j++) {
            if (dp[n][j]) {
                ans = Math.min(Math.abs(target - j), ans);
            }
        }
        return ans;
    }

    public int minimizeTheDifference1(int[][] mat, int target) {
        int m = mat.length, n = mat[0].length;
        boolean[] dp = new boolean[4901];
        for (int i = 0; i < n; i++) {
            dp[mat[0][i]] = true;
        }
        for (int i = 1; i < m; i++) {
            boolean[] cur = new boolean[4901];
            for (int k = 0; k < 4901; k++) {
                if (dp[k]) {
                    for (int j = 0; j < n; j++) {
                        int num = mat[i][j];
                        if (num + k < 4901) //
                            cur[num + k] = true;
                    }
                }

            }
            dp = cur;
        }
        int ans = Integer.MAX_VALUE;
        for (int k = 0; k < 4901; k++) {
            if (dp[k]) {
                ans = Math.min(ans, Math.abs(k - target));
            }
        }
        return ans;
    }
}
