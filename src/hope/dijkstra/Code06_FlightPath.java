package hope.dijkstra;

import java.io.*;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code06_FlightPath
 * @date 2023年11月27日 17:01
 */
// 飞行路线（语言提供的堆）
// Alice和Bob现在要乘飞机旅行，他们选择了一家相对便宜的航空公司
// 该航空公司一共在n个城市设有业务，设这些城市分别标记为0 ~ n−1
// 一共有m种航线，每种航线连接两个城市，并且航线有一定的价格
// Alice 和 Bob 现在要从一个城市沿着航线到达另一个城市，途中可以进行转机
// 航空公司对他们这次旅行也推出优惠，他们可以免费在最多k种航线上搭乘飞机
// 那么 Alice 和 Bob 这次出行最少花费多少
// 测试链接 : https://www.luogu.com.cn/problem/P4568
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过

public class Code06_FlightPath {

    public static int maxn = 10001;
    public static int maxm = 100001;
    public static int maxk = 11;

    public static int[] head = new int[maxn];
    public static int[] next = new int[maxm];
    public static int[] to = new int[maxm];
    public static int[] weight = new int[maxm];
    public static int cnt;

    public static int[][] distance = new int[maxn][maxk];
    public static boolean[][] visited = new boolean[maxn][maxk];

    // 0:城市编号
    // 1:已经免费的次数
    // 2:花费多少
    public static PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    public static int n, m, k, s, t;

    public static void build() {
        cnt = 1;
        for (int i = 0; i < n; i++) {
            head[i] = 0;
            for (int j = 0; j <= k; j++) {
                distance[i][j] = Integer.MAX_VALUE;
                visited[i][j] = false;
            }
        }
        heap.clear();
    }

    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            in.nextToken();
            k = (int) in.nval;
            in.nextToken();
            s = (int) in.nval;
            in.nextToken();
            t = (int) in.nval;
            build();
            for (int i = 0, a, b, c; i < m; i++) {
                in.nextToken();
                a = (int) in.nval;
                in.nextToken();
                b = (int) in.nval;
                in.nextToken();
                c = (int) in.nval;
                addEdge(a, b, c);
                addEdge(b, a, c);
            }
            out.println(dijkstra());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int dijkstra() {
        distance[s][0] = 0;
        heap.add(new int[]{s, 0, 0});
        while (!heap.isEmpty()) {
            int[] record = heap.poll();
            int u = record[0];
            int use = record[1];
            int cost = record[2];
            if (visited[u][use]) {
                continue;
            }
            visited[u][use] = true;
            if (u == t) {
                return cost;
            }
            for (int ei = head[u], v, w; ei > 0; ei = next[ei]) {
                v = to[ei];
                w = weight[ei];
                if (use < k && distance[v][use + 1] > distance[u][use]) {
                    // 使用免费
                    distance[v][use + 1] = distance[u][use];
                    heap.add(new int[]{v, use + 1, distance[v][use + 1]});
                }
                // 不用免费
                if (distance[v][use] > distance[v][use] + w) {
                    distance[v][use] = distance[v][use] + w;
                    heap.add(new int[]{v, use, distance[v][use]});
                }
            }
        }
        return -1;
    }
}
