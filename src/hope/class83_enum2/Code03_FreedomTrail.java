package hope.class83_enum2;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code03_FreedomTrail
 * @date 2024年05月15日 上午 09:53
 */
// 自由之路
// 测试链接 : https://leetcode.cn/problems/freedom-trail/
public class Code03_FreedomTrail {

    public static int maxn = 101;
    public static int maxc = 26;
    public static int[] ring = new int[maxn];
    public static int[] key = new int[maxn];
    public static int[] size = new int[maxc];
    public static int[][] where = new int[maxc][maxn];
    public static int[][] dp = new int[maxn][maxn];
    public static int n, m;

    public static void build(String r, String k) {
        Arrays.fill(size, 0);
        n = r.length();
        m = k.length();
        for (int i = 0, v; i < n; i++) {
            v = r.charAt(i) - 'a';
            where[v][size[v]++] = i;
            ring[i] = v;
        }
        for (int i = 0; i < m; i++) {
            key[i] = k.charAt(i) - 'a';
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
    }

    public static int findRotateSteps(String r, String k) {
        build(r, k);
        return f(0, 0);
    }

    // 指针当前指着i，要搞定key[j....]所有字符，最小的代价
    public static int f(int i, int j) {
        // 都搞定了
        if (j == m) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = 0;
        if (ring[i] == key[j]) { // 相等，只需要按一下，代价是1，然后跑后续流程
            ans = 1 + f(i, j + 1);
        } else {
            // 轮盘处在i位置，并且ring[i]!=key[j]
            // jump1 :顺时针找到key[j]字符所在的位置
            // distance1: 顺时针走到jump1有多远
            int jump1 = clock(i, key[j]);
            int distance1 = (jump1 > i ? (jump1 - i) : (n - i + jump1));

            int jump2 = counterClock(i, key[j]);
            int distance2 = (i > jump2 ? (i - jump2) : (i + n - jump2));
            ans = Math.min(distance1 + f(jump1, j), distance2 + f(jump2, j));
        }
        dp[i][j] = ans;
        return ans;
    }

    // 从i顺指针走到v，有多远
    public static int clock(int i, int v) {
        int l = 0;
        // size[v]属于v字符的下标有几个
        int r = size[v] - 1, m;
        // sorted[0...size[v]-1]收集了所有的下标，并且有序
        int[] sorted = where[v];
        int find = -1;
        //找到 > i尽量靠左的位置
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] > i) {
                find = m;
                r = m - 1;
            } else l = m + 1;
        }
        return find == -1 ? sorted[0] : sorted[find];
    }

    // 从i 逆时针走到v
    public static int counterClock(int i, int v) {
        int l = 0, r = size[v] - 1, m;
        int[] sorted = where[v];
        int find = -1;
        // 有序数组中，找<i尽量靠右的下标
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] < i) {
                find = m;
                l = m + 1;
            } else r = m - 1;
        }
        return find != -1 ? sorted[find] : sorted[size[v] - 1];
    }
}
