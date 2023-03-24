package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1508
 * @Author Duys
 * @Description
 * @Date 2023/2/3 15:27
 **/
// 1508. 子数组和排序后的区间和
public class LeetCode_1508 {
    int mod = 1_000_000_007;

    public int rangeSum1(int[] nums, int n, int left, int right) {
        // 统计
        int[] arr = new int[n * (n + 1) / 2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = i; j < n; j++) {
                cur += nums[j];
                arr[index++] = cur;
            }
        }
        Arrays.sort(arr);
        int ans = 0;
        for (int i = left - 1; i < right; i++) {
            ans += (ans + arr[i]) % mod;
        }
        return ans;
    }

    // 使用前缀和的前缀和
    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int[] prePreSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prePreSum[i + 1] = prePreSum[i] + preSum[i + 1];
        }
        return (getSum(preSum, prePreSum, n, right) - getSum(preSum, prePreSum, n, left - 1)) % mod;
    }

    public int getSum(int[] preSum, int[] prePreSum, int n, int k) {
        int num = getKth(preSum, n, k);
        int sum = 0;
        int cnt = 0;
        for (int i = 0, j = 1; i < n; i++) {
            while (j <= n && preSum[j] - preSum[i] < num) {
                j++;
            }
            j--;
            sum = (sum + prePreSum[j] - prePreSum[i] - preSum[i] * (j - i)) % mod;
            cnt += j - i;
        }
        sum = (sum + num * (k - cnt)) % mod;
        return sum;
    }

    public int getKth(int[] preSum, int n, int k) {
        int low = 0, hig = preSum[n];
        while (low < hig) {
            int m = low + ((hig - low) >> 1);
            if (getCount(preSum, n, m) < k) {
                low = m + 1;
            } else hig = m;
        }
        return low;
    }

    // 找到小于等于k元素的个数
    public int getCount(int[] preSum, int n, int k) {
        int cnt = 0;
        for (int i = 0, j = 1; i < n; i++) {
            while (j <= n && preSum[j] - preSum[i] <= k) {
                j++;
            }
            j--;
            cnt += j - i;
        }
        return cnt;
    }
}
