package week.week_2023_06_02;

import java.util.Arrays;

/**
 * @ClassName Code_05_LeetCode_1187
 * @Author Duys
 * @Description
 * @Date 2023/6/15 10:18
 **/
// 1187. 使数组严格递增
// https://leetcode.cn/problems/make-array-strictly-increasing/
public class Code_05_LeetCode_1187 {
    public static int makeArrayIncreasing1(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int cnt = 1;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] != arr2[i - 1]) {
                cnt++;
            }
        }
        int[] help = new int[cnt];
        help[0] = arr2[0];
        for (int i = 1, j = 1; i < arr2.length; i++) {
            if (arr2[i] != arr2[i - 1]) {
                help[j++] = arr2[i];
            }
        }
        int[] dp = new int[arr1.length + 1];
        Arrays.fill(dp, -1);
        int ans = process1(arr1, help, -1, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process1(int[] arr1, int[] arr2, int i, int[] dp) {
        if (i == arr1.length) {
            return 0;
        } else {
            if (dp[i + 1] != -1) {
                return dp[i + 1];
            }
            int cur = i == -1 ? Integer.MIN_VALUE : arr1[i];
            int f = find(arr2, cur);
            int ans = Integer.MAX_VALUE;
            int times = 0;
            for (int j = i + 1; j <= arr1.length; j++) {
                if (j == arr1.length || cur < arr1[j]) {
                    int next = process1(arr1, arr2, j, dp);
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, times + next);
                    }
                }
                if (f != -1 && f < arr2.length) {
                    cur = arr2[f++];
                    times++;
                } else {
                    break;
                }
            }
            dp[i + 1] = ans;
            return ans;
        }
    }

    // 迭代版的动态规划
    public static int makeArrayIncreasing3(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int m = 1;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] != arr2[m - 1]) {
                arr2[m++] = arr2[i];
            }
        }
        int n = arr1.length;
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= -1; i--) {
            int cur = i == -1 ? Integer.MIN_VALUE : arr1[i];
            int f = find3(arr2, m, cur);
            dp[i + 1] = Integer.MAX_VALUE;
            int times = 0;
            for (int j = i + 1; j <= n; j++) {
                if (j == n || cur < arr1[j]) {
                    if (dp[j + 1] != Integer.MAX_VALUE) {
                        dp[i + 1] = Math.min(dp[i + 1], times + dp[j + 1]);
                    }
                }
                if (f != -1 && f < m) {
                    cur = arr2[f++];
                    times++;
                } else {
                    break;
                }
            }
        }
        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0];
    }

    public static int find3(int[] arr2, int size, int num) {
        int l = 0;
        int r = size - 1;
        int m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr2[m] > num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // 在arr2中找到刚刚大于num的数
    public static int find(int[] arr2, int num) {
        int l = 0, r = arr2.length - 1, m = 0, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr2[m] > num) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }
}
