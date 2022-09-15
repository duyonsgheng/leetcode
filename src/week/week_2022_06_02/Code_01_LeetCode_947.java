package week.week_2022_06_02;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_01_LeetCode_947
 * @Author Duys
 * @Description
 * @Date 2022/6/16 9:54
 **/
// 947. 移除最多的同行或同列石头
// n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
//如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
//给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
//链接：https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column
public class Code_01_LeetCode_947 {

    // 并查集，我们把同行或者同列得全部合并到一个集合里，最后算总共有多少个集合，就是最终不用删除的石头
    public static int removeStones(int[][] stones) {
        if (stones == null || stones.length < 1 || stones[0] == null || stones[0].length < 1) {
            return -1;
        }
        int n = stones.length;
        // 分别记录行的代表和列的代表，每一行我们只需要和代表节点合并，每一列我们只需要和代表节点合并，最后该合并的合并，剩下的就是不能删除的
        Map<Integer, Integer> rowIndexMap = new HashMap<>();
        Map<Integer, Integer> colIndexMap = new HashMap<>();
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            int x = stones[i][0]; // 石头所在的行
            int y = stones[i][1]; // 石头所在的列
            // 行上有没有石头
            if (!rowIndexMap.containsKey(x)) {
                rowIndexMap.put(x, i);
            } else {
                // 有了 就把当前的石头和之前的石头合并
                uf.union(i, rowIndexMap.get(x));
            }
            if (!colIndexMap.containsKey(y)) {
                colIndexMap.put(y, i);
            } else {
                uf.union(i, colIndexMap.get(y));
            }
        }
        // 合并结束，并查集里剩下的石头是不能删除的。那么能删除的就是总数减去不能删除的
        return n - uf.getSets();
    }

    public static class UnionFind {
        public int[] parent;
        public int[] size;
        public int[] help;
        public int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) {
            int xp = find(x);
            int yp = find(y);
            if (xp == yp) {
                return;
            }
            if (size[xp] > size[yp]) {
                size[xp] += size[yp];
                parent[yp] = xp;
            } else {
                size[yp] += size[xp];
                parent[xp] = yp;
            }
            sets--;
        }

        public int getSets() {
            return sets;
        }

        private int find(int x) {
            int index = 0;
            while (x != parent[x]) {
                help[index++] = x;
                x = parent[x];
            }
            while (index != 0) {
                parent[help[--index]] = x;
            }
            return x;
        }
    }
}
