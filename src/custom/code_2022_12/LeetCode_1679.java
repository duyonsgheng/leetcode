package custom.code_2022_12;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1679
 * @Author Duys
 * @Description
 * @Date 2022/12/14 10:25
 **/
// 1697. 检查边长度限制的路径是否存在
public class LeetCode_1679 {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int[][] arr = new int[queries.length][2];
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            arr[i][0] = queries[i][2];
            arr[i][1] = i;
        }
        // 按照limit排序
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        // 按照距离排序
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        UnionFind uf = new UnionFind(n);
        int j = 0;
        for (int[] arrs : arr) {
            // 如果当前的边是小于的，可以进行合并，属于一个集合，这个集合都是满足的
            while (j < edgeList.length && edgeList[j][2] < queries[arrs[1]][2]) {
                uf.union(edgeList[j][0], edgeList[j][1]);
                j++;
            }
            ans[arrs[1]] = uf.find(queries[arrs[1]][0]) == uf.find(queries[arrs[1]][1]);
        }
        return ans;
    }

    class UnionFind {
        int[] parent;
        int[] help;
        int[] size;
        int sets;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            size = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int a) {
            int hi = 0;
            while (a != parent[a]) {
                help[hi++] = a;
                a = parent[a];
            }
            for (hi--; hi >= 0; hi--)
                parent[help[hi]] = a;
            return a;
        }

        public boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return false;
            }
            if (size[pa] >= size[pb]) {
                parent[pb] = pa;
                size[pa] += size[pb];
            } else if (size[pa] < size[pb]) {
                parent[pa] = pb;
                size[pb] += size[pa];
            }
            sets--;
            return true;
        }

        public int getSets() {
            return sets;
        }
    }
}
