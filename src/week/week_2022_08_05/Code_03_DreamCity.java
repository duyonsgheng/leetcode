package week.week_2022_08_05;

import java.util.Arrays;

/**
 * @ClassName Code_03_DreamCity
 * @Author Duys
 * @Description
 * @Date 2022/9/1 9:19
 **/

// 本题测试链接 : https://zoj.pintia.cn/problem-sets/91827364500/problems/91827367873
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交如下方法，把主类名改成Main，可以直接通过
public class Code_03_DreamCity {
    // 题意:
    // tree[][] 数组
    // tree[0][] 表示树的份量
    // tree[1][] 表示树的增量-每一天的增量
    // 要求 m天砍树的最大份量
    // 分析：m天砍的树最大份量，那么我们一定要砍满m棵树，每天只能砍一颗
    public static int maxTreeWight(int[][] tree, int m) {
        int n = tree[0].length;
        int[][] dp = new int[n + 1][m + 1];
        // 1.先根据增量来排序，把增量越大的越后砍
        Arrays.sort(tree, 0, n, (a, b) -> a[1] - b[1]);
        // 第0天只能选择第0棵树，没有增量
        dp[0][0] = tree[0][0];
        // 第i棵树，在第一天砍
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], tree[i][0]);
        }
        // 第0棵树，在第i天砍
        for (int i = 1; i < m; i++) {
            // 每一天有增量
            dp[0][i] = dp[0][i - 1] + tree[0][1];
        }
        // 从左往右尝试
        for (int i = 1; i < n; i++) {
            for (int j = 1; i < m; j++) {
                // 当前树砍和不砍
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + tree[i][0] + tree[i][1] * j);
            }
        }
        return dp[n - 1][m - 1];
    }
}
