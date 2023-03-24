package custom.code_2023_02;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1476
 * @Author Duys
 * @Description
 * @Date 2023/2/1 14:57
 **/
// 1476. 子矩形查询
public class LeetCode_1476 {
    class SubrectangleQueries {
        int[][] arr;
        List<int[]> update;
        List<Integer> value;

        public SubrectangleQueries(int[][] rectangle) {
            arr = rectangle;
            update = new ArrayList<>();
            value = new ArrayList<>();
        }

        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            update.add(new int[]{row1, col1, row2, col2});
            value.add(newValue);
        }

        public int getValue(int row, int col) {
            // 倒叙查询
            for (int i = update.size() - 1; i >= 0; i--) {
                int[] cur = update.get(i);
                // 如果在这个区域。那么就返回这个区域的更新值
                if (cur[0] <= row && row <= cur[2] && col >= cur[1] && col <= cur[3]) {
                    return value.get(i);
                }
            }
            return arr[row][col];
        }
    }
}
