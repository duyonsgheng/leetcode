package week.week_2022_02_03;

/**
 * @ClassName Code_05_StoneGameIX
 * @Author Duys
 * @Description
 * @Date 2022/3/29 14:50
 **/
public class Code_05_LeetCode_2029 {
    // Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
    //Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones中移除任一石子。
    //如果玩家移除石子后，导致 所有已移除石子 的价值总和 可以被 3 整除，那么该玩家就 输掉游戏 。
    //如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
    //假设两位玩家均采用最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/stone-game-ix
    public boolean stoneGameIX(int[] stones) {
        int[] cost = new int[3]; // 看看所有的结果
        for (int i : stones) {
            cost[i % 3]++;
        }
        // 如果余数是0的数没有
        // 那么看看余数为1和为2的是不是都有，如果都有，无论我怎么拿，后手都不会是3的整数倍
        // 如果余数为0的有，看看余数部位0的数差值是多少，如果差值只有一个，那我无论怎么拿都会剩下3的整数倍的
        return cost[0] % 2 == 0 ? cost[1] != 0 && cost[2] != 0 : Math.abs(cost[1] - cost[2]) > 2;
    }
}
