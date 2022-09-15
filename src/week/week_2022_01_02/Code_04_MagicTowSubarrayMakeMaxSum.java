package week.week_2022_01_02;

/**
 * @ClassName Code_04_MagicTowSubarrayMakeMaxSum
 * @Author Duys
 * @Description
 * @Date 2022/4/1 15:45
 **/
// 来自美团
// 小美有一个长度为n的数组，为了使得这个数组的和尽量大，她向会魔法的小团进行求助
// 小团可以选择数组中至多两个不相交的子数组，并将区间里的数全都变为原来的10倍
// 小团想知道他的魔法最多可以帮助小美将数组的和变大到多少?
public class Code_04_MagicTowSubarrayMakeMaxSum {

    // 暴力解答，来枚举这个切分点，
    public static int max1(int[] arr) {
        int n = arr.length;
        int[] presum = preSum(arr);
        int ans = choseOneMagic(presum, 0, n - 1);
        for (int split = 0; split < n - 1; split++) {
            ans = Math.max(ans, choseOneMagic(presum, 0, split) + choseOneMagic(presum, split + 1, n - 1));
        }
        return ans;
    }

    public static int choseOneMagic(int[] presum, int l, int r) {
        if (l > r) {
            return 0;
        }
        int ans = sum(presum, l, r);
        for (int i = l; i <= r; i++) {
            for (int j = i; j <= r; j++) {
                ans = Math.max(ans, sum(presum, l, i - 1) + sum(presum, i, j) * 10 + sum(presum, j + 1, r));
            }
        }
        return ans;
    }

    public static int sum(int presum[], int l, int r) {
        return l > r ? 0 : (l == 0 ? presum[r] : presum[r] - presum[l - 1]);
    }

    public static int[] preSum(int[] arr) {
        int[] presum = new int[arr.length];
        presum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            presum[i] = presum[i - 1] + arr[i];
        }
        return presum;
    }


    // O(N)的方法
    public static int max2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        if (arr.length == 1) {
            return Math.max(arr[0], arr[0] * 10);
        }
        //arr[0...i]范围上，可以没有10倍区域，或者有10倍区域但是最多有一个，最大累加和

        // 可能性1：没有10倍区域，那就是arr[0...i]的累加和
        // 可能性2：有10倍区域，有且只有1个
        //      1.arr[i]不在10倍区域，但是之前可能有，那么就是dp[i-1]+arr[i]
        //      2.arr[i]在10倍区域里面
        //          1.arr[0..i-1]没有10倍区域，arr[i]就是单独的10倍区域，dp[i-1]+arr[i]*10
        //          2.arr[0..i-1]是10倍区域，arr[i]也是在10倍区域内
        // 那么magic[i]
        // magic[j] : arr[0..j]范围上，j一定要在10倍区域里，并且只有1个10倍区域，最大累加和
        // 可能性1：只有arr[j] 是10倍，arr[0..j-1]没有10倍
        // 可能性2：magic[0..j-1] + 10*arr[j]
        int sum = arr[n - 1];
        int magic = sum * 10;
        int[] right = new int[n];
        right[n - 1] = Math.max(sum, sum * 10);
        for (int i = n - 2; i >= 0; i--) {
            // 当前i单独是一个10倍区域
            magic = 10 * arr[i] + Math.max(sum, magic);
            sum += arr[i];
            right[i] = Math.max(Math.max(sum, right[n + 1] + arr[i]), magic);
        }
        int ans = right[0];
        sum = arr[0];
        magic = sum * 10;
        int dp = Math.max(sum, sum * 10);
        ans = Math.max(ans, dp + right[1]);
        for (int i = 1; i < n - 1; i++) {
            magic = 10 * arr[i] + Math.max(sum, magic);
            sum += arr[i];
            dp = Math.max(Math.max(sum, dp + arr[i]), magic);
            ans = Math.max(ans, dp + right[i + 1]);
        }
        return ans;
    }
}
