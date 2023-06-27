package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1828
 * @Author Duys
 * @Description
 * @Date 2023/5/24 13:39
 **/
// 1828. 统计一个圆中点的数目
public class LeetCode_1828 {
    public int[] countPoints(int[][] points, int[][] queries) {
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] < b[0] ? -1 : 1;
            }
            return a[1] < b[1] ? -1 : 1;
        });
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1], r = queries[i][2];
            // 顶一个上界和下界
            int l = Math.max(0, upper(points, x - r));
            int u = Math.min(low(points, x + r), points.length - 1);
            for (int j = l; j <= u; j++) {
                int[] cur = points[j];
                if ((cur[0] - x) * (cur[0] - x) + (cur[1] - y) * (cur[1] - y) <= r * r) {
                    ans[i]++;
                }
            }
        }
        return ans;
    }

    public int upper(int[][] points, int v) {
        int l = 0, r = points.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (points[m][0] >= v) {
                r = m - 1;
            } else l = m + 1;
        }
        return l;
    }

    public int low(int[][] points, int v) {
        int l = 0, r = points.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (points[m][0] <= v) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }
}
