package week.week_2023_07_04;

/**
 * @author Mr.Du
 * @ClassName Code_04_LeetCode_474
 * @date 2023年07月27日
 */
// https://leetcode.cn/problems/ones-and-zeroes/
public class Code_04_LeetCode_474 {
    public static int findMaxForm1(String[] strs, int m, int n) {
        int len = strs.length;
        // 来到当前字符串，0多少个，1多少个
        int[][] arr = new int[len][2];
        for (int i = 0, zeros, ones; i < strs.length; i++) {
            zeros = 0;
            ones = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            arr[i][0] = zeros;
            arr[i][1] = ones;
        }
        return process1(arr, 0, m, n);
    }

    // 来到i位置字符串
    // 剩下 z个0
    // 剩下 o个1
    public static int process1(int[][] arr, int i, int z, int o) {
        if (i == arr.length) {
            return 0;
        }
        // 要当前位置
        int p1 = process1(arr, i + 1, z, o);
        int p2 = 0;
        if (arr[i][0] <= z && arr[i][1] <= o) {
            p2 = process1(arr, i + 1, z - arr[i][0], o - arr[i][1]);
        }
        return Math.max(p1, p2);
    }

    public static int zeros, ones;

    public static void zeroAndOne(String str) {
        zeros = 0;
        ones = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                zeros++;
            } else {
                ones++;
            }
        }
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            zeroAndOne(str);
            // 0 的个数
            for (int i = m; i >= zeros; i--) {
                // 1的个数
                for (int j = n; j >= ones; j--) {
                    // 要当前和不要当前的取最大
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
