package duys.class_08_24;

/**
 * @ClassName RotateMatrix_01
 * @Author Duys
 * @Description TODO
 * @Date 2021/8/25 15:13
 **/
public class RotateMatrix_01 {
    /**
     * 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子
     * a  b  c		    g  d  a
     * d  e  f			h  e  b
     * g  h  i			i  f  c
     * <p>
     * n阶矩阵 那么关键的几个位置(0,0),(n-1,n-1) 然后开始点的坐标++，结束点的坐标-- 这两个点每次同步操作就是一个圈
     */

    // 矩阵存在圈的说法(0,0)
    public static void rotate(int[][] matrix) {

        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix.length - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    public static void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
        int tmp = 0;
        for (int i = 0; i < d - b; i++) {
            // 第i层的第一个
            tmp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = tmp;
        }
    }
}
