package hope.unionFind;

import java.util.HashMap;

/**
 * @author Mr.Du
 * @ClassName Code06_MostStonesRemovedWithSameRowOrColumn
 * @date 2023年11月10日 10:23
 */
// 移除最多的同行或同列石头
// n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头
// 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头
// 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置
// 返回 可以移除的石子 的最大数量。
// 测试链接 : https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column/
public class Code06_MostStonesRemovedWithSameRowOrColumn {

    public static HashMap<Integer, Integer> rowFirst = new HashMap<Integer, Integer>();

    public static HashMap<Integer, Integer> colFirst = new HashMap<Integer, Integer>();

    public static int MAXN = 1001;
    public static int[] father = new int[MAXN];
    public static int sets;

    public static void build(int n) {
        rowFirst.clear();
        colFirst.clear();
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        sets = n;
    }

    public static int find(int x) {
        if (x != father[x]) {
            father[x] = find(father[x]);
        }
        return father[x];
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa == fb) {
            return;
        }
        father[fa] = fb;
        sets--;
    }

    // 这里有一个结论，同一个集合里的石头最后一定可以只剩下一个
    public static int removeStones(int[][] stones) {
        int n = stones.length;
        build(n);
        for (int i = 0; i < n; i++) {
            int x = stones[i][0];
            int y = stones[i][1];
            if (!rowFirst.containsKey(x)) {
                rowFirst.put(x, i);
            } else {
                union(i, rowFirst.get(x));
            }
            if (!colFirst.containsKey(y)) {
                colFirst.put(y, i);
            } else {
                union(i, colFirst.get(y));
            }
        }
        return n - sets;
    }
}
