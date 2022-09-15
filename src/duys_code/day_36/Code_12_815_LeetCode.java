package duys_code.day_36;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_12_815_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/bus-routes/
 * @Date 2021/12/14 14:04
 **/
public class Code_12_815_LeetCode {

    // routes[i] 表示第i辆公交车在这一条线路上循环行驶
    // 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
    // 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        // 一层一层的遍历
        // 给每一个站标记是属于哪些公交车的
        if (source == target) {
            return 0;
        }
        // 总共有多少线路
        int n = routes.length;
        // 每一个车站属于哪些线路
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!map.containsKey(routes[i][j])) {
                    map.put(routes[i][j], new ArrayList<>());
                }
                map.get(routes[i][j]).add(i);
            }
        }

        // 开始遍历
        List<Integer> queue = new ArrayList<>();
        boolean[] set = new boolean[n];
        // 看看我们从出发站属于哪一个线路
        // 然后如果遍历过程中新引出了线路，就表示需要换乘了
        for (int route : map.get(source)) {
            queue.add(route);
            set[route] = true;
        }
        int len = 1;
        while (!queue.isEmpty()) {
            // 因为是一层一层的遍历，看看下一层有些新得线路
            List<Integer> nextLevel = new ArrayList<>();
            for (int route : queue) {
                // queue里面是公交站对应的线路
                int[] bus = routes[route];
                for (int station : bus) {
                    if (station == target) {
                        return len;
                    }
                    // 否则就看看这个站又能引出哪些新得线路
                    for (int next : map.get(station)) {
                        if (set[next]) {
                            continue;
                        }
                        nextLevel.add(next);
                        set[next] = true;
                    }
                }
            }
            queue = nextLevel;
            len++;
        }
        return -1;
    }
}
