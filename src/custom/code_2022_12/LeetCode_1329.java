package custom.code_2022_12;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @ClassName LeetCode_1329
 * @Author Duys
 * @Description
 * @Date 2022/12/12 14:26
 **/
// 1329. 将矩阵按对角线排序
public class LeetCode_1329 {

    public static int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        // 竖着来一次
        PriorityQueue<Integer> set = new PriorityQueue<>();
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 当前对角线
            int row = 0;
            int col = i;
            while (col < n && row < m) {
                points.add(new int[]{row, col});
                set.add(mat[row][col]);
                col++;
                row++;
            }
            for (int[] point : points) {
                mat[point[0]][point[1]] = set.poll();
            }
            // 排序，并且填好
            set.clear();
            points.clear();
        }
        // 横着来一次
        for (int i = 1; i < m; i++) {
            // 当前对角线
            int row = i;
            int col = 0;
            while (col < n && row < m) {
                points.add(new int[]{row, col});
                set.add(mat[row][col]);
                col++;
                row++;
            }
            for (int[] point : points) {
                mat[point[0]][point[1]] = set.poll();
            }
            // 排序，并且填好
            set.clear();
            points.clear();
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {93, 49, 54, 89, 100, 90, 63, 28, 46, 67},
                {24, 97, 48, 73, 62, 32, 44, 100, 24, 79},
                {81, 65, 45, 14, 79, 86, 45, 53, 68, 83},
                {29, 83, 12, 90, 39, 37, 4, 91, 62, 43},
                {87, 19, 42, 54, 30, 31, 92, 24, 44, 43},
                {97, 63, 90, 89, 38, 73, 60, 31, 20, 91},
                {93, 36, 83, 50, 27, 89, 27, 47, 80, 5},
                {8, 99, 39, 62, 24, 25, 50, 58, 67, 20},
                {84, 42, 21, 55, 92, 48, 84, 6, 79, 11}};
        int[][] ints = diagonalSort(mat);
        System.out.println();
    }
}
