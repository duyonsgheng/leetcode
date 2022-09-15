package week.week_2022_02_02;

/**
 * @ClassName Code_01_LeetCode_679
 * @Author Duys
 * @Description
 * @Date 2022/3/29 16:05
 **/

public class Code_01_LeetCode_679 {
    //给定一个长度为4的整数数组cards。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。您应该使用运算符['+', '-', '*', '/']和括号'('和')'将这些卡片上的数字排列成数学表达式，以获得值24。
    //你须遵守以下规则:
    //除法运算符 '/' 表示实数除法，而不是整数除法。
    //例如，4 /(1 - 2 / 3)= 4 /(1 / 3)= 12。
    //每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
    //例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
    //你不能把数字串在一起
    //例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
    //如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/24-game

    public static boolean judgePoint24(int[] cards) {
        if (cards == null || cards.length == 0) {
            return false;
        }
        Number[] arr = new Number[cards.length];
        for (int i = 0; i < cards.length; i++) {
            arr[i] = new Number(cards[i], 1);
        }
        return judge(arr, cards.length);
    }

    // 再arr中，有效的位置就是 0到 size -1
    public static boolean judge(Number[] arr, int size) {
        if (size == 1) {
            return arr[0].numerator == 24 && arr[0].denominator == 1;
        }
        // 去尝试了
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Number inum = arr[i];
                Number jnum = arr[j];
                // 先把size-1拿出来，因为下面size要收缩了
                arr[j] = arr[size - 1];
                arr[i] = add(inum, jnum);
                if (judge(arr, size - 1)) {
                    return true;
                }
                arr[i] = minus(jnum, inum);
                if (judge(arr, size - 1)) {
                    return true;
                }
                arr[i] = minus(inum, jnum);
                if (judge(arr, size - 1)) {
                    return true;
                }
                arr[i] = multiply(inum, jnum);
                if (judge(arr, size - 1)) {
                    return true;
                }
                arr[i] = divide(jnum, inum);
                if (arr[i] != null && judge(arr, size - 1)) {
                    return true;
                }
                arr[i] = divide(inum, jnum);
                if (arr[i] != null && judge(arr, size - 1)) {
                    return true;
                }
                // 现场还原
                arr[i] = inum;
                arr[j] = jnum;
            }
        }
        return false;
    }

    // 这个题需要我们做实际除法。那么我们对每一个数字进行包装
    // 对于一个整数来说，分子就是数本身，分母就是1
    public static class Number {
        // 分子
        public int numerator;
        // 分母
        public int denominator;

        public Number(int num, int den) {
            numerator = num;
            denominator = den;
        }
    }

    // 分式相加
    public static Number add(Number a, Number b) {
        // 就是分式相加
        return simple(a.numerator * b.denominator + b.numerator * a.denominator, a.denominator * b.denominator);
    }

    //分式相减
    public static Number minus(Number a, Number b) {
        return simple(a.numerator * b.denominator - b.numerator * a.denominator, a.denominator * b.denominator);
    }

    //分式相乘
    public static Number multiply(Number a, Number b) {
        return simple(a.numerator * b.numerator, a.denominator * b.denominator);
    }

    //分式相除
    public static Number divide(Number a, Number b) {
        return simple(a.numerator * b.denominator, a.denominator * b.numerator);
    }


    public static Number simple(int up, int down) {
        if (up == 0) {
            return new Number(0, 1);
        }
        int gcd = Math.abs(gcd(up, down));
        return new Number(up / gcd, down / gcd);
    }

    // 就是找到 两个数的最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
