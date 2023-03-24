package duys_code.day_03;

/**
 * @ClassName Code_03_LeetCode_1139
 * @Author Duys
 * @Description 力扣原题: https://leetcode-cn.com/problems/largest-1-bordered-square/
 * @Date 2021/9/18 17:13
 **/
public class Code_03_LeetCode_1139 {
    /**
     * 给定一个只有0和1组成的二维数组
     * 返回边框全是1的最大正方形面积
     * 思路：
     * 再一个N*N的矩阵中，总共有N^2个点，那么边长是 1~N,总共代价就是N^3，这个是不能免的代价，
     * 那么我们想办法找到从左上到右下的所有位置，从当前位置出发，右边有多少个连续的1，和下面有多少个连续的1，都求出来
     */
    public static int largest1BorderedSquare(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBordMap(m, right, down);
        for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size * size;
            }
        }
        return 0;
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        // 这里是算正方形，那么只能按照 right 或者down来。不然会出现问题
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size &&
                        // 意思是列不动，行去当前行+size的地方看看他的右边是不是也是满足的
                        right[i + size - 1][j] >= size &&
                        // 行不动，列去当前列+size的地方看看是不是满足的，就是看看四条边
                        down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void setBordMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length; // 行
        int c = m[0].length; // 列

        // 最后一行的，最右一列
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        // 最后一列的，列固定，算不同的行
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                down[i][c - 1] = down[i + 1][c - 1] + 1;
                right[i][c - 1] = 1;
            }
        }
        // 最后一行的，行固定，列从右到左
        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                down[r - 1][i] = 1;
                right[r - 1][i] = right[r - 1][i + 1] + 1;
            }
        }

        // 普遍的位置
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    down[i][j] = down[i + 1][j] + 1;
                    right[i][j] = right[i][j + 1] + 1;
                }
            }
        }
    }
}
