package custom.code_2023_09;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2186
 * @date 2023年09月07日
 */
// 2186. 使两字符串互为字母异位词的最少步骤数
// https://leetcode.cn/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii/
public class LeetCode_2186 {
    public int minSteps(String s, String t) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0, j = 0; i < s.length() || j < t.length(); i++, j++) {
            if (i < s.length()) {
                cnt1[s.charAt(i) - 'a']++;
            }
            if (j < t.length()) {
                cnt2[t.charAt(j) - 'a']++;
            }
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += Math.abs(cnt2[i] - cnt1[i]);
        }
        return ans;
    }
}
