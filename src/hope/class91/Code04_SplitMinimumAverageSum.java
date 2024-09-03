package hope.class91;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code04_SplitMinimumAverageSum
 * @date 2024年09月03日 下午 04:51
 */

// 平均值最小累加和
// 给定一个数组arr，长度为n
// 再给定一个数字k，表示一定要将arr划分成k个集合
// 每个数字只能进一个集合
// 返回每个集合的平均值都累加起来的最小值
// 平均值向下取整
// 1 <= n <= 10^5
// 0 <= arr[i] <= 10^5
// 1 <= k <= n
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Code04_SplitMinimumAverageSum {
    // 当前k-1个数单独成为k-1个集合，后面剩下的数，都在一个集合，
    public static int minAverageSum(int[] arr, int k) {
        Arrays.sort(arr);
        int ans = 0;
        for (int i = 0; i < k - 1; i++) {
            ans += arr[i];
        }
        int sum = 0;
        for (int i = k - 1; i < arr.length; i++) {
            sum += arr[i];
        }
        ans += sum / (arr.length - k + 1);
        return ans;
    }
}
