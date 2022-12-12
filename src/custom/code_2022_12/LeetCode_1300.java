package custom.code_2022_12;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1300
 * @Author Duys
 * @Description
 * @Date 2022/12/5 17:31
 **/
// 1300. 转变数组后最接近目标值的数组和
public class LeetCode_1300 {
    // 根据数据量，二分
    public int findBestValue1(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];
        }
        int r = arr[n - 1];
        int ans = 0, diff = target;
        for (int i = 1; i <= r; i++) {
            int index = Arrays.binarySearch(arr, i);
            if (index < 0) {
                index = -index - 1;
            }
            int cur = preSum[index] + (n - index) * i;
            if (Math.abs(cur - target) < diff) {
                ans = i;
                diff = Math.abs(cur - target);
            }
        }
        return ans;
    }

    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int x = (target - sum) / (n - i);
            if (x < arr[i]) {
                double t = (double) (target - sum) / (n - i);
                if (t - x > 0.5) {
                    return x + 1;
                } else {
                    return x;
                }
            }
            sum += arr[i];
        }
        return arr[n - 1];
    }
}
