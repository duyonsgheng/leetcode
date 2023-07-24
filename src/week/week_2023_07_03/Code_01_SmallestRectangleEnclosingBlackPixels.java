package week.week_2023_07_03;

/**
 * @author Mr.Du
 * @ClassName Code_01_SmallestRectangleEnclosingBlackPixels
 * @date 2023年07月20日
 */
// 图片在计算机处理中往往是使用二维矩阵来表示的
// 给你一个大小为 m x n 的二进制矩阵 image 表示一张黑白图片，0 代表白色像素，1 代表黑色像素
// 黑色像素相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素。像素点是水平或竖直方向连接的
// 给你两个整数 x 和 y 表示某一个黑色像素的位置
// 请你找出包含全部黑色像素的最小矩形（与坐标轴对齐），并返回该矩形的面积
// 你必须设计并实现一个时间复杂度低于 O(m*n) 的算法来解决此问题。
// 测试链接 : https://leetcode.cn/problems/smallest-rectangle-enclosing-black-pixels/
public class Code_01_SmallestRectangleEnclosingBlackPixels {
    // 给定(x,y)点，题目给出一定存在连续的1，然后可以用二分往上下，二分往左右
    public static int minArea(char[][] image, int x, int y) {
        int l = left(image, y);
        int r = right(image, y);
        int up = up(image, x, l, r);
        int down = down(image, x, l, r);
        return (r - l + 1) * (down - up + 1);
    }

    // 从col列往左
    public static int left(char[][] image, int col) {
        int l = 0, r = col - 1, m, ans = col;
        boolean find = false;
        while (l <= r) {
            m = (l + r) / 2;
            find = false;
            for (int i = 0; i < image.length; i++) {
                if (image[i][m] == '1') {
                    find = true;
                    break;
                }
            }
            if (find) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // 从col往右
    public static int right(char[][] image, int col) {
        int l = col + 1, r = image[0].length - 1, m, ans = col;
        boolean find = false;
        while (l <= r) {
            m = (l + r) / 2;
            find = false;
            for (int i = 0; i < image.length; i++) {
                if (image[i][m] == '1') {
                    find = true;
                    break;
                }
            }
            if (find) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static int up(char[][] image, int row, int l, int r) {
        int up = 0, down = row - 1, m, ans = row;
        boolean find = false;
        while (up <= down) {
            m = (up + down) / 2;
            find = false;
            // 每一行，每一行的搞
            for (int i = l; i <= r; i++) {
                if (image[m][i] == '1') {
                    find = true;
                    break;
                }
            }
            if (find) {
                ans = m;
                down = m - 1;
            } else {
                up = m + 1;
            }
        }
        return ans;
    }

    public static int down(char[][] image, int row, int l, int r) {
        int up = row + 1, down = image.length - 1, m, ans = row;
        boolean find = false;
        while (up <= down) {
            m = (up + down) / 2;
            find = false;
            // 每一行，每一行的搞
            for (int i = l; i <= r; i++) {
                if (image[m][i] == '1') {
                    find = true;
                    break;
                }
            }
            if (find) {
                ans = m;
                up = m + 1;
            } else {
                down = m - 1;
            }
        }
        return ans;
    }
}
