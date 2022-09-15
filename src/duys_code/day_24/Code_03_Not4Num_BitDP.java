package duys_code.day_24;

/**
 * @ClassName Code_03_Not4Num_BitDP
 * @Author Duys
 * @Description 数位DP，与数本身无关，与数的二进制位置有关
 * @Date 2021/11/15 10:54
 **/
public class Code_03_Not4Num_BitDP {

    /**
     * 正常的里程表会依次显示自然数表示里程
     * 吉祥的里程表会忽略含有4的数字而跳到下一个完全不含有4的数
     * 正常：1 2 3 4 5 6 7 8   9 10 11 12 13 14 15    X
     * 吉祥：1 2 3 5 6 7 8 9 10 11 12 13 15 16 17 ... 38 39 50 51 52 53 55
     * 给定一个吉祥里程表的数字num(当然这个数字中不含有4)
     * 返回这个数字代表的真实里程
     */

    // 1.暴力解答
    public static long notContains4Nums1(long num) {
        long count = 0;
        for (long i = 0; i <= num; i++) {
            if (isNot4(i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isNot4(long num) {
        while (num != 0) {
            if (num % 10 == 4) {
                return false;
            }
            num /= 10;
        }
        return true;
    }

    // 只包含 1位数字的时候，不包含4，有几个数字 1 2 3 5 6 7 8 9 0 总共9个
    // 只包含 2位数字的时候，不包含4，有几个72个数字，2位数字以内，总共就是81个
    // 只包含 3位数字的时候，不包含4，总共有几个72*9,3位数字以内，总共就是 81 * 9
    // 所以是一个 9^n n是数字的长度
    public static long[] arr = {0L, 1L, 9L, 81L, 729L, 6561L, 59049L, 531441L, 4782969L, 43046721L, 387420489L,
            3486784401L, 31381059609L, 282429536481L, 2541865828329L, 22876792454961L, 205891132094649L,
            1853020188851841L, 16677181699666569L, 150094635296999121L, 1350851717672992089L};

    // 2.正式解答
    public static long notContains4Nums2(long num) {
        if (num < 0) {
            return 0;
        }
        // 比如num = 23456789
        // len = 8
        int len = getNumLen(num);
        // offset = 10000000
        long offset = getOffset(len);
        long first = num / offset;
        // 在arr中加一个0L，那么len就不用len-1了
        // 把一个数组分成三部分算
        // 65721、
        // p1 : 以6xxxx开头的总共多少个，那就是arr[len] - 1 ，-1是要减去0，当前面已经有数字的时候就不用 -1了
        // p2 : 10000 - 59999 总共多少个，那就是  1xxxx,2xxxx,3xxxx,4xxxx,5xxxx合计多少个
        // p3 : 6开头，长度位len-1 的数字有多少
        return arr[len] - 1 + (first < 4 ? first - 1 : first - 2) * arr[len] + process(num % offset, offset / 10, len - 1);
    }

    public static long process(long num, long offset, int len) {
        if (len == 0) {
            return 1;
        }
        long first = num / offset;
        // 依次进行拆分
        return (first < 4 ? first : first - 1) * arr[len] + process(num % offset, offset / 10, len - 1);
    }

    public static int getNumLen(long num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static long getOffset(long len) {
        long offset = 1;
        for (int i = 1; i < len; i++) {
            offset *= 10;
        }
        return offset;
    }

    // 最优解，9进制来玩儿
    public static long notContains4Nums3(long num) {
        if (num < 0) {
            return 0;
        }
        long ans = 0;
        long cur = 0;
        for (int base = 1; num != 0; num /= 10, base *= 9) {
            cur = num % 10;
            ans += (cur < 4 ? cur : cur - 1) * base;
        }
        return ans;
    }
}
