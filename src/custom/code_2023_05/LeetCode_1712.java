package custom.code_2023_05;

/**
 * @ClassName LeetCode_1712
 * @Author Duys
 * @Description
 * @Date 2023/5/8 9:27
 **/
// 1712. 将数组分成三个子数组的方案数
public class LeetCode_1712 {
    // 数组被分成三个 非空 连续子数组，从左至右分别命名为 left ， mid ， right 。
    // left 中元素和小于等于 mid 中元素和，mid 中元素和小于等于 right 中元素和。
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int mod = 1_000_000_007;
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        long ans = 0;
        // i,j,k都是不回退的
        for (int i = 1, j = 2, k = 1; i < n && sums[i] * 3 <= sums[n]; i++) {
            int left = sums[i];
            j = Math.max(i + 1, j);
            while (j < n && sums[j] - sums[i] < left) {
                j++;
            }
            while (k < n - 1 && sums[k + 1] - sums[i] <= sums[n] - sums[k + 1]) {
                k++;
            }
            ans += k - j + 1;
        }
        return (int) (ans % mod);
    }
}
