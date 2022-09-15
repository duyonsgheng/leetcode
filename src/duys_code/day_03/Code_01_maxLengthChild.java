package duys_code.day_03;

/**
 * @ClassName Code_01_maxLengthChild
 * @Author Duys
 * @Description 力扣原题： https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @Date 2021/9/17 13:40
 **/
public class Code_01_maxLengthChild {
    /**
     * 求一个字符串中，最长无重复字符子串长度
     */

    // 字串问题，子数组问题，先想到范围尝试，以当前位置开始怎么样，以当前位置结束怎么样

    /**
     * 那么这个题我们怎么去想：
     * 首先来到 0 号位置 我们-1位置上是没有的，但是至少有一个字符，长度就是1 记录当前的字符出现位置是多少
     * 来到1号位置，当前字符是否出现过，出现过了，直接就是 1-之前出现的位置，更新一下当前字符出现的位置，没出现过，直接就是前一次的长度+1
     * 来到 i号位 i-1号位置最大长度+1，i在之前出现位置 i-之前出现的位置，两个值求一个最小值 比如
     * a  b  c  d  b  a
     * 0  1  2  3  4  5
     * 来到第4号位置求解的时候，首先看看第三号位置的答案，是 4，再看看第四号位置字符出现的位置 是 1 那么就是3，因为之前有重复，所以只能推3长度
     */

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        // 所有的ascII吗只有0到255
        int[] map = new int[256];
        // 默认出现的位置都是-1
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        char[] str = s.toCharArray();
        // 这里为了方便计算，
        map[str[0]] = 0;
        int ans = 1;// 就算只有一个字符长度都是1
        int pre = 1;// 上一个位置答案是啥
        for (int i = 1; i < str.length; i++) {
            int p1 = i - map[str[i]];
            int p2 = pre + 1;
            int cur = Math.min(p1, p2);
            pre = cur;
            ans = Math.max(ans, cur);
            // 更新当前位置字符出现的位置了
            map[str[i]] = i;
        }
        return ans;
    }
}
