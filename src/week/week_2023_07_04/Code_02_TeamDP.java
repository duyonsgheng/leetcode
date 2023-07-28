package week.week_2023_07_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName Code_02_TeamDP
 * @date 2023年07月27日
 */
// 自 01背包问世之后，小 A 对此深感兴趣
// 一天，小 A 去远游，却发现他的背包不同于 01 背包，他的物品大致可分为 k 组
// 每组中的物品只能选择1件，现在他想知道最大的利用价值是多少
// 测试链接 : www.luogu.com.cn/problem/P1757
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code_02_TeamDP {
    // 一个分组背包问题
    public static int maxn = 1001;
    public static int maxm = 1001;
    // arr[i][0] 重量
    // arr[i][1] 价值
    // arr[i][2] 组号
    public static int[][] arr = new int[maxn][3];
    // dp[i] 重量为i的时候最优的选择方案
    public static int[] dp = new int[maxm];
    public static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            // 总背包的载重
            m = (int) in.nval;
            in.nextToken();
            // 物品数量
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
                in.nextToken();
                arr[i][2] = (int) in.nval;
            }

            // 根据组号排序
            // 1 : a b c   2 : d e  3 : f g h
            Arrays.sort(arr, 0, n, (a, b) -> a[2] - b[2]);
            // dp[位置][剩余重量]
            // dp[重量]
            Arrays.fill(dp, 0, m + 1, 0);
            out.println(compute());
            out.flush();
        }
    }

    private static int compute() {
        for (int start = 0, end = 1; start < n; ) {
            // start 当前组的第一个物品
            // end 下一个组的开始位置
            while (end < n && arr[end][2] != arr[start][2]) {
                end++;
            }
            // 背包剩余空间
            for (int rest = m; rest >= 0; rest--) {
                // 当前组内选择物品
                for (int i = start; i < end; i++) {
                    if (rest >= arr[i][0]) {
                        dp[rest] = Math.max(dp[rest], arr[i][1] + dp[rest - arr[i][0]]);
                    }
                }
            }
            // 下一组准备开始了，位置该调整的就调整
            start = end++;
        }
        return dp[m];
    }
}
