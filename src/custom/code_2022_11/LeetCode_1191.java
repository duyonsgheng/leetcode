package custom.code_2022_11;

/**
 * @ClassName LeetCode_1191
 * @Author Duys
 * @Description
 * @Date 2022/11/23 11:44
 **/
// 1191. K 次串联后最大子数组之和
public class LeetCode_1191 {
    public static int mod = 1_000_000_007;

    public int kConcatenationMaxSum1(int[] arr, int k) {
        // arr = [1,-2,1], k = 5
        // 1 -2 1  1 -2 1  1 -2 1  1 -2 1  1 -2 1
        // 先找一组的，然后再把两个数组拼起来
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        long maxEnd = arr[0] > 0 ? arr[0] : 0; // 代表以当前数结尾的最大和子数组
        long max = maxEnd;
        long sum = arr[0];
        int n = arr.length;
        for (int i = 1; i < Math.min(k, 2) * n; i++) {
            maxEnd = Math.max(maxEnd + arr[i % n], arr[i % n]);
            max = Math.max(maxEnd, max);
            if (i < n) {
                sum += arr[i];
            }
        }
        while (sum > 0 && --k >= 2) {
            max = (max + sum) % mod;
        }
        return (int) max % mod;
    }

    public int kConcatenationMaxSum(int[] arr, int k) {
        int[] max1 = max(arr, 0, 0, 0);
        if (k <= 1) {
            return max1[0];
        }
        int[] max2 = max(arr, max1[0], max1[1], arr[0]);
        if (max1[0] >= max2[0] || k == 2) {
            return max1[0];
        }
        int[] max3 = max(arr, max2[0], max2[1], arr[0]);
        if (max3[0] == max2[0]) {
            return max3[0];
        }
        return (int) ((max2[0] + (long) (max3[0] - max2[0]) * (k - 2)) % mod);
    }

    // arr
    // 前一个数组的最大子数组
    // 前一个数组以最后结尾的最大子数组
    // 最大值的基础
    public static int[] max(int[] arr, int preMax, int preMaxEnd, int pre) {
        long last = Math.max(preMaxEnd + arr[0], pre);
        long max = Math.max(preMax, last);
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            long end = Math.max(last + cur, cur);
            max = Math.max(max, end);
            max %= mod;
            end %= mod;
            last = end;
        }
        return new int[]{(int) max, (int) last};
    }
}
