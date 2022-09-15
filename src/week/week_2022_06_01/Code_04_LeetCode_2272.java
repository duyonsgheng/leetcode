package week.week_2022_06_01;

/**
 * @ClassName Code_04_LeetCode_2272
 * @Author Duys
 * @Description
 * @Date 2022/6/9 14:07
 **/
// 2272. 最大波动的子字符串
// 字符串的 波动定义为子字符串中出现次数 最多的字符次数与出现次数 最少的字符次数之差。
//
//给你一个字符串s，它只包含小写英文字母。请你返回 s里所有 子字符串的最大波动值。
//
//子字符串 是一个字符串的一段连续字符序列。
//链接：https://leetcode.cn/problems/substring-with-largest-variance
public class Code_04_LeetCode_2272 {
    // 首先我们使用暴力的方法来做
    // 我尝试每一种字符作为多的 和少的
    public static int largestVariance(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        int ans = 0;
        for (int more = 0; more < 26; more++) {
            for (int less = 0; less < 26; less++) {
                if (more == less) {
                    continue;
                }
                // 连续A多少个
                int lianxuA = 0;
                // 是否出现了B
                boolean appearB = false;
                int max = 0;
                for (int i = 0; i < n; i++) {
                    if (arr[i] != more && arr[i] != less) {
                        continue;
                    }
                    if (arr[i] == more) {
                        lianxuA++;
                        // 多的字符连续出现了，但是如果出现了B，则max需要更新
                        if (appearB) {
                            max++;
                        }
                    } else {
                        max = Math.max(max, lianxuA) - 1;
                        appearB = true;
                        lianxuA = 0;// 连续A归0
                    }
                    ans = Math.max(ans, max);
                }
            }
        }
        return ans;
    }

    public static int largestVariance1(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        int ans = 0;
        // dp[a][b] 就是 more 是a less 是b
        // dp[b][a] 就是 more 是b less 是a
        int[][] dp = new int[26][26];
        // continuous[a][b] more 是a less 是b 连续出现a的次数
        // continuous[b][a] more 是b less 是a 连续出现b的次数
        int[][] continuous = new int[26][26];
        // appear[a][b] more 是a less 是b b有没有出现过
        // appear[b][a] more 是b less 是a a有没有出现过
        boolean[][] appear = new boolean[26][26];
        for (int i : arr) {
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    continue;
                }
                // i作为more，j作为less
                ++continuous[i][j];
                if (appear[i][j]) {
                    ++dp[i][j];
                }
                // j作为more i作为less
                if (!appear[j][i]) {
                    appear[j][i] = true;
                    dp[j][i] = continuous[j][i] - 1;
                } else {
                    dp[j][i] = Math.max(dp[j][i], continuous[j][i]) - 1;
                }
                continuous[j][i] = 0;
                ans = Math.max(ans, Math.max(dp[i][j], dp[j][i]));
            }
        }
        return ans;
    }
}
