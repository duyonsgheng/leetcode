package hope.dp_2;

import java.io.*;
import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code05_NodenHeightNotLargerThanm
 * @date 2023年12月18日 15:37
 */
// 节点数为n高度不大于m的二叉树个数
// 现在有n个节点，计算出有多少个不同结构的二叉树
// 满足节点个数为n且树的高度不超过m的方案
// 因为答案很大，所以答案需要模上1000000007后输出
// 测试链接 : https://www.nowcoder.com/practice/aaefe5896cce4204b276e213e725f3ea
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
public class Code05_NodenHeightNotLargerThanm {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            out.println(compute2(n, m));
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int maxn = 51;
    public static int mod = 1_000_000_007;
    public static long[][] dp1 = new long[maxn][maxn];

    static {
        for (int i = 0; i < maxn; i++) {
            Arrays.fill(dp1[i], -1);
        }
    }

    // 二叉树节点为n，高度不超过m
    public static int compute1(int n, int m) {
        if (n == 0) {
            return 1;
        }
        if (m == 0) {
            return 0;
        }
        if (dp1[n][m] != -1) {
            return (int) dp1[n][m];
        }
        long ans = 0;
        // n个节点，头占1个
        for (int k = 0; k < n; k++) {
            // 如果一共n个节点，头占用了一个
            // 如果左树是k个，那么右树就是n-k-1个
            ans += (ans + ((long) compute1(k, m - 1) * compute1(n - k - 1, m - 1)) % mod) % mod;
        }
        dp1[n][m] = ans;
        return (int) ans;
    }

    public static long[][] dp2 = new long[maxn][maxn];

    public static int compute2(int n, int m) {
        for (int j = 0; j <= m; j++) {
            dp2[0][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp2[i][j] = 0;
                for (int k = 0; k < i; k++) {
                    dp2[i][j] = (dp2[i][j] + dp2[k][j - 1] * dp2[i - k - 1][j - 1] % mod) % mod;
                }
            }
        }
        return (int) dp2[n][m];
    }
}
