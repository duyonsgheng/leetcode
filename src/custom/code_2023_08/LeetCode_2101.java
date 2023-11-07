package custom.code_2023_08;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2101
 * @date 2023年08月17日
 */
// 2101. 引爆最多的炸弹
// https://leetcode.cn/problems/detonate-the-maximum-bombs/
public class LeetCode_2101 {
    int max = 1;
    boolean[] visited;

    int[][] boos;

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        visited = new boolean[n];
        boos = bombs;
        for (int i = 0; i < n; i++) {
            dfs(i);
            Arrays.fill(visited, false);
        }
        return max;
    }

    public int dfs(int cur) {
        if (visited[cur]) {
            return 0;
        }
        visited[cur] = true;
        int ans = 1;
        for (int i = 0; i < boos.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (can(cur, i)) {
                ans += dfs(i);
            }
        }
        max = Math.max(max, ans);
        return ans;
    }

    public boolean can(int i, int j) {
        int[] a1 = boos[i]; // x y r
        int[] a2 = boos[j]; // x y r
        long x1 = a1[0], y1 = a1[1], r1 = a1[2], x2 = a2[0], y2 = a2[1];
        // 两点的距离
        long len = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        long r = r1 * r1;
        if (len <= r) {
            return true;
        }
        return false;
    }
}
