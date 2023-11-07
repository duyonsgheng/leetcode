package custom.code_2023_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2131
 * @date 2023年08月28日
 */
// 2131. 连接两字母单词得到的最长回文串
// https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words/
public class LeetCode_2131 {
    public int longestPalindrome(String[] words) {
        int[][] map = new int[26][26];
        for (String str : words) {
            map[str.charAt(0) - 'a'][str.charAt(1) - 'a']++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < i; j++) {
                if (map[i][j] != 0) {
                    ans += 4 * Math.min(map[i][j], map[j][i]);
                }
            }
        }
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            if (map[i][i] == 0) {
                continue;
            }
            if (map[i][i] % 2 == 1) {
                flag = true;
                ans += 2 * (map[i][i] - 1);
            } else {
                ans += 2 * map[i][i];
            }
        }
        if (flag) {
            ans += 2;
        }
        return ans;
    }
}
