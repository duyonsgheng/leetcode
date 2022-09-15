package custom.code_2022_04;

/**
 * @ClassName LeetCode_12
 * @Author Duys
 * @Description
 * @Date 2022/4/26 13:20
 **/
// https://leetcode-cn.com/problems/integer-to-roman/
public class LeetCode_12 {
    // 整数转罗马数字
    // 1  5 10 50  100  500 1000
    static char[] arr = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    // 1999
    // M 1000
    // CM 900
    // IC 99
    public static String intToRoman(int num) {
        // 从高到地 3位一转
        return process(num);
    }

    public static String process(int num) {
        if (num > 999) {
            return process999(num);
        }
        if (num > 99) {
            return process99(num);
        }
        if (num > 9) {
            return process9(num);
        }
        return process0(num);
    }

    // 1000 - 9999
    public static String process999(int num) {
        if (num == 0) {
            return "";
        }
        String ans = "";
        if (num >= 1000) {
            ans += "M" + process999(num - 1000);
        } else {
            ans += process99(num);
        }
        return ans;
    }

    // 100 - 999
    public static String process99(int num) {
        if (num == 0) {
            return "";
        }
        String ans = "";
        if (num >= 900) {
            ans += "CM" + process99(num - 900);
        } else if (num >= 500 && num < 900) {
            ans += "D" + process99(num - 500);
        } else if (num >= 400 && num < 500) {
            ans += "CD" + process99(num - 400);
        } else if (num >= 100 && num < 400) {
            ans += "C" + process99(num - 100);
        } else {
            ans += process9(num);
        }
        return ans;
    }

    // 10 - 99
    public static String process9(int num) {
        if (num == 0) {
            return "";
        }
        String ans = "";
        if (num >= 90) {
            ans += "XC" + process9(num - 90);
        } else if (num >= 50 && num < 90) {
            ans += "L" + process9(num - 50);
        } else if (num >= 40 && num < 50) {
            ans += "XL" + process9(num - 40);
        } else if (num >= 10 && num < 40) {
            ans += "X" + process9(num - 10);
        } else {
            ans += process0(num);
        }
        return ans;
    }

    public static String process0(int num) {
        if (num == 0) {
            return "";
        }
        String ans = "";
        if (num >= 9) {
            ans += "IX" + process0(num - 9);
        } else if (num >= 5 && num < 9) {
            ans += "V" + process0(num - 5);
        } else if (num == 4) {
            ans += "IV";
        } else if (num >= 1 && num < 4) {
            ans += "I" + process0(num - 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
        int ax = 98;
        System.out.println(98 - (98 % 10));
    }


}
