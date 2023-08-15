package custom.code_2023_08;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Mr.Du
 * @ClassName LeetCode_980
 * @date 2023年08月04日
 */
// 980. 不同路径 III
// https://leetcode.cn/problems/unique-paths-iii/description/
public class LeetCode_980 {
    int[][] dist = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int uniquePathsIII(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        // 开始和结束都抓出来
        int cnt = 0;
        int sr = 0;
        int sc = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    cnt++;
                } else if (grid[i][j] == 1) {
                    cnt++;
                    sr = i;
                    sc = j;
                }
            }
        }
        return dfs(grid, n, m, sr, sc, cnt);
    }

    public int dfs(int[][] arr, int n, int m, int sr, int sc, int cnt) {
        if (arr[sr][sc] == 2) {
            return cnt == 0 ? 1 : 0;
        }
        int cur = arr[sr][sc];
        // 确保当前走的这条路其他的都没走过。标准的dfs流程
        arr[sr][sc] = -1;
        int ans = 0;
        for (int[] di : dist) {
            int nr = sr + di[0];
            int nc = sc + di[1];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && (arr[nr][nc] == 0 || arr[nr][nc] == 2)) {
                ans += dfs(arr, n, m, nr, nc, cnt - 1);
            }
        }
        arr[sr][sc] = cur;
        return ans;
    }

    public static void main(String[] args) {
        LeetCode_980 lc = new LeetCode_980();
        int i = lc.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}});
        System.out.println(i);
    }
}
