package custom.code_2022_09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_01_08
 * @Author Duys
 * @Description
 * @Date 2022/9/30 8:53
 **/
// 面试题 01.08. 零矩阵
// https://leetcode.cn/problems/zero-matrix-lcci/
// 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
public class LeetCode_01_08 {

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
            return;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 0) {
                    continue;
                }
                if (!map.containsKey(i)) {
                    map.put(i, new HashSet<>());
                }
                map.get(i).add(j);
            }
        }
        for (int row : map.keySet()) {
            Arrays.fill(matrix[row], 0);
            for (int col : map.get(row)) {
                for (int i = 0; i < n; i++) {
                    matrix[i][col] = 0;
                }
            }
        }
    }

}
