package duys_code.day_34;

/**
 * @ClassName Code_08_340_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * @Date 2021/12/7 14:31
 **/
public class Code_08_340_LeetCode {

    // 在s的字串中，必须要包含k中字符，最长的字串长度
    // 一看这个问题 具有单调性，s长度边长，字符种类只可能变多或者不变
    // 具有单调性的题，使用窗口
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() <= 0 || k < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        // 词频
        int[] count = new int[256];
        int diff = 0; // 窗口内字符种类数
        int r = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 窗口的右边界，窗口边界扩展
            while (r < n && diff < k || (diff == k) && count[str[r]] > 0) {
                // 窗口即将扩展，如果当前位置之前没有来到，那么现在来到了，种类增加1个
                diff += count[str[r]] == 0 ? 1 : 0;
                // 词频增加，窗口向右扩展
                count[str[r++]]++;
            }
            // 窗口形成了，需要收集答案，并且左边开始缩窗口了
            ans = Math.max(ans, r - i);
            // 窗口即将缩小，如果i位置上的字符只有一个，那么就减去种类
            diff -= count[str[i]] == 1 ? 1 : 0;
            count[str[i]]--;
        }
        return ans;
    }
}
