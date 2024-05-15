package hope.class84_bitdp1;

/**
 * @author Mr.Du
 * @ClassName Code04_CountSpecialIntegers
 * @date 2024年05月15日 下午 05:11
 */
// 完全没有重复的数字个数
// 给定正整数n，返回在[1, n]范围内每一位都互不相同的正整数个数
// 测试链接 : https://leetcode.cn/problems/count-special-integers/
public class Code04_CountSpecialIntegers {
    public static int countSpecialNumbers(int n) {
        int len = 1;
        int offset = 1;
        int tmp = n / 10;
        while (tmp > 0) {
            len++;
            offset *= 10;
            tmp /= 10;
        }
        // cnt[i]
        // 一共长度为len，还剩下i位没有确定，已经确定的前缀为 len-i,且确定的前缀不为空，且有效
        // 比如 len=4
        // cnt[4] 不计算
        // cnt[3] = 9*8*7
        // cnt[2] = 8*7
        // cnt[1] = 7
        // cnt[0] = 1 表示之前做的决定是有效的
        // 比如len = 6
        // cnt[6] 不计算
        // cnt[5] =9*8*7*6*5
        // cnt[4] = 8*7*6*5
        // cnt[3] = 7*6*5
        // cnt[2] = 6*5
        // cnt[1] = 5
        // cnt[0] = 1
        int[] cnt = new int[len];
        cnt[0] = 1;
        for (int i = 1, k = 10 - len + 1; i < len; i++, k++) {
            cnt[i] = cnt[i - 1] * k;
        }
        int ans = 0;
        if (len >= 2) {
            ans = 9;
            // 这个意思就是 比如5位，我只算小于5位的数
            for (int i = 2, a = 9, b = 9; i < len; i++, b--) {
                a *= b;
                ans += a;
            }
        }
        // 现在就计算len长度的数，有多少
        int first = n / offset; // 最高位
        // 如果最高位上小于目标最高位first，那么可以直接算
        ans += (first - 1) * cnt[len - 1];
        // 如果最高位是等于目标最高位first
        ans += f(cnt, n, len - 1, offset / 10, 1 << first);
        return ans;
    }

    // 前面的前缀已经确定和num的对应前缀相同了
    // 还有len位没确定
    // 并且那些数字已经选了，状态在status中
    // 返回 <= num，且每一位数字都不一样的数字个数
    public static int f(int[] cnt, int num, int len, int offset, int status) {
        if (len == 0) {
            return 1; // 其实就是num本身。
        }
        int ans = 0;
        int first = (num / offset) % 10;
        // 先看看小于目标位置对应的数，直接结算
        for (int cur = 0; cur < first; cur++) {
            if ((status & (1 << cur)) == 0) { // 小于且数字没用过的
                ans += cnt[len - 1];
            }
        }
        // 最后算，和目标对应位置的数字相等的情况
        if ((status & (1 << first)) == 0) {
            ans += f(cnt, num, len - 1, offset / 10, status ^ (1 << first));
        }
        return ans;
    }
}
