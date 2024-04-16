package hope.class80_status1;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code04_TSP1
 * @date 2024年04月16日 下午 03:19
 */
// 售货员的难题 - TSP问题
// 某乡有n个村庄(1<=n<=20)，有一个售货员，他要到各个村庄去售货
// 各村庄之间的路程s(1<=s<=1000)是已知的
// 且A村到B村的路程，与B到A的路大多不同(有向带权图)
// 为了提高效率，他从商店出发到每个村庄一次，然后返回商店所在的村，
// 假设商店所在的村庄为1
// 他不知道选择什么样的路线才能使所走的路程最短
// 请你帮他选择一条最短的路
// 测试链接 : https://www.luogu.com.cn/problem/P1171
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code04_TSP1 {
    public static int MAXN = 19;

    public static int[][] graph = new int[MAXN][MAXN];

    public static int[][] dp = new int[1 << MAXN][MAXN];

    public static int n;

    public static void build() {
        for (int s = 0; s < (1 << n); s++) {
            for (int i = 0; i < n; i++) {
                dp[s][i] = -1;
            }
        }
    }

    // 会卡空间
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            build();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    in.nextToken();
                    graph[i][j] = (int) in.nval;
                }
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute() {
        return f(1, 0);
    }

    // 当前在那个位置 i
    // s中 1是已经走过的状态，0是可以走的
    public static int f(int s, int i) {
        if (s == (1 << n) - 1) {
            // 从i回到0
            return graph[i][0];
        }
        if (dp[s][i] != -1) {
            return dp[s][i];
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if ((s & (1 << j)) == 0) { // 可以走
                ans = Math.min(ans, graph[i][j] + f(s | (1 << j), j));
            }
        }
        dp[s][i] = ans;
        return ans;
    }
}
