package duys_code.day_38;

/**
 * @ClassName Code_08_FillGapMinStep
 * @Author Duys
 * @Description
 * @Date 2021/12/20 15:03
 **/
public class Code_08_FillGapMinStep {
    // 来自字节
    // 给定两个数a和b
    // 第1轮，把1选择给a或者b
    // 第2轮，把2选择给a或者b
    // ...
    // 第i轮，把i选择给a或者b
    // 想让a和b的值一样大，请问至少需要多少轮？

    // 二分思路
    // 比如 n 和 m  m> n
    // n+a = m+b -> a>b
    // 1. m- n = s -> a -b = s
    // 2. a+b = 1+2+3+...+i -> i*(i+1) /2 = sum
    // 3. 1 步+ 2步 -> a = (sum+s)/2  b = (sum-s)/2
    // 4. 由于b是比较小的，因为b>=0 b是整数，那么sum-s 必须是大于等于0 的偶数，又由于sum = i*(i+1) /2
    public static int minStep(int n, int m) {
        if (n == m) {
            return 0;
        }
        int s = Math.abs(m - n);
        int minI = best(s << 1);
        // 从最小的满足 >= 2s 的i开始，找到大于等于i且是偶数的
        for (; (minI * (minI + 1) / 2 - s) % 2 != 0; ) {
            minI++;
        }
        return minI;
    }

    // 二分找到模糊的界限
    public static int best(int s) {
        int l = 0;
        int r = 0;
        // 2倍的去试解法
        for (; r * (r + 1) < s; ) {
            l = r;
            r *= 2;
        }
        // 因为是2倍，所以这里我们如果找到了，那么需要往左边找，看看有没有更小的
        int ans = 0;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (m * (m + 1) >= s) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
