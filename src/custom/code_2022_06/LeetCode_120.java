package custom.code_2022_06;

import java.util.List;

/**
 * @ClassName LeetCode_120
 * @Author Duys
 * @Description
 * @Date 2022/6/14 14:39
 **/
//120. 三角形最小路径和
// 给定一个三角形 triangle ，找出自顶向下的最小路径和。
//每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，
//如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
//链接：https://leetcode.cn/problems/triangle
public class LeetCode_120 {

    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }
        return process(triangle, 0, 0);
    }

    public static int process(List<List<Integer>> triangle, int col, int index) {
        if (col == triangle.size()) {
            return 0;
        }
        List<Integer> list = triangle.get(col);
        if (index > list.size()) {
            return Integer.MAX_VALUE;
        }
        int ans = list.get(index);
        int p1 = process(triangle, col + 1, index);
        int p2 = process(triangle, col + 1, index + 1);
        if (p1 == Integer.MAX_VALUE && p2 == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (p1 == Integer.MAX_VALUE) {
            return ans + p2;
        }
        if (p2 == Integer.MAX_VALUE) {
            return ans + p1;
        }
        return Math.min(p1, p2) + ans;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        // 第0行只有一个
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            // 我第二行开头的都可以选择上一行开头的
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                // 然后普通位置 就是我当前位置的值来源于我上一行的左边和上一行
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            // 每一行最后一个位置必定来源于我上一行的最后一个
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int min = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }
}
