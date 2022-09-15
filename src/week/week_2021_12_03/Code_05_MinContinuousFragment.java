package week.week_2021_12_03;

/**
 * @ClassName Code_05_MinContinuousFragment
 * @Author Duys
 * @Description
 * @Date 2022/4/15 13:15
 **/
// 来自CMU入学申请考试
// 给定一个长度为 N 的字符串 S，由字符'a'和'b'组成，空隙由 '?' 表示
// 你的任务是用a字符或b字符替换每个间隙，
// 替换完成后想让连续出现同一种字符的最长子串尽可能短
// 例如，S = "aa??bbb"，
// 如果将"??"替换为"aa" ，即"aaaabbb"，则由相等字符组成的最长子串长度为4
// 如果将"??"替换为"ba" ，即"aababbb"，则由相等字符组成的最长子串长度为3
// 那么方案二是更好的结果，返回3
// S的长度 <= 10^6
public class Code_05_MinContinuousFragment {

    // 暴力解答
    public static int min1(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    public static int process(char[] ar, int index) {
        if (index == ar.length) {
            return maxLen(ar);
        } else {
            if (ar[index] != '?') {
                return process(ar, index + 1);
            } else {
                // 跑一个深度优先
                ar[index] = 'a';
                int p1 = process(ar, index + 1);
                ar[index] = 'b';
                int p2 = process(ar, index + 1);
                ar[index] = '?';
                return Math.min(p1, p2);
            }
        }
    }

    /**
     * 可能性：
     * 1.问号的左边和右边是相等的，且问号的长度是奇数 例如 b ? ? ? b ，那么这个时候，这些问号的填法是固定的。a b a 整体就是 b a b a b全部切开了
     * 2.问号的左边等于右边，且问号长度是偶数 例如 a ? ? ? ? a .卡住两边往中间填，局部最优。那么就从两边往中间填。a b a a b a。我让问号的左边和右边断掉。
     * 这样做的最基本的可以保证，再这个偶数长度的问号内最大长度可以控制。问号的左边和问号的右边我目前是控制不了的，一个小贪心。局部最优从而达到全局最优。
     * 3.问号左边和右边不等。并且问号长度是偶数，例如 a ? ? ? ? b，卡住两边往中间填，依次相反。
     * 4.问号左边不等于右边，并且问号长度是一个 大于1的奇数。a ? ? ? b，卡住两边，往中间填，局部最优。
     * 5.问号左边不等于右边，问号只有一个 . a ? b .那么就要分析左右两边长度了。决定最后一个问号怎么填。
     */
    public static int min2(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int n = chars.length;
        int l = 0;
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '?') {
                set(chars, l, r);
                l = i + 1;
                r = i;
            } else {
                r++;
            }
        }
        set(chars, l, r);
        // 单独处理第5种情况。因为要分析两边字符的长度。
        for (int i = 0; i < n; i++) {
            if (chars[i] == '?') {
                for (l = i - 1; l >= 0 && chars[l] == chars[i - 1]; l--)
                    ;
                for (r = i + 1; r < n && chars[r] == chars[i + 1]; r++)
                    ;
                l = i - l - 1;
                r = r - i - 1;
                if (l <= r) {
                    chars[i] = chars[i - 1];
                } else {
                    chars[i] = chars[i + 1];
                }
            }
        }
        return maxLen(chars);
    }

    // l 到 r都是问号
    // 如果满足前面4种情况，先处理，从两边往中间填，
    // 如果是第五种情况，先不填
    public static void set(char[] str, int l, int r) {
        int n = str.length;
        if (l > r) {
            return;
        }
        if (l == 0 && r == n - 1) {
            for (int i = 0; i < n; i++) {
                str[i] = (i & 1) == 0 ? 'a' : 'b';
            }
        } else if (l == 0) {
            for (int i = r; i >= 0; i--) {
                str[i] = str[i + 1] == 'a' ? 'b' : 'a';
            }
        } else if (r == n - 1) {
            for (int i = l; i < n; i++) {
                str[i] = str[i - 1] == 'a' ? 'b' : 'a';
            }
        } else {
            if (str[l - 1] == str[r + 1] || l != r) {
                for (; l <= r; l++, r--) {
                    str[l] = str[l - 1] == 'a' ? 'b' : 'a';
                    str[r] = str[r + 1] == 'a' ? 'b' : 'a';
                }
            }
        }
    }

    public static int maxLen(char[] str) {
        int ans = 1;
        int cur = 1;
        for (int i = 1; i < str.length; i++) {
            if (str[i] != str[i - 1]) {
                ans = Math.max(ans, cur);
                cur = 1;
            } else {
                cur++;
            }
        }
        ans = Math.max(ans, cur);
        return ans;
    }
}
