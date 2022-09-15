package week.week_2022_04_02;

import java.util.Arrays;

/**
 * @ClassName Code_05_MaxSumDividedBy7
 * @Author Duys
 * @Description
 * @Date 2022/4/13 14:42
 **/

// 来自美团
// 3.36笔试
// 给定一个非负数组，任意选择数字，使累加和最大且为7的倍数
public class Code_05_MaxSumDividedBy7 {

    public static int maxSum1(int[] arr) {
        return process(arr, 0, 0);
    }

    public static int process(int[] arr, int index, int pre) {
        if (arr.length == index) {
            return pre % 7 == 0 ? pre : 0;
        }
        int p1 = process(arr, index + 1, pre);
        int p2 = process(arr, index + 1, pre + arr[index]);
        return Math.max(p1, p2);
    }

    public static int maxSum2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int n = arr.length;
        // dp[i][j] 含义：当来到i位置，累加和最大且%7=j
        int[][] dp = new int[n][7];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][arr[0] % 7] = arr[0];
        for (int i = 1; i < n; i++) {
            int curMod = arr[i] % 7;
            for (int j = 0; j < 7; j++) {
                dp[i][j] = dp[i - 1][j];
                // 比如当前i位置%7是2，j = 3 我要找到之前是%7=1位置的数
                // 当前是 3 j =6 ，那么我要找到之前是3的 那么就是10或者3
                // 当前是 6， j =1 那么我要找到之前是2的
                // 所以公式：
                int findMod = (7 - curMod + j) % 7;
                if (dp[i - 1][findMod] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][findMod]);
                }
            }
        }
        return dp[n - 1][0] == -1 ? 0 : dp[n - 1][0];
    }
}
