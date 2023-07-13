package custom.code_2023_06;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1930
 * @date 2023年07月05日
 */
// 1930. 长度为 3 的不同回文子序列
// https://leetcode.cn/problems/unique-length-3-palindromic-subsequences/
public class LeetCode_1930 {
    // 当前字符在之前出现了几个
    // 当前字符在之后出现了几个
    // 以为要求是长度为3的回文，那么就以每一个字符作为中点来枚举
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] pre = new int[26];
        int[] suf = new int[26];
        // 以当前字符作为中心点
        boolean[][] visited = new boolean[26][26];
        for (int i = n - 1; i >= 0; i--) {
            suf[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            suf[index]--;
            // 当前字符作为中心点，看看前后是否出现过相同的字符
            for (int j = 0; j < 26; j++) {
                visited[j][index] |= pre[j] >= 1 && suf[j] >= 1;
            }
            pre[index]++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                ans += visited[i][j] ? 1 : 0;
            }
        }
        return ans;
    }
}
