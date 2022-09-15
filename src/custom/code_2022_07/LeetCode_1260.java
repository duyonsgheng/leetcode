package custom.code_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1260
 * @Author Duys
 * @Description
 * @Date 2022/7/20 8:48
 **/
// 1260. 二维网格迁移
public class LeetCode_1260 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            ret.add(row);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index1 = (i * n + j + k) % (m * n);
                ret.get(index1 / n).set(index1 % n, grid[i][j]);
            }
        }
        return ret;
    }

    public static List<List<Integer>> shiftGrid1(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        // 转成一维数组
        int[] arr = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i * n + j] = grid[i][j];
            }
        }
        int len = n * m - 1;
        // i j号元素去
        int[] arr_ = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            arr_[index(len, i, k)] = arr[i];
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans.add(new ArrayList<>(m));
        }
        for (int i = 0; i < arr_.length; i++) {
            int row = i / n;
            int col = i % m;
            ans.get(row).add(col, arr_[i]);
        }
        return ans;
    }

    public static int index(int r, int index, int k) {
        int tar = index + k;
        if (tar > r) {
            return tar % k;
        }
        return tar;
    }

    public static void main(String[] args) {
        int[][] arr = {{1}, {2}, {3}, {4}, {7}, {6}, {5}};//{{3, 8, 1, 9}, {19, 7, 2, 5}, {4, 6, 11, 10}, {12, 0, 21, 13}};
        int k = 23;
        shiftGrid1(arr, k);
    }
}
