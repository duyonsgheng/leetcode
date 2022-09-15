package week.week_2022_08_04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName Code_03_FindPosition
 * @Author Duys
 * @Description
 * @Date 2022/8/25 9:30
 **/
// 小团在地图上放了3个定位装置，想依赖他们进行定位！
// 地图是一个n*n的棋盘，
// 有3个定位装置(x1,y1),(x2,y2),(x3,y3)，每个值均在[1,n]内。
// 小团在(a,b)位置放了一个信标，
// 每个定位装置会告诉小团它到信标的曼哈顿距离，也就是对于每个点，小团知道|xi-a|+|yi-b|
// 求信标位置，信标不唯一，输出字典序最小的。
// 输入n，然后是3个定位装置坐标，
// 最后是3个定位装置到信标的曼哈顿记录。
// 输出最小字典序的信标位置。
// 1 <= 所有数据值 <= 50000
public class Code_03_FindPosition {
    // 分析
    // 一个点到其他的三个点的曼哈顿距离确定
    // 那么我们以最小的距离为基点，进行向周围辐射
    public static int[] find(int n, int[] a, int[] b, int[] c, int ad, int bd, int cd) {
        int[] x1 = null;
        int r1 = Integer.MAX_VALUE;
        int[] x2 = null;
        int r2 = 0;
        int[] x3 = null;
        int r3 = 0;
        // 找最小的点
        if (ad < r1) {
            x1 = a;
            r1 = ad;
            x2 = b;
            r2 = bd;
            x3 = c;
            r3 = cd;
        }
        if (bd < r1) {
            x1 = b;
            r1 = bd;
            x2 = a;
            r2 = ad;
            x3 = c;
            r3 = cd;
        }
        if (cd < r1) {
            x1 = c;
            r1 = cd;
            x2 = a;
            r2 = ad;
            x3 = b;
            r3 = bd;
        }
        // 宽度优先遍历
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        List<int[]> ans = new ArrayList<>();
        // 开始的点
        queue.add(new int[]{x1[0] - r1, x1[1]});
        visited.add((x1[0] - r1) + "_" + x1[1]);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 如果当前点满足了，就收集答案
            if (cur[0] >= 1 && cur[0] <= n && cur[1] >= 1 && cur[1] <= n
                    && distance(cur[0], cur[1], x2) == r2
                    && distance(cur[0], cur[1], x3) == r3) {
                ans.add(cur);
            }
            //
            if (ans.size() >= 2) {
                break;
            }
            // 八个方向去走
            add(cur[0] + 1, cur[1] - 1, x1, r1, queue, visited);
            add(cur[0] + 1, cur[1] + 1, x1, r1, queue, visited);
            add(cur[0] + 1, cur[1], x1, r1, queue, visited);
            add(cur[0] - 1, cur[1] + 1, x1, r1, queue, visited);
            add(cur[0] - 1, cur[1] - 1, x1, r1, queue, visited);
            add(cur[0] - 1, cur[1], x1, r1, queue, visited);
            add(cur[0], cur[1] - 1, x1, r1, queue, visited);
            add(cur[0], cur[1] - 1, x1, r1, queue, visited);
        }
        // 计算答案，如果有多个，返回最小的
        if (ans.size() == 1) {
            return ans.get(0);
        }
        int[] dot1 = ans.get(0);
        int[] dot2 = ans.get(1);
        if (dot1[0] < dot1[0] || dot1[0] == dot2[0] && dot1[1] < dot2[1]) {
            return ans.get(0);
        } else {
            return ans.get(1);
        }
    }

    public static void add(int x, int y, int[] c, int r, Queue<int[]> queue, Set<String> visited) {
        String key = x + "_" + y;
        if (distance(x, y, c) == r && !visited.contains(key)) {
            queue.add(new int[]{x, y});
            visited.add(key);
        }
    }

    //
    public static int distance(int x, int y, int[] a) {
        return Math.abs(x - a[0]) + Math.abs(y - a[1]);
    }
}
