package week.week_2022_01_03;

import java.util.PriorityQueue;

/**
 * @ClassName Code_01_AStarAlgorithm
 * @Author Duys
 * @Description
 * @Date 2022/3/30 16:50
 **/
// A*算法
// 过程和Dijskra高度相处
// 有到终点的预估函数
// 只要预估值<=客观上最优距离，就是对的
// 预估函数是一种吸引力：
// 1）合适的吸引力可以提升算法的速度
// 2）吸引力“过强”会出现错误
// 讲述A*算法
// 预估终点距离选择曼哈顿距离
// 要求先在体系学习班图的章节听过"Dijkstra算法"
public class Code_01_AStarAlgorithm {

    // Dijkstra算法
    // map[i][j] == 0代表障碍
    // map[i][j] > 0 代表可以通过的代价
    // 从 start点开始要到target点去，最少的花费
    public static int minDistance1(int[][] map, int startX, int startY, int targetX, int targetY) {
        if (map[startX][startY] == 0 || map[targetX][targetY] == 0) {
            return Integer.MAX_VALUE;
        }
        int n = map.length;
        int m = map[0].length;

        // heap 小根堆
        // int[] 0-花费，1，表示行，2-表示列
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        boolean[][] closed = new boolean[n][m];

        heap.add(new int[]{map[startX][startY], targetX, targetY});
        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int curDis = cur[0];
            int curRow = cur[1];
            int curCol = cur[2];
            if (closed[curRow][curCol]) {
                continue;
            }
            closed[curRow][curCol] = true;
            if (curRow == targetX && curCol == targetY) {
                ans = curDis;
                break;
            }
            // 四个方向去走吧
            go(curDis, curRow - 1, curCol, n, m, map, closed, heap);
            go(curDis, curRow + 1, curCol, n, m, map, closed, heap);
            go(curDis, curRow, curCol - 1, n, m, map, closed, heap);
            go(curDis, curRow, curCol + 1, n, m, map, closed, heap);
        }
        return ans;
    }

    public static void go(int preDis, int row, int col, int n, int m, int[][] map, boolean[][] closed, PriorityQueue<int[]> heap) {
        if (row >= 0 && row < n && col >= 0 && col < m && map[row][col] != 0 && !closed[row][col]) {
            heap.add(new int[]{preDis + map[row][col], row, col});
        }
    }


    // A*算法
    // 相比上一个最短距离算法，这里多了一个期望，我期望越来越小，那么就有可能越短
    public static int minDistance2(int[][] map, int startX, int startY, int targetX, int targetY) {
        if (map[startX][startY] == 0 || map[targetX][targetY] == 0) {
            return Integer.MAX_VALUE;
        }
        int n = map.length;
        int m = map[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        boolean[][] closed = new boolean[n][m];
        heap.add(new int[]{map[startX][startY], distance(startX, startY, targetX, targetY), startX, startY});

        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int dis = cur[0];
            int row = cur[2];
            int col = cur[3];
            if (closed[row][col]) {
                continue;
            }
            closed[row][col] = true;
            if (row == targetX && col == targetY) {
                ans = dis;
                break;
            }
            go2(dis, row + 1, col, targetX, targetY, n, m, map, closed, heap);
            go2(dis, row - 1, col, targetX, targetY, n, m, map, closed, heap);
            go2(dis, row, col + 1, targetX, targetY, n, m, map, closed, heap);
            go2(dis, row, col - 1, targetX, targetY, n, m, map, closed, heap);
        }
        return ans;
    }

    public static void go2(int pre, int row, int col, int tx, int ty, int n, int m, int[][] map, boolean[][] closed, PriorityQueue<int[]> heap) {
        if (row >= 0 && row < n && col >= 0 && col < m && map[row][col] != 0 && !closed[row][col]) {
            heap.add(new int[]{pre + map[row][col], distance(row, col, tx, ty), row, col});
        }
    }

    // 曼哈顿距离
    public static int distance(int sx, int sy, int tx, int ty) {
        return Math.abs(tx - sx) + Math.abs(ty - sy);
    }
}
