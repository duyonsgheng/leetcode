package custom.code_2023_07;

import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2002
 * @date 2023年07月25日
 */
// 2002. 两个回文子序列长度的最大乘积
// https://leetcode.cn/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/
public class LeetCode_2002 {
    // s的长度是最大12
    // 用状态来表示
    public int maxProduct(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int ans = 0;
        // 当前状态下的答案
        int[] dp = new int[1 << n];
        for (int i = 1; i < (1 << n); i++) {
            int l = 0, r = 0, num = i;
            // 101
            while (num != 0) {
                l++;
                int cnt = num & 1;
                if (cnt == 1 && r == 0) {
                    r = l;
                }
                num >>= 1;
            }
            //
            if (l == r) {
                dp[i] = 1;
            } else {
                dp[i] = Math.max(dp[i], dp[i & (~(1 << (r - 1)))]);
                dp[i] = Math.max(dp[i], dp[i & (~(1 << (l - 1)))]);
                if (arr[n - l] == arr[n - r]) {
                    dp[i] = Math.max(dp[i], dp[i & (~(1 << (l - 1))) & (~(1 << (r - 1)))] + 2);
                }
            }
        }
        for (int i = 1; i < (1 << n); i++) {
            int j = ((1 << n) - 1) ^ i;
            ans = Math.max(ans, dp[i] * dp[j]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n = n & (n - 1);
        }
        // 1001
        // 1000
        System.out.println(cnt);
    }
}
