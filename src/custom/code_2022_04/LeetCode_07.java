package custom.code_2022_04;

/**
 * @ClassName LeetCode_07
 * @Author Duys
 * @Description
 * @Date 2022/4/25 15:38
 **/
// https://leetcode-cn.com/problems/reverse-integer/
public class LeetCode_07 {

    public static int reverse(int x) {
        if (x == 0 || x == Integer.MIN_VALUE) {
            return 0;
        }
        String pr = "";
        int s = 0;
        if (x > 0) {
            s = x;
        } else {
            s = -x;
            pr = "-";
        }
        String str = String.valueOf(s);
        char[] chars = str.toCharArray();
        int n = chars.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            char tmp = chars[l];
            if (chars[r] - '0' == -48) {
                r--;
                continue;
            }
            chars[l++] = chars[r];
            chars[r--] = tmp;
        }
        String ans = pr + String.valueOf(chars);
        Long aLong = Long.valueOf(ans);
        if (aLong > Integer.MAX_VALUE || aLong < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.valueOf(ans);
    }

    public static void main(String[] args) {
        int a = 1534236469;
        System.out.println(reverse(a));
    }
}
