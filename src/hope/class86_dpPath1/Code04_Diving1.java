package hope.class86_dpPath1;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code04_Diving1
 * @date 2024年05月20日 下午 04:27
 */
// 潜水的最大时间与方案
// 一共有n个工具，每个工具都有自己的重量a、阻力b、提升的停留时间c
// 因为背包有限，所以只能背重量不超过m的工具
// 因为力气有限，所以只能背阻力不超过v的工具
// 希望能在水下停留的时间最久
// 返回最久的停留时间和下标字典序最小的选择工具的方案
// 注意这道题的字典序设定（根据提交的结果推论的）：
// 下标方案整体构成的字符串保证字典序最小
// 比如下标方案"1 120"比下标方案"1 2"字典序小
// 测试链接 : https://www.luogu.com.cn/problem/P1759
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code04_Diving1 {

    // 讲解069 - 多维费用背包
    // 不做空间压缩的版本
    // 无法通过全部测试用例
    // 这个题必须做空间压缩
    // 空间压缩的实现在Code04_Diving2
    public static int MAXN = 101;

    public static int MAXM = 201;

    public static int[] a = new int[MAXN];

    public static int[] b = new int[MAXN];

    public static int[] c = new int[MAXN];

    public static int[][][] dp = new int[MAXN][MAXM][MAXM];

    public static String[][][] path = new String[MAXN][MAXM][MAXM]; // 路径

    public static int m, v, n;

    public static void build() {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= v; k++) {
                    dp[i][j][k] = 0;
                    path[i][j][k] = null;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            m = (int) in.nval;
            in.nextToken();
            v = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            build();
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                a[i] = (int) in.nval;
                in.nextToken();
                b[i] = (int) in.nval;
                in.nextToken();
                c[i] = (int) in.nval;
            }
            compute();
            out.println(dp[n][m][v]);
            out.println(path[n][m][v]);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void compute() {
        String p2;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= v; k++) {
                    // 不要i位置
                    dp[i][j][k] = dp[i - 1][j][k];
                    path[i][j][k] = path[i - 1][j][k];
                    // 要i位置
                    if (j >= a[i] && k >= b[i]) {
                        if (path[i - 1][j - a[i]][k - b[i]] == null) {
                            p2 = String.valueOf(i);
                        } else {
                            p2 = path[i - 1][j - a[i]][k - b[i]] + " " + String.valueOf(i);
                        }
                        // 停留时间较长的
                        if (dp[i][j][k] < dp[i - 1][j - a[i]][k - b[i]] + c[i]) {
                            dp[i][j][k] = dp[i - 1][j - a[i]][k - b[i]] + c[i];
                            path[i][j][k] = p2;
                        } else if (dp[i][j][k] == dp[i - 1][j - a[i]][k - b[i]] + c[i]) {
                            // 停留时间一样，选择字典序小的
                            if (p2.compareTo(path[i][j][k]) < 0) {
                                path[i][j][k] = p2;
                            }
                        }
                    }
                }
            }
        }
    }
}
