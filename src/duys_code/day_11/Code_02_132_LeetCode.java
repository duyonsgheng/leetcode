package duys_code.day_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_02_132_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode.com/problems/palindrome-partitioning-ii/
 * @Date 2021/10/18 17:21
 **/
public class Code_02_132_LeetCode {
    /**
     * 给定一个字符串，可以任意划分成一批字符串，使得划分的每一个区域内都是回文，返回最小的划分次数
     */

    /**
     * 思路：
     * 很明显是从左往右的尝试模型
     * 然后校验划分的区域内是不是回文。那么我们首先要解决的是 i~j 区域内是不是回文的问题
     * 所以是两个动态规划
     */
    public static int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] checkMap = createCheckMap(str, N);
        // dp[i] 的含义 是 i到n-1 上划分了几个部分
        int[] dp = new int[N + 1];
        dp[N] = 0;// 越界位置，0部分，划分不了
        for (int i = N - 1; i >= 0; i--) {
            if (checkMap[i][N - 1]) {
                dp[i] = 1;
            } else {
                // 枚举i到N-1的
                int next = Integer.MAX_VALUE;
                for (int j = i; j < N; j++) {
                    // 如果我当前i~j是回文，
                    if (checkMap[i][j]) {
                        // 那么我就拿j+1的值。
                        next = Math.min(next, dp[j + 1]);
                    }
                }
                // 最后算上当前这一个部分。
                dp[i] = next + 1;
            }
        }
        return dp[0] - 1;
    }

    public static boolean[][] createCheckMap(char[] str, int N) {
        boolean[][] ans = new boolean[N][N];
        // 同样的，我们的对角线以下的区域无效，对角线是true
        // 第二条对角线上如果两个字符相同就是true
        for (int i = 0; i < N - 1; i++) {
            ans[i][i] = true;
            ans[i][i + 1] = str[i] == str[i + 1];
        }
        ans[N - 1][N - 1] = true;
        // 普遍位置填就是了
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                // 两个位置的字符要相等，并且中间也是回文，才算回文
                ans[i][j] = str[i] == str[j] && ans[i + 1][j - 1];
            }
        }
        return ans;
    }

    // 2.返回其中的一个划分结果
    public static List<String> findAnsOnlyOne(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = createCheckMap(str, N);
            // dp[i] 的含义 是 i到n-1 上划分了几个部分
            int[] dp = new int[N + 1];
            dp[N] = 0;// 越界位置，0部分，划分不了
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    // 枚举i到N-1的
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        // 如果我当前i~j是回文，
                        if (checkMap[i][j]) {
                            // 那么我就拿j+1的值。
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    // 最后算上当前这一个部分。
                    dp[i] = next + 1;
                }
            }
            for (int i = 0, j = 1; j <= N; j++) {
                // 啥意思？
                if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                    ans.add(s.substring(i, j));
                    i = j;
                }
            }
        }
        return ans;
    }

    // 3.返回所有得划分结果
    public static List<List<String>> findAnsAll(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(Arrays.asList(s));
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = createCheckMap(str, N);
            // dp[i] 的含义 是 i到n-1 上划分了几个部分
            int[] dp = new int[N + 1];
            dp[N] = 0;// 越界位置，0部分，划分不了
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    // 枚举i到N-1的
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        // 如果我当前i~j是回文，
                        if (checkMap[i][j]) {
                            // 那么我就拿j+1的值。
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    // 最后算上当前这一个部分。
                    dp[i] = next + 1;
                }
            }
            process(s, 0, 1, checkMap, dp, new ArrayList(), ans);
        }
        return ans;
    }

    // s[0...i-1] 已经算出答案了，放到了path中了
    // s[i...j-1] 需要算
    public static void process(String s, int i, int j, boolean[][] checkMap, int[] dp,
                               List<String> path,
                               List<List<String>> ans) {
        if (j == s.length()) {
            // 这里我们需要考虑之前dp得时候是怎么来的
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                ans.add(Arrays.asList(path.stream().toArray(String[]::new)));
                path.remove(path.size() - 1);
            }
        } else {
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                process(s, j, j + 1, checkMap, dp, path, ans);
                path.remove(path.size() - 1);
            }
            process(s, i, j + 1, checkMap, dp, path, ans);
        }
    }
}
