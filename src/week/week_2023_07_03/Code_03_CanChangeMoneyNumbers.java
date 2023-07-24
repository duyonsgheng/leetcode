package week.week_2023_07_03;

import java.io.*;
import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code_03_CanChangeMoneyNumbers
 * @date 2023年07月20日
 */
// 每一种货币都给定面值val[i]，和拥有的数量cnt[i]
// 想知道目前拥有的货币，在钱数为1、2、3...m时，能找零成功的钱数有多少
// 也就是说当钱数的范围是1~m，返回这个范围上有多少可以找零成功的钱数
// 比如只有3元的货币，数量是5张
// m = 10
// 那么在1~10范围上，只有钱数是3、6、9时，可以成功找零
// 所以返回3，表示有3种钱数可以找零成功
// 测试链接 : http://poj.org/problem?id=1742
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code_03_CanChangeMoneyNumbers {
    // 状态压缩的dp
    public static int MAXN = 101;
    public static int[] val = new int[MAXN]; // 货币的面值
    public static int[] cnt = new int[MAXN]; // 货币的张数
    public static int MAXM = 100001;
    public static boolean[] dp = new boolean[MAXM];
    public static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            if (n != 0 || m != 0) {
                for (int i = 1; i <= n; i++) {
                    in.nextToken();
                    val[i] = (int) in.nval;
                }
                for (int i = 1; i <= n; i++) {
                    in.nextToken();
                    cnt[i] = (int) in.nval;
                }
                out.println(compute());
            } else {
                out.flush();
            }
        }
    }

    // 当前需要处理的钱是m。当前用到的零钱是 i 面值的，总共有j张
    // 如果我不用当前的面值，那么就是 dp[i-1][m]
    // 用一张 就是dp[i-1][m-arr[i]]
    // 用两张 就是dp[i-1][m-arr[i]*2]
    // ..........直到j张
    public static int compute() {
        Arrays.fill(dp, 1, m + 1, false);
        dp[0] = true;
        // 每一种货币都遍历
        for (int i = 1; i <= n; i++) {
            // 只有一张货币
            if (cnt[i] == 1) {
                for (int j = m; j >= val[i]; j--) {
                    if (dp[j - val[i]]) {
                        dp[j] = true;
                    }
                }
            }
            // 钱很多，可以自由使用
            else if (val[i] * cnt[i] > m) {
                for (int j = val[i]; j <= m; j++) {
                    if (dp[j - val[i]]) {
                        dp[j] = true;
                    }
                }
            }
            // 不是自由选择，意思就是 当前的钱用完，也不能搞定m
            else {
                // 需要做位置依赖，通过观察，发现，当前位置依赖上一行的 当前钱的取模
                for (int mod = 0; mod < val[i]; mod++) {
                    // val[i] =3
                    // 0:
                    // 1:
                    // 2:
                    int trueCnt = 0;
                    // 0 : m元 m-3元 m-6元 m-9元
                    // 1 : m-1元 m-4元 m-7元 m-10元
                    // 2 : m-2元
                    // 这里做一个窗口，
                    for (int j = m - mod, size = 0; j >= 0 && size <= cnt[i]; j -= val[i], size++) {
                        trueCnt += dp[j] ? 1 : 0;
                    }
                    // 窗口弹一个，然后加一个
                    for (int j = m - mod, l = j - val[i] * (cnt[i] + 1); j >= 1; j -= val[i], l -= val[i]) {
                        if (dp[j]) { // 出窗口了
                            trueCnt--;
                        } else {
                            if (trueCnt != 0) {
                                dp[j] = true;
                            }
                        }
                        if (l >= 0) {
                            trueCnt += dp[l] ? 1 : 0;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            if (dp[i]) {
                ans++;
            }
        }
        return ans;
    }
}
