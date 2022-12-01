package week.week_2022_11_05;

/**
 * @ClassName Code_04_LeetCode_400
 * @Author Duys
 * @Description
 * @Date 2022/12/1 10:43
 **/
// https://leetcode.cn/problems/nth-digit/
public class Code_04_LeetCode_400 {
    // 观察+数位DP
    public static final long[] under = {
            0L,    // 0位数，一共能解决几个位
            9L,    // 1位数，一共能解决几个位
            189L,  // 1~2位数，一共能解决几个位
            2889L, // 1~3位数，一共能解决几个位
            38889L,
            488889L,
            5888889L,
            68888889L,
            788888889L,
            8888888889L,
            98888888889L};

    public static final int[] help = {
            0,
            1,    // 1
            10,   // 2
            100,  // 3
            1000, // 4
            10000,
            100000,
            1000000,
            10000000,
            100000000,
            1000000000};

    // 先看看第n位数字落到了几位数上
    // 然后一次从左往右确定一位后算下一位
    public static int findNthDigit(int n) {
        int len = 0;
        for (int i = 1; i < under.length; i++) {
            if (under[i] >= n) {
                len = i;
                break;
            }
        }
        return number(0, len, help[len], help[len], (int) (n - under[len - 1]));
    }

    public static int number(int prePath, int len, int offset, int all, int nth) {
        if (offset == 0) {
            return (prePath / help[nth]) % 10;
        }
        // 当前如果是最高位，那么一定不能是0
        int cur = offset == all ? 1 : 0;
        // 看看有几轮，比如当前nth = 1345
        // 三位数组 当前是1 那么就是100 -199 100个数组，一轮就能搞定300个
        int j = nth / (len * offset);
        if (nth % (len * offset) == 0) {
            j--;
        }
        cur += j;
        // 搞定了一位，继续下一位开始
        return number(cur * (all / offset) + prePath, len, offset / 10, all, nth - j * len * offset);
    }
}
