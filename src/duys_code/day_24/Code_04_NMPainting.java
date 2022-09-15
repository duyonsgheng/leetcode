package duys_code.day_24;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_04_NMPar
 * @Author Duys
 * @Description
 * @Date 2021/11/15 14:33
 **/
public class Code_04_NMPainting {
    /**
     * N * M的棋盘（N和M是输入参数）
     * 每种颜色的格子数必须相同的
     * 上下左右的格子算相邻
     * 相邻格子染的颜色必须不同
     * 所有格子必须染色
     * 返回至少多少种颜色可以完成任务
     */
    // 有点类似高中数据的经典排列组合，就是根据具体的n和m来找规律
    public static int minColors(int N, int M) {
        // 首先不可能是只有1种颜色，最多也就是 n*m种颜色
        for (int c = 2; c < N * M; c++) {

            // 如果 N*M 都不是颜色的整数倍，那么肯定是不行的
            if ((N * M) % c != 0) {
                continue;
            }
            int[][] arr = new int[N][M];
            if (can(arr, N, M, c)) {
                return c;
            }
        }
        // 实在没有就返回 n*m
        return N * M;
    }

    // 矩阵是n*m的，只需要p种颜色，能不能达到要求
    public static boolean can(int[][] arr, int n, int m, int p) {
        int all = n * m;
        int every = all / p;// 每一种颜色有多少个格子
        List<Integer> rest = new ArrayList<>();
        rest.add(0);
        for (int i = 1; i <= p; i++) {
            rest.add(every);
        }
        return process(arr, n, m, p, 0, 0, rest);
    }

    public static boolean process(int[][] arr, int n, int m, int p, int row, int col, List<Integer> rest) {
        if (row == n) {
            return true;
        }
        if (col == m) {
            // 换行来
            return process(arr, n, m, p, row + 1, 0, rest);
        }
        // 从上往下填
        int left = col == 0 ? 0 : arr[row][col - 1];
        int up = row == 0 ? 0 : arr[row - 1][col];
        for (int color = 1; color <= p; color++) {
            if (left != color && up != color && rest.get(color) > 0) {
                int count = rest.get(color);// 还有多少格子
                rest.set(color, count - 1);
                arr[row][col] = color;
                // 当前位置填了其中一种，然后进行递归去
                // 只要有成功的，那么就能实现
                if (process(arr, n, m, p, row, col + 1, rest)) {
                    return true;
                }
                rest.set(color, count);
                arr[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // 根据代码16行的提示，打印出答案，看看是答案是哪个因子
        for (int N = 2; N < 10; N++) {
            for (int M = 2; M < 10; M++) {
                System.out.println("N   = " + N);
                System.out.println("M   = " + M);
                System.out.println("ans = " + minColors(N, M));
                System.out.println("===========");
            }
        }
        // 打印答案，分析可知，是N*M最小的质数因子，原因不明，也不重要
        // 反正打表法猜出来了
    }
}
