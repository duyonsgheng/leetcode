package custom.code_2022_05;

/**
 * @ClassName LeetCode_63
 * @Author Duys
 * @Description
 * @Date 2022/5/6 17:47
 **/
//一个机器人位于一个m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
//机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
//现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
//网格中的障碍物和空位置分别用 1 和 0 来表示。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/unique-paths-ii
public class LeetCode_63 {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length <= 0
                || obstacleGrid[0] == null || obstacleGrid[0].length <= 0) {
            return 0;
        }
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if (obstacleGrid[n - 1][m - 1] == 1) {
            return 0;
        }
        // dp
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] != 0) {
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                // 从上边能不能到达
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 1}, {0, 0}};
        System.out.println(uniquePathsWithObstacles(arr));
    }
}
