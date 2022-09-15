package duys_code.day_24;

/**
 * @ClassName Code_06_ChildStr
 * @Author Duys
 * @Description
 * @Date 2021/11/15 13:41
 **/
public class Code_06_ChildStr {
    /**
     * str1包含str2的所有字符的最短字串，有单调性，那么首先尝试窗口。欠债模型
     * 1.根据str2建立一个词频表。设置总共需要还的字符 all
     * 2.遍历str1。如果在词频中当前字符个数>0 有效还款，all -- ，如果当前字符的词频 < 0 。all 不变
     * 3.all = 0 的时候。当前有效的字串产生，然后收缩窗口。
     * 4.窗口开始收缩。收缩的时候字符词频依次。根据第2条的规则收缩和词频记录。当all > 0 的时候。窗口往右扩展
     */
    public static int minLength(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return Integer.MAX_VALUE;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // Map<Character, Integer> map = new HashMap<>();
        int[] map = new int[256];
        for (int i = 0; i < str2.length; i++) {
            map[str2[i]]++;
        }
        int all = str2.length;
        int L = 0;
        int R = 0;
        int ans = Integer.MAX_VALUE;
        while (R != str1.length) {
            map[str1[R]]--;
            if (map[str1[R]] >= 0) {
                all--;
            }
            // 如果all == 0 了。需要收缩窗口。来获取更好的答案了
            if (all == 0) {
                // 收缩，收缩的标准是我们的词频表所有的都是 <= 0 的。
                while (map[str1[L]] < 0) {
                    map[str1[L++]]++;
                }
                ans = Math.min(R - L + 1, ans);
                all++;
                map[str1[L++]]++;
            }
            R++;
        }
        return ans;
    }
}
