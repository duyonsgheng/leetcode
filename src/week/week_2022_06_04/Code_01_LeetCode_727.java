package week.week_2022_06_04;

/**
 * @ClassName Code_01_LeetCode_727
 * @Author Duys
 * @Description
 * @Date 2022/6/29 20:02
 **/
// 最小窗口子序列
// 给定字符串 S and T，找出 S 中最短的（连续）子串 W ，使得 T 是 W 的 子序列 。
// 如果 S 中没有窗口可以包含 T 中的所有字符，返回空字符串 ""。
// 如果有不止一个最短长度的窗口，返回开始位置最靠左的那个。
// 示例 1：
// 输入：
// S = "abcdebdde", T = "bde"
// 输出："bcde"
// 解释：
// "bcde" 是答案，因为它在相同长度的字符串 "bdde" 出现之前。
// "deb" 不是一个更短的答案，因为在窗口中必须按顺序出现 T 中的元素。
// 测试链接 : https://leetcode.cn/problems/minimum-window-subsequence/
public class Code_01_LeetCode_727 {
    /**
     * 在S中最短的连续串中的子序列能搞定T
     * 思路：先在S中找到以T字符串开头字母的位置，往下找，依次找到每一个在S中以T字符串开头字母的位置，找到最短的
     * 1.动态规划。
     * 2.记录所有字符都在哪些位置，使用TreeSet。比如当前来到a，位置是10 我们需要找到a的下一个字符b在>=10的最近出现在那儿了。
     */

    // 暴力解答
    public String minWindow1(String s, String t) {
        if (s == null || t == null) {
            return null;
        }
        char[] str = s.toCharArray();
        char[] tar = t.toCharArray();
        int n = str.length;
        int m = tar.length;
        if (m > n) {
            return null;
        }
        int l = -1;
        int r = -1;
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int next = process(str, tar, i, 0);
            if (next != Integer.MAX_VALUE && next - i < len) {
                l = i;
                r = next;
                len = r - l;
            }
        }
        return l == -1 ? "" : s.substring(l, r);
    }

    // str[si....]
    // tar[ti....]
    // str从si出发搞定tar从ti出发的下一个位置
    public static int process(char[] str, char[] tar, int si, int ti) {
        // 如果ti都来到了tar的最后一个位置，说明搞定了，返回si就行
        if (ti == tar.length) {
            return si;
        }
        // 如果ti还有位置没去，但是si到了str的最后位置了，说明搞不定
        if (si == str.length) {
            return Integer.MAX_VALUE;
        }
        // 不要则当前位置
        int p1 = process(str, tar, si + 1, ti);
        int p2 = str[si] == tar[ti] ? process(str, tar, si + 1, ti + 1) : Integer.MAX_VALUE;
        return Math.min(p1, p2);
    }

    // 动态规划
    public String minWindow2(String s, String t) {
        if (s == null || t == null) {
            return null;
        }
        char[] str = s.toCharArray();
        char[] tar = t.toCharArray();
        int n = str.length;
        int m = tar.length;
        if (m > n) {
            return null;
        }
        int[][] dp = new int[n + 1][m + 1];
        // si可以到越界的位置，但是ti不能
        for (int si = 0; si <= n; si++) {
            dp[si][m] = si;
        }
        for (int ti = 0; ti < m; ti++) {
            dp[n][ti] = Integer.MAX_VALUE;
        }
        // 我当前位置都依赖我的后一个位置
        for (int si = n - 1; si >= 0; si--) {
            for (int ti = m - 1; ti >= 0; ti--) {
                int p1 = dp[si + 1][ti];
                int p2 = str[si] == tar[ti] ? dp[si + 1][ti + 1] : Integer.MAX_VALUE;
                dp[si][ti] = Math.min(p1, p2);
            }
        }
        int len = Integer.MAX_VALUE;
        int l = -1;
        int r = -1;
        for (int i = 0; i < n; i++) {
            int next = dp[i][0];
            if (next != Integer.MAX_VALUE && next - i < len) {
                l = i;
                r = next;
                len = r - l;
            }
        }
        return l == -1 ? "" : s.substring(l, r);
    }
}
