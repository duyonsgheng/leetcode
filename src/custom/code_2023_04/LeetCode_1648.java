package custom.code_2023_04;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1648
 * @Author Duys
 * @Description
 * @Date 2023/4/23 14:49
 **/
// 1648. 销售价值减少的颜色球
public class LeetCode_1648 {

    public int maxProfit(int[] inventory, int orders) {
        int mod = 1_000_000_007;
        int max = Arrays.stream(inventory).max().getAsInt();
        long l = 0, r = max;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            long sum = 0;
            for (int in : inventory) {
                if (in > mid) {
                    sum += (in - mid);
                }
            }
            if (sum >= orders) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        long ans = 0;
        for (int num : inventory) {
            if (num > l) {
                orders -= (num - l);
                ans += (num + l + 1) * (num - l) / 2; // 等差数列求和公式 n(a1+an)/2  n*a1+n(n-1)*d/2
                ans %= mod;
            }
        }
        ans += orders * l;
        return (int) (ans % mod);

    }
}
