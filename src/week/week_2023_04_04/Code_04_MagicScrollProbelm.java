package week.week_2023_04_04;

/**
 * @ClassName Code_04_MagicScrollProbelm
 * @Author Duys
 * @Description
 * @Date 2023/4/27 14:08
 **/
// 来自微众银行
// 两个魔法卷轴问题
// 给定一个数组arr，其中可能有正、负、0，
// 一个魔法卷轴可以把arr中连续的一段全变成0，你希望数组整体的累加和尽可能大
// 你有两个魔法卷轴，请返回数组尽可能大的累加和
// 1 <= arr长度 <= 100000
// -100000 <= arr里的值 <= 100000
public class Code_04_MagicScrollProbelm {
    // dp
    // 可能性分析
    // 1.一个也不用
    // 2.用一个
    //   2.1 当前位置用
    //   2.2 当前位置不用
    // 3.用两个
    //  当前位置的左边用一个，右边用一个
    public static int maxSum(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int p1 = 0; // 一个也不用
        for (int num : arr) {
            p1 += num;
        }
        int n = arr.length;
        int[] left = new int[n];
        int sum = arr[0];
        int maxSum = Math.max(0, sum);
        for (int i = 1; i < n; i++) {
            // 当前位置不用，那么就是dp[i-1]+arr[i]
            // 当前位置用，那么就是之前遇到的最大值
            left[i] = Math.max(left[i - 1] + arr[i], maxSum);
            sum += arr[i];
            maxSum = Math.max(maxSum, sum);
        }
        int p2 = left[n - 1];
        int[] right = new int[n];
        sum = arr[n - 1];
        maxSum = Math.max(sum, 0);
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1] + arr[i], maxSum);
            sum += arr[i];
            maxSum = Math.max(maxSum, sum);
        }
        int p3 = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            p3 = Math.max(p3, left[i - 1] + right[i]);
        }
        return Math.max(p1, Math.max(p2, p3));
    }
}
