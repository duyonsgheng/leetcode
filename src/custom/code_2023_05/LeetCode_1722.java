package custom.code_2023_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_1722
 * @Author Duys
 * @Description
 * @Date 2023/5/11 15:20
 **/
// 1722. 执行交换操作后的最小汉明距离
public class LeetCode_1722 {

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);
        for (int[] arr : allowedSwaps) {
            uf.union(arr[0], arr[1]);
        }
        // 记录target中的元素和位置
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!indexMap.containsKey(target[i])) {
                indexMap.put(target[i], new ArrayList<>());
            }
            indexMap.get(target[i]).add(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!indexMap.containsKey(source[i])) {
                ans++;
                continue;
            }
            List<Integer> curIndexs = indexMap.get(source[i]);
            Iterator<Integer> iterator = curIndexs.iterator();
            boolean flag = false;
            while (iterator.hasNext()) {
                if (uf.isOnce(i, iterator.next())) {
                    // 当前i位置用掉了一个，需要同样的去掉一个。
                    iterator.remove();
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ans++;
            }
        }
        return ans;
    }

    public class UnionFind {
        int[] parent;
        int[] size;
        int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return;
            }
            if (size[pa] >= size[pb]) {
                parent[pb] = pa;
                size[pa] += size[pb];
            } else {
                parent[pa] = pb;
                size[pb] += size[pa];
            }
        }

        public int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                help[hi++] = x;
                x = parent[x];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = x;
            }
            return x;
        }
    }
}
