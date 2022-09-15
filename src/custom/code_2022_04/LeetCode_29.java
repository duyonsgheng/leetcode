package custom.code_2022_04;

/**
 * @ClassName LeetCode_29
 * @Author Duys
 * @Description
 * @Date 2022/4/27 14:03
 **/
// 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
//返回被除数dividend除以除数divisor得到的商。
//整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
//链接：https://leetcode-cn.com/problems/divide-two-integers
public class LeetCode_29 {

    public static int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 疑惑算出符号位是否有值
        boolean negative = (dividend ^ divisor) < 0 ? true : false;

        long up = Math.abs((long) dividend);
        long down = Math.abs((long) divisor);

        int rest = 0;
        int i = 31;
        while (i >= 0) {
            if ((up >> i) >= down) {
                rest += 1 << i;
                up -= down << i;
            }
            i--;
        }
        return negative ? -rest : rest;
    }

    public static void main(String[] args) {
        System.out.println(divide(-2147483648, 1));
    }
}
