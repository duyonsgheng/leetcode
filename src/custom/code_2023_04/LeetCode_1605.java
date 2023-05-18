package custom.code_2023_04;

/**
 * @ClassName LeetCode_1605
 * @Author Duys
 * @Description
 * @Date 2023/4/18 10:40
 **/
// 1605. 给定行和列的和求可行矩阵
public class LeetCode_1605 {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rowSize = rowSum.length;
        int colSize = colSum.length;
        // 初始态全部为0，然后来填了
        int[][] ans = new int[rowSize][colSize];
        int i = 0, j = 0;
        while (i < rowSize && j < colSize) {
            // 先选择最小的，每个位置都定义成最小的
            int cur = Math.min(rowSum[i], colSum[j]);
            ans[i][j] = cur;
            rowSum[i] -= cur;
            colSum[j] -= cur;
            // 如果当前行和为0了，则需要换行了
            if (rowSum[i] == 0) {
                i++;
            }
            // 当前列没了，需要换列
            if (colSum[j] == 0) {
                j++;
            }
        }
        return ans;
    }
}
