package hope.class85_bitdp2;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code02_MengNumber
 * @date 2024年05月16日 下午 04:48
 */
// 萌数
// 如果一个数字，存在长度至少为2的回文子串，那么这种数字被称为萌数
// 比如101、110、111、1234321、45568
// 求[l,r]范围上，有多少个萌数
// 由于答案可能很大，所以输出答案对1000000007求余
// 测试链接 : https://www.luogu.com.cn/problem/P3413
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code02_MengNumber {
    public static int MOD = 1_000_000_007;
    public static int maxn = 1001;
    public static int[][][][] dp = new int[maxn][11][11][2];

    public static void build(int n) {
        for (int a = 0; a < n; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int c = 0; c <= 10; c++) {
                    for (int d = 0; d <= 1; d++) {
                        dp[a][b][c][d] = -1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String[] strs = br.readLine().split(" ");
        out.println(compute(strs[0].toCharArray(), strs[1].toCharArray()));
        out.flush();
        out.close();
        br.close();
    }

    // 依然是老套路，大范围的 - 小范围的，然后临界值单独算
    public static int compute(char[] l, char[] r) {
        int ans = (cnt(r) - cnt(l) + MOD) % MOD;
        if (check(l)) {
            ans = (ans + 1) % MOD;
        }
        return ans;
    }

    // 0...num范围上有多少萌数
    public static int cnt(char[] num) {
        if (num[0] == '0') { // 数字开头位置是0，只有可能是0
            return 0;
        }
        int n = num.length;
        long all = 0;
        long base = 1;
        // 把数的总数算出来
        for (int i = n - 1; i >= 0; i--) {
            all = (all + base * (num[i] - '0')) % MOD;
            base = (base * 10) % MOD;
        }
        build(n);
        // 所有的减去不是萌数的，那么剩下就是萌数
        return (int) ((all - f(num, 0, 10, 10, 0) + MOD) % MOD);
    }

    // 算出0...num范围上不是萌数的个数
    // 不是萌数的定义就是 i和i-1不等 且 i 和 i-2位置也不等
    // 当前来到i位置
    // i-2 位置是prepre
    // i-1 位置是 pre
    // free = 1 表示可以自由选择
    // free = 0 表示之前选择和num保持一致，不能自由选择
    public static int f(char[] num, int i, int prepre, int pre, int free) {
        if (i == num.length) {
            return 1; // 都来到了-1位置了，说明之前的抉择有效
        }
        if (dp[i][prepre][pre][free] != -1) {
            return dp[i][prepre][pre][free];
        }
        int ans = 0;
        if (free == 0) { // 不能自由选择
            if (pre == 10) { // 之前没有选择数字
                // 当前也可以不选
                ans = (ans + f(num, i + 1, 10, 10, 1)) % MOD;
                for (int cur = 1; cur < num[i] - '0'; cur++) {
                    ans = (ans + f(num, i + 1, pre, cur, 1)) % MOD;
                }
                ans = (ans + f(num, i + 1, pre, num[i] - '0', 0)) % MOD;
            } else {
                // 之前做出过选择，而且和num一样，那么当前就不能随意选择
                for (int cur = 1; cur < num[i] - '0'; cur++) {
                    if (cur != pre && cur != prepre) {
                        ans = (ans + f(num, i + 1, pre, cur, 1)) % MOD;
                    }
                }
                if (num[i] - '0' != pre && num[i] - '0' != prepre) {
                    ans = (ans + f(num, i + 1, pre, num[i] - '0', 0)) % MOD;
                }
            }
        } else { // 可以自由选择
            if (pre == 10) {
                // 之前没有选
                // 也不选择
                ans = (ans + f(num, i + 1, 10, 10, 1)) % MOD;
                // 选择1...9之间的
                for (int cur = 1; cur <= 9; cur++) {
                    ans = (ans + f(num, i + 1, pre, cur, 1)) % MOD;
                }
            } else {
                // 之前有选择
                for (int cur = 0; cur <= 9; cur++) {
                    if (cur != pre && cur != prepre) {
                        ans = (ans + f(num, i + 1, pre, cur, 1)) % MOD;
                    }
                }
            }
        }
        dp[i][prepre][pre][free] = ans;
        return ans;
    }

    // 单独校验，满足 i!=i-1 && i!= i-2
    public static boolean check(char[] num) {
        for (int pp = -2, p = -1, i = 0; i < num.length; pp++, p++, i++) {
            if (pp >= 0 && num[pp] == num[i]) {
                return true;
            }
            if (p >= 0 && num[p] == num[i]) {
                return true;
            }
        }
        return false;
    }
}
