package custom.code_2023_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1992
 * @date 2023年07月24日
 */
// 1992. 找到所有的农场组
// https://leetcode.cn/problems/find-all-groups-of-farmland/
public class LeetCode_1992 {
    // 就暴力来吧
    public int[][] findFarmland(int[][] land) {
        List<int[]> ans = new ArrayList<>();
        int n = land.length, m = land[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 0) {
                    continue;
                }
                // 向下，向右
                int row = i, col = j;
                while (row + 1 < n && land[row + 1][j] == 1) row++;
                while (col + 1 < m && land[i][col + 1] == 1) col++;
                ans.add(new int[]{i, j, row, col});
                // 把这个区域全部改成0
                for (int r = i; r <= row; r++) {
                    for (int c = j; c <= col; c++) {
                        land[r][c] = 0;
                    }
                }
            }
        }
        return ans.toArray(new int[0][0]);
    }
}
