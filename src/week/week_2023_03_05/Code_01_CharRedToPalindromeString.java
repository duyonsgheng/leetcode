package week.week_2023_03_05;

/**
 * @ClassName Code_01_CharRedToPalindromeString
 * @Author Duys
 * @Description
 * @Date 2023/3/30 9:03
 **/
// 来自百度
// 用r、e、d三种字符，拼出一个回文子串数量等于x的字符串
// 1 <= x <= 10^5
public class Code_01_CharRedToPalindromeString {
    // 当前 x = 52
    // 利用等差数列求和公式 n*(n+1)/2，先看看哪个n的满足 <= n*(n+1)/2 n=9的时候 45，可以用10个r，还剩下 52-45 还剩下7个，继续分解 n=3 那么就用3个e。还剩下一个 使用 一个d
    // 逐步分解，三种字符依次交替
    public static String palindromeX(int x) {
        StringBuilder builder = new StringBuilder();
        char cur = 'r';
        while (x > 0) {
            int near = near(x);
            for (int i = 0; i < near; i++) {
                builder.append(cur);
            }
            x -= near * (near + 1) / 2;
            cur = cur == 'r' ? 'e' : (cur == 'e' ? 'd' : 'r');
        }
        return builder.toString();
    }

    // 当前x=52，需要几个，相同字符可以搞定
    // 找到最接近x的
    public static int near(int x) {
        int l = 1;
        int r = x;
        int m, ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (ok(m, x)) {
                l = m + 1;
                ans = m;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static boolean ok(int n, int x) {
        return (long) n * (n + 1) / 2 <= x;
    }
}
