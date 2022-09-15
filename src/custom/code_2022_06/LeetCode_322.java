package custom.code_2022_06;

import java.util.Arrays;

/**
 * @ClassName LeetCode_322
 * @Author Duys
 * @Description
 * @Date 2022/6/13 10:11
 **/
// 322. 零钱兑换
// 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
//你可以认为每种硬币的数量是无限的。
//链接：https://leetcode.cn/problems/coin-change
public class LeetCode_322 {

    public static int coinChange1(int[] coins, int amount) {
        if (amount < 0 || coins == null || coins.length < 1) {
            return -1;
        }
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(coins, 0, amount, dp);
    }

    public static int process(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (rest <= 0) {
            return 0;
        }
        if (arr.length == index) {
            return -1;
        }
        int p1 = process(arr, index + 1, rest, dp);

        int p2 = -1;
        int next = -1;
        int ans = -1;
        if (rest >= arr[index]) {
            next = process(arr, index, rest - arr[index], dp);
        }
        if (next != -1) {
            p2 = 1 + next;
        }
        if (p1 == -1) {
            dp[index][rest] = p2;
            return p2;
        }
        if (p2 == -1) {
            dp[index][rest] = p1;
            return p1;
        }
        ans = Math.min(p1, p2);
        dp[index][rest] = ans;
        return ans;
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount < 0 || coins == null || coins.length < 1) {
            return -1;
        }
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < n; j++) {
                if (coins[j] <= i) { // 可以选
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        int[] arr = {411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422};
        int n = 9864;
        //System.out.println(coinChange1(arr, n));
        System.out.println(coinChange(arr, n));
    }
}
