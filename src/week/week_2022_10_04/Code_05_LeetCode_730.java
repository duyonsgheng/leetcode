package week.week_2022_10_04;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_05_LeetCode_730
 * @Author Duys
 * @Description
 * @Date 2022/10/27 14:32
 **/
// 730. 统计不同回文子序列
public class Code_05_LeetCode_730 {
    public int countPalindromicSubsequences(String s) {
        int mod = 1_000_000_007;
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Map<Character, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++) {
            left[i] = last.getOrDefault(arr[i], -1);
            last.put(arr[i], i);
        }
        last.clear();
        for (int i = n - 1; i >= 0; i--) {
            right[i] = last.getOrDefault(arr[i], n);
            last.put(arr[i], i);
        }
        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1; // 对角线只有自己1个
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    // i i+1 .... j-1 j
                    // 两个位置相等
                    // 看看i到j者之间有没有i位置和j位置的字符
                    int l = Math.min(j, right[i]);
                    int r = Math.max(i, left[j]);
                    if (l > r) { // 没有r或者l位置的字符
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    } else if (l == r) { // 只有1个
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else { // 有 >= 2个
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1] + mod;
                    }
                } else {
                    // 如果两个位置不等
                    // i i+1 ..... j-1 j
                    // 就看i到j-1和 i+1 到j 然后去掉重复的部分 i+1 到 j-1
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1] + mod;
                }
                dp[i][j] %= mod;
            }
        }
        return (int) dp[0][n - 1];
    }
}
