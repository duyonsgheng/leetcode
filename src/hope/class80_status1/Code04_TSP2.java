package hope.class80_status1;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code04_TSP2
 * @date 2024年04月16日 下午 03:24
 */
public class Code04_TSP2 {
    public static int MAXN = 19;

    public static int[] start = new int[MAXN];

    public static int[] back = new int[MAXN];

    // 这个图中，其实是不算起始村的，其他村庄彼此到达的路径长度
    public static int[][] graph = new int[MAXN][MAXN];

    // 不算起始村庄的
    public static int[][] dp = new int[1 << MAXN][MAXN];

    public static int n;

    public static void build() {
        for (int s = 0; s < (1 << n); s++) {
            for (int i = 0; i < n; i++) {
                dp[s][i] = -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval - 1;
            build();
            in.nextToken();
            for (int i = 0; i < n; i++) {
                in.nextToken();
                start[i] = (int) in.nval;
            }
            for (int i = 0; i < n; i++) {
                in.nextToken();
                back[i] = (int) in.nval;
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
        int ans = Integer.MAX_VALUE;
        // 起始村无编号
        for (int i = 0; i < n; i++) {
            // 起始村 -> i号村  +  i号村出发所有村子都走最终回到起始村
            ans = Math.min(ans, start[i] + f(1 << i, i));
        }
        return ans;
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
