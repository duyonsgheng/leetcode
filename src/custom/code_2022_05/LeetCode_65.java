package custom.code_2022_05;

/**
 * @ClassName LeetCode_65
 * @Author Duys
 * @Description
 * @Date 2022/5/10 9:16
 **/
// 65. 有效数字
//有效数字（按顺序）可以分成以下几个部分：
//
//一个 小数 或者 整数
//（可选）一个 'e' 或 'E' ，后面跟着一个 整数
//小数（按顺序）可以分成以下几个部分：
//
//（可选）一个符号字符（'+' 或 '-'）
//下述格式之一：
//至少一位数字，后面跟着一个点 '.'
//至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
//一个点 '.' ，后面跟着至少一位数字
//整数（按顺序）可以分成以下几个部分：
//
//（可选）一个符号字符（'+' 或 '-'）
//至少一位数字
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/valid-number
// 部分有效数字列举如下：["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
//部分无效数字列举如下：["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
public class LeetCode_65 {

    public static boolean isNumber(String s) {
        if (s == null || s.length() <= 0) {
            return false;
        }
        if (s.length() == 1) {
            return s.equals("e") || s.equals(".") || s.equals("+") || s.equals("-") ? false : true;
        }
        char[] str = s.toCharArray();
        char one = str[0];
        char tow = str[1];
        if (one == '+' || one == '-') {
            if (tow == 'e' || tow == 'E') {
                return false;
            }
            return process(str, 1, false, false, true, one);
        }
        if (one == '.') {
            if (tow == 'e' || tow == 'E') {
                return false;
            }
            return process(str, 1, true, false, false, one);
        }
        if (one - '0' > 9 || one - '0' < 0) {
            return false;
        }
        return process(str, 1, false, false, false, one);
    }

    public static boolean process(char[] arr, int index, boolean preDian, boolean preE, boolean preFu, char pre) {
        if (index == arr.length) {
            if ((pre == 'e') || pre == 'E' || pre == '+' || pre == '-') {
                return false;
            } else {
                return true;
            }
        }
        if (arr[index] == '.') {
            if (preDian) {
                return false;
            }
            if (preE) {
                return false;
            }
            if (index + 1 >= arr.length && (pre == '+' || pre == '-')) {
                return false;
            }
            preDian = true;
            return process(arr, index + 1, preDian, preE, preFu, arr[index]);
        }
        if (arr[index] == 'e' || arr[index] == 'E') {
            if (preE) {
                return false;
            }
            if (pre == '+' || pre == '-') {
                return false;
            }
            preE = true;
            return process(arr, index + 1, preDian, preE, preFu, arr[index]);
        }
        if (arr[index] == '+' || arr[index] == '-') {
            if (pre == 'e' || pre == 'E') {
                return process(arr, index + 1, preDian, preE, true, arr[index]);
            }
            if ((pre - '0' >= 0 && pre - '0' <= 9) || pre == '.') {
                return false;
            }
            if (preFu) {
                return false;
            }
            preFu = true;
            return process(arr, index + 1, preDian, preE, preFu, arr[index]);
        }
        return arr[index] - '0' > 9 || arr[index] - 0 < 0 ? false : process(arr, index + 1, preDian, preE, preFu, arr[index]);
    }

    public static void main(String[] args) {

        System.out.println(isNumber("-.4"));

    }

    public static boolean check(char c) {
        if (c == 'E' || c == 'e') {
            return true;
        }
        if (c - '0' >= 0 && c - '0' <= 9) {
            return true;
        }
        return false;
    }
}
