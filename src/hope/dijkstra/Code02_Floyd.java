package hope.dijkstra;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code02_Floyd
 * @date 2023年11月30日 10:27
 */
// Floyd算法模版（洛谷）
// 测试链接 : https://www.luogu.com.cn/problem/P2910
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
public class Code02_Floyd {
    // floyd三层for循环
    // 最外层一定是中间经过的点
    public static int MAXN = 101;

    public static int MAXM = 10001;

    public static int[] path = new int[MAXM];

    public static int[][] distance = new int[MAXN][MAXN];

    public static int n, m, ans;

    // 初始时设置任意两点之间的最短距离为无穷大，表示任何路不存在
    public static void build() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                path[i] = (int) in.nval - 1;
            }
            // 这道题给的图是邻接矩阵的形式
            // 任意两点之间的边权都会给定
            // 所以显得distance初始化不太必要
            // 但是一般情况下，distance初始化一定要做
            build();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    in.nextToken();
                    distance[i][j] = (int) in.nval;
                }
            }
            floyd();
            ans = 0;
            for (int i = 1; i < m; i++) {
                ans += distance[path[i - 1]][path[i]];
            }
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void floyd() {
        for (int bridge = 0; bridge < n; bridge++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (distance[from][bridge] != Integer.MAX_VALUE && distance[bridge][to] != Integer.MAX_VALUE
                            && distance[from][to] > distance[from][bridge] + distance[bridge][to]) {
                        distance[from][to] = distance[from][bridge] + distance[bridge][to];
                    }
                }
            }
        }
    }
}
