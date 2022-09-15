package week.week_2022_03_03;

import java.util.Arrays;

/**
 * @ClassName Code_01_ChangeNums
 * @Author Duys
 * @Description
 * @Date 2022/3/17 14:20
 **/
public class Code_01_ChangeNums {
    // 来自美团
    // 给定一个数组arr，你可以随意挑选其中的数字
    // 但是你挑选的数中，任何两个数a和b，不能让Math.abs(a - b) <= 1
    // 返回你最多能挑选几个数

    /**
     * 思路：
     * 看到这种情况第一时间想到的先排序，然后从左往右尝试
     * 但是在排序的时候，我们需要处理几个情况，当有重复的值的时候，只保留一个
     * 然后来到i位置 看看与i-1位置是否满足，然后看看与i-2位置是否满足，因为是去重，然后排序的，所以最多考虑到i-2
     */
    public static int changeNums(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int size = 1;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 如果相邻不相等
            if (arr[i] != arr[i - 1]) {
                arr[size++] = arr[i];
            }
        }
        // 下面只需要遍历 0到size就行了
        int[] dp = new int[size];
        dp[0] = 1; // 只有0位置的数，只有一个选择。
        int ans = 1;
        for (int i = 1; i < size; i++) {
            // 当前位置数自己就是一个，
            dp[i] = 1;
            if (arr[i] - arr[i - 1] > 1) {
                dp[i] = dp[i - 1] + 1;
            }
            if (i - 2 > 0 && arr[i] - arr[i - 2] > 1) {
                dp[i] = Math.max(dp[i], dp[i - 2] + 1);
            }
            // 抓住每一步的答案
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
