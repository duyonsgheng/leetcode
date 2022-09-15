package duys_code.day_42;

/**
 * @ClassName Code_01_265_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/paint-house-ii/
 * @Date 2021/12/29 17:47
 **/
public class Code_01_265_LeetCode {
    // 房子编号0到n-1，每一座房子可以染色，每一座房子对应颜色的代价不一样，要求相邻房子不能相同颜色，求最小的代价
    // 普通的解答方法是dp
    // dp[i][j] 的含义是第i号房子必须上j号色的最低代价是啥

    // 但是这个题有更好的解答
    // 我们想这么一个问题。第一号房子最右答案和次优答案给拿出来
    // 然后用当前的房子的最优和次优看看会不会和之前的冲突
    // 依次这么递推
    public static int minCostII(int[][] costs) {
        // 总共有多少房子
        int n = costs.length;
        if (n == 0) {
            return 0;
        }
        // 总共有多少中颜色
        int k = costs[0].length;
        // 之前取得最小的代价
        int preMinCost = 0;
        // 之前取得最小的代价时候的颜色
        int preMinCostColor = -1;

        // 之前取得次小的代价
        int preMin2Cost = 0;
        // 之前取得次小的代价时候的颜色
        int preMin2CostColor = -1;

        for (int i = 0; i < n; i++) { // i号房子
            // 当前的四个变量
            int curMinCost = Integer.MAX_VALUE;
            int curMinCostColor = -1;
            int curMin2Cost = Integer.MAX_VALUE;
            int curMin2CostColor = -1;
            for (int j = 0; j < k; j++) { // j号颜色
                if (j != preMinCostColor) { // 当前来到的颜色 不是上一次取得最好的代价的颜色。，看看能不能更新
                    // 如果之前的最好代价加上来到我这儿的最好代价是小于当前的最好代价的
                    // 那么我就把我的更新成当前的次小
                    if (preMinCost + costs[i][j] < curMinCost) {
                        curMin2Cost = curMinCost;
                        curMin2CostColor = curMinCostColor;
                        curMinCost = preMinCost + costs[i][j];
                        curMinCostColor = j;
                    }
                    // 如果之前的最好代价到我比我当前的次小都还小
                    // 因为上一个分支已经更新了最小和次小
                    // 这个分支就更新我的次小
                    else if (preMinCost + costs[i][j] < curMin2Cost) {
                        curMin2Cost = preMinCost + costs[i][j];
                        curMin2CostColor = j;
                    }
                } else if (j != preMin2CostColor) {
                    if (preMin2Cost + costs[i][j] < curMinCost) {
                        curMin2Cost = curMinCost;
                        curMin2CostColor = curMinCostColor;
                        curMinCost = preMin2Cost + costs[i][j];
                        curMinCostColor = j;
                    } else if (preMin2Cost + costs[i][j] < curMin2Cost) {
                        curMin2Cost = preMin2Cost + costs[i][j];
                        curMin2CostColor = j;
                    }
                }
            }
            preMinCost = curMinCost;
            preMinCostColor = curMinCostColor;
            preMin2Cost = curMin2Cost;
            preMin2CostColor = curMin2CostColor;
        }
        return preMinCost;
    }
}
