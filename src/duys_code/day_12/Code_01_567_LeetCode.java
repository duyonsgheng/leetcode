package duys_code.day_12;

/**
 * @ClassName Code_01_567_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/permutation-in-string/
 * @Date 2021/10/19 11:27
 **/
public class Code_01_567_LeetCode {
    /**
     * 题意：
     * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     *  比如s1 是aabcd s2是 dcabase 在s2中存在 2个a，1个b，1个c 是满足的
     */
    /**
     * 思路：
     * 先对s1来一个词频统计，词频的总数就是我们窗口的大小，
     * 然后在s2上玩儿一个窗口，先形成窗口，窗口形成后，就开始滑动，右边进一个，对应词频+1 左边出一个对应词频-1
     * 当我们对应的词频个数+1之前 >=0 ，总数+1，
     * 当我们对应的词频个数-1之前 > 0，总数-1
     */
    // 返回子序列的开头位置
    public static boolean checkInclusion(String s1, String s2) {
        return process(s1, s2) != -1;
    }

    public static int process(String s1, String s2) {
        // s1是我们的字典
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        // 来一个词频统计
        int len1 = str1.length;
        int[] count = new int[256]; // 可以用数组来做，也可以用map来做用asicII码
        for (int i = 0; i < len1; i++) {
            count[str1[i]]++;
        }
        int all = len1;
        char[] str2 = s2.toCharArray();
        int R = 0;// 窗口的有边界
        for (; R < len1; R++) { // 初始化我们的窗口
            // 我们词频统计之后大于0了，我们的总数要--
            if (count[str2[R]]-- > 0) {
                all--;
            }
        }
        // 从第一个窗口的右边界开始，进一个出一个，
        for (; R < str2.length; R++) {
            // 先判断之前的是否已经满足了
            if (all == 0) {
                return R - len1;
            }
            // 词频新进入这个区域，词频要--，--之前如果值是大于0的，我们总数需要--
            if (count[str2[R]]-- > 0) {
                all--;
            }
            // 词频从这个区域走了，如果对应的词频数之前就是>=0 的，我们的总数需要++
            if (count[str2[R - len1]]++ >= 0) {
                all++;
            }
        }
        return all == 0 ? R - len1 : -1;
    }

    public static void main(String[] args) {
        int a = 1;
        System.out.println(a--);
        System.out.println(a);
    }
}
