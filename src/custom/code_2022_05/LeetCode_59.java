package custom.code_2022_05;

/**
 * @ClassName LeetCode_59
 * @Author Duys
 * @Description
 * @Date 2022/5/6 13:44
 **/
//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
public class LeetCode_59 {

    public static int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[][]{};
        }
        int[][] ma = new int[n][n];
        if (n == 1) {
            ma[0][0] = 1;
            return ma;
        }
        process(ma, n);
        return ma;
    }

    // 一圈一圈的填
    public static void process(int[][] ma, int n) {
        // 定义4个点
        int left = 0, top = 0, but = n - 1, right = n - 1;
        int index = 1;
        while (left <= right && top <= but) {
            // 上边
            for (int col = left; col <= right; col++) {
                ma[top][col] = index++;
            }
            // 右边
            for (int row = top + 1; row <= but; row++) {
                ma[row][right] = index++;
            }
            if (left < right && top < but) {
                for (int col = right - 1; col >= left; col--) {
                    ma[but][col] = index++;
                }
                for (int row = but-1; row > top; row--) {
                    ma[row][left] = index++;
                }
            }
            top++;
            left++;
            but--;
            right--;
        }
    }

    public static void main(String[] args) {
        generateMatrix(4);
    }
}
