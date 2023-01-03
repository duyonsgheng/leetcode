package custom.code_2022_12;

/**
 * @ClassName LeetCode_1409
 * @Author Duys
 * @Description
 * @Date 2022/12/27 16:42
 **/
// 1409. 查询带键的排列
public class LeetCode_1409 {
    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        // queries = [3,1,2,1], m = 5
        // n = 4
        IndexTree indexTree = new IndexTree(n + m);
        int[] pos = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            pos[i] = i + n;
            indexTree.update(n + i, 1);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = pos[queries[i]];
            indexTree.update(cur, -1);
            ans[i] = indexTree.query(cur);
            cur = n - i;
            pos[queries[i]] = cur;
            indexTree.update(cur, 1);
        }
        return ans;
    }

    public class IndexTree {
        int[] tree;
        int n;

        public IndexTree(int n) {
            this.n = n;
            this.tree = new int[n + 2];
        }

        public int query(int x) {
            int ans = 0;
            while (x != 0) {
                ans += tree[x];
                x -= (x & -x);
            }
            return ans;
        }

        public int update(int x, int y) {
            while (x <= n) {
                tree[x] += y;
                x += (x & -x);
            }
            return x;
        }
    }
}
