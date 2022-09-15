package week.week_2022_02_03;

import java.util.Arrays;

/**
 * @ClassName Code_04_MaxTeamNumber
 * @Author Duys
 * @Description
 * @Date 2022/3/29 14:34
 **/
// 来自微软
// 给定一个数组arr，一个正数num，一个正数k
// 可以把arr中的某些数字拿出来组成一组，要求该组中的最大值减去最小值<=num
// 且该组数字的个数一定要正好等于k
// 每个数字只能选择进某一组，不能进多个组
// 返回arr中最多有多少组
public class Code_04_MaxTeamNumber {

    // 暴力方法
    public static int max1(int[] arr, int num, int k) {
        Arrays.sort(arr);
        return process(arr, 0, new int[arr.length], 0, num, k);
    }

    public static int process(int[] arr, int index, int[] path, int size, int num, int k) {
        if (index == arr.length) {
            if (size % k != 0) {
                return 0;
            } else {
                // 因为是有序的，所以这里只需要看看每一组是否符合就行
                for (int start = 0; start < size; start += k) {
                    if (path[start + k - 1] - path[start] > num) {
                        return 0;
                    }
                }
                return size / k;
            }
        } else {
            int p1 = process(arr, index + 1, path, size, num, k);
            path[size] = arr[index];
            int p2 = process(arr, index + 1, path, size + 1, num, k);
            return Math.min(p1, p2);
        }
    }

    //
    public static int max2(int[] arr, int num, int k) {
        int n = arr.length;
        if (k > n) {
            return 0;
        }
        Arrays.sort(arr);
        int[] dp = new int[n];
        dp[k - 1] = arr[k - 1] - arr[0] <= num ? 1 : 0;
        for (int i = k; i < n; i++) {
            int p1 = dp[i - 1]; // 不要当前的数和之前的搞到一组
            // 当前数和前面的搞到一组
            int p2 = ((arr[i] - arr[i - k + 1]) <= num ? 1 : 0) + dp[i - k];
            dp[i] = Math.max(p1, p2);
        }
        return dp[n - 1];
    }
}
