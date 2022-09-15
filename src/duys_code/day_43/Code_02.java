package duys_code.day_43;

import java.util.Arrays;

/**
 * @ClassName Code_02
 * @Author Duys
 * @Description
 * @Date 2022/1/5 13:32
 **/
public class Code_02 {
    // 来自360笔试
    // 给定一个正数数组arr，长度为n，下标0~n-1
    // arr中的0、n-1位置不需要达标，它们分别是最左、最右的位置
    // 中间位置i需要达标，达标的条件是 : arr[i-1] > arr[i] 或者 arr[i+1] > arr[i]哪个都可以
    // 你每一步可以进行如下操作：对任何位置的数让其-1
    // 你的目的是让arr[1~n-2]都达标，这时arr称之为yeah！数组
    // 返回至少要多少步可以让arr变成yeah！数组
    // 数据规模 : 数组长度 <= 10000，数组中的值<=500

    // 这个题意思就是类似一个下坡然后再上坡。我们尝试每一个位置是坡底
    public static int min1(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        // 防止再递归的时候减成负数
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] += arr.length - min;
        }
        return process1(arr, 1, arr[0], true);
    }

    // 当前来到idnex位置
    // pre 前一个位置的值，因为再之前的过程中，arr[index-1]位置上的值可能已经改了
    // preOk 前一个位置的值是否被它左边的数变得有效了
    // 返回让arr变有效，最小的代价
    public static int process1(int[] arr, int index, int pre, boolean preOk) {
        if (index == arr.length - 1) {
            return preOk || pre < arr[index] ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        if (preOk) {
            // 就一直下坡嘛
            for (int cur = arr[index]; cur >= 0; cur--) {
                int next = process1(arr, index + 1, cur, cur < pre);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, arr[index] - cur + next);
                }
            }
        } else {
            for (int cur = arr[index]; cur > pre; cur--) {
                int next = process1(arr, index + 1, cur, false);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, arr[index] - cur + next);
                }
            }
        }
        return ans;
    }

    public static int min2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] += n - min;
        }
        int[][][] dp = new int[n][2][];
        for (int i = 1; i < n; i++) {
            dp[i][0] = new int[arr[i - 1] + 1];
            dp[i][1] = new int[arr[i - 1] + 1];
            Arrays.fill(dp[i][0], Integer.MAX_VALUE);
            Arrays.fill(dp[i][1], Integer.MAX_VALUE);
        }
        for (int pre = 0; pre < arr[n - 2]; pre++) {
            dp[n - 1][0][pre] = pre < arr[n - 1] ? 0 : Integer.MAX_VALUE;
            dp[n - 1][1][pre] = 0;
        }
        for (int index = n - 2; index >= 1; index--) {
            for (int pre = 0; pre <= arr[index - 1]; pre++) {
                for (int cur = arr[index]; cur > pre; cur++) {
                    int next = dp[index + 1][0][cur];
                    if (next != Integer.MAX_VALUE) {
                        dp[index][0][pre] = Math.min(dp[index][0][pre], arr[index] - cur + next);
                    }
                }
                for (int cur = arr[index]; cur >= 0; cur--) {
                    int next = dp[index + 1][cur < pre ? 1 : 0][cur];
                    if (next != Integer.MAX_VALUE) {
                        dp[index][0][pre] = Math.min(dp[index][0][pre], arr[index] - cur + next);
                    }
                }
            }
        }
        return dp[1][1][arr[0]];
    }
}
