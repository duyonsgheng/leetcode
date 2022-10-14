package week.week_2022_10_02;

import java.util.PriorityQueue;

/**
 * @ClassName Code_05_SoldierFindEnemy
 * @Author Duys
 * @Description
 * @Date 2022/10/13 13:45
 **/
// 来自华为
// 给定一个N*M的二维矩阵，只由字符'O'、'X'、'S'、'E'组成
// 'O'表示这个地方是可通行的平地
// 'X'表示这个地方是不可通行的障碍
// 'S'表示这个地方有一个士兵，全图保证只有一个士兵
// 'E'表示这个地方有一个敌人，全图保证只有一个敌人
// 士兵可以在上、下、左、右四个方向上移动
// 走到相邻的可通行的平地上，走一步耗费a个时间单位
// 士兵从初始地点行动时，不管去哪个方向，都不用耗费转向的代价
// 但是士兵在行动途中，如果需要转向，需要额外再付出b个时间单位
// 返回士兵找到敌人的最少时间
// 如果因为障碍怎么都找不到敌人，返回-1
// 1 <= N,M <= 1000
// 1 <= a,b <= 100000
// 只会有一个士兵、一个敌人
public class Code_05_SoldierFindEnemy {
    // 思路，对 Dijkstra算法 的改进，增加一个位置，来表示朝向
    public static int minCost(char[][] map, int a, int b) {
        int n = map.length;
        int m = map[0].length;
        int tx = 0;
        int ty = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S') {
                    tx = i;
                    ty = j;
                }
            }
        }
        // int[]arr  arr[0] - x位置 arr[1] - y位置 arr[2] - 来到当前x,y位置的朝向 arr[3] - 来到x,y位置的权重
        PriorityQueue<int[]> heap = new PriorityQueue<>((a1, b1) -> a1[3] - b1[3]);
        // 所以三个位置
        heap.add(new int[]{tx, ty, 0, 0});// 朝上
        heap.add(new int[]{tx, ty, 1, 0});// 朝下
        heap.add(new int[]{tx, ty, 2, 0});// 朝左
        heap.add(new int[]{tx, ty, 3, 0});// 朝右
        boolean[][][] visited = new boolean[n][m][4];
        int ans = -1;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int x = cur[0];
            int y = cur[1];
            int s = cur[2];
            int w = cur[3];
            if (map[x][y] == 'E') {
                ans = w;
                break;
            }
            if (visited[x][y][s]) {
                continue;
            }
            visited[x][y][s] = true;
            // 超上
            add(x, y - 1, 0, s, w, a, b, map, visited, heap);
            // 下
            add(x, y + 1, 1, s, w, a, b, map, visited, heap);
            // 左
            add(x - 1, y, 2, s, w, a, b, map, visited, heap);
            // 右
            add(x + 1, y, 3, s, w, a, b, map, visited, heap);
        }
        return ans;
    }

    // 来到 x y 位置
    // 当前的朝向是 d
    // 之前的朝向是 preD
    public static void add(int x, int y, int d, int preD, int cost, int a, int b, char[][] map, boolean[][][] visited, PriorityQueue<int[]> heap) {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length || visited[x][y][d]) {
            return;
        }
        cost += a;
        // 朝向不一样，花费
        if (d != preD) {
            cost += b;
        }
        heap.add(new int[]{x, y, d, cost});
    }

}
