package week.week_2022_10_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName Code_03_FindMazeMinPath
 * @Author Duys
 * @Description
 * @Date 2022/10/13 13:20
 **/
// 定义一个二维数组N*M，比如5*5数组下所示：
// 0, 1, 0, 0, 0,
// 0, 1, 1, 1, 0,
// 0, 0, 0, 0, 0,
// 0, 1, 1, 1, 0,
// 0, 0, 0, 1, 0,
// 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路
// 只能横着走或竖着走，不能斜着走
// 要求编程序找出从左上角到右下角距离最短的路线
// 测试链接 : https://www.nowcoder.com/practice/cf24906056f4488c9ddb132f317e03bc
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把主类名改成"Main"
public class Code_03_FindMazeMinPath {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            int[][] map = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    in.nextToken();
                    map[i][j] = (int) in.nval;
                }
            }
            ArrayList<int[]> ans = dijkstra(n, m, map);
            for (int i = ans.size() - 1; i >= 0; i--) {
                out.println("(" + ans.get(i)[0] + "," + ans.get(i)[1] + ")");
            }
            out.flush();
        }
    }

    public static ArrayList<int[]> dijkstra(int n, int m, int[][] map) {
        // 用于回溯怎么来的
        int[][][] last = new int[n][m][2];
        // 按照路径排序
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[n][m];
        queue.add(new int[]{0, 0, 0});
        ArrayList<int[]> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            if (x == n - 1 && y == m - 1) {
                break;
            }
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            add(x, y, x + 1, y, cost, n, m, map, visited, queue, last);
            add(x, y, x - 1, y, cost, n, m, map, visited, queue, last);
            add(x, y, x, y + 1, cost, n, m, map, visited, queue, last);
            add(x, y, x, y - 1, cost, n, m, map, visited, queue, last);
        }
        int x = n - 1;
        int y = m - 1;
        while (x != 0 || y != 0) {
            ans.add(new int[]{x, y});
            int lastX = last[x][y][0];
            int lastY = last[x][y][1];
            x = lastX;
            y = lastY;
        }
        ans.add(new int[]{0, 0});
        return ans;
    }

    public static void add(int x, int y, int nx, int ny, int cost, int n, int m, int[][] map, boolean[][] visited,
                           PriorityQueue<int[]> heap, int[][][] last) {
        if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 0 && !visited[nx][ny]) {
            heap.add(new int[]{nx, ny, cost + 1});
            last[nx][ny][0] = x;
            last[nx][ny][1] = y;
        }
    }
}
