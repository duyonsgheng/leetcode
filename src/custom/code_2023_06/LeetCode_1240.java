package custom.code_2023_06;

/**
 * @ClassName LeetCode_1240
 * @Author Duys
 * @Description
 * @Date 2023/6/8 9:17
 **/
// 1240. 铺瓷砖
public class LeetCode_1240 {
    // 就以每一个点为顶点开始尝试
    int ans = 0;
    int N, M;

    public int tilingRectangle(int n, int m) {
        N = n;
        M = m;
        ans = Math.max(n, m);
        boolean[][] visited = new boolean[n][m];
        dfs(0, 0, visited, 0);
        return ans;
    }

    public void dfs(int x, int y, boolean[][] visited, int cnt) {
        if (cnt >= ans) {
            return;
        }
        if (x >= N) {
            ans = cnt;
            return;
        }
        // 这一列到头了，下一行
        if (y >= M) {
            dfs(x + 1, 0, visited, cnt);
            return;
        }
        // 当前位置已经被覆盖了
        if (visited[x][y]) {
            dfs(x, y + 1, visited, cnt);
            return;
        }
        // 枚举剩下的长度
        for (int k = Math.min(N - x, M - y); k >= 1 && check(visited, x, y, k); k--) {
            fill(visited, x, y, k, true);
            // 已经有一个正方形了
            dfs(x, y + k, visited, cnt + 1);
            // 恢复现场
            fill(visited, x, y, k, false);
        }
    }

    public boolean check(boolean[][] visited, int x, int y, int k) {
        // 以(x,y)为左上顶点，看看当前长度为k的情况下，能不能填满整个正方形
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (visited[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fill(boolean[][] visited, int x, int y, int k, boolean v) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                visited[x + i][y + j] = v;
            }
        }
    }
}
