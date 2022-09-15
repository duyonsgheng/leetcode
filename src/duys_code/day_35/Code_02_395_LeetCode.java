package duys_code.day_35;

/**
 * @ClassName Code_02_395_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/
 * @Date 2021/12/8 13:06
 **/
public class Code_02_395_LeetCode {
    // 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

    // 题目给定：只有小写字母
    // 所以我们尝试 1到26个小写字符的
    // 大体思路：当只包含1种字符，只包含两种字符，。。。。包含26种字符
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int n = str.length;
        int ans = 0;
        for (int require = 1; require <= 26; require++) {
            // 词频统计
            int[] count = new int[26];
            // 当前窗口已经收集了几种字符
            int collect = 0;
            // 当前窗口内出现了次数>=k的字符有几种了
            int satisfy = 0;
            // 窗口的有边界
            int r = -1;
            // 窗口的左边界尝试每一个位置
            for (int l = 0; l < n; l++) {
                // 窗口内不满足字符种类是require个，并且r+1位置上的字符还没出现在窗口里面
                // 那么往右扩展窗口
                while (r + 1 < n && !(collect == require && count[str[r + 1] - 'a'] == 0)) {
                    r++;
                    // 如果是新的一种字符
                    if (count[str[r] - 'a'] == 0) {
                        collect++;
                    }
                    // 如果是老的字符，但是个数出现了 k次。满足的个数加1
                    if (count[str[r] - 'a'] == k - 1) {
                        satisfy++;
                    }
                    count[str[r] - 'a']++;
                }
                //
                if (satisfy == collect) {
                    ans = Math.max(ans, r - l + 1);
                }
                // 该缩窗口了
                if (count[str[l] - 'a'] == 1) {
                    collect--;
                }
                if (count[str[l] - 'a'] == k) {
                    satisfy--;
                }
                count[str[l] - 'a']--;
            }
        }
        return ans;
    }
}
