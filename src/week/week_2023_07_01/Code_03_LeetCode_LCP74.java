package week.week_2023_07_01;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code_03_LeetCode_LCP74
 * @date 2023年07月06日
 */
// LCP 74. 最强祝福力场
// https://leetcode.cn/problems/xepqZ5/
public class Code_03_LeetCode_LCP74 {
    // 离散化+二维差分

    // 离散化的原因是由于坐标点和边长可能会导致出现0.5的点，不方便参与计算
    public static int fieldOfGreatestBlessing(int[][] fields) {
        int n = fields.length;
        // 坐标扩大一倍，不影响整体计算流程
        long[] xs = new long[n << 1];
        long[] ys = new long[n << 1];
        for (int i = 0, p = 0, k = 0; i < n; i++) {
            long x = fields[i][0];
            long y = fields[i][1];
            long r = fields[i][2];
            xs[k++] = (x << 1) - r;
            xs[k++] = (x << 1) + r;
            ys[p++] = (y << 1) - r;
            ys[p++] = (y << 1) + r;
        }
        int sizex = sort(xs);
        int sizey = sort(ys);
        // 差分的数据，扩展2行和2列
        int[][] diff = new int[sizex + 2][sizey + 2];
        for (int i = 0, a, b, c, d; i < n; i++) {
            long x = fields[i][0];
            long y = fields[i][1];
            long r = fields[i][2];
            a = rank(xs, (x << 1) - r, sizex);
            b = rank(ys, (y << 1) - r, sizey);
            c = rank(xs, (x << 1) + r, sizex);
            d = rank(ys, (y << 1) + r, sizey);
            set(diff, a, b, c, d);
        }
        int ans = 0;
        for (int i = 1; i < diff.length; i++) {
            for (int j = 1; j < diff[0].length; j++) {
                // 当前位置等于左边+上边 - 左上
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                ans = Math.max(ans, diff[i][j]);
            }
        }
        return ans;
    }

    public static int sort(long[] arr) {
        Arrays.sort(arr);
        int size = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[size - 1]) {
                arr[size++] = arr[i];
            }
        }
        return size;
    }

    public static int rank(long[] arr, long v, int size) {
        int l = 0, r = size - 1, m = 0, ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] >= v) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans + 1;
    }


    // 二维差分
    public static void set(int[][] diff, int a, int b, int c, int d) {
        diff[a][b] += 1;
        diff[c + 1][d + 1] += 1;
        diff[c + 1][b] -= 1;
        diff[a][d + 1] -= 1;
    }
}
