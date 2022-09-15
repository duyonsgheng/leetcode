package custom.code_2022_08;

/**
 * @ClassName LeetCode_518
 * @Author Duys
 * @Description
 * @Date 2022/8/17 17:56
 **/
// 518. 零钱兑换 II
public class LeetCode_518 {

    public static int change(int amount, int[] coins) {
        return process(coins, 0, amount);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int p = 0;
        for (int i = 0; i * arr[index] <= rest; i++) {
            p += process(arr, index + 1, rest - (i * arr[index]));
        }
        return p;
    }


    public static int change1(int amount, int[] coins) {
        if (amount < 0 || coins == null || coins.length <= 0) {
            return 0;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - coins[i] >= 0) {
                    dp[i][rest] += dp[i][rest - coins[i]];
                }
            }
        }
        return dp[0][amount];
    }

    public static void main(String[] args) {
        int[] arr = {99, 1};
        int r = 100;
        System.out.println(change(r, arr));
    }
}
