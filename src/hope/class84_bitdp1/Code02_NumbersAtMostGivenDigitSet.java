package hope.class84_bitdp1;

/**
 * @author Mr.Du
 * @ClassName Code02_NumbersAtMostGivenDigitSet
 * @date 2024年05月15日 下午 05:32
 */
// 最大为N的数字组合
// 给定一个按 非递减顺序 排列的数字数组 digits
// 已知digits一定不包含'0'，可能包含'1' ~ '9'，且无重复字符
// 你可以用任意次数 digits[i] 来写的数字
// 例如，如果 digits = ['1','3','5']
// 我们可以写数字，如 '13', '551', 和 '1351315'
// 返回 可以生成的小于或等于给定整数 n 的正整数的个数
// 测试链接 : https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/
public class Code02_NumbersAtMostGivenDigitSet {
    public static int atMostNGivenDigitSet1(String[] strs, int num) {
        int tmp = num / 10;
        int len = 1;
        int offset = 1;
        while (tmp > 0) {
            len++;
            offset *= 10;
            tmp /= 10;
        }
        int m = strs.length;
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.valueOf(strs[i]);
        }
        return f(arr, num, offset, len, 0, 0);
    }

    // 还剩下len位没搞定
    // 如果之前决定的位置对应的数 已经比num对应前缀都要小了，free=1，表示后面的数可以自由决定
    // 如果之前决定的位置和num对应的前缀一样，free=0，表示不能自由选择
    // fix = 1表示 ，前面已经选择过数字的
    // fix = 0表示，前面没有选择任何数字 00000全0的状态
    public static int f(int[] arr, int num, int offset, int len, int free, int fix) {
        if (len == 0) {
            return fix == 0 ? 0 : 1; // 决定完了，但是一个数都没选，那么就没用
        }
        int ans = 0;
        int cur = (num / offset) % 10;
        if (fix == 0) {
            // 之前没选择过数字
            // 接下来依然可以选择什么都不选
            ans += f(arr, num, offset / 10, len - 1, 1, 0);
        }
        // 不能自由选择
        if (free == 0) {
            for (int i : arr) {
                if (i < cur) { // 小于目标的对应位置
                    ans += f(arr, num, offset / 10, len - 1, 1, 1);
                } else if (cur == i) {
                    ans += f(arr, num, offset / 10, len - 1, 0, 1);
                }
            }
        } else {
            // 可以自由选择
            ans += f(arr, num, offset / 10, len - 1, 1, 1);
        }
        return ans;
    }

    // 优化
    public static int atMostNGivenDigitSet2(String[] strs, int num) {
        int tmp = num / 10;
        int len = 1;
        int offset = 1;
        while (tmp > 0) {
            len++;
            offset *= 10;
            tmp /= 10;
        }
        int m = strs.length;
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.valueOf(strs[i]);
        }
        // 打个表，比如位数不够len的情况
        int[] cnt = new int[len];
        cnt[0] = 1; // 表示之前做的决定是有效的
        int ans = 0;
        for (int i = m, k = 1; k < len; k++, i *= m) {
            cnt[k] = i;
            ans += i;
        }
        return ans + f1(arr, cnt, num, offset, len);
    }

    // 这个意义变化了
    // 只有之前决定的策略和num一致的情况下，才走递归，其他的情况，直接打表了
    public static int f1(int[] arr, int[] cnt, int num, int offset, int len) {
        if (len == 0) {
            return 1;
        }
        int cur = (num / offset) % 10;
        int ans = 0;
        for (int i : arr) {
            if (i < cur) {
                ans += cnt[len - 1];
            } else if (i == cur) {
                ans += f1(arr, cnt, num, offset / 10, len - 1);
            }
        }
        return ans;
    }
}
