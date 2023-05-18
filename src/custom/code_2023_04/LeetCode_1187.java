package custom.code_2023_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_1187
 * @Author Duys
 * @Description
 * @Date 2023/4/20 13:49
 **/
// 1187. 使数组严格递增
public class LeetCode_1187 {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        int pre = -1;
        for (int num : arr2) {
            if (num != pre) {
                list.add(num);
                pre = num;
            }
        }
        int n = arr1.length;
        int m = list.size();
        // 前i个元素进行了j次替换后得最小结尾元素
        int[][] dp = new int[n + 1][Math.min(n, m) + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= Math.min(m, n); j++) {
                // 当前元素大于了序列最后元素
                if (arr1[i - 1] > dp[i - 1][j]) {
                    dp[i][j] = arr1[i - 1]; // 不用替换了
                }
                if (j > 0 && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    // 二分找严格大于dp[i-1][j-1]得最小元素
                    int idx = binarySearch(list, j - 1, dp[i - 1][j - 1]);
                    if (idx != list.size()) {
                        dp[i][j] = Math.min(list.get(idx), dp[i][j]);
                    }
                }
                if (i == n && dp[i][j] != Integer.MAX_VALUE) {
                    return j;
                }
            }
        }
        return -1;
    }

    public int binarySearch(List<Integer> list, int low, int target) {
        int high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
