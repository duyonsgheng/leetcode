package duys.class_07_28;

/**
 * @ClassName ArraySplitSum
 * @Author Duys
 * @Description
 * @Date 2021/7/28 18:28
 **/
public class ArraySplitSum {
    /**
     * 给定一个正数数组arr，
     * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
     * 返回：
     * 最接近的情况下，较小集合的累加和
     */
    public static int arrSplit(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        // 最接近为sum的一半，转换成背包问题求解
        return process(arr, 0, sum >> 1);
    }

    // index 表示来到数组的位置，rest表示剩余需要搞定的数（尽量接近rest，但是不能超过rest）
    public static int process(int[] arr, int index, int rest) {

        if (index == arr.length) {
            return 0;
        } else {
            // 选择要当前位置和不要当前位置
            int p1 = process(arr, index + 1, rest);
            int p2 = 0;
            // 当前要的位置，不超过目标值，才能要
            if (arr[index] <= rest) {
                p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
            }
            // 已经限制了不能大于rest，所以这里谁更大就是离rest更近
            return Math.max(p1, p2);
        }
    }

    /**
     * - 0 1 2 3 4 5 - sum>>1 rest
     * 0
     * 1
     * 2
     * 3 0 0 0 0 0 0 (base case得到的)
     * index
     */
    public static int dp1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int N = arr.length;
        int M = sum >> 1;
        // 行是数组长度，列是 目标
        // index -  0 - N
        // rest  -  0 - M
        int[][] dp = new int[N + 1][M + 1];
        // base case
        /*for (int i = 0; i <= M; i++) {
            dp[N][i] = 0;
        }*/
        // 当前行依赖下一行的位置
        // 从下往上填
        for (int index = N - 1; index >= 0; index--) {

            for (int rest = 0; rest <= M; rest++) {
                // 选择要当前位置和不要当前位置
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest >= arr[index]) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][M];
    }
}
