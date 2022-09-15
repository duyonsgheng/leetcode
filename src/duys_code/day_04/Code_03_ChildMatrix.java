package duys_code.day_04;

/**
 * @ClassName Code_03_ChildMatrix
 * @Author Duys
 * @Description 变种：https://leetcode-cn.com/problems/max-submatrix-lcci/
 * @Date 2021/9/22 15:07
 **/
public class Code_03_ChildMatrix {
    /**
     * 返回一个二维数组中，子矩阵最大累加和。----数组压缩技巧
     */

    /**
     * 暴力解答：
     * 1.矩形只包含第0行
     * 2.矩形只包含第0行，第1行
     * 3.矩形只包含第0，第1，第2
     * .。。。。。。。
     * 只包含第1行 ，2 ，3。。。
     * 。。。
     * 只包含 2  3  4.。。
     * 答案就在其中
     */

    /**
     * 解答：
     * 如果只有一行，那么就跟第二题一样了。也就是第0行开始。使用一个辅助数组，数组的长度，就是列数
     * 如果包含了多行，需要处理，多行，把每一列的数据相加，加到辅助数组的每一个位置去,然后就跟只有一行的求法一样了
     * ...
     * 使用列和行较小的哪一个作为平方项。也就是列数。
     */

    public static int maxSum(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        //行
        int N = matrix.length;
        // 列
        int M = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            // 辅助数组，就是把每一行和上一行对应的列的数相加
            int[] sup = new int[M];
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    sup[k] += matrix[j][k];
                }
                max = Math.max(max, maxSubArray(sup));
            }
        }
        return max;
    }

    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int max = arr[0];
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre = Math.max(arr[0], pre + arr[0]);
            max = Math.max(max, pre);
        }
        return max;
    }

    public static int[] getMaxMatrix(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return null;
        }
        //行
        int N = matrix.length;
        // 列
        int M = matrix[0].length;
        int cur = 0;
        int max = Integer.MIN_VALUE;
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < N; i++) {
            // 辅助数组，就是把每一行和上一行对应的列的数相加
            int[] sup = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    sup[k] += matrix[j][k];
                    cur += sup[k];
                    if (max < cur) {
                        max = cur;
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                    }
                    if (cur < 0) {
                        cur = 0;
                        begin = k + 1;
                    }
                }

            }
        }
        return new int[]{a, b, c, d};
    }
}
