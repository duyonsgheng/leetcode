package duys_code.day_28;

/**
 * @ClassName Code_01_8_LeetCode
 * @Author Duys
 * @Description 力扣: https://leetcode-cn.com/problems/string-to-integer-atoi/
 * @Date 2021/11/22 15:41
 **/
public class Code_01_8_LeetCode {
    /**
     * 字符串转数字
     * 用负数来装数字不容易越界，因为负数的范围比正数的范围大
     * 负数可以兼容正数，但是正数不能兼容负数
     */
    public static int myAtoi(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        s = removeHeadZero(s.trim());
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        if (!isValid(str)) {
            return 0;
        }
        // str 是符合日常书写的，正经整数形式
        boolean posi = str[0] == '-' ? false : true;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        for (int i = (str[0] == '-' || str[0] == '+') ? 1 : 0; i < str.length; i++) {
            // 3  cur = -3   '5'  cur = -5    '0' cur = 0
            cur = '0' - str[i];
            // res < minq 因为res 即将*10
            // res == minq && cur < minr res 已经等于Integer.MIN_VALUE / 10了，那么 res*10 + cur 不能越界
            if ((res < minq) || (res == minq && cur < minr)) {
                return posi ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + cur;
        }
        // res 负
        if (posi && res == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return posi ? -res : res;
    }

    public static String removeHeadZero(String str) {
        boolean r = (str.startsWith("+") || str.startsWith("-"));
        int s = r ? 1 : 0;
        // 找到第一个不为数字的位置
        for (; s < str.length(); s++) {
            if (str.charAt(s) != '0') {
                break;
            }
        }
        // s 到了第一个不是'0'字符的位置
        int e = -1;
        // 左<-右
        for (int i = str.length() - 1; i >= (r ? 1 : 0); i--) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                e = i;
            }
        }
        // e 到了最左的 不是数字字符的位置
        return (r ? String.valueOf(str.charAt(0)) : "") + str.substring(s, e == -1 ? str.length() : e);
    }

    public static boolean isValid(char[] chas) {
        if (chas[0] != '-' && chas[0] != '+' && (chas[0] < '0' || chas[0] > '9')) {
            return false;
        }
        if ((chas[0] == '-' || chas[0] == '+') && chas.length == 1) {
            return false;
        }
        // 0 +... -... num
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] < '0' || chas[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(removeHeadZero(" with words 4193"));
    }
}
