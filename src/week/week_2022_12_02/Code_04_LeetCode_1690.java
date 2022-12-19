package week.week_2022_12_02;

/**
 * @ClassName Code_04_LeetCode_1690
 * @Author Duys
 * @Description
 * @Date 2022/12/15 13:13
 **/
// 1690. 石子游戏 VII
public class Code_04_LeetCode_1690 {
    public int stoneGameVII1(int[] stones) {
        int sum = 0;
        for (int num : stones)
            sum += num;
        int alice = first(stones, 0, stones.length - 1, sum);
        int bob = second(stones, 0, stones.length - 1, sum);
        return Math.abs(alice - bob);
    }

    // 先手来拿
    public int first(int[] arr, int l, int r, int sum) {
        if (l == r) { // 只有一个了，那么就不能得到后续的
            return 0;
        }
        // 作为先手拿l位置，获得了  sum - arr[l] ，加上当前先手作为后手的后续
        int f1 = sum - arr[l] + second(arr, l + 1, r, sum - arr[l]);
        // 后手作为先手去l+1 到r上拿
        int s1 = first(arr, l + 1, r, sum - arr[l]);

        int f2 = sum - arr[r] + second(arr, l, r - 1, sum - arr[r]);
        int s2 = first(arr, l, r - 1, sum - arr[r]);
        // 每个人都拿最优的。先手也会拿最大的返回
        return (f1 - s1) > (f2 - s2) ? f1 : f2;
    }

    // 后手来拿
    public int second(int[] arr, int l, int r, int sum) {
        if (l == r) {
            return 0;
        }
        int s1 = sum - arr[l] + second(arr, l + 1, r, sum - arr[l]);
        int f1 = first(arr, l + 1, r, sum - arr[l]);

        int s2 = sum - arr[r] + second(arr, l, r - 1, sum - arr[r]);
        int f2 = first(arr, l, r - 1, sum - arr[r]);

        return (s1 - f1) > (s2 - f2) ? f1 : f2;
    }

    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }
        int[][] first = new int[n][n];
        int[][] second = new int[n][n];
        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                int sumr = preSum[r + 1] - preSum[l];
                int a = sumr - stones[l] + second[l + 1][r];
                int b = first[l + 1][r];
                int c = sumr - stones[r] + second[l][r - 1];
                int d = first[l][r - 1];
                first[l][r] = (a - b) > (c - d) ? a : c;
                second[l][r] = (a - b) > (c - d) ? b : d;
            }
        }
        return Math.abs(first[0][n - 1] - second[0][n - 1]);
    }
}
