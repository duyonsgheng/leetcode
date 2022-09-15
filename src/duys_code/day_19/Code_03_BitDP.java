package duys_code.day_19;

/**
 * @ClassName Code_03_BitDP
 * @Author Duys
 * @Description 数位DP，与数本身无关，与数的二进制位置有关
 * @Date 2021/11/3 16:04
 **/
public class Code_03_BitDP {

    /**
     * 给定一个正数N，比如N = 13，在纸上把所有数都列出来如下：
     * 1 2 3 4 5 6 7 8 9 10 11 12 13
     * 可以数出1这个字符出现了6次
     * 给定一个正数N，如果把1~N都列出来，
     * 返回1这个字符出现的多少次
     */
    public static int solution2(int num) {
        if (num < 1) {
            return 0;
        }
        // num有多少位
        int len = getLenOfNum(num);
        if (len == 1) {
            return 1;
        }
        // 比如
        // num = 32221.在千位上至少有多少1，那么就是千位是1，后面的位自由选择，10(百位)*10(十位)*10(个位)
        // tmp1= 10000 就是 10^(len-1)
        int tmp1 = powerBaseOf10(len - 1);

        // 最高位是啥 ,最高位是几
        int bigBit = num / tmp1;

        // 最高是1  最高位是1，总共有多少个数字满足：num % tmp1 + 1
        // 最高位不是1 那么最高位是1的时候，总共有多少个数字满足：tmp1
        int bigBitOneNum = bigBit == 1 ? num % tmp1 + 1 : tmp1;
        // 除去最高位之外，剩下1的数量
        // 最高位1 10^(k-2) * (k-1) * 1
        // 最高位不是1 10^(k-2) * (k-1) * bigBit
        // tmp1 是10^(len-1)
        int otherOneNum = bigBit * (len - 1) * (tmp1 / 10);
        return bigBitOneNum + otherOneNum + solution2(num % tmp1);
    }

    public static int powerBaseOf10(int base) {
        return (int) Math.pow(10, base);
    }

    public static int getLenOfNum(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

}
