package duys.class_07_28;

/**
 * @ClassName ArraySplitSum2
 * @Author Duys
 * @Description
 * @Date 2021/7/28 19:40
 **/
public class ArraySplitSum2 {
    /**
     * 给定一个正数数组arr，请把arr中所有的数分成两个集合
     * 如果arr长度为偶数，两个集合包含数的个数要一样多
     * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
     * 请尽量让两个集合的累加和接近
     * 返回：
     * 最接近的情况下，较小集合的累加和
     */
    public static int arraySplitSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        // 最接近为sum的一半，转换成背包问题求解
        //是偶数长度
        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length / 2, sum >> 1);
        } else {
            return Math.max(process(arr, 0, arr.length / 2, sum >> 1),
                    process(arr, 0, arr.length / 2 + 1, sum >> 1));
        }
    }

    // picks表示挑选的数字的个数
    public static int process(int[] arr, int index, int picks, int rest) {
        // 奇数 偶数。。。
        if (index == arr.length) {
            // 用-1标记做不到有效的划分
            return picks == 0 ? 0 : -1;
        }
        // 当前位置要和不要
        int p1 = process(arr, index + 1, picks, rest);
        int next = -1;
        if (arr[index] <= rest) {
            next = process(arr, index + 1, picks - 1, rest - arr[index]);
        }
        int p2 = -1;
        // 如果要了当前位置的数，且后续是有效的，那么需要累加上当前位置，否则，无效
        if (next != -1) {
            p2 = arr[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static int dp1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        // index 0 - n
        // picks 0 -  n/2+1
        // rest  0 - sum/2
        int N = arr.length;
        int M = (N + 1) >> 1;
        int P = sum >> 1;
        int[][][] dp = new int[N + 1][M + 1][P + 1];
        // 根据base case

        // 所有初始位置全部都是-1
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= P; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        // 当index == arr.length
        for (int j = 0; j <= P; j++) {
            dp[N][0][j] = 0;
        }
        // 到这里，index = N 的这一层就填写完了，然后填index = N-1这一层了
        for (int index = N - 1; index >= 0; index--) {
            for (int picks = 0; picks <= M; picks++) {
                for (int rest = 0; rest <= P; rest++) {
                    // 当前位置要和不要
                    int p1 = dp[index + 1][picks][rest];
                    int next = -1;
                    if (picks - 1 >= 0 && arr[index] <= rest) {
                        next = dp[index + 1][picks - 1][rest - arr[index]];
                    }
                    int p2 = -1;
                    // 如果要了当前位置的数，且后续是有效的，那么需要累加上当前位置，否则，无效
                    if (next != -1) {
                        p2 = arr[index] + next;
                    }
                    dp[index][picks][rest] = Math.max(p1, p2);
                }
            }
        }
        // 最接近为sum的一半，转换成背包问题求解
        //是偶数长度
        if ((arr.length & 1) == 0) {
            return dp[0][M][P];//process(arr, 0, sum >> 1, arr.length / 2);
        } else {
            return Math.max(dp[0][M][P], dp[0][M + 1][P]);
        }
    }
}
