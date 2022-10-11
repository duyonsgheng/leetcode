package custom.code_2022_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_886
 * @Author Duys
 * @Description
 * @Date 2022/10/11 11:10
 **/
// 886. 可能的二分法-解法，并查集
public class LeetCode_886 {

    // 跑并查集
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 先把那些人不和哪些人在一组标记
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) { // 每一个人给一个图
            graph.add(new ArrayList<>());
        }
        for (int[] arr : dislikes) {
            graph.get(arr[0]).add(arr[1]);
            graph.get(arr[1]).add(arr[0]);
        }
        UnionFind unionFind = new UnionFind(n);
        for (int i = 1; i <= n; i++) {
            // 哪些人不能跟自己在一个集合
            List<Integer> list = graph.get(i);
            if (list.size() == 0) {
                continue;
            }
            for (int next : list) {
                // i 本来就不能和nnext在一起
                if (unionFind.isOnce(i, next)) {
                    return false;
                } else {
                    // 这些人可以合并在一起
                    unionFind.union(list.get(0), next);
                }
            }
        }
        return true;
    }

    public class UnionFind {
        int[] parent;
        int[] size;
        int[] help;

        public UnionFind(int n) {
            n++;
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return;
            }
            if (size[px] >= size[py]) {
                size[px] += size[py];
                parent[py] = px;
            } else {
                size[py] += size[px];
                parent[px] = py;
            }
        }

        public boolean isOnce(int x, int y) {
            return find(x) == find(y);
        }

        private int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                x = parent[x];
                help[hi++] = x;
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = x;
            }
            return x;
        }
    }
}
