package duys.class_09_13;

/**
 * @ClassName StrangePrinter
 * @Author Duys
 * @Description 力扣原题: https://leetcode-cn.com/problems/strange-printer/
 * @Date 2021/9/13 14:05
 **/
public class StrangePrinter {
    /**
     * 题意：给一个str，每一次从L到R位置上只能刷成一种字符，问如果需要刷成str的样子，需要刷几次
     */
    public static int printer1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1);
    }

    // L .. R范围上要刷成 str的样子。返回最小的刷的次数
    public static int process1(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        int ans = R - L + 1;
        // 去枚举每一种可能，
        // 从 L ~ L  L+1 ~ R
        // 从 L ~ L+1  L+2 ~ R ..... 依次枚举
        for (int i = L + 1; i <= R; i++) {
            int p1 = process1(str, L, i - 1);
            int p2 = process1(str, i, R);
            // 这里的含义是：如果i位置和L位置是相同的，就表示i位置在刷L位置的时候已经刷了，不需要刷了，这里需要减去
            int p3 = str[i] == str[L] ? 1 : 0;
            ans = Math.min(ans, p1 + p2 - p3);
        }
        return ans;
    }

    public static int printer2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        return process2(str, 0, str.length - 1, dp);
    }

    public static int process2(char[] str, int L, int R, int[][] dp) {
        if (dp[L][R] != 0) {
            return dp[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = 1;
        } else {
            for (int i = L + 1; i <= R; i++) {
                int p1 = process2(str, L, i - 1, dp);
                int p2 = process2(str, i, R, dp);
                // 这里的含义是：如果i位置和L位置是相同的，就表示i位置在刷L位置的时候已经刷了，不需要刷了，这里需要减去
                int p3 = str[i] == str[L] ? 1 : 0;
                ans = Math.min(ans, p1 + p2 - p3);
            }
        }
        dp[L][R] = ans;
        return ans;
    }

    public static int printer3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;// 对角线
            // i 和自己的下一个位置如果都相同，只需要刷一次，不同则刷两次，这是第二条对角线
            dp[i][i + 1] = str[i] == str[i + 1] ? 1 : 2;
        }
        // 普遍格子的依赖关系
        // 每一个格子依赖自己左边的格子和下边的格子
        // 那么从下网上，从左往右填
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int ans = R - L + 1;
                // 去枚举每一种可能，
                // 从 L ~ L  L+1 ~ R
                // 从 L ~ L+1  L+2 ~ R ..... 依次枚举
                for (int i = L + 1; i <= R; i++) {
                    int p1 = dp[L][i - 1];
                    int p2 = dp[i][R];
                    // 这里的含义是：如果i位置和L位置是相同的，就表示i位置在刷L位置的时候已经刷了，不需要刷了，这里需要减去
                    int p3 = str[i] == str[L] ? 1 : 0;
                    ans = Math.min(ans, p1 + p2 - p3);
                }
                dp[L][R] = ans;
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "aaabbb";
        System.out.println(printer3(s));
    }
}
