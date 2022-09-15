package week.week_2022_03_04;

import java.util.Arrays;

/**
 * @ClassName Code_03_MinTowNumberSumABS
 * @Author Duys
 * @Description
 * @Date 2022/3/25 13:47
 **/
// 给定一个数组arr，可能有正、有负、有0，无序
// 只能挑选两个数字，想尽量让两个数字加起来的绝对值尽量小
// 返回可能的最小的值
public class Code_03_MinTowNumberSumABS {


    /**
     * 思路：
     * 1.排序
     * 2.然后从 最小的区域往中间遍历，
     * 比如 -9 -2 0 1 2 3 6 9
     * 来到-9 看看离 +9 最近的数字是哪一个
     */
    public static int minSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int split = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                split = i;
                break;
            }
        }
        if (split == 0) {
            return arr[0] + arr[1];
        }
        // 说明没有正数
        if (split == -1) {
            return -arr[n - 2] - arr[n - 1];
        }
        // 普通情况
        int ans = Integer.MIN_VALUE;
        if (split + 1 < n) {
            ans = arr[split] + arr[split + 1];
        }
        if (split - 2 >= 0) {
            ans = Math.min(ans, -arr[split - 1] - arr[split - 2]);
        }
        for (int i = 0; i < split; i++) {
            ans = Math.min(ans, Math.abs(arr[i] + near(arr, split, -arr[i])));
        }
        return ans;
    }

    // 离num最近的
    public static int near(int[] arr, int start, int num) {
        int l = start;
        int r = arr.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] <= num) {
                l = m - 1;
                ans = m;
            } else {
                r = m + 1;
            }
        }
        if (ans == -1) {
            return arr[start];
        }
        if (ans == arr.length - 1) {
            return arr[arr.length - 1];
        }
        if (Math.abs(arr[ans] - num) <= Math.abs(arr[ans + 1] - num)) {
            return arr[ans];
        } else {
            return arr[ans + 1];
        }
    }
}
