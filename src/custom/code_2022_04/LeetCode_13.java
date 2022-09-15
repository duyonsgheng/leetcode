package custom.code_2022_04;

/**
 * @ClassName LeetCode_13
 * @Author Duys
 * @Description
 * @Date 2022/4/26 14:33
 **/
// https://leetcode-cn.com/problems/roman-to-integer/comments/
public class LeetCode_13 {
    // IV 4
    // IX 9
    // XL 40
    // XC 90
    // CD 400
    // CM 900
    public static int romanToInt(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        s = s.replaceAll("IV", "a");
        s = s.replaceAll("IX", "b");
        s = s.replaceAll("XL", "c");
        s = s.replaceAll("XC", "d");
        s = s.replaceAll("CD", "e");
        s = s.replaceAll("CM", "f");
        char[] chars = s.toCharArray();
        int res = 0;
        for (char ch : chars) {
            switch (ch) {
                case 'I':
                    res += 1;
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    res += 10;
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    res += 100;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
                case 'a':
                    res += 4;
                    break;
                case 'b':
                    res += 9;
                    break;
                case 'c':
                    res += 40;
                    break;
                case 'd':
                    res += 90;
                    break;
                case 'e':
                    res += 400;
                    break;
                case 'f':
                    res += 900;
                    break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }
}
