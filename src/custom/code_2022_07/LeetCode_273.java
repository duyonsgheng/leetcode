package custom.code_2022_07;

/**
 * @ClassName LeetCode_273
 * @Author Duys
 * @Description
 * @Date 2022/7/13 15:37
 **/
// 273. 整数转换英文表示
public class LeetCode_273 {
    static String[] names = {"One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ",
            "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen", "Eighteen ",
            "Nineteen "};
    static String[] names_max = {"Billion ", "Million ", "Thousand ", ""};
    static String[] tyNames = {"Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};

    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        String res = "";
        if (num < 0) {
            res = "Negative ";
        }
        if (num == Integer.MIN_VALUE) {
            res += "Tow Billion ";
            num %= -2000000000;
        }
        num = Math.abs(num);
        int high = 1000000000;
        int highIndex = 0;
        while (num != 0) {
            int cur = num / high;
            num %= high;
            if (cur != 0) {
                res += processTo999(cur);
                res += names_max[highIndex];
            }
            high /= 1000;
            highIndex++;
        }
        return res.trim();
    }

    public static String processTo999(int num) {
        if (num < 1 || num > 999) {
            return "";
        }
        if (num < 100) {
            return processTo99(num);
        }
        int high = num / 100;
        return processTo19(high) + "Hundred " + processTo99(num % 100);
    }

    public static String processTo99(int num) {
        if (num < 1 || num > 99) {
            return "";
        }
        if (num < 20) {
            return processTo19(num);
        }
        int hig = num / 10;
        return tyNames[hig - 2] + processTo19(num % 10);
    }

    public static String processTo19(int num) {
        if (num < 1 || num > 19) {
            return "";
        }
        return names[num - 1];
    }

    public static void main(String[] args) {
        String str = "123";
        System.out.println(numberToWords(123));
    }
}
