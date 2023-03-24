package custom.code_2023_02;

/**
 * @ClassName LeetCode_1524
 * @Author Duys
 * @Description
 * @Date 2023/2/6 11:06
 **/
// 1524. 和为奇数的子数组数目
public class LeetCode_1524 {
    // 奇数偶数
    public int numOfSubarrays1(int[] arr) {
        int mod = 1_000_000_007;
        long ans = 0;
        int odd = 0; // 奇数
        int even = 0; // 偶数
        for (int i = 0; i < arr.length; i++) {
            // 这里同样如此。当前是奇数的话，需要找到之前几个偶数了
            if ((arr[i] & 1) == 1) {
                int cur = even;
                even = odd;
                odd = cur + 1;
            } else even++;
            ans += odd;
            ans %= mod;
        }
        return (int) ans;
    }

    public int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;
        long ans = 0;
        long sum = 0;
        int odd = 0; // 奇数
        int even = 1; // 偶数，前缀和是0，的时候，也算一个偶数
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // 这里为什么是偶数的时候。要取奇数呢？而是奇数的时候要取偶数呢？
            // 因为算到当前位置，如果前缀和是偶数，那么我们需要找到前面奇数和的个数，偶数+奇数才是奇数，奇偶互斥性
            ans += (ans + (sum % 2 == 0 ? odd : even)) % mod;
            if (sum % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return (int) ans;
    }
}
