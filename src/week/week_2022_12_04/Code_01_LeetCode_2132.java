package week.week_2022_12_04;

/**
 * @ClassName Code_01_LeetCode_2132
 * @Author Duys
 * @Description
 * @Date 2022/12/29 9:58
 **/
// 2132. 用邮票贴满网格图
public class Code_01_LeetCode_2132 {
    // 一维差分解决：先批量操作，最后把操作后得结果返回，利用的前缀和的信息
    //  二维差分解决本题，生成二维的前缀和信息，然后查询再二维中某一个范围是否符合前缀和是0，然后利用一维差分的原理
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int n = grid.length;
        int m = grid[0].length;
        // 坐上累加和数组
        // 多准备一行和一列，防止再处理过程中处理越界情况
        int[][] leftSum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                leftSum[i + 1][j + 1] = grid[i][j];
            }
        }
        build(leftSum);
        // 差分数组
        // 多准备两行两列
        int[][] diff = new int[n + 2][m + 2];
        // 枚举每一个为0的位置作为邮票的左上角
        for (int a = 1, c = a + stampHeight - 1; c <= n; a++, c++) {
            for (int b = 1, d = b + stampWidth - 1; d <= m; b++, d++) {
                if (empty(leftSum, a, b, c, d)) {
                    set(diff, a, b, c, d);
                }
            }
        }
        build(diff);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void build(int[][] leftSum) {
        for (int i = 1; i < leftSum.length; i++) {
            for (int j = 1; j < leftSum[0].length; j++) {
                // 左边的+上边的 多加了一块，减去多加的那一块
                leftSum[i][j] += leftSum[i - 1][j] + leftSum[i][j - 1] - leftSum[i - 1][j - 1];
            }
        }
    }

    //
    //   a,b
    //
    //      c,d
    public boolean empty(int[][] sum, int a, int b, int c, int d) {
        return sum[c][d] - sum[c][b - 1] - sum[a - 1][d] + sum[a - 1][b - 1] == 0;
    }

    // 给当前区域内。平衡左上累加和
    public void set(int[][] diff, int a, int b, int c, int d) {
        diff[a][b] += 1;
        diff[c + 1][d + 1] += 1;
        diff[c + 1][b] -= 1;
        diff[a][d + 1] -= 1;
    }
}
