package custom.code_2022_08;

/**
 * @ClassName LeetCode_400
 * @Author Duys
 * @Description
 * @Date 2022/8/9 10:47
 **/
//400. 第 N 位数字
//给你一个整数 n ，请你在无限的整数序列[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第n 位上的数字。
//链接：https://leetcode.cn/problems/nth-digit
public class LeetCode_400 {
    // 长度为n的数字范围
    // [10^(n-1) ,10^n -1]
    public int findNthDigit(int n) {
        int len = 1;
        while (len * 9 * Math.pow(10, len - 1) < n) {
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }
        long s = (long) Math.pow(10, len - 1);
        long x = n / len - 1 + s;
        n -= (x - s + 1) * len;
        return n == 0 ? (int) (x % 10) : (int) ((x + 1) / Math.pow(10, len - n) % 10);
    }

    public int process(int len) {
        int d = 0;
        int curL = 1;
        int curC = 9;
        while (curL <= len) {
            d += curL * curC;
            curL++;
            curC *= 10;
        }
        return d;
    }
}
