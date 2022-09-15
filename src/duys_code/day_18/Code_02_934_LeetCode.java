package duys_code.day_18;

/**
 * @ClassName Code_02_934_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/shortest-bridge/
 * @Date 2021/11/2 10:10
 **/
public class Code_02_934_LeetCode {

    /**
     * 在给定的二维二进制数组A中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
     * 现在，我们可以将0变为1，以使两座岛连接起来，变成一座岛。
     * 返回必须翻转的0 的最小数目。（可以保证答案至少是 1 。）
     */

    /**
     * 大思路：
     * 先找到其中第一个连成一片的1.然后找到位置，把自己到自己的距离置为1.并且修改原矩阵中为1的地方值为2.数组2维变1维
     */
    public static int shortestBridge(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int all = N * M;
        int island = 0;
        int[] curs = new int[all];
        int[] nexts = new int[all];
        int[][] records = new int[2][all]; // 明确说了只有两片岛
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1) {
                    int queueSize = infect(m, i, j, N, M, curs, 0, records[island]);
                    int V = 1;
                    while (queueSize != 0) {
                        V++;
                        // curs 和nexts交替
                        queueSize = bfs(N, M, all, V, curs, queueSize, nexts, records[island]);
                        int[] tmp = curs;
                        curs = nexts;
                        nexts = tmp;
                    }
                    island++;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < all; i++) {
            min = Integer.min(min, records[0][i] + records[1][i]);
        }
        // 这里 -3 是因为我设置的自己到自己的距离是1
        return min - 3;
    }

    // 当前来到 m[i][j] 位置，总行数是N，总列数是M
    // m[i][j]感染出去(找到这一片岛所有的1)，把每一个1的坐标放入到curs中去
    // 二维变一维
    // 1 (a,b) -> curs[index++] = (a * M + b)
    // 1 (c,d) -> curs[index++] = (c * M + d)
    // 设置距离record[a * M +b ] = 1
    public static int infect(int[][] m, int i, int j, int N, int M, int[] curs, int index, int[] record) {
        if (i < 0 || i == N || j < 0 || j == M || m[i][j] != -1) {
            return index;
        }
        // m[i][j] 不越界，切真的是1
        m[i][j] = 2;
        int p = i * M + j;// 二维变一维
        record[p] = 1;
        curs[index++] = p;
        // 上下所有去感染吧。直到遇到不是1或者越界得地方停下
        index = infect(m, i + 1, j, N, M, curs, index, record);
        index = infect(m, i - 1, j, N, M, curs, index, record);
        index = infect(m, i, j + 1, N, M, curs, index, record);
        index = infect(m, i, j - 1, N, M, curs, index, record);
        // 返回最后我得curs实际用到了哪里
        return index;
    }

    // N 总行数
    // M 是总列数
    // all 就是总的格子数
    // V 第几层
    // curs 在第V-1层 nexts 就是V层
    // record 里面拿实际距离
    public static int bfs(int N, int M, int all, int V,
                          int[] curs, int size, int[] nexts, int[] record) {
        int nextIndex = 0; // 下一个队列的实际容量
        for (int i = 0; i < size; i++) {
            int up = curs[i] < M ? -1 : curs[i] - M;
            int down = curs[i] + M > all ? -1 : curs[i] + M;
            int left = curs[i] % M == 0 ? -1 : curs[i] - 1;
            int right = curs[i] % M == M - 1 ? -1 : curs[i] + 1;
            if (up != -1 && record[up] == 0) {
                record[up] = V;
                nexts[nextIndex++] = up;
            }
            if (down != -1 && record[down] == 0) {
                record[down] = V;
                nexts[nextIndex++] = down;
            }
            if (left != -1 && record[left] == 0) {
                record[left] = V;
                nexts[nextIndex++] = left;
            }
            if (right != -1 && record[right] == 0) {
                record[right] = V;
                nexts[nextIndex++] = right;
            }
        }
        return nextIndex;
    }

}
