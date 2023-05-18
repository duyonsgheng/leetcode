package week.week_2023_04_04;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_06_LeetCode_2603
 * @Author Duys
 * @Description
 * @Date 2023/4/27 14:32
 **/
// 2603. 收集树中金币
public class Code_06_LeetCode_2603 {
    // 先把树中哪些无用的枝丫剪掉
    // 然后遍历找到核心的哪些点，核心的点必定会经历两次
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        int[] in = new int[n];// 入度表
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            in[edge[0]]++;
            in[edge[1]]++;
        }
        int[] queue = new int[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            // 这些无用的点
            if (in[i] == 1 && coins[i] == 0) {
                queue[r++] = i;
            }
        }
        // 开始剪枝
        while (l < r) {
            int cur = queue[l++];
            for (int next : graph.get(cur)) {
                if (--in[next] == 1 && coins[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        // 现在从哪些有金币的点开始
        for (int i = 0; i < n; i++) {
            if (in[i] == 1 && coins[i] == 1) {
                queue[r++] = i;
            }
        }
        int[] rank = new int[n];
        while (l < r) {
            int cur = queue[l++];
            for (int next : graph.get(cur)) {
                // 从当前cur位置走到next去
                if (--in[next] == 1) {
                    rank[next] = rank[cur] + 1;
                    queue[r++] = next;
                }
            }
        }
        int ans = 0;
        for (int[] edge : edges) {
            if (rank[edge[0]] >= 2 && rank[edge[1]] >= 2) {
                ans += 2;
            }
        }
        return ans;
    }
}
