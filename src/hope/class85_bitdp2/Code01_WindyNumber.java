package hope.class85_bitdp2;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code01_WindyNumber
 * @date 2024年05月16日 下午 03:50
 */
// windy数
// 不含前导零且相邻两个数字之差至少为2的正整数被称为windy数
// windy想知道[a,b]范围上总共有多少个windy数
// 测试链接 : https://www.luogu.com.cn/problem/P2657
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code01_WindyNumber {

    public static int manlen = 11;
    //
    public static int[][][] dp = new int[manlen][11][2];

    public static void build(int len) {
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= 10; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int a = (int) in.nval;
            in.nextToken();
            int b = (int) in.nval;
            out.println(compute(a, b));
        }
        out.flush();
        out.close();
        br.close();
    }


    public static int compute(int a, int b) {
        // 大范围的 - 小范围的，就是满足需要的
        return cnt(b) - cnt(a - 1);
    }

    // 0...num范围上windy数的个数
    public static int cnt(int num) {
        if (num == 0) {
            return 1;
        }
        int len = 1;
        int offset = 1;
        int tmp = num / 10;
        while (tmp > 0) {
            len++;
            offset *= 10;
            tmp /= 10;
        }
        build(len);
        return f(num, offset, len, 10, 0);
    }

    // 还有len位没决定
    // 前一位数字是啥，=10 表示之前没有选择数字
    // 如果之前的抉择是和num的前缀保持一致，则等于0，表示接下来不能胡乱选择
    // 如果之前的抉择是比num的前缀小，则等于1，表示接下来可以自由选择。只要保持满足相邻之差大于等于2
    public static int f(int num, int offset, int len, int pre, int free) {
        if (len == 0) {
            return 1; // 找到了一个合法的
        }
        if (dp[len][pre][free] != -1) {
            return dp[len][pre][free];
        }
        int cur = num / offset % 10; // 拿出当前位置的数字
        int ans = 0;

        if (free == 0) { // 之前是和num的前缀保持一致的，接下来选择不能大于num对应的位置的数字
            if (pre == 10) { // 之前没做出选择
                // 可以继续和之前保持一致，不作出选择，也就说一个数字也不要
                ans += f(num, offset / 10, len - 1, 10, 1);
                // 开始做抉择，选择比num还小的
                for (int i = 1; i < cur; i++) {
                    ans += f(num, offset / 10, len - 1, i, 1);
                }
                // 选择和num对应位置一样的
                ans += f(num, offset / 10, len - 1, cur, 0);
            } else { // 之前有选择的
                // 虽然之前做出选择了，但是之前选择的和num保持一致的，所以接下来就不能随意选择
                for (int i = 0; i <= 9; i++) {
                    if (i <= pre - 2 || i >= pre + 2) {
                        if (i < cur) {
                            ans += f(num, offset / 10, len - 1, i, 1);
                        } else if (i == cur) {
                            ans += f(num, offset / 10, len - 1, i, 0);
                        }
                    }
                }
            }
        } else { // 之前的选择就已经小于了num，那么接下来可以随意选择
            if (pre == 10) { // 之前没做出选择
                // 同样可以不做选择
                ans += f(num, offset / 10, len - 1, 10, 1);
                for (int i = 1; i <= 9; i++) {
                    ans += f(num, offset / 10, len - 1, i, 1);
                }
            } else { // 之前做出选择了，且当下可以随意选择
                for (int i = 0; i <= 9; i++) {
                    if (i <= pre - 2 || i >= pre + 2) {
                        ans += f(num, offset / 10, len - 1, i, 1);
                    }
                }
            }
        }
        dp[len][pre][free] = ans;
        return ans;
    }
}
