package hope.class87;

import java.io.*;

/**
 * @author Mr.Du
 * @ClassName Code01_BuyMonster
 * @date 2024年08月19日 下午 05:00
 */
// 贿赂怪兽
// 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的n只怪兽
// 如果你当前的能力小于i号怪兽的能力，则必须付出b[i]的钱贿赂这个怪兽
// 然后怪兽就会加入你，他的能力a[i]直接累加到你的能力上
// 如果你当前的能力大于等于i号怪兽的能力，你可以选择直接通过，且能力不会下降
// 但你依然可以选择贿赂这个怪兽，然后怪兽的能力直接累加到你的能力上
// 返回通过所有的怪兽，需要花的最小钱数
// 测试链接 : https://www.nowcoder.com/practice/736e12861f9746ab8ae064d4aae2d5a9
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code01_BuyMonster {
    // 讲解本题的目的不仅仅是为了通过这个题，而是进行如下的思考:
    // 假设a[i]数值的范围很大，但是b[i]数值的范围不大，该怎么做？
    // 假设a[i]数值的范围不大，但是b[i]数值的范围很大，又该怎么做？

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            int[] a = new int[n + 1];
            int[] b = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                a[i] = (int) in.nval;
                in.nextToken();
                b[i] = (int) in.nval;
            }
            out.println(compute1(n, a, b));
        }
        out.flush();
        out.close();
        br.close();
    }

    // 1.假设a[i]数值的范围很大，但是b[i]数值的范围不大
    // 时间复杂度O(n * 所有怪兽的钱数累加和)
    public static int compute1(int n, int[] a, int[] b) {
        int money = 0;
        for (int m : b) {
            money += m;
        }
        // dp[i][j]含义：花的钱小于等于j，通过1...i号怪物，能得到的最大能力
        // 如果dp[i][j] == Integer.MIN_VALUE
        // 表示花的钱不能超过j，无论如何都无法通过前i个怪兽
        int[][] dp = new int[n + 1][money + 1];
        for (int i = 1; i <= n; i++) { // 怪兽
            for (int j = 0; j <= money; j++) { // 钱
                dp[i][j] = Integer.MIN_VALUE; //
                // 可能性1：选择不贿赂
                if (dp[i - 1][j] >= a[i]) { // 如果之前得到的能力 >= 当前怪兽的能力，可以选择不贿赂
                    dp[i][j] = dp[i - 1][j];
                }
                // 可能性2：选择贿赂怪兽
                if (j >= b[i] && dp[i - 1][j - b[i]] != Integer.MIN_VALUE) { // 之前的位置是有效的
                    dp[i][j] = Math.max(dp[i][j], a[i] + dp[i - 1][j - b[i]]); // 能力在之前的位置上累加，并且两种方案中取大的
                }
            }
        }
        int ans = -1;
        // 看看到n号怪物了，钱从小到大，最先有效的钱，就是通关的最小花费
        for (int j = 0; j <= money; j++) {
            if (dp[n][j] != Integer.MIN_VALUE) {
                ans = j;
                break;
            }
        }
        return ans;
    }

    // 上面方法的空间压缩
    // 每一行只是依赖前一行的答案，所以一个一维数组往下递推就可以了
    public static int compute2(int n, int[] a, int[] b) {
        int money = 0;
        for (int m : b) {
            money += m;
        }
        int[] dp = new int[money + 1];
        for (int i = 1, cur; i <= n; i++) {
            for (int j = 0; j <= money; j++) {
                cur = Integer.MIN_VALUE;
                if (dp[j] >= a[i]) {
                    cur = dp[j];
                }
                if (j >= b[i] && dp[j - b[i]] != Integer.MIN_VALUE) {
                    cur = Math.max(cur, dp[j - b[i]] + a[i]);
                }
                dp[j] = cur;
            }
        }
        int ans = -1;
        for (int j = 0; j <= money; j++) {
            if (dp[j] != Integer.MIN_VALUE) {
                ans = j;
                break;
            }
        }
        return ans;
    }


    // 2.假设b[i]数值的范围很大，但是a[i]数值的范围不大
    // 时间复杂度O(n * 所有怪兽的能力数累加和)
    public static int compute3(int n, int[] a, int[] b) {
        int power = 0;
        for (int p : b) {
            power += p;
        }
        // dp[i][j]含义：能力正好为j，并且确保能通过1...i号怪兽，最少的钱数
        // 如果dp[i][j] =Integer.MAX_VALUE 表示不能通过
        int[][] dp = new int[n + 1][power + 1];
        for (int j = 1; j <= power; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= power; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                // 不贿赂,能力>= 当前，并且之前的花费不能是无效的
                if (j >= a[i] && dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 贿赂
                if (j >= a[i] && dp[i - 1][j - a[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - a[i]] + b[i]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= power; j++) {
            ans = Math.min(ans, dp[n][j]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 上面方法的空间压缩
    // 每一行只是依赖前一行的答案，所以一个一维数组往下递推就可以了
    public static int compute4(int n, int[] a, int[] b) {
        int power = 0;
        for (int p : b) {
            power += p;
        }
        int[] dp = new int[power + 1];
        for (int j = 1; j <= power; j++) {
            dp[j] = Integer.MAX_VALUE;
        }
        for (int i = 1, cur; i <= n; i++) {
            for (int j = 0; j <= power; j++) {
                cur = Integer.MAX_VALUE;
                // 不贿赂,能力>= 当前，并且之前的花费不能是无效的
                if (j >= a[i] && dp[j] != Integer.MAX_VALUE) {
                    cur = dp[j];
                }
                // 贿赂
                if (j >= a[i] && dp[j - a[i]] != Integer.MAX_VALUE) {
                    cur = Math.min(cur, dp[j - a[i]] + b[i]);
                }
                dp[j] = cur;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= power; j++) {
            ans = Math.min(ans, dp[j]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
