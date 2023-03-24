package week.week_2023_02_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * @ClassName Code_04_FourKindsPaperQueryWays
 * @Author Duys
 * @Description
 * @Date 2023/2/2 13:53
 **/
// https://www.luogu.com.cn/problem/P1450
public class Code_04_FourKindsPaperQueryWays {
    // 容斥原理的变形
    public static int limit = 100000;
    // 无限制情况下的方法数，得到dp
    // 可以用这个dp，去求解，2元 交上 3元的违规方法
    public static long[] dp = new long[limit + 1];
    public static int[] c = new int[4];
    public static int[] d = new int[4];
    public static int n, s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            // 先给每一种面值
            // 存好，可能有多组查询，但是有哪些面值是不变的
            // 面值的种数也不变，4种面值
            c[0] = (int) in.nval;
            in.nextToken();
            c[1] = (int) in.nval;
            in.nextToken();
            c[2] = (int) in.nval;
            in.nextToken();
            c[3] = (int) in.nval;
            in.nextToken();
            // 有多少组查询
            n = (int) in.nval;
            init();
            for (int i = 0; i < n; i++) {
                // 一组查询
                // a 面值 给你多少张
                // b 面值 给你多少张
                // c 面值 给你多少张
                // d 面值 给你多少张
                in.nextToken();
                d[0] = (int) in.nval;
                in.nextToken();
                d[1] = (int) in.nval;
                in.nextToken();
                d[2] = (int) in.nval;
                in.nextToken();
                d[3] = (int) in.nval;
                in.nextToken();
                // 花钱总数
                // 不超过10的5次方的
                s = (int) in.nval;
                out.println(query());
                out.flush();
            }
        }
    }

    // 不限制张数的情况下用所有的货币搞定面额的总共方法数
    public static void init() {
        dp[0] = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = c[i]; j <= limit; j++) {
                dp[j] += dp[j - c[i]];
            }
        }
    }

    public static long query() {
        long minus = 0; // 违规的方法数
        // 用状态来标识 比如 0 1 1 0 就是第二种钱币和第三种钱币相交的数量
        for (int status = 1; status <= 15; status++) {
            long t = s;
            int sign = -1;
            for (int j = 0; j <= 3; j++) {
                // 当前货币是否存在
                if (((status >> j) & 1) == 1) {
                    t -= c[j] * (d[j] + 1);// 减去违规的
                    sign = -sign; // 容斥原理，+ - 交替
                }
            }
            if (t >= 0) {
                minus += dp[(int) t] * sign;
            }
        }
        // 用总共的减去违规的
        return dp[s] - minus;
    }
}
