package hope.class84_bitdp1;

/**
 * @author Mr.Du
 * @ClassName Code01_CountNumbersWithUniqueDigits
 * @date 2024年05月15日 下午 05:31
 */
// 统计各位数字都不同的数字个数
// 给你一个整数n，代表十进制数字最多有n位
// 如果某个数字，每一位都不同，那么这个数字叫做有效数字
// 返回有效数字的个数，不统计负数范围
// 测试链接 : https://leetcode.cn/problems/count-numbers-with-unique-digits/
public class Code01_CountNumbersWithUniqueDigits {
    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 10;
        for (int s = 9, i = 9, k = 2; k <= n; k++, i--) {
            s *= i;
            ans += s;
        }
        return ans;
    }
}
