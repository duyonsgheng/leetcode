package custom.code_2022_10;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_939
 * @Author Duys
 * @Description
 * @Date 2022/10/19 13:28
 **/
// 939. 最小面积矩形
public class LeetCode_939 {
    // [1,1],
    // [1,3],
    // [3,1],
    // [3,3],
    // [2,2]

    // 枚举对角线
    public int minAreaRect(int[][] points) {
        Set<Integer> set = new HashSet<>();
        for (int[] point : points) {
            set.add(40001 * point[0] + point[1]);
        }
        int ans = Integer.MAX_VALUE;
        int n = points.length;
        // 开始枚举对角线
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 如果x和y都不相等，可以形成对角线
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
                    // 已经存在了上述两个点，只需要找到两外两个点也是存在的就行了
                    if (set.contains(40001 * points[i][0] + points[j][1]) && set.contains(40001 * points[j][0] + points[i][1])) {
                        // 可以获取
                        ans = Math.min(ans, Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]));
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
