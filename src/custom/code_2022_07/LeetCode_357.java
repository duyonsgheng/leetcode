package custom.code_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_357
 * @Author Duys
 * @Description
 * @Date 2022/7/20 10:45
 **/
// 357. 统计各位数字都不同的数字个数
// 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
public class LeetCode_357 {

    // 排列组合
    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        // 已经包含了 0 和 1
        int ans = 10;
        for (int i = 2, last = 9; i <= n; i++) {
            int cur = last * (10 - i + 1);
            ans += cur;
            last = cur;
        }
        return ans;
    }

    // 假设 dp(x) 函数是能返回 [0,x]之间有多少个数合法
    // 1.位数和x相同，且最高位比x的最高位要小
    // 2.位数和x相同，且最高位与x的最高位相同
    // 3.位数比x少，
    // 举例：x = 678
    // 情况1：534
    // 情况2：高位相同了都是6，那么次高位 那么次高位选 0-6 6已经选了，只能0-5 6中选择
    public static int countNumbersWithUniqueDigits_dp(int n) {
        int[][] dp = new int[10][10];
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                int cur = 1;
                for (int k = i; k <= j; k++) {
                    cur *= k;
                    dp[i][j] = cur;
                }
            }
        }

        return dp((int) Math.pow(10, n) - 1, dp);
    }

    public static int dp(int x, int[][] dp) {
        int target = x;
        List<Integer> nums = new ArrayList<>();
        while (target != 0) {
            // 依次从最低到最高位
            nums.add(target % 10);
            target /= 10;
        }
        int len = nums.size();
        // 如果只有一位，那就是[0~9]
        if (len <= 1) {
            return x + 1;
        }
        int ans = 0;
        // 来记录位数和x相同的
        //  s 的低10位，来记录0-9使用了哪些数字了
        // p 是填充了多少位了，填充了多少位置，意味着(0-9)使用了多少个数字了
        for (int i = len - 1, p = 1, s = 0; i >= 0; i--, p++) {
            int cur = nums.get(i), cnt = 0;
            // 最高位从0到cur-1
            for (int j = cur - 1; j >= 0; j--) {
                if (i == len - 1 && j == 0) { // 最高位不能为0
                    continue;
                }
                // 还没用的数字，是有效的
                if (((s >> j) & 1) == 0) {
                    cnt++;
                }
            }
            // 还有几位要填充的，剩下几个数字
            int a = 10 - p;
            int b = a - (len - p) + 1;
            ans += b <= a ? cnt * dp[b][a] : cnt;
            if (((s >> cur) & 1) == 1) {
                break;
            }
            // 使用过了，就需要记录上，不能再使用了
            s |= (1 << cur);
            if (i == 0) {
                ans++;
            }
        }

        // 位数比x还少的
        ans += 10; // n为1的时候。
        // 普遍情况
        for (int i = 2, last = 9; i < len; i++) {
            int cur = last * (10 - i + 1);
            ans += cur;
            last = cur;
        }
        return ans;
    }

}
