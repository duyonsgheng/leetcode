package hope.class87;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code04_MakeArrayStrictlyIncreasing
 * @date 2024年08月20日 上午 11:23
 */
// 使数组严格递增的最小操作数
// 给你两个整数数组 arr1 和 arr2
// 返回使 arr1 严格递增所需要的最小操作数（可能为0）
// 每一步操作中，你可以分别从 arr1 和 arr2 中各选出一个索引
// 分别为 i 和 j，0 <= i < arr1.length 和 0 <= j < arr2.length
// 然后进行赋值运算 arr1[i] = arr2[j]
// 如果无法让 arr1 严格递增，请返回-1
// 1 <= arr1.length, arr2.length <= 2000
// 0 <= arr1[i], arr2[i] <= 10^9
// 测试链接 : https://leetcode.cn/problems/make-array-strictly-increasing/
public class Code04_MakeArrayStrictlyIncreasing {
    // 对arr2进行排序和去重
    public static int makeArrayIncreasing1(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int m = 1;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] != arr2[m - 1]) {
                arr2[m++] = arr2[i];
            }
        }
        int n = arr1.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int ans = f1(arr1, arr2, n, m, 0, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // arr1的长度为n
    // arr2的长度为m
    // arr2的有效部分可以替换arr1的数字
    // arr1中 0...i-1已经严格递增，且i-1位置一定是没有被替换的
    // 返回arr1从i...到n-1位置整体都严格递增，需要arr2中的数字替换几次
    // 如果不能做到，则返回无穷大
    public static int f1(int[] arr1, int[] arr2, int n, int m, int i, int[] dp) {
        if (i == n) { // 整体已经严格递增了
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans = Integer.MAX_VALUE;
        // 前一位的数字是啥
        int pre = i == 0 ? Integer.MIN_VALUE : arr1[i - 1];
        int find = bs(arr2, m, pre);
        // 开始枚举arr1中每一个不需要替换的位置
        for (int j = i, k = 0, next; j <= n; j++, k++) {
            if (j == n) {
                ans = Math.min(ans, k);
            } else {
                if (pre < arr1[j]) {
                    next = f1(arr1, arr2, n, m, j + 1, dp);
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, k + next);
                    }
                }
                if (find != -1 && find < m) {
                    pre = arr2[find++];
                } else break;
            }
        }
        dp[i] = ans;
        return ans;
    }

    // 在arr2[o...size-1]中找到刚刚比num大的最左位置
    public static int bs(int[] arr2, int size, int num) {
        int l = 0, r = size - 1, m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr2[m] > num) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }

    public static int makeArrayIncreasing2(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int m = 1;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] != arr2[m - 1]) {
                arr2[m++] = arr2[i];
            }
        }
        int n = arr1.length;
        int[] dp = new int[n];
        for (int i = n - 1, ans, find, pre; i >= 0; i--) {
            ans = Integer.MAX_VALUE;
            pre = i == 0 ? Integer.MIN_VALUE : arr1[i - 1];
            find = bs(arr2, m, pre);
            for (int j = i, k = 0, next; j <= n; j++, k++) {
                if (j == n) {
                    ans = Math.min(ans, k);
                } else {
                    if (pre < arr1[j]) {
                        next = dp[j + 1];
                        if (next != Integer.MAX_VALUE) {
                            ans = Math.min(ans, k + next);
                        }
                    }
                    if (find != -1 && find < m) {
                        pre = arr2[find++];
                    } else break;
                }
            }
            dp[i] = ans;
        }
        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0];
    }
}
