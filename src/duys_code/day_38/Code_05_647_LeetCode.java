package duys_code.day_38;

/**
 * @ClassName Code_05_647_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/palindromic-substrings/
 * @Date 2021/12/20 13:35
 **/
public class Code_05_647_LeetCode {
    // 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。

    // 使用manacher算法求出回文半径

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = getManacher(s);
        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            ans += dp[i] >> 1;
        }
        return ans;
    }

    // 回文半径
    public int[] getManacher(String s) {
        char[] man = manacherString(s);
        int[] arr = new int[man.length];
        int c = -1;
        int r = -1;
        for (int i = 0; i < man.length; i++) {
            arr[i] = r > i ? Math.min(arr[2 * c - i], r - i) : 1;
            while (i + arr[i] < man.length && i - arr[i] > -1) {
                if (man[i + arr[i]] == man[i - arr[i]]) {
                    arr[i]++;
                } else {
                    break;
                }
            }
            if (i + arr[i] > r) {
                r = i + arr[i];
                c = i;
            }
        }
        return arr;
    }

    // 变成manacher串
    public char[] manacherString(String s) {
        char[] tar = s.toCharArray();
        char[] res = new char[tar.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            // res串的奇数位置是tar上的字符，偶数位置全部是 #
            res[i] = (i & 1) == 0 ? '#' : tar[index++];
        }
        return res;
    }
}
