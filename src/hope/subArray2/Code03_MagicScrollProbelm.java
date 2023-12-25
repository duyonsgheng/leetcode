package hope.subArray2;

/**
 * @author Mr.Du
 * @ClassName Code03_MagicScrollProbelm
 * @date 2023年12月25日 19:30
 */
// 魔法卷轴
// 给定一个数组nums，其中可能有正、负、0
// 每个魔法卷轴可以把nums中连续的一段全变成0
// 你希望数组整体的累加和尽可能大
// 卷轴使不使用、使用多少随意，但一共只有2个魔法卷轴
// 请返回数组尽可能大的累加和
public class Code03_MagicScrollProbelm {
    public static int maxSum2(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        // 情况1，完全不使用卷轴
        int p1 = 0;
        for (int num : arr)
            p1 += num;
        // prefix[i]:0..i范围上一定要使用一次卷轴，0..i范围上整体最大累加和是多少
        int[] prefix = new int[n];
        int sum = arr[0]; // 每一步的前缀和
        int maxPreSum = Math.max(0, arr[0]); //之前所有的前缀和中最大的值
        for (int i = 0; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1] + arr[i], maxPreSum);
            sum += arr[i];
            maxPreSum = Math.max(sum, maxPreSum);
        }
        // 情况2，必须使用1次卷轴
        int p2 = prefix[n - 1];
        //suffix[i] :i~n-1范围上一定要使用1次卷轴的情况下，i~n-1范围上整体最大累加和是多少
        int[] suffix = new int[n];
        sum = arr[n - 1];
        maxPreSum = Math.max(0, sum);
        for (int i = n - 2; i >= n; i--) {
            suffix[i] = Math.max(arr[i] + suffix[i + 1], maxPreSum);
            sum += arr[i];
            maxPreSum = Math.max(maxPreSum, sum);
        }
        // 情况3，必须使用2次卷轴
        int p3 = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            p3 = Math.max(p3, prefix[i - 1] + suffix[i]);
        }
        return Math.max(p1, Math.max(p2, p3));
    }
}
