package custom.code_2022_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_902
 * @Author Duys
 * @Description
 * @Date 2022/10/18 9:53
 **/
// 902. 最大为 N 的数字组合
public class LeetCode_902 {

    public static void main(String[] args) {
        String[] digits = {"1", "3", "5", "7"};
        int n = 100;
        System.out.println(atMostNGivenDigitSet(digits, n));
    }

    // 数位DP
    public static int atMostNGivenDigitSet(String[] digits, int n) {
        String s = Integer.toString(n);
        int m = digits.length;
        int k = s.length();
        // dp[i][0] 从digits选出数字 小于 n的前i位数字组成的数字
        // dp[i][1] 从digits选出数字 等于 n的前i位数字组成的数字
        // 例如 n = 2345
        // 2 23 234 2345
        int[][] dp = new int[k + 1][2];
        dp[0][1] = 1; // 仅当0的时候，存在一个
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < m; j++) {
                if (digits[j].charAt(0) == s.charAt(i - 1)) {
                    dp[i][1] = dp[i - 1][1];
                } else if (digits[j].charAt(0) < s.charAt(i - 1)) {
                    dp[i][0] += dp[i - 1][1];
                } else {
                    break;
                }
            }
            if (i > 1) {
                dp[i][0] += m + dp[i - 1][0] * m;
            }
        }
        return dp[k][0] + dp[k][1];
    }

    // 分析：把digits转为nums，因为digits中已经是非递减序，
    // 我们使用 digits 中的数组组成的数 <= x，满足一下三种
    // 1.组成的数字和x位数相同，且最高位是小于x的最高位的，记为ans1
    // 2.组成的数字和x位数相同，且最高位是等于x的最高位的。记为ans2
    // 3.组成的数字比x位数少，记为ans3
    public static int atMostNGivenDigitSet1(String[] digits, int n) {
        int len = digits.length;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(digits[i]);
        }
        //
        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % 10);
            n /= 10;
        }
        // n的位数
        int size = list.size();
        int ans = 0;
        // 1.位数和n相同的
        for (int i = size - 1, p = 1; i >= 0; i--, p++) {
            int cur = list.get(i);
            int l = 0, r = len - 1;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                // 找到 <= mid的最右位置
                if (nums[mid] <= cur) l = mid;
                else r = mid - 1;
            }
            if (nums[r] > cur) {
                break;
            } else if (nums[r] == cur) {
                ans += r * (int) Math.pow(len, (size - p));
                if (i == 0) { // 为0的时候，需要多加一个
                    ans++;
                }
            } else {
                ans += (r + 1) * (int) Math.pow(len, (size - p));
                break;
            }
        }

        // 位数比n少的
        for (int i = 1, last = 1; i < size; i++) {
            int cur = last * len;
            ans += cur;
            last = cur;
        }
        return ans;
    }
}
