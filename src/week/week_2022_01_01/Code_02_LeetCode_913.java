package week.week_2022_01_01;

import java.util.Arrays;

/**
 * @ClassName Code_02_LeetCode_913
 * @Author Duys
 * @Description
 * @Date 2022/4/12 13:31
 **/
// 猫和老鼠的游戏
// 无向图
// 如果猫和老鼠出现在同一个节点，猫获胜。
//如果老鼠到达洞中，老鼠获胜。
//如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
//如果老鼠获胜，则返回 1；
//如果猫获胜，则返回 2；
//如果平局，则返回 0 。
public class Code_02_LeetCode_913 {

    public static int catMouseGame(int[][] graph) {
        int n = graph.length;
        // 关键就是这个limit的设置。
        int limit = (n * 2) << 1;//(n * (n - 1)) << 1 + 1;
        int[][][] dp = new int[n][n][limit];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process(graph, limit, 2, 1, 1, dp);
    }

    // map -图
    // limit - 最多多少回合
    // cat -猫所在的位置
    // mouse - 老鼠所在的位置
    // turn - 当前是多少回合
    // 题意：老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
    // 那么 老鼠是走奇数回合，猫是走偶数回合
    public static int process(int[][] map, int limit, int cat, int mouse, int turn, int[][][] dp) {
        // 平局
        if (turn == limit) {
            return 0;
        }
        if (dp[cat][mouse][turn] != -1) {
            return dp[cat][mouse][turn];
        }
        int ans = 0;
        if (cat == mouse) {
            ans = 2;
        } else if (mouse == 0) {
            ans = 1;
        } else {
            // 老鼠的回合
            if ((turn & 1) == 1) {
                ans = 2;
                // 老鼠往哪里去？
                for (int next : map[mouse]) {
                    int p = process(map, limit, cat, next, turn + 1, dp);
                    ans = p == 1 ? 1 : (p == 0 ? 0 : ans);
                    if (ans == 1) {
                        break;
                    }
                }
            } else { // 猫的回合
                ans = 1;
                for (int next : map[cat]) {
                    if (next == 0) {
                        continue;
                    }
                    int p = process(map, limit, next, mouse, turn + 1, dp);
                    ans = p == 2 ? 2 : (p == 0 ? 0 : ans);
                    if (ans == 2) {
                        break;
                    }
                }
            }
        }
        dp[cat][mouse][turn] = ans;
        return ans;
    }
}
