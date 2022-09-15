package custom.code_2022_07;

/**
 * @ClassName LeetCode_304
 * @Author Duys
 * @Description
 * @Date 2022/7/14 15:36
 **/
public class LeetCode_304 {

    public static class NumMatrix {
        // 保存每一行的前缀和
        long[][] rowSums;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix[0] == null) {
                return;
            }
            build(matrix);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (rowSums == null) {
                return Integer.MIN_VALUE;
            }
            if (row1 > row2 || col1 > col2) {
                return Integer.MIN_VALUE;
            }
            long sum = 0;
            for (int i = row1; i <= row2; i++) {
                long[] cur = rowSums[i];
                sum += cur[col2] - (col1 > 0 ? cur[col1 - 1] : 0);
            }
            return (int) sum;
        }

        private void build(int[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            rowSums = new long[n][m];
            for (int i = 0; i < n; i++) {
                long[] sums = new long[m];
                int[] cur = matrix[i];
                long sum = 0;
                for (int j = 0; j < m; j++) {
                    sum += cur[j];
                    sums[j] = sum;
                }
                rowSums[i] = sums;
            }
        }
    }

    public static void main(String[] args) {
        // ["NumMatrix","sumRegion","sumRegion","sumRegion"]
        //[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
        int[][] arr = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix matrix = new NumMatrix(arr);
        System.out.println(matrix.sumRegion(2, 1, 4, 3));
    }

}
