package custom.code_2023_06;

/**
 * @ClassName LeetCode_1901
 * @Author Duys
 * @Description
 * @Date 2023/6/26 9:47
 **/
// 1901. 寻找峰值 II
public class LeetCode_1901 {
    // 我们把每一行的最大值拿出来，然后再这些最大值中找最大的
    // 要想达到满足题意的 n(logm) 或者 m(logn)，那么必须使用二分
    // 每一次二分 然后看看当前行的上下行的情况
    public int[] findPeakGrid(int[][] mat) {
        int l = 0, r = mat.length - 1;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            int curMaxCol = findMax(mat, m);
            // m-1行的最大
            int max1 = m - 1 >= 0 ? mat[m - 1][findMax(mat, m - 1)] : -1;
            // m行的最大
            int max2 = mat[m][curMaxCol];
            // m+1行的最大
            int max3 = m + 1 < mat.length ? mat[m + 1][findMax(mat, m + 1)] : -1;
            if (max2 > max1 && max2 > max3) {
                return new int[]{m, curMaxCol};
            }
            // 如果m-1行的更大，就往前搜索
            if (max1 > max3) {
                r = m - 1;
            } else l = m + 1;
        }
        return new int[]{l, findMax(mat, l)};
    }

    // 返回最大的那一列
    public int findMax(int[][] arr, int row) {
        int maxCol = 0, n = arr[row].length;
        for (int i = 0; i < n; i++) {
            if (arr[row][i] >= arr[row][maxCol]) {
                maxCol = i;
            }
        }
        return maxCol;
    }
}
