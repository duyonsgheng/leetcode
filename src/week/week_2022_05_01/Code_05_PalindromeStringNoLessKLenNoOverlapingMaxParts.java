package week.week_2022_05_01;

/**
 * @ClassName Code_05_PalindromeStringNoLessKLenNoOverlapingMaxParts
 * @Author Duys
 * @Description
 * @Date 2022/5/7 18:00
 **/
// 来自optiver
// 给定一个字符串str，和一个正数k
// 你可以随意的划分str成多个子串，
// 目的是找到在某一种划分方案中，有尽可能多的回文子串，长度>=k，并且没有重合
// 返回有几个回文子串
public class Code_05_PalindromeStringNoLessKLenNoOverlapingMaxParts {

    // 知识点：manacher
    // 贪心+manacher
    // 从开始位置遍历，遍历到i 0到i有一个回文那么就算一次，然后从i+1开始继续这个流程
    public static int max(String s, int k) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        char[] str = manacherString(s);
        // 回文半径数据
        int[] p = new int[str.length];
        int ans = 0;
        int next = 0;
        while ((next = manacherFind(str, p, next, k)) != -1) {
            next = str[next] == '#' ? next : (next + 1);
            ans++;
        }
        return ans;
    }

    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] ans = new char[str.length * 2 + 1];
        int index = 0;
        // 整个串的奇数位置全部是 #
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return ans;
    }

    // s[l.....]只在这个范围上操作，且s[l]一定是 '#'
    // 从l开始，一旦有一个中心回文半径 >k 就返回右边界，然后下次接着从右边界+1开始
    public static int manacherFind(char[] manacher, int[] p, int l, int k) {
        int c = l - 1;
        int r = l - 1;
        int n = manacher.length;
        for (int i = l; i < n; i++) {
            p[i] = r > i ? Math.min(p[2 * c - i], r - i) : 1;
            // 推以i为中心的回文半径
            while (i + p[i] < n && i - p[i] > l - 1 && manacher[i + p[i]] == manacher[i - p[i]]) {
                if (++p[i] > k) {
                    return i + k;
                }
            }
            if (i + p[i] > r) {
                r = i + p[i];
                c = i;
            }
        }
        return -1;
    }
}
