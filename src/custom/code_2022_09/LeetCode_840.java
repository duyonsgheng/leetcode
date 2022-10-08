package custom.code_2022_09;

/**
 * @ClassName LeetCode_840
 * @Author Duys
 * @Description
 * @Date 2022/9/28 11:26
 **/
// 840. 矩阵中的幻方
public class LeetCode_840 {

    // 就枚举每一个矩阵
    public int numMagicSquaresInside1(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0] == null || grid[0].length < 3) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        // 每一个矩阵的开头位置
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {
                // 检查当前的
                if (grid[i + 1][j + 1] != 5) {
                    continue;
                }
                if (magic1(new int[]{
                        grid[i][j], grid[i][j + 1], grid[i][j + 2],
                        grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2],
                        grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2]})) {
                    ans++;
                }
            }
        }


        return ans;
    }

    public boolean magic1(int[] vals) {
        int[] count = new int[16];
        for (int v : vals) count[v]++;
        for (int v = 1; v <= 9; ++v)
            if (count[v] != 1)
                return false;

        return (vals[0] + vals[1] + vals[2] == 15 &&
                vals[3] + vals[4] + vals[5] == 15 &&
                vals[6] + vals[7] + vals[8] == 15 &&
                vals[0] + vals[3] + vals[6] == 15 &&
                vals[1] + vals[4] + vals[7] == 15 &&
                vals[2] + vals[5] + vals[8] == 15 &&
                vals[0] + vals[4] + vals[8] == 15 &&
                vals[2] + vals[4] + vals[6] == 15);
    }

    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i <= n - 3; ++i)
            for (int j = 0; j <= m - 3; ++j) {
                if (grid[i + 1][j + 1] != 5) continue;  // optional skip
                if (magic(new int[]{grid[i][j], grid[i][j + 1], grid[i][j + 2],
                        grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2],
                        grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2]}))
                    ans++;
            }

        return ans;
    }

    public boolean magic(int[] vals) {
        int[] count = new int[16];
        for (int v : vals) count[v]++;
        for (int v = 1; v <= 9; ++v)
            if (count[v] != 1)
                return false;

        return (vals[0] + vals[1] + vals[2] == 15 &&
                vals[3] + vals[4] + vals[5] == 15 &&
                vals[6] + vals[7] + vals[8] == 15 &&
                vals[0] + vals[3] + vals[6] == 15 &&
                vals[1] + vals[4] + vals[7] == 15 &&
                vals[2] + vals[5] + vals[8] == 15 &&
                vals[0] + vals[4] + vals[8] == 15 &&
                vals[2] + vals[4] + vals[6] == 15);
    }
}
