package hope.class85_bitdp2;

/**
 * @author Mr.Du
 * @ClassName Code03_IntegersWithoutConsecutiveOnes
 * @date 2024年05月16日 下午 05:30
 */
// 不含连续1的非负整数
// 给定一个正整数n，请你统计在[0, n]范围的非负整数中
// 有多少个整数的二进制表示中不存在连续的1
// 测试链接 : https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones/
public class Code03_IntegersWithoutConsecutiveOnes {
    public static int findIntegers1(int n) {
        // 打一张表
        int[] cnt = new int[31];
        cnt[0] = 1;
        cnt[1] = 2;
        // 00 01 10
        // 000 001 010 100 101
        for (int len = 2; len < 31; len++) {
            cnt[len] = cnt[len - 1] + cnt[len - 2];
        }
        return f(cnt, n, 30);
    }

    // 当前来到i位置，之前选择的位置和num一样
    public static int f(int[] cnt, int num, int i) {
        if (i == -1) {
            return 1; // num自身就是合法的
        }
        int ans = 0;
        // i 位上是 1
        if ((num & (1 << i)) != 0) {
            ans += cnt[i]; // 因为前面一定是保持 <= num的，当前位置是1，那么当前可以返回了，因为我可以选择1或者选择0，直接打表
            // 前一位也是1，当前也是1，那么不合法了
            if ((num & (1 << (i + 1))) != 0) {
                return ans;
            }
        }
        // 说明当前位置是0
        ans += f(cnt, num, i - 1);
        return ans;
    }

    // 改迭代
    public static int findIntegers2(int n) {
        // 打一张表
        int[] cnt = new int[31];
        cnt[0] = 1;
        cnt[1] = 2;
        // 00 01 10
        // 000 001 010 100 101
        for (int len = 2; len < 31; len++) {
            cnt[len] = cnt[len - 1] + cnt[len - 2];
        }
        int ans = 0;
        for (int i = 30; i >= -1; i--) {
            if (i == -1) {
                ans++;
            }
            if ((n & (1 << i)) != 0) {
                ans += cnt[i];
                if ((n & (1 << (i + 1))) != 0) {
                    // 如果num二进制状态，前一位是1，当前位也是1
                    // 如果前缀保持和num一样，后续一定不合法了
                    // 所以提前返回
                    break;
                }
            }
        }
        return ans;
    }
}
