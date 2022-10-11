package custom.code_2022_10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_885
 * @Author Duys
 * @Description
 * @Date 2022/10/11 10:32
 **/
// 885. 螺旋矩阵 III
// 解法 就是硬模拟，从上下左右四个方向走，直到跑完整个矩阵
public class LeetCode_885 {
    // 再 (0,0)位置，开始朝向东
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        // 向下，向左，向上，向右  形成整个顺时针的走势
        int all = rows * cols; // 所有的点，全部收集完就结束
        int[][] ans = new int[all][2];
        int cnt = 0;
        int l = cStart;
        int r = cStart;
        int up = rStart;
        int down = rStart;
        while (cnt < all) {
            // 先向右走
            for (int i = l; i <= r; i++) {
                // 从(up,l)开始向右 走到(up,r)
                if (ok(up, i, rows, cols)) {
                    ans[cnt] = new int[]{up, i};
                    cnt++;
                }
            }
            r++;
            // 向下走
            for (int i = up; i <= down; i++) {
                // 从(up,r)开始向下 走到(down,r)
                if (ok(i, r, rows, cols)) {
                    ans[cnt] = new int[]{i, r};
                    cnt++;
                }
            }
            down++;

            // 向左走
            for (int i = r; i >= l; i--) {
                // 从(down,r)开始 向左走 走到(down,l)
                if (ok(down, i, rows, cols)) {
                    ans[cnt] = new int[]{down, i};
                    cnt++;
                }
            }
            l--;
            // 向上走
            for (int i = down; i >= up; i--) {
                // 从(down,l)开始 向上走 走到(up,l)
                if (ok(i, l, rows, cols)) {
                    ans[cnt] = new int[]{i, l};
                    cnt++;
                }
            }
            up--;
        }
        return ans;
    }

    public boolean ok(int row, int col, int m, int n) {
        if (row >= 0 && row < m && col >= 0 && col < n) {
            return true;
        }
        return false;
    }
}
