package week.week_2022_10_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * @ClassName Code_01_MaxPrimePairs
 * @Author Duys
 * @Description
 * @Date 2022/10/27 9:18
 **/

// 来自华为
// 若两个正整数的和为素数，则这两个正整数称之为"素数伴侣"
// 给定N(偶数)个正整数中挑选出若干对，组成"素数伴侣"
// 例如有4个正整数：2，5，6，13，
// 如果将5和6分为一组的话，只能得到一组"素数伴侣"
// 如果将2和5、6和13编组，将得到两组"素数伴侣"
// 这是得到"素数伴侣"最多的划分方案
// 输入:
// 有一个正偶数 n ，表示待挑选的自然数的个数。后面给出 n 个具体的数字。
// 输出:
// 输出一个整数 K ，表示最多能找出几对"素数伴侣"
// 数据范围： 1 <= n <= 100, 2 <= val <= 30000
// 测试链接 : https://www.nowcoder.com/practice/b9eae162e02f4f928eac37d7699b352e
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code_01_MaxPrimePairs {

    // 就是一个KM的模板题目
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            int[][] graph = matrix(arr, n);
            out.println(km(graph) / 2);
            out.flush();
        }

    }

    public static int[][] matrix(int[] arr, int n) {
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = isPrime(arr[i] + arr[j]) ? 1 : 0;
            }
        }
        return ans;
    }

    // 是否是素数
    public static boolean isPrime(int num) {
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // km算法原型
    public static int km(int[][] graph) {
        int n = graph.length;
        // match是当前公主是谁与之配对的
        int[] match = new int[n];
        // 王子最好的预期
        int[] lx = new int[n];
        // 公主最好的预期
        int[] ly = new int[n];

        // 再dfs过程中当前王子有没有碰过
        boolean[] bx = new boolean[n];
        // 再dfs过程中当前公主有没有碰过
        boolean[] by = new boolean[n];

        int[] slack = new int[n];

        int invalid = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 初始的时候没有与之配对的
            match[i] = -1;
            lx[i] = -invalid;
            // 更新王子最好的期望
            for (int j = 0; j < n; j++) {
                lx[i] = Math.max(lx[i], graph[i][j]);
            }
            // 公主的初始期望都是0
            ly[i] = 0;
        }
        // 每个王子出发，去配最合适的
        for (int f = 0; f < n; f++) {
            for (int i = 0; i < n; i++)
                slack[i] = invalid;
            Arrays.fill(bx, false);
            Arrays.fill(by, false);
            while (!dfs(f, bx, by, lx, ly, match, slack, graph)) {
                int d = invalid;
                for (int i = 0; i < n; i++)
                    d = !by[i] ? Math.min(slack[i], d) : d;
                for (int i = 0; i < n; i++) {
                    if (bx[i]) {
                        lx[i] = lx[i] - d;
                    }
                    if (by[i]) {
                        ly[i] = ly[i] + d;
                    }
                }
                Arrays.fill(bx, false);
                Arrays.fill(by, false);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (lx[i] + ly[i]);
        }
        return ans;
    }

    public static boolean dfs(int from, boolean[] x, boolean[] y, int[] lx, int[] ly,
                              int[] match, int[] slack, int[][] map) {
        int n = map.length;
        x[from] = true;// 来到当前的from，那么往前from的王子就算算过了
        // 当前王子去尝试每一个公主
        for (int to = 0; to < n; to++) {
            // 差值
            int d = lx[from] + ly[to] - map[from][to];
            if (y[to] || d != 0) {
                slack[to] = Math.min(slack[to], d);
            } else {
                y[to] = true;
                if (match[to] == -1 || dfs(match[to], x, y, lx, ly, match, slack, map)) {
                    match[to] = from;
                    return true;
                }
            }
        }
        return false;
    }
}
