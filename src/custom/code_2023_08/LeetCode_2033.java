package custom.code_2023_08;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2033
 * @date 2023年08月01日
 */
// 2033. 获取单值网格的最小操作数
// https://leetcode.cn/problems/minimum-operations-to-make-a-uni-value-grid/
public class LeetCode_2033 {
    // 找中位数
    public int minOperations(int[][] grid, int x) {
        int n = grid.length;
        int m = grid[0].length;
        int[] arr = new int[n * m];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[idx++] = grid[i][j];
            }
        }
        Arrays.sort(arr);
        int i = arr[(n * m) / 2];
        int ans = 0;
        for (int a : arr) {
            int diff = Math.abs(a - i);
            if (diff % x != 0) {
                return -1;
            }
            ans += diff / x;
        }
        return ans;
    }
}
