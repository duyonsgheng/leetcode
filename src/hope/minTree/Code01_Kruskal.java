package hope.minTree;

import java.io.*;
import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code01_Kruskal
 * @date 2023年11月22日 9:17
 */
// Kruskal算法模版（洛谷） - 最小生成树
// 静态空间实现
// 测试链接 : https://www.luogu.com.cn/problem/P3366
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
public class Code01_Kruskal {
    // 使用并查集
    // 节点数
    public static int MAXN = 50001;
    // 边的数
    public static int MAXM = 200001;

    public static int[] father = new int[MAXN];
    public static int[][] edges = new int[MAXM][3];
    public static int n, m;

    public static void build() {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
    }

    public static int find(int x) {
        if (x != father[x]) {
            father[x] = find(father[x]);
        }
        return father[x];
    }

    public static boolean union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            return true;
        } else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();
            for (int i = 0; i <= m; i++) {
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }
            // 边排序，
            Arrays.sort(edges, 0, m, (a, b) -> a[2] - b[2]);
            int ans = 0;
            int edgeCnt = 0;
            // 从小边开始遍历，使用并查集检查是否已经在同一个集合了
            for (int[] edge : edges) {
                if (union(edge[0], edge[1])) {
                    edgeCnt++;
                    ans += edge[2];
                }
            }
            out.println(edgeCnt == n - 1 ? ans : "orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
