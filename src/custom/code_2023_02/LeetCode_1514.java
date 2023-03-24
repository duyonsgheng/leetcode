package custom.code_2023_02;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1514
 * @Author Duys
 * @Description
 * @Date 2023/2/6 9:50
 **/
// 1514. 概率最大的路径
public class LeetCode_1514 {
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<Point>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int len = edges.length;
        for (int i = 0; i < len; i++) {
            graph.get(edges[i][0]).add(new Point(edges[i][0], edges[i][1], succProb[i]));
            graph.get(edges[i][1]).add(new Point(edges[i][1], edges[i][0], succProb[i]));
        }
        double ans = 0;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Point> queue = new PriorityQueue<>((a, b) -> {
            return b.m > a.m ? 1 : -1;
        });
        // 从start开始
        List<Point> points = graph.get(start);
        if (points.size() == 0) {
            return 0;
        }
        for (Point point : points) {
            queue.add(new Point(point.x, point.y, point.m));
        }
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (visited[cur.x][cur.y]) {
                continue;
            }
            if (cur.y == end) {
                ans = Math.max(ans, cur.m);
            }
            visited[cur.x][cur.y] = true;
            List<Point> list = graph.get(cur.y);
            for (Point point : list) {
                queue.add(new Point(cur.y, point.y, cur.m * point.m));
            }
        }
        return ans;
    }

    static class Point {
        int x;
        int y;
        double m;

        public Point(int a, int b, double c) {
            x = a;
            y = b;
            m = c;
        }
    }

    public static void main(String[] args) {
        System.out.println(maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
        System.out.println(maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.3}, 0, 2));
        System.out.println(maxProbability(3, new int[][]{{0, 1}}, new double[]{0.5}, 0, 2));
    }

}
