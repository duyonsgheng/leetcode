package hope.bitmap;

/**
 * @author Mr.Du
 * @ClassName Video_033_BitOperationAddMinusMultiplyDivide
 * @date 2023年08月15日
 */
// 用位运算实现 + - * /
public class Video_033_BitOperationAddMinusMultiplyDivide {


    // 1. 加法
    // 两个数亦或运算
    // 然后两个数的进位信息
    // 重复这个步骤，直到没有进位信息产生
    public static int add(int a, int b) {
        int ans = a;
        while (b != 0) {
            ans = a ^ b; // 两个数的亦或信息
            b = (a & b) << 1; // 两个数相加的进位信息
            a = ans;
        }
        return ans;
    }

    // 2.减法，那就更简单了，a-b 等于 a+(~b+1)
    public static int minus(int a, int b) {
        return add(a, neg(b));
    }

    // 3.乘法，利用小学的乘法原理
    // 每一用b的每一位去和a的所有位相乘，然后 b 每次 >>> 1，对应的a << 1，因为乘法要位对齐
    public static int multiply(int a, int b) {
        int ans = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = add(ans, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return ans;
    }

    // 4.除法
    // 比如 280 / 25
    // 看看 280 是否含有25*2^31 ，是否含有25*2^30
    // 如果含有则减去，然后剩下的值继续看下一个次方。直到不包含，余下小于25的数就是模数
    public static int div(int a, int b) {
        int x = a < 0 ? neg(a) : a;
        int y = b < 0 ? neg(b) : b;
        int ans = 0;
        // x>= y*2^i
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                ans |= (1 << i);
                x = minus(x, y << i);
            }
        }
        // a和b的符号不相同，则返回负数，
        return a < 0 ^ b < 0 ? neg(ans) : ans;
    }

    public static int min = Integer.MIN_VALUE;

    // 如果有整数最小值参与除法，那么 neg会越界
    public static int divide(int a, int b) {
        if (a == min && b == min) {
            return 1;
        }
        if (a != min && b != min) {
            return div(a, b);
        }
        if (b == min) {
            return 0;
        }
        // a是最小，且b是-1
        if (b == neg(1)) {
            return Integer.MAX_VALUE;
        }
        // 这里a一定是整数最小，b不是,所以先对a加工，先让a减小或者放大一点。避免整数越界
        a = add(a, b > 0 ? b : neg(b));
        int ans = div(a, b);
        int off = b > 0 ? neg(1) : 1; // 之前多增加或者减少了一个，这里需要加上或者扣掉
        return add(ans, off);
    }

    public static int neg(int n) {
        return add(~n, 1);
    }
}
