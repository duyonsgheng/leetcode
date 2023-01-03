package custom.code_2022_12;

/**
 * @ClassName LeetCode_1395
 * @Author Duys
 * @Description
 * @Date 2022/12/27 11:38
 **/
// 1395. 统计作战单位数
public class LeetCode_1395 {
    // 需要直到当前位置的左边比自己小的有几个，右边比自己大的有几个数
    // 用两个树状数组来完成
    public int numTeams(int[] rating) {
        int max = 100001;
        IndexTree right = new IndexTree(max);
        int ans = 0;
        int n = rating.length;
        for (int i = 1; i < n; i++) {
            right.add(rating[i]);
        }
        IndexTree left = new IndexTree(max);
        left.add(rating[0]);
        for (int i = 1; i < n; i++) {
            int cur = rating[i];
            right.sub(cur);
            ans += left.queryLess(cur) * right.queryMore(cur);
            ans += left.queryMore(cur) * right.queryLess(cur);
            left.add(cur);
        }
        return ans;
    }

    public class IndexTree {
        int len;
        int[] tree;
        int all;

        public IndexTree(int n) {
            len = n + 2;
            tree = new int[len];
        }

        public void add(int index) {
            while (index < len) {
                tree[index]++;
                index += index & -index;
            }
            all++;
        }

        public void sub(int index) {
            while (index < len) {
                tree[index]--;
                index += index & -index;
            }
            all--;
        }

        public int query(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

        public int queryLess(int index) {
            return query(index - 1);
        }

        public int queryMore(int index) {
            return all - query(index);
        }
    }
}
