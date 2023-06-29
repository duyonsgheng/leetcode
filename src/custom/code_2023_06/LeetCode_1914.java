package custom.code_2023_06;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1914
 * @date 2023年06月29日
 */
// 1914. 循环轮转矩阵
// https://leetcode.cn/problems/cyclically-rotating-a-grid/
public class LeetCode_1914 {
    // 就是一个枚举
    // 枚举每一层
    public int[][] rotateGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int leven = Math.min(n / 2, m / 2);
        // 当前层
        for (int l = 0; l < leven; l++) {
            // 当前层一共多少数据
            int nodeNum = 2 * (m + n - 4 * (l + 1) + 2);
            // 如果旋转的次数正好是圈的整数倍，那么又回到原点
            if (k % nodeNum == 0) {
                continue;
            }
            // 看看需要旋转几次
            int num = k % nodeNum;
            for (int i = 0; i < num; i++) {
                int flag = 0; // 控制方向
                int head = grid[l][l]; // 把第一个位置的数保存起来
                // 起始位置
                int col = l;
                int row = l;
                // 所有的元素都需要移动
                for (int step = 0; step < nodeNum; step++) {
                    switch (flag) {
                        case 0: // 向右
                            grid[row][col] = grid[row][col + 1];
                            if (col == m - l - 2) {
                                flag = 1; // 需要向下了
                            }
                            col++;
                            break;
                        case 1:// 向下
                            grid[row][col] = grid[row + 1][col];
                            if (row == n - l - 2) {
                                flag = 2;// 需要向左了
                            }
                            row++;
                            break;
                        case 2: // 向左
                            grid[row][col] = grid[row][col - 1];
                            if (col == l + 1) {
                                flag = 3; // 需要向上了
                            }
                            col--;
                            break;
                        case 3: // 向上
                            // 如果转到了最后一个位置了，需要把头设置上
                            if (row - 1 == l && col == l) {
                                grid[row][col] = head;
                            } else
                                grid[row][col] = grid[row - 1][col];
                            if (row == l + 1) {
                                flag = 0; // 需要到下一层去了
                            }
                            row--;
                            break;
                    }
                }
            }
        }
        return grid;
    }
}
