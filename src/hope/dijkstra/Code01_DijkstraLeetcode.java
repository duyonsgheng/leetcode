package hope.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code01_DijkstraLeetcode
 * @date 2023年11月27日 9:55
 */
// Dijkstra算法模版（Leetcode）
// 网络延迟时间
// 有 n 个网络节点，标记为 1 到 n
// 给你一个列表 times，表示信号经过 有向 边的传递时间
// times[i] = (ui, vi, wi)，表示从ui到vi传递信号的时间是wi
// 现在，从某个节点 s 发出一个信号
// 需要多久才能使所有节点都收到信号
// 如果不能使所有节点收到信号，返回 -1
// 测试链接 : https://leetcode.cn/problems/network-delay-time
public class Code01_DijkstraLeetcode {
    // 动态建图+普通堆的实现
    public static int networkDelayTime1(int[][] times, int n, int s) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        boolean[] visited = new boolean[n + 1];
        // 0:当前节点
        // 1:源点到当前节点的距离
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{s, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;
            for (int[] next : graph.get(cur[0])) {
                int a = next[0];
                int b = next[1];
                if (!visited[a] && dist[cur[0]] + b < dist[a]) {
                    dist[a] = dist[cur[0]] + b;
                    queue.add(new int[]{a, dist[a]});
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(dist[i], ans);
        }
        return ans;
    }

    public static int maxn = 101; // 点
    public static int maxm = 6001;// 边

    // 链式前向星
    public static int[] head = new int[maxn];
    public static int[] next = new int[maxm];
    public static int[] to = new int[maxm];
    public static int[] weight = new int[maxm];
    public static int cnt;

    // 反向索引堆
    public static int[] heap = new int[maxn];
    // where[i] = -1表示节点i从来没进过堆
    // where[i] = -2 表示节点i已经弹出过了
    // where[i] = a(a>=0) 表示节点在堆的位置是a
    public static int[] where = new int[maxn];
    public static int heapSize;
    public static int[] distance = new int[maxn];

    public static void build(int n) {
        cnt = 1;
        heapSize = 0;
        Arrays.fill(head, 1, n + 1, 0);
        Arrays.fill(where, 1, n + 1, -1);
        Arrays.fill(distance, 1, n + 1, Integer.MAX_VALUE);
    }

    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    public static void addOrUpdateOrIgnore(int v, int c) {
        if (where[v] == -1) {
            heap[heapSize] = v;
            where[v] = heapSize++;
            distance[v] = c;
            heapInsert(where[v]);
        } else if (where[v] >= 0) {
            distance[v] = Math.min(distance[v], c);
            heapInsert(where[v]);
        }
    }

    public static int pop() {
        int ans = heap[0];
        swap(0, --heapSize);
        heapify(0);
        where[ans] = -2;
        return ans;
    }

    public static boolean isEmpty() {
        return heapSize == 0;
    }

    public static void heapInsert(int i) {
        while (distance[heap[i]] < distance[heap[(i - 1) / 2]]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void heapify(int i) {
        int l = 1;
        while (l < heapSize) {
            int best = l + 1 < heapSize && distance[heap[l + 1]] < distance[heap[l]] ? l + 1 : l;
            best = distance[heap[best]] < distance[heap[i]] ? best : i;
            if (best == i) {
                break;
            }
            swap(best, i);
            i = best;
            l = i * 2 + 1;
        }
    }

    public static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        where[heap[i]] = i;
        where[heap[j]] = j;
    }

    // 链式前向星+反向索引堆的实现
    public static int networkDelayTime(int[][] times, int n, int s) {
        build(n);
        for (int[] edge : times) {
            addEdge(edge[0], edge[1], edge[2]);
        }
        addOrUpdateOrIgnore(s, 0);
        while (!isEmpty()) {
            int u = pop();
            for (int ei = head[u]; ei > 0; ei = next[ei]) {
                addOrUpdateOrIgnore(to[ei], distance[u] + weight[ei]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }

}
