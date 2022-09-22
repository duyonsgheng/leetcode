package custom.code_2022_09;

/**
 * @ClassName LeetCode_807
 * @Author Duys
 * @Description
 * @Date 2022/9/22 15:43
 **/
// 807. 保持城市天际线
public class LeetCode_807 {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        // 列出每一行的最大，每一列的最大
        int[] rowMax = new int[n];
        int[] cloMax = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                cloMax[j] = Math.max(cloMax[j], grid[i][j]);
            }
        }
        int ans = 0;
        // 每到一个位置，找当前列的最大，和行的最大，取一个小的
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += Math.min(rowMax[i], cloMax[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
