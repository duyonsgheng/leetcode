package week.week_2022_08_02;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName Code_01_ParenthesesDye
 * @Author Duys
 * @Description
 * @Date 2022/8/11 8:55
 **/
// 来自猿辅导
// 2022.8.7笔试第三道
// 给定一个数组arr，和一个正数k
// 如果arr[i] == 0，表示i这里既可以是左括号也可以是右括号，
// 而且可以涂上1~k每一种颜色
// 如果arr[i] != 0，表示i这里已经确定是左括号，颜色就是arr[i]的值
// 那么arr整体就可以变成某个括号字符串，并且每个括号字符都带有颜色
// 返回在括号字符串合法的前提下，有多少种不同的染色方案
// 不管是排列、还是颜色，括号字符串任何一点不一样，就算不同的染色方案
// 最后的结果%10001，为了方便，我们不处理mod，就管核心思路
// 2 <= arr长度 <= 5000
// 1 <= k <= 1000
// 0 <= arr[i] <= k
public class Code_01_ParenthesesDye {
    /**
     * 1.先想明白一个问题当我们源字符串中存在了a个确定的位置了，也就是arr[i]!=0 ，那么左括号就已经存在了a个，并且颜色也确定
     * 与之配对的a个右括号，那么剩下多少对呢？ (n-2a)/2 颜色有k种，那么就是k^((n-2a)/2) 这么多
     * 2.所以我们需要先确定有多少种合法的方案，然后来算出颜色
     */
    public static int ways(int[] arr, int k) {
        int n = arr.length;
        // 奇数就不行，配对不了
        if ((n & 1) != 0) {
            return 0;
        }
        int way = totalWays(arr);
        int a = 0;
        for (int num : arr) {
            if (num != 0) {
                a++;
            }
        }
        return way * ((int) (Math.pow((double) k, (double) ((n - (a << 1)) >> 1))));
    }

    public static int totalWays(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(arr, 0, 0, dp);
    }

    // 当前来到i位置，i之前做的决定种，剩下左括号是 m个，问要使得整个串有效，有多少不同的方案
    public static int process(int[] arr, int i, int m, int[][] dp) {
        if (i == arr.length || m < 0) {
            return m == 0 ? 1 : 0;
        }
        // 如果我们剩余的长度都不够m了，说明无效
        if (arr.length - i < m) {
            return 0;
        }
        if (dp[i][m] != -1) {
            return dp[i][m];
        }
        int ans = 0;
        // 左括号
        if (arr[i] != 0) {
            ans = process(arr, i + 1, m + 1, dp);
        } else {
            // 当前作为左括号
            int p1 = process(arr, i + 1, m + 1, dp);
            // 当前作为右括号
            int p2 = process(arr, i + 1, m - 1, dp);
            // 都是不同的方案
            ans = p1 + p2;
        }
        dp[i][m] = ans;
        return ans;
    }
}
