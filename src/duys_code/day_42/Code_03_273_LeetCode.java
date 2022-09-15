package duys_code.day_42;

/**
 * @ClassName Code_03_273_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/integer-to-english-words/
 * @Date 2021/12/31 17:13
 **/
public class Code_03_273_LeetCode {
    // 将非负整数 num 转换为其对应的英文表示。
    //
    //
    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        String res = "";
        if (num < 0) {
            res += "Negative ";
        }
        if (num == Integer.MIN_VALUE) {
            res += "Two Billion ";
            num %= -2000000000;
        }
        num = Math.abs(num);
        int hig = 1000000000;
        int higIndex = 0;
        String[] names = {"Billion ", "Million ", "Thousand ", ""};
        while (num != 0) {
            //
            int cur = num / hig;
            num %= hig;
            if (cur != 0) {
                res += num1To999(cur);
                res += names[higIndex];
            }
            hig /= 1000;
            higIndex++;
        }
        return res.trim();
    }

    public static String num1To999(int num) {
        if (num < 1 || num > 999) {
            return "";
        }
        if (num < 100) {
            return num1To99(num);
        }
        int hig = num / 100;
        return num1To99(hig) + "Hundred " + num1To99(num % 100);
    }

    public static String num1To99(int num) {
        if (num < 1 || num > 99) {
            return "";
        }
        if (num < 20) {
            return num1To19(num);
        }
        int high = num / 10;
        String[] tyNames = {"Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
        return tyNames[high - 2] + num1To19(num % 10);
    }

    public static String num1To19(int num) {
        if (num < 1 || num > 19) {
            return "";
        }
        String[] names = {"One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ",
                "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen", "Eighteen ",
                "Nineteen "};
        return names[num - 1];
    }


    public static void main(String[] args) {
        int num = 300_0021;// 三百万零二十一
        System.out.println(num2(num));
    }

    static String[] numNames = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    public static String num2(int num) {
        String ans = "";

        String[] names = {"亿", "万", "千", "百", "十"};
        if (num == 0) {
            ans = numNames[0];
        }
        if (num < 0) {
            ans = "负";
        }
        num = Math.abs(num);
        return ans;
    }

    public static String num21To9999(int num) {
        // 小于万的就去千
        if (num < 10000) {
            return num21To999(num);
        }
        int hig = num / 10000;
        return numNames[hig] + "万" + (num % 10000 == 0 ? "" : (num % 10000 < 9 ? "零" + numNames[num % 10000] : num21To999(num % 10000)));
    }

    public static String num21To999(int num) {
        // 去百
        if (num < 1000) {
            return num21To99(num);
        }
        int hig = num / 1000;
        return numNames[hig] + "千" + ((num % 1000 == 0 ? "" : (num % 1000 < 9) ? "零" + numNames[num % 1000] : num21To99(num % 1000)));
    }

    public static String num21To99(int num) {
        // 去十
        if (num < 100) {
            return num21To9(num);
        }
        int hig = num / 100;
        return numNames[hig] + "百" + ((num % 100) == 0 ? "" : ((num % 100) < 9 ? "零" + numNames[num % 100] : num21To9(num % 100)));
    }

    public static String num21To9(int num) {
        if (num < 9) {
            return numNames[num];
        }
        int hig = num / 10;
        return numNames[hig] + "十" + (num % 10 == 0 ? "" : numNames[num % 10]);
    }
}
