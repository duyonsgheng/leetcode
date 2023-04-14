package custom.code_2023_03;

/**
 * @ClassName LeetCode_1583
 * @Author Duys
 * @Description
 * @Date 2023/3/24 15:37
 **/
// 1583. 统计不开心的朋友
public class LeetCode_1583 {
    // 根据题意转换
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] order = new int[n][n];
        // 当前i号人的跟第preferences[i][j]的关系在j位置，位置越小说明跟i号人的关系越好
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                order[i][preferences[i][j]] = j;
            }
        }
        // 做一个配对表 i号人配对的人是 match[i]
        int[] match = new int[n];
        for (int[] par : pairs) {
            match[par[0]] = par[1];
            match[par[1]] = par[0];
        }
        int unhappy = 0;
        for (int x = 0; x < n; x++) {
            int y = match[x];
            // 比 x跟y关系 关系更好的是是恶
            int index = order[x][y];
            for (int i = 0; i < index; i++) {
                // 遍历跟x关系好的人
                int u = preferences[x][i];
                int v = match[u];
                // 如果u和x关系没有u和v的关系好
                // x是不开心的
                if (order[u][x] < order[u][v]) {
                    unhappy++;
                    break;
                }
            }
        }
        return unhappy;
    }
}
