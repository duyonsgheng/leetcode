package week.week_2022_08_04;

import java.util.Arrays;

/**
 * @ClassName Code_02_ChangeToSame
 * @Author Duys
 * @Description
 * @Date 2022/8/25 9:18
 **/
// 来自美团
// 8.20笔试
// 小团生日收到妈妈送的两个一模一样的数列作为礼物！
// 他很开心的把玩，不过不小心没拿稳将数列摔坏了！
// 现在他手上的两个数列分别为A和B，长度分别为n和m。
// 小团很想再次让这两个数列变得一样。他现在能做两种操作：
// 操作一是将一个选定数列中的某一个数a改成数b，这会花费|b-a|的时间，
// 操作二是选择一个数列中某个数a，将它从数列中丢掉，花费|a|的时间。
// 小团想知道，他最少能以多少时间将这两个数列变得再次相同！
// 输入描述：
// 第一行两个空格隔开的正整数n和m，分别表示数列A和B的长度。
// 接下来一行n个整数，分别为A1 A2…An
// 接下来一行m个整数，分别为B1 B2…Bm
// 对于所有数据，1 ≤ n,m ≤ 2000， |Ai|,|Bi| ≤ 10000
// 输出一行一个整数，表示最少花费时间，来使得两个数列相同。
public class Code_02_ChangeToSame {
    // 分析
    // arr1 {a,b,c,d}
    // arr2 {1,2,3,4}
    // 来到arr1的每一位置，看看是删除还是变
    public static int minCost(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(A, B, 0, 0, dp);
    }

    public static int process(int[] a, int[] b, int ia, int ib, int[][] dp) {
        if (ia == a.length && ib == b.length) {
            return 0;
        }
        if (dp[ia][ib] != -1) {
            return dp[ia][ib];
        }
        int ans = 0;
        if (ia == a.length && ib != b.length) {
            ans = b[ib] + process(a, b, ia, ib + 1, dp);
        } else if (ia != a.length && ib == b.length) {
            ans = a[ia] + process(a, b, ia + 1, ib, dp);
        } else {
            // 都还有位可选
            // p1 删掉ia
            int p1 = a[ia] + process(a, b, ia + 1, ib, dp);
            // p2 删掉ib
            int p2 = b[ib] + process(a, b, ia, ib + 1, dp);
            // p3 ia ib 同时删掉
            //int p3 = a[ia] + b[ib] + process(a, b, ia + 1, ib + 1);
            // p4 ia 变成 ib
            int p4 = Math.abs(a[ia] - b[ib]) + process(a, b, ia + 1, ib + 1, dp);
            // p5 ia 和 ib相等
            //int p5 = process(a, b, ia + 1, ib + 1);
            // 分析 p3 被p4 的过程分解了 p5 被p4的过程包含了

            ans = Math.min(p1, Math.min(p2, p4));
        }
        dp[ia][ib] = ans;
        return ans;
    }
}
