package week.week_2022_06_01;

/**
 * @ClassName LeetCode_1706
 * @Author Duys
 * @Description
 * @Date 2022/6/9 13:12
 **/
// 用一个大小为 m x n 的二维网格 grid 表示一个箱子
// 你有 n 颗球。箱子的顶部和底部都是开着的。
// 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，
// 可以将球导向左侧或者右侧。
// 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
// 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
// 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。
// 如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
// 返回一个大小为 n 的数组 answer ，
// 其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，
// 如果球卡在盒子里，则返回 -1
// https://leetcode.cn/problems/where-will-the-ball-fall/submissions/
public class Code_01_LeetCode_1706 {
    // 比如当前来到 i j位置
    // 我能去的地方是 i j-1 或者i j+1的位置
    // 首先我去的地方不能越界，否则-1
    // 我能去地方和我当前的是保持平衡的
    public static int[] findBall(int[][] grid) {
        int n = grid.length; // 行
        int m = grid[0].length; // 列
        int[] ans = new int[m];
        // 看看小球从 第0行每一列往下
        for (int col = 0; col < m; col++) {
            int i = 0;
            int j = col;
            // 小球往下落
            while (i < n) {
                // 正好，grid[i][j] 值为-1或1
                int nextRow = j + grid[i][j];
                if (nextRow < 0 || nextRow == m || grid[i][j] != grid[i][nextRow]) {
                    ans[col] = -1;
                    break;
                }
                i++;
                j = nextRow;
            }
            if (i == n) {
                ans[col] = j;
            }
        }
        return ans;
    }
}
