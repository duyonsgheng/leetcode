package custom.code_2023_04;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1637
 * @Author Duys
 * @Description
 * @Date 2023/4/21 11:28
 **/
// 1637. 两点之间不包含任何点的最宽垂直区域
public class LeetCode_1637 {
    // 就根据x轴上的位置排个序
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            ans = Math.max(points[i][0] - points[i - 1][0], ans);
        }
        return ans;
    }
}
