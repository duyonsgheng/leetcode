package custom.code_2022_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_417
 * @Author Duys
 * @Description
 * @Date 2022/8/9 13:32
 **/
//417. 太平洋大西洋水流问题
public class LeetCode_417 {
    public int[][] dis = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        // 太平洋的
        for (int i = 0; i < m; i++) {
            dfs(0, i, pacific, heights);
        }
        for (int i = 1; i < n; i++) {
            dfs(i, 0, pacific, heights);
        }
        // 大西洋
        for (int i = 0; i < m - 1; i++) {
            dfs(n - 1, i, atlantic, heights);
        }
        for (int i = 0; i < n; i++) {
            dfs(i, m - 1, atlantic, heights);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (atlantic[i][j] && pacific[i][j]) {
                    List<Integer> res = new ArrayList<>();
                    res.add(i);
                    res.add(j);
                    ans.add(res);
                }
            }
        }
        return ans;
    }

    public void dfs(int row, int col, boolean[][] ok, int[][] arr) {
        if (ok[row][col]) {
            return;
        }
        ok[row][col] = true;
        // 四个方向去
        for (int[] cur : dis) {
            int curR = row + cur[0];
            int curC = col + cur[1];
            if (curR >= 0 && curC >= 0 && curR < arr.length && curC < arr[0].length && arr[curR][curC] >= arr[row][col]) {
                dfs(curR, curC, ok, arr);
            }
        }
    }
}
