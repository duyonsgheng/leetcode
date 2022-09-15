package duys_code.day_17;

/**
 * @ClassName Code_01_FindNum
 * @Author Duys
 * @Description
 * @Date 2021/10/28 15:18
 **/
public class Code_01_FindNum {
    /**
     * 给定一个每一行有序、每一列也有序，整体可能无序的二维数组
     * 再给定一个数num，
     * 返回二维数组中有没有num这个数
     */
    /**
     * 遇到这种题。我们得想一个问题。从哪里出发去找。可以利用单调性
     * 每一行都有序，意思是每一行从左往右在变大
     * 每一列都有序，意思是每一列从上往下在变大
     * 可能性1：我们从[0][0]位置找，那么我们不知道需要排除哪些
     * 可能性2：我们从[N-1][M-1]位置开始找，也不知道排除哪些
     * 可能性3：我们从[0][N-1]位置开始找，那么如果当前数比num大，那么我就往左走，如果当前数比num小，我就往下走 可行。可以排除很多位置
     * 可能性4：我们从[N-1][0]位置开始找，那么如果当前数比num大，那么我就往右走，如果当前数比num小，我就往上走 可行。可以排除很多位置
     */
    public static boolean isContains(int[][] matrix, int K) {
        int strX = 0;
        int strY = matrix[0].length - 1;
        while (strX <= matrix.length - 1 && strY >= 0) {
            if (matrix[strX][strY] > K) {
                strY--;
            } else if (matrix[strX][strY] < K) {
                strX++;
            } else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6}, // 0
                {10, 12, 13, 15, 16, 17, 18}, // 1
                {23, 24, 25, 26, 27, 28, 29}, // 2
                {44, 45, 46, 47, 48, 49, 50}, // 3
                {65, 66, 67, 68, 69, 70, 71}, // 4
                {96, 97, 98, 99, 100, 111, 122}, // 5
                {166, 176, 186, 187, 190, 195, 200}, // 6
                {233, 243, 321, 341, 356, 370, 380} // 7
        };
        int K = 233;
        System.out.println(isContains(matrix, K));
    }
}
