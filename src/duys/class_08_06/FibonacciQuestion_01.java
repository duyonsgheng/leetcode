package duys.class_08_06;

/**
 * @ClassName FibonacciQuestion_01
 * @Author Duys
 * @Description TODO
 * @Date 2021/8/6 15:20
 **/
public class FibonacciQuestion_01 {
    /**
     * 题意：
     *  第一年农场有1只成熟的母牛A，往后的每年：
     *
     * 1）每一只成熟的母牛都会生一只母牛
     *
     * 2）每一只新出生的母牛都在出生的第三年成熟
     *
     * 3）每一只母牛永远不会死
     *
     * 返回N年后牛的数量
     */

    /**
     * 1  2  3  4  5  6
     * A  A  A  A  A  A
     *    B  B  B  B  B
     *       C  C  C  C
     *          D  D  D
     *             E  E
     *             G  G
     *                F
     *                H
     *                I
     *
     * F(n) = F(n-1)+F(n-3)
     * 三阶的 满足
     * Fn Fn-1 F n-2 = F3 F2 F1 * 一个三阶矩阵的 n-3次方
     *  解答出行列式对应的矩阵
     * | 1  1  0 |
     * | 0  0  1 |
     * | 1  0  0 |
     */
    // 求第n项的值
    public static int fibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // [ 1 ,1 ]
        // [ 1, 0 ]
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int n) {
        int res[][] = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        // 算矩阵的n次方
        int[][] t = m;// 矩阵1次方
        // 比如现在的p = 3  0000.....000101
        // 每次右移一位
        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0) {
                // 如果末尾是1，说明本次需要进行计算到结果中
                res = muliMatrix(res, t);
            }
            // 否则，就自己跟自己玩儿
            t = muliMatrix(t, t);
        }
        return res;
    }

    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] = m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
}
