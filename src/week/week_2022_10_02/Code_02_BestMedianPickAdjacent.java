package week.week_2022_10_02;

import java.util.Arrays;

/**
 * @ClassName Code_02_BestMedianPickAdjacent
 * @Author Duys
 * @Description
 * @Date 2022/10/13 13:07
 **/
// 来自京东
// 实习岗位笔试题
// 给定一个数组arr，长度为n
// 相邻的两个数里面至少要有一个被选出来，组成子序列
// 求选出来的数字构成的所有可能的子序列中，最大中位数是多少
// 中位数的定义为上中位数
// [1, 2, 3, 4]的上中位数是2
// [1, 2, 3, 4, 5]的上中位数是3
// 2 <=  n <= 10^5
// 1 <= arr[i] <= 10^9
// 我写的帖子解答 : https://www.mashibing.com/question/detail/34771
public class Code_02_BestMedianPickAdjacent {
    // 思路：
    // 1.尝试中位数哪些数可以满足条件
    // 2.给数组排序，然后选择其中某一个数组为基准
    // 3.然后再数组中满足大于等于这个基准的 为1，不满足的为-1
    // 4.然后求一个数组满足条件的最大累加和
    // 累加和如果 大于 0 ，那么说明当前选的基准是可以的。再排序数组中找满足的最右边界在哪里。二分
    public static int bestMedian(int[] arr) {
        int n = arr.length;
        int[] sort = new int[n];
        for (int i = 0; i < n; i++)
            sort[i] = arr[i];
        Arrays.sort(sort);
        int l = 0;
        int r = n - 1;
        int m = 0;
        int ans = -1;
        int[] help = new int[n];
        // dp[i][0] - 表示 选择i位置的时候，最大的累加和
        // dp[i][1] - 表示 没选择i位置的时候，最大的累加和
        int[][] dp = new int[n + 1][2];
        while (l <= r) {
            m = (l + r) / 2;
            if (maxSum(arr, help, dp, sort[m], n) > 0) {
                ans = sort[m];
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static int maxSum(int[] arr, int[] help, int[][] dp, int median, int n) {
        for (int i = 0; i < n; i++) {
            help[i] = arr[i] >= median ? 1 : -1;
        }
        for (int i = n - 1; i >= 0; i--) {
            // 选择当前的
            dp[i][0] = help[i] + dp[i + 1][1];
            // 选择当前的 和 不选择当前的
            dp[i][1] = Math.max(help[i] + dp[i + 1][1], dp[i + 1][0]);
        }
        return dp[0][1];
    }
}
