package duys_code.day_52;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Code_01_656_LeetCode
 * @Author Duys
 * @Description 力扣: https://leetcode-cn.com/problems/coin-path/
 * @Date 2021/11/8 16:24
 **/
public class Code_01_656_LeetCode {
    // 问题：给定一个数组 A（下标从 1 开始）包含 N 个整数：A1，A2，……，AN 和一个整数 B。 你可以从数组 A 中的任何一个位置（下标为 i）跳到下标 i+1，i+2，……，i+B 的任意一个可以跳到的位置上。
    // 如果你在下标为 i 的位置上，你需要支付 Ai 个金币。 如果 Ai 是 -1，意味着下标为 i 的位置是不可以跳到的。
    // 如果有多种花费最少的方案，输出字典顺序最小的路径。如果无法到达 N 位置，请返回一个空数组

    // 问题1：不要求最小字典序
    public static int minCost(int[] arr, int jump) {
        if (arr == null || arr.length < 1 || jump <= 0) {
            return 0;
        }

        int n = arr.length;
        // 最开始就是不能跳的，最后一个位置也不能跳。直接返回-1
        if (arr[0] == -1 || arr[n - 1] == -1) {
            return -1;
        }
        // dp[i] : 从0位置开始出发，到达i位置的最小代价
        int[] dp = new int[n];
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            if (arr[i] == -1) {
                continue;
            }
            // 看看从之前的哪一个位置跳到当前i位置是最小的代价
            for (int pre = Math.max(0, i - jump); pre < i; pre++) {
                if (dp[pre] != -1) {
                    dp[i] = Math.min(dp[i], arr[i] + dp[pre]);
                }
            }
        }
        return dp[n - 1];
    }

    // 返回最优的代价，
    // 1.遇到代价低的就换方案。
    // 2.如果代价相同，但是跳的步骤越多，字典序越小
    // 证明很麻烦，利用反证法
    public static List<Integer> cheapestJump(int[] arr, int jump) {
        int n = arr.length;
        // 代价
        int[] best = new int[n];
        // 上一步的最优是在那个位置
        int[] last = new int[n];
        // 到上一个位置一共跳了多少步
        int[] size = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        Arrays.fill(last, -1);
        best[0] = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == -1) {
                continue;
            }
            for (int j = Math.max(0, i - jump); j < i; j++) {
                if (arr[j] == -1) {
                    continue;
                }
                int cur = arr[i] + best[j];
                // 1.遇到代价低的就换方案。
                // 2.如果代价相同，但是跳的步骤越多，字典序越小
                if (cur < best[i] || (cur == best[i] && size[i] - 1 < size[j])) {
                    best[i] = cur;
                    last[i] = j;
                    size[i] = size[j] + 1;
                }
            }
        }
        List<Integer> ans = new LinkedList<>();
        for (int cur = n - 1; cur >= 0; cur = last[cur]) {
            ans.add(0, cur + 1);
        }
        return ans.get(0) != 1 ? new LinkedList<>() : ans;
    }
}
