package week.week_2022_06_01;

/**
 * @ClassName Code_02_LeetCode_467
 * @Author Duys
 * @Description
 * @Date 2022/6/9 13:26
 **/
// https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
// 把字符串 s 看作 "abcdefghijklmnopqrstuvwxyz"的无限环绕字符串，所以s 看起来是这样的：
//"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." 。
//现在给定另一个字符串 p 。返回s 中 不同 的 p 的 非空子串的数量。
//链接：https://leetcode.cn/problems/unique-substrings-in-wraparound-string
public class Code_02_LeetCode_467 {
    // 以某一个字符结尾的时候得到一个什么什么答案、。
    public static int findSubstringInWraproundString(String p) {
        char[] str = p.toCharArray();
        int n = str.length;
        int ans = 0;
        int len = 1;
        int[] map = new int[26];
        map[str[0] - 'a']++;
        for (int i = 1; i < n; i++) {
            char cur = str[i];
            char pre = str[i - 1];
            // 看看能不能链起来
            if ((cur == 'a' && pre == 'z') || cur - 1 == pre) {
                len++;
            } else {
                len = 1;
            }
            map[cur - 'a'] = Math.max(map[cur - 'a'], len);
        }
        for (int i : map)
            ans += i;
        return ans;
    }
}
