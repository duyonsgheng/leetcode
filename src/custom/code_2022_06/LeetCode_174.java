package custom.code_2022_06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_174
 * @Author Duys
 * @Description
 * @Date 2022/6/30 17:01
 **/
// 174. 地下城游戏
public class LeetCode_174 {


    public static int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length < 1 || dungeon[0] == null || dungeon[0].length < 1) {
            return Integer.MAX_VALUE;
        }
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[n][m - 1] = 1;
        dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int cur = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(cur - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    // 最短路径算法 有负数不行
    public static int calculateMinimumHP1(int[][] dungeon) {
        if (dungeon == null || dungeon.length < 1 || dungeon[0] == null || dungeon[0].length < 1) {
            return Integer.MAX_VALUE;
        }
        int n = dungeon.length;
        int m = dungeon[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{dungeon[0][0], 0, 0});
        boolean[][] consts = new boolean[n][m];
        int ans = 0;
        while (!heap.isEmpty()) {
            int[] poll = heap.poll();
            int cur1 = poll[0];
            int curn = poll[1];
            int curm = poll[2];
            if (consts[curn][curm]) {
                continue;
            }
            consts[curn][curm] = true;
            if (curn == n - 1 && curm == m - 1) {
                ans = cur1;
                break;
            }
            // 往下
            go(cur1, curn + 1, curm, consts, heap, dungeon);
            //往右
            go(cur1, curn, curm + 1, consts, heap, dungeon);
        }
        return ans > 0 ? 0 : -ans + 1;
    }

    public static void go(int pre, int row, int col, boolean[][] cos, PriorityQueue<int[]> heap, int[][] arr) {
        if (col >= 0 && row >= 0 && row < arr.length && col < arr[0].length && !cos[row][col]) {
            heap.add(new int[]{pre + arr[row][col], row, col});
        }
    }
}
