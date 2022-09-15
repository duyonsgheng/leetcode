package custom.code_2022_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_447
 * @Author Duys
 * @Description
 * @Date 2022/8/11 15:45
 **/
// 447. 回旋镖的数量
public class LeetCode_447 {

    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 当前i点到其他点的距离个数
            Map<Integer, Integer> distanceMap = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                // a^2 = b^2+c^2
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int dist = dx * dx + dy * dy;
                distanceMap.put(dist, distanceMap.getOrDefault(dist, 0) + 1);
            }
            for (int d : distanceMap.keySet()) {
                int count = distanceMap.get(d);
                // 当前点作为中点，其他的点任意排列
                ans += count * (count - 1);
            }
        }
        return ans;
    }
}
