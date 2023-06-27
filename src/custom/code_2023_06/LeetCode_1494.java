package custom.code_2023_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_1494
 * @Author Duys
 * @Description
 * @Date 2023/6/16 10:37
 **/
// 1494. 并行课程 II
// 给你一个整数n表示某所大学里课程的数目，编号为1到n，数组relations中，relations[i] = [xi, yi] 表示一个先修课的关系，也就是课程xi必须在课程yi之前上。同时你还有一个整数k。
//在一个学期中，你 最多可以同时上 k门课，前提是这些课的先修课在之前的学期里已经上过了。
//请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/parallel-courses-ii
public class LeetCode_1494 {
    // 只是拓扑排序，貌似不行
    public static int minNumberOfSemesters1(int n, int[][] relations, int k) {
        // 建图 - 有向图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 入度表
        int[] in = new int[n + 1];
        // [a,b] 想修b，必选先a
        for (int[] rela : relations) {
            graph.get(rela[0]).add(rela[1]);
            in[rela[1]]++;
        }
        // 从入度为0开始
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        while (!queue.isEmpty()) {
            // 依次弹出
            int size = queue.size();
            int tmp = 0;
            for (int i = 0; i < size; i++) {
                Integer cur = queue.poll();
                if (!set.contains(cur)) {
                    tmp++;
                    set.add(cur);
                }
                List<Integer> nexts = graph.get(cur);
                for (int next : nexts) {
                    in[next]--;
                    if (in[next] == 0) {
                        queue.add(next);
                    }
                }
            }
            ans += (tmp + k - 1) / k;
        }
        return ans;
    }

    public static int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] pre = new int[n];
        for (int[] rela : relations) {
            // 所有的课程需要的前置课程
            pre[rela[1] - 1] |= 1 << rela[0] - 1;
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int mask = 0; mask < 1 << n; mask++) {
            int curState = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) continue; // 已经学过了
                // 囊括了我要学的课程，则可以学习
                if ((mask & pre[i]) == pre[i]) {
                    curState |= (1 << i);
                }
                for (int cur = curState; cur > 0; cur = (cur - 1) & curState) {
                    if (Integer.bitCount(cur) > k) {
                        continue;
                    }
                    dp[mask | cur] = Math.min(dp[mask | cur], dp[mask] + 1);
                }

            }
        }
        return dp[(1 << n) - 1];
    }

    public static void main(String[] args) {
        // n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
        System.out.println(minNumberOfSemesters(4, new int[][]{{2, 1}, {3, 1}, {1, 4}}, 2));
        // n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
        int n = 5, relations[][] = {{2, 1}, {3, 1}, {4, 1}, {1, 5}}, k = 2;
        System.out.println(minNumberOfSemesters(n, relations, k));
    }
}
