package custom.code_2022_08;

/**
 * @ClassName LeetCode_498
 * @Author Duys
 * @Description
 * @Date 2022/8/17 13:59
 **/
// 498. 对角线遍历
public class LeetCode_498 {

    // 1  2  3  13
    // 4  5  6  14
    // 7  8  9  15
    // 10 11 12 16

    // 1 2 3
    // 3 4 4
    // n=3 m=2

    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] ans = new int[m * n];
        int index = 0;
        for (int i = 0; i < m + n - 1; i++) {
            // 奇数行从右上往左下
            if (i % 2 == 1) {
                // 如果当前的对角线是小于列的
                int x = i < m ? 0 : i - m + 1;
                int y = i < m ? i : m - 1;
                while (x < n && y >= 0) {
                    ans[index++] = mat[x++][y--];
                }
            }
            // 偶数行从左下往右上
            else {
                // 当前对角线都已经大于了行了。
                int x = i < n ? i : n - 1;
                int y = i < n ? 0 : i - n + 1;
                while (x >= 0 && y < m) {
                    ans[index++] = mat[x--][y++];
                }
            }
        }
        return ans;
    }
}
