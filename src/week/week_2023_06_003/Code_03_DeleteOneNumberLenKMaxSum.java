package week.week_2023_06_003;

// 来自字节
// 给定整数数组arr，求删除任一元素后，
// 新数组中长度为k的子数组和的最大值
public class Code_03_DeleteOneNumberLenKMaxSum {
    // 思路，维持一个大小为k+1的窗口，然后减去窗口内的最小值
    public static int maxSum(int[] arr, int k) {
        int n = arr.length;
        if (n <= k) {
            return 0;
        }
        int[] window = new int[n]; // 窗口内的最小值更新结构
        int l = 0, r = 0, ans = Integer.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            // 如果栈顶都是大于等于当前的，之前的全部不要
            while (l < r && arr[window[r - 1]] >= arr[i])
                r--;
            window[r++] = i;
            sum += arr[i];
            if (i >= k) {
                ans = Math.max(ans, (int) (sum - arr[window[l]]));
                // 左边即将出队列，看看最小值是不是即将出去的位置
                if (window[l] == i - k) {
                    l++;
                }
                // 左边的出去
                sum -= arr[i - k];
            }
        }
        return ans;
    }
}
