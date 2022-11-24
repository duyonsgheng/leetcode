package week.week_2022_11_04;

/**
 * @ClassName Code_02_LeetCode_1049
 * @Author Duys
 * @Description
 * @Date 2022/11/24 16:22
 **/
// 1049. 最后一块石头的重量 II
public class Code_02_LeetCode_1049 {
    // 标准动态规划
    // 相当于每一个元素前 加上 + 或者 - 然后算累计和接近 sum/2
    // 要求最后一快留下的石头尽可能的小，那么只要我们 + - 算出来的值尽可能的接近 sum/2 那么结果就最小
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }
        int half = sum / 2;
        int[][] dp = new int[n + 1][half + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= half; rest++) {
                // 不要当前的石头，
                int p1 = dp[i + 1][rest];
                int p2 = 0;
                // 要当前的石头
                if (stones[i] <= rest) {
                    p2 = stones[i] + dp[i + 1][rest - stones[i]];
                }
                // 选择尽可能大的结果
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return sum - (dp[0][half] * 2);
    }
}
