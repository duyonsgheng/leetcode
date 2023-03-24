package custom.code_2023_01;

/**
 * @ClassName LeetCode_2319
 * @Author Duys
 * @Description
 * @Date 2023/1/31 9:55
 **/
// 2319. 判断矩阵是否是一个 X 矩阵
public class LeetCode_2319 {

    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        // 对角线上不是0 左对角线 是i==j 右对角线是 i+j =n-1
        // 其他的都是0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || (i + j) == n - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
