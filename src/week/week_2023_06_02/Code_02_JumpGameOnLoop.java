package week.week_2023_06_02;

import java.util.Arrays;

/**
 * @ClassName Code_02_JumpGameOnLoop
 * @Author Duys
 * @Description
 * @Date 2023/6/15 9:22
 **/
// 来自华为od
// 给定一个数组arr，长度为n，表示n个格子的分数，并且这些格子首尾相连
// 孩子不能选相邻的格子，不能回头选，不能选超过一圈
// 但是孩子可以决定从任何位置开始选，也可以什么都不选
// 返回孩子能获得的最大分值
// 1 <= n <= 10^6
// 0 <= arr[i] <= 10^6
public class Code_02_JumpGameOnLoop {

    public int max1(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        int n = arr.length;
        // 状态压缩
        int[][] dp = new int[n][4];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        // 1.拿0位置
        int ans = arr[0] + process1(arr, 1, 1, 1, dp);
        // 2.不拿0位置
        ans = Math.max(ans, process1(arr, 1, 0, 0, dp));
        return ans;
    }

    // arr[0....n-1]，注意0和n-1是相邻的！
    // 当前来到的位置是i, 要，不要
    // pre i位置的前一个数，
    // pre == 1，i位置的前一个数已经拿了
    // pre == 0，i位置的前一个数没拿
    // end == 1，说明: 有朝一日来到n-1位置的话，不能拿
    // end == 0，说明: 有朝一日来到n-1位置的话，可以考虑
    // 返回值 : 在上面的设定下，i....n-1，能获得的最大累加和是多少
    public int process1(int[] arr, int i, int pre, int end, int[][] dp) {
        if (i == arr.length - 1) {
            return (pre == 1 || end == 1) ? 0 : Math.max(0, arr[i]);
        } else {
            if (dp[i][(pre << 1) | end] != Integer.MIN_VALUE) {
                return dp[i][(pre << 1) | end];
            }
            // 当前位置不拿
            int p1 = process1(arr, i + 1, 0, end, dp);
            int p2 = Integer.MIN_VALUE;
            if (pre != 1) {
                // 拿了当前位置
                p2 = process1(arr, i + 1, 1, end, dp) + arr[i];
            }
            int ans = Math.max(p1, p2);
            dp[i][(pre << 1) | end] = ans;
            return ans;
        }
    }

    // 正式方法
    // 严格位置依赖的动态规划 + 空间压缩
    // 时间复杂度O(N)
    public static int max(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        int n = arr.length;
        int[] next = new int[4];
        int[] cur = new int[4];
        next[0] = Math.max(0, arr[n - 1]);
        for (int i = n - 2; i >= 1; i--) {
            for (int pre = 0; pre < 2; pre++) {
                for (int end = 0; end < 2; end++) {
                    cur[(pre << 1) | end] = next[end];
                    if (pre != 1) {
                        cur[(pre << 1) | end] = Math.max(cur[(pre << 1) | end], arr[i] + next[2 + end]);
                    }
                }
            }
            next[0] = cur[0];
            next[1] = cur[1];
            next[2] = cur[2];
            next[3] = cur[3];
        }
        return Math.max(arr[0] + next[3], next[0]);
    }
}
