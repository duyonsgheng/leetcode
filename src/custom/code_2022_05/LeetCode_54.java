package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_54
 * @Author Duys
 * @Description
 * @Date 2022/5/6 10:58
 **/
// 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
public class LeetCode_54 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        // 一圈一圈的输出
        // 左上角是 top left
        // 右上角是 top right
        // 左下角是 but left
        // 右下角是 but right
        //     top left     top right
        //          1    2   3
        //          2    3   4
        //     but left     but right
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
            return ans;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int top = 0, left = 0;
        int but = rows - 1, right = cols - 1;
        while (left <= right && top <= but) {
            // 4条边
            // 上边
            for (int col = left; col <= right; col++) {
                ans.add(matrix[top][col]);
            }
            // 右边
            for (int row = top + 1; row <= but; row++) {
                ans.add(matrix[row][right]);
            }
            // 防止只剩下一行的时候
            if (left < right && top < but) {
                // 下边
                for (int col = right - 1; col > left; col--) {
                    ans.add(matrix[but][col]);
                }
                // 左边
                for (int row = but; row > top; row--) {
                    ans.add(matrix[row][left]);
                }
            }
            left++;
            top++;
            but--;
            right--;
        }
        return ans;
    }
}
