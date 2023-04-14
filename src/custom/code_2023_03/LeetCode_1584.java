package custom.code_2023_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName LeetCode_1584
 * @Author Duys
 * @Description
 * @Date 2023/3/24 17:06
 **/
// 1584. 连接所有点的最小费用
public class LeetCode_1584 {
    // 连接所有的点最小的代价-曼哈顿距离
    public int minCostConnectPoints(int[][] points) {
        // x轴排序。y轴从小达到排序
        // [[0,0],[2,2],[3,10],[5,2],[7,0]]
        // [[0,0],[2,2],[3,10],[5,2],[7,0]]
        //Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int n = points.length;
        DisjointSetUnion union = new DisjointSetUnion(n);
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                list.add(new Edge(dist(points, i, j), i, j));
            }
        }
        Collections.sort(list, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.len - o2.len;
            }
        });
        int ans = 0, num = 1;
        for (Edge edge : list) {
            int len = edge.len, x = edge.x, y = edge.y;
            if (union.union(x, y)) {
                ans += len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return ans;
    }

    public int dist(int[][] arr, int x, int y) {
        return Math.abs(arr[x][0] - arr[y][0]) + Math.abs(arr[x][1] - arr[y][1]);
    }

    class DisjointSetUnion {
        int[] parent;
        int[] rank;
        int[] help;
        int n;

        public DisjointSetUnion(int n) {
            this.n = n;
            this.rank = new int[n];
            this.help = new int[n];
            Arrays.fill(rank, 1);
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private int find(int i) {
            int index = 0;
            while (i != parent[i]) {
                help[index++] = i;
                i = parent[i];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = i;
            }
            return i;
        }

        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return false;
            }
            if (rank[px] < rank[py]) {
                rank[py] += rank[px];
                parent[px] = py;
            } else {
                rank[px] += rank[py];
                parent[py] = px;
            }
            return true;
        }
    }

    class Edge {
        int len, x, y;

        public Edge(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }
}
