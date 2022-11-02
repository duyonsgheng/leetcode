package custom.code_2022_11;

import java.util.Stack;

/**
 * @ClassName LeetCode_1006
 * @Author Duys
 * @Description
 * @Date 2022/11/2 14:01
 **/
// 1006. 笨阶乘
public class LeetCode_1006 {

    // 10 * 9 / 8  + 7 -  6 * 5 / 4  + 3 -  2 * 1
    public static int clumsy(int n) {
        int mod = n % 4;
        long sum = Long.MAX_VALUE;
        for (int i = n; i > mod; i -= 4) {
            long cur = op(i, i - 1, i - 2);
            if (sum == Long.MAX_VALUE) {
                sum = cur;
            } else {
                sum -= cur;
            }
            sum += i - 3;
        }
        int y = mod * (mod - 1 > 0 ? mod - 1 : 1) / (mod - 2 > 0 ? mod - 2 : 1);
        if (sum != Long.MAX_VALUE) {
            sum -= y;
        } else {
            sum = y;
        }
        sum += mod - 3 > 0 ? mod - 3 : 0;
        return (int) sum;
    }

    public static long op(int a, int b, int c) {
        return (int) Math.floor(a * b / c);
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(clumsy(n));
        System.out.println(1 % 4);
    }

}
