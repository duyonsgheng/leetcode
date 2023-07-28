package custom.code_2023_07;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2013
 * @date 2023年07月26日
 */
// 2013. 检测正方形
// https://leetcode.cn/problems/detect-squares/
public class LeetCode_2013 {
    class DetectSquares {
        // 记录每一行上有那些点
        Map<Integer, Map<Integer, Integer>> map;

        public DetectSquares() {
            map = new HashMap<>();
        }

        public void add(int[] point) {
            map.putIfAbsent(point[1], new HashMap<>());
            Map<Integer, Integer> ymp = map.get(point[1]);
            ymp.put(point[0], ymp.getOrDefault(point[0], 0) + 1);
        }

        public int count(int[] point) {
            int ans = 0;
            int x = point[0], y = point[1];
            if (!map.containsKey(y)) {
                return ans;
            }
            Map<Integer, Integer> ymp = map.get(y);
            // 遍历当前y轴上所有的点，看看那些店可以构成正方形
            Set<Map.Entry<Integer, Map<Integer, Integer>>> entries = map.entrySet();
            for (Map.Entry<Integer, Map<Integer, Integer>> m : entries) {
                int yp = m.getKey(); // y方向
                Map<Integer, Integer> xpt = m.getValue(); // y确定，看看有几个x落在当前y上的
                if (yp == y) { // 同一个y方向，可以跳过
                    continue;
                }
                int d = yp - y; // 边长确定
                ans += xpt.getOrDefault(x, 0) // 当前 x y在y这条边上有几个点
                        * ymp.getOrDefault(x + d, 0) //
                        * xpt.getOrDefault(x + d, 0);
                ans += xpt.getOrDefault(x, 0) // 当前 x y在y这条边上有几个点
                        * ymp.getOrDefault(x - d, 0) //
                        * xpt.getOrDefault(x - d, 0);
            }
            return ans;
        }
    }
}
