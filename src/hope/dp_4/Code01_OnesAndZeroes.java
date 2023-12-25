package hope.dp_4;

/**
 * @author Mr.Du
 * @ClassName Code01_OnesAndZeroes
 * @date 2023年12月22日 15:04
 */
// 一和零(多维费用背包)
// 给你一个二进制字符串数组 strs 和两个整数 m 和 n
// 请你找出并返回 strs 的最大子集的长度
// 该子集中 最多 有 m 个 0 和 n 个 1
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集
// 测试链接 : https://leetcode.cn/problems/ones-and-zeroes/
public class Code01_OnesAndZeroes {
    public static int zeros, ones;

    public static void count(String str) {
        zeros = 0;
        ones = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                zeros++;
            } else ones++;
        }
    }

    public static int findMaxForm1(String[] strs, int m, int n) {
        return f1(strs, 0, m, n);
    }

    public static int f1(String[] arr, int i, int z, int o) {
        if (i == arr.length) {
            return 0;
        }
        // 不用当前字符串
        int p1 = f1(arr, i + 1, z, o);
        int p2 = 0;
        count(arr[i]);
        if (zeros <= z && ones <= o) {
            p2 = 1 + f1(arr, i + 1, z - zeros, o - ones);
        }
        return Math.max(p1, p2);
    }

    public static int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = len - 1; i >= 0; i--) {
            count(strs[i]);
            for (int z = 0, p1, p2; z <= m; z++) {
                for (int o = 0; o <= n; o++) {
                    p1 = dp[i + 1][z][o];
                    p2 = 0;
                    if (zeros <= z && ones <= o) {
                        p2 = 1 + dp[i + 1][z - zeros][o - ones];
                    }
                    dp[i][z][o] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][m][n];
    }
}
