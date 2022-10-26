package custom.code_2022_10;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_963
 * @Author Duys
 * @Description
 * @Date 2022/10/24 20:38
 **/
//
public class LeetCode_963 {
    // 枚举
    public double minAreaFreeRect(int[][] points) {
        double ans = Double.MAX_VALUE;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int m = j + 1; m < n; m++) {
                    for (int k = m + 1; k < n; k++) {
                        if (!ok(points[i], points[j], points[m], points[k])) {
                            continue;
                        }
                        // 找到最小的距离
                        long[] cur = new long[3];
                        cur[0] = line(points[i], points[j]);
                        cur[1] = line(points[i], points[m]);
                        cur[2] = line(points[i], points[k]);
                        Arrays.sort(cur);
                        ans = Math.min(ans, Math.sqrt(cur[0]) * Math.sqrt(cur[1]));
                    }
                }
            }
        }
        return ans == Double.MAX_VALUE ? 0 : ans;
    }

    // 验证边距离
    public boolean ok(int[] p1, int[] p2, int[] p3, int[] p4) {
        return line2line(p1, p2, p3, p4) && // 横边
                line2line(p1, p3, p2, p4) && // 竖边
                line2line(p1, p4, p2, p3); // 对角线
    }

    public boolean line2line(int[] p1, int[] p2, int[] p3, int[] p4) {
        long l1 = line(p1, p2);
        long l2 = line(p3, p4);
        return l1 == 0 || l2 == 0 ? false : l1 == l2;
    }

    public long line(int[] p1, int[] p2) {
        long d1 = Math.abs(p1[0] - p2[0]);
        long d2 = Math.abs(p1[1] - p2[1]);
        return d1 * d1 + d2 * d2;
    }

}
