package duys_code.day_33;

/**
 * @ClassName Code_07_251_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/flatten-2d-vector/
 * @Date 2021/12/6 14:30
 **/
public class Code_07_251_LeetCode {
    // 一个二维数组的迭代
    public static class Vector2D {
        private int[][] matrix;
        private int row;
        private int col;
        // 当前光标来到的位置的数，是否被使用过了
        private boolean curUse;

        public Vector2D(int[][] v) {
            matrix = v;
            row = 0;
            col = -1;
            curUse = true;
            hasNext();
        }

        public int next() {
            int ans = matrix[row][col];
            curUse = true;
            hasNext();
            return ans;
        }

        public boolean hasNext() {
            if (row == matrix.length) {
                return false;
            }
            // 当前位置的数，一直没有被使用，那么防止平凡来移动光标
            if (!curUse) {
                return true;
            }
            //
            if (col < matrix[row].length - 1) {
                col++;
            } else { // 换行
                col = 0;
                // 不越界，且一定要有数的时候
                while (row < matrix.length && matrix[row].length == 0) {
                    row++;
                }
            }
            if (row != matrix.length) {
                curUse = false;
                return true;
            } else {
                return false;
            }
        }
    }
}
