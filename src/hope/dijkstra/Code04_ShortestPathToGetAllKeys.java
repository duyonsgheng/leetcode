package hope.dijkstra;

/**
 * @author Mr.Du
 * @ClassName Code04_ShortestPathToGetAllKeys
 * @date 2023年11月27日 11:25
 */
// 获取所有钥匙的最短路径
// 给定一个二维网格grid，其中：
// '.' 代表一个空房间、'#' 代表一堵、'@'是起点
// 小写字母代表钥匙、大写字母代表锁
// 从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间
// 不能在网格外面行走，也无法穿过一堵墙
// 如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
// 假设 k为 钥匙/锁 的个数，且满足1 <= k<= 6，
// 字母表中的前 k个字母在网格中都有自己对应的一个小写和一个大写字母
// 换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁
// 另外，代表钥匙和锁的字母互为大小写并按字母顺序排列
// 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回-1。
// 测试链接：https://leetcode.cn/problems/shortest-path-to-get-all-keys
public class Code04_ShortestPathToGetAllKeys {
    // 分层图
    // 扩点，任务到达一个点和钥匙的状态是一个点，这样就对点进行了扩展
    public static int maxn = 31;
    public static int maxm = 31;
    public static int maxk = 6;
    // 0:上，1:右，2:下，3:左
    public static int[] move = new int[]{-1, 0, 1, 0, -1};
    public static char[][] grid = new char[maxn][];
    public static boolean[][][] visited = new boolean[maxn][maxm][1 << maxk];
    // 0:行
    // 1:列
    // 2:钥匙的状态
    public static int[][] queue = new int[maxn * maxm * (1 << maxk)][3];
    public static int l, r, n, m, key;

    public static void build(String[] strs) {
        l = r = key = 0;
        n = strs.length;
        m = strs[0].length();
        for (int i = 0; i < n; i++) {
            grid[i] = strs[i].toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '@') {
                    queue[r][0] = i;
                    queue[r][1] = j;
                    // 开始点，钥匙是没有的
                    queue[r++][2] = 0;
                }
                if (grid[i][j] >= 'a' && grid[i][j] <= 'f') {
                    key |= 1 << (grid[i][j] - 'a');
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k <= key; k++) {
                    visited[i][j][k] = false;
                }
            }
        }
    }

    public static int shortestPathAllKeys(String[] g) {
        build(g);
        int level = 1;
        while (l < r) {
            // 一次一层
            for (int k = 0, size = r - l, x, y, s; k < size; k++) {
                x = queue[l][0];
                y = queue[l][1];
                s = queue[l++][2];
                for (int i = 0, nx, ny, ns; i < 4; i++) {
                    nx = x + move[i];
                    ny = y + move[i + 1];
                    ns = s;
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || grid[nx][ny] == '#') {
                        continue;
                    }
                    // 是锁，但是没钥匙
                    if (grid[nx][ny] >= 'A' && grid[nx][ny] <= 'F' && ((ns & (1 << (grid[nx][ny] - 'A'))) == 0)) {
                        continue;
                    }
                    // 钥匙的话，收集
                    if (grid[nx][ny] >= 'a' && grid[nx][ny] <= 'f') {
                        ns |= (1 << (grid[nx][ny] - 'a'));
                    }
                    // 收集到所有的钥匙
                    if (ns == key) {
                        return level;
                    }
                    if (!visited[nx][ny][ns]) {
                        visited[nx][ny][ns] = true;
                        queue[r][0] = nx;
                        queue[r][1] = ny;
                        queue[r++][2] = ns;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
