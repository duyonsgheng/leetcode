package duys_code.day_49;

/**
 * @ClassName Code_06_440_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 * @Date 2021/10/27 17:22
 **/
public class Code_06_440_LeetCode {
    /**
     * 给定一个正数n和k ，找到1~n字典序种，第k小的数字
     */
    /**
     * 字典序：比如 1xx 1开头，三位的字典序种有哪些
     * 1        1个
     * 10~19    10个
     * 110~199  100个
     * 总共111个
     * 1开头的 4位总共有 1111个
     */
    public static int[] offset = {0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    public static int[] number = {0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111, 1111111111};

    public int findKthNumber(int n, int k) {
        // 获取当前数字的长度
        int len = len(n);
        // 获取当前数字的开头数字
        int first = n / offset[len];
        // 左边区域有多少个数
        int leftSum = (first - 1) * number[len];

        int pick = 0;
        int already = 0;
        // 直接就在左边去找了
        if (k <= leftSum) {
            // 向上取整，比如要找13222
            pick = (k + number[len] - 1) / number[len];
            already = (pick - 1) * number[len];
            return kth((pick + 1) * offset[len] - 1, len, k - already);
        }
        // 中间区域
        // 比如 65323
        int mid = number[len - 1] + (n % offset[len]) + 1;
        if (k - leftSum <= mid) {
            return kth(n, len, k - leftSum);
        }
        // 在右边区域
        k -= leftSum + mid;
        len--;
        pick = (k + number[len] - 1) / number[len] + first;
        already = (pick - first - 1) * number[len];
        return kth((pick + 1) * offset[len] - 1, len, k - already);
    }

    public static int kth(int max, int len, int k) {
        // 是否在中间区域
        boolean isToMid = true;
        int ans = max / offset[len];
        while (--k > 0) {
            max %= offset[len--];
            int pick = 0;
            if (!isToMid) {
                pick = (k - 1) / number[len];
                ans = ans * 10 + pick;
                k -= pick * number[len];
            } else {
                int first = max / offset[len];
                int left = first * number[len];
                if (k <= left) {
                    isToMid = false;
                    pick = (k - 1) / number[len];
                    ans = ans * 10 + pick;
                    k -= pick * number[len];
                    continue;
                }
                k -= left;
                int mid = number[len - 1] + (max % offset[len]) + 1;
                if (k <= mid) {
                    ans = ans * 10 + first;
                    continue;
                }
                isToMid = false;
                k -= mid;
                len--;
                pick = (k + number[len] - 1) / number[len] + first;
                ans = ans * 10 + pick;
                k -= (pick - first - 1) * number[len];
            }

        }
        return ans;
    }

    // 获取数字的长度
    public static int len(int n) {
        int len = 0;
        while (n != 0) {
            n /= 10;
            len++;
        }
        return len;
    }
}
