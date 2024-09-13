package hope.class94;

import java.io.*;
import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code05_CuttingTree
 * @date 2024年09月13日 下午 04:58
 */
// 砍树
// 一共有n棵树，每棵树都有两个信息：
// 第一天这棵树的初始重量、这棵树每天的增长重量
// 你每天最多能砍1棵树，砍下这棵树的收益为：
// 这棵树的初始重量 + 这棵树增长到这一天的总增重
// 从第1天开始，你一共有m天可以砍树，返回m天内你获得的最大收益
// 测试链接 : https://pintia.cn/problem-sets/91827364500/exam/problems/91827367873
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code05_CuttingTree {

    // 树的大小、天数
    public static int MAXN = 251;
    // 树的初始重量和树的增长重量
    public static int[][] tree = new int[MAXN][2];
    // dp[i][j] : 在j天内，从前i棵树中选若干棵树进行砍伐，最大收益是多少
    public static int[][] dp = new int[MAXN][MAXN];
    public static int t, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        t = (int) in.nval;
        for (int i = 1; i <= t; i++) {
            in.nextToken();
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for (int j = 1; j <= n; j++) {
                in.nextToken();
                tree[j][0] = (int) in.nval;
            }
            for (int j = 1; j <= n; j++) {
                in.nextToken();
                tree[j][1] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    // 背包问题
    public static int compute() {
        // 树的增长重量越大的越后砍
        Arrays.sort(tree, 1, n + 1, (a, b) -> a[1] - b[1]);
        // dp[0][...] = 0 : 表示如果没有树，不管过去多少天，收益都是0
        // dp[...][0] = 0 : 表示不管有几棵树，没有时间砍树，收益都是0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 1.当前最后一颗树不砍
                // 2.就是要在最后一天砍最后这一颗树
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + tree[i][0] + tree[i][1] * (j - 1));
            }
        }
        return dp[n][m];
    }
}
