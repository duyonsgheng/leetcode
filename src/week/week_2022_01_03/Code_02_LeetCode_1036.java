package week.week_2022_01_03;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName Code_02_LeetCode_1036
 * @Author Duys
 * @Description
 * @Date 2022/3/30 17:25
 **/
public class Code_02_LeetCode_1036 {
    // 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为(x, y) 。
    //现在从源方格source = [sx, sy]开始出发，意图赶往目标方格target = [tx, ty] 。数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi]
    //表示坐标为 (xi, yi) 的方格是禁止通行的。
    //每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表blocked上。同时，不允许走出网格。
    //只有在可以通过一系列的移动从源方格source 到达目标方格target 时才返回true。否则，返回 false。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/escape-a-large-maze

    static long offset = 1000000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int n = blocked.length;
        // 最多n的点。那么最多能封锁多少个点
        int maxPoints = n * (n - 1) / 2;
        Set<Long> blockSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            blockSet.add(blocked[i][0] * offset + blocked[i][1]);
        }
        return bfs(source[0], source[1], target[0], target[1], maxPoints, blockSet) &&
                bfs(target[0], target[1], source[0], source[1], maxPoints, blockSet);
    }

    public static boolean bfs(int fromx, int fromy, int tox, int toy, int maxPoints, Set<Long> blockSet) {
        Set<Long> visited = new HashSet<>();
        Queue<Long> queue = new LinkedList<>();
        visited.add(fromx * offset + fromy);
        queue.add(fromx * offset + fromy);
        while (!queue.isEmpty() && visited.size() <= maxPoints) {
            long cur = queue.poll();
            long curx = cur / offset;
            long cury = cur - curx * offset;
            // 四个方向去走
            if (find(curx + 1, cury, tox, toy, blockSet, visited, queue)
                    || find(curx - 1, cury, tox, toy, blockSet, visited, queue)
                    || find(curx, cury + 1, tox, toy, blockSet, visited, queue)
                    || find(curx, cury - 1, tox, toy, blockSet, visited, queue)) {
                return true;
            }
        }
        return visited.size() > maxPoints;
    }

    public static boolean find(long row, long col, int tox, int toy, Set<Long> blockSet, Set<Long> visited, Queue<Long> queue) {
        // 越界了
        if (row < 0 || row == offset || col < 0 || col == offset) {
            return false;
        }
        if (row == tox && col == toy) {
            return true;
        }
        long val = row * offset + col;
        if (!blockSet.contains(val) && !visited.contains(val)) {
            visited.add(val);
            queue.add(val);
        }
        return false;
    }
}
