package custom.code_2022_07;

/**
 * @ClassName LeetCode_307
 * @Author Duys
 * @Description
 * @Date 2022/7/14 16:45
 **/
// 307. 区域和检索 - 数组可修改
// 线段树
public class LeetCode_307 {


    public static class NumArray {
        public int size;
        // 线段树下标从1开始
        public int[] arr;
        public int[] sum;
        public int[] lazy;
        public int[] change;
        public boolean[] update;

        public NumArray(int[] ori) {
            size = ori.length + 1;
            arr = new int[size];
            sum = new int[size << 2];
            lazy = new int[size << 2];
            change = new int[size << 2];
            update = new boolean[size << 2];
            for (int i = 1; i < size; i++) {
                arr[i] = ori[i - 1];
            }
            build(1, size - 1, 1);
        }

        public void build(int l, int r, int root) {
            if (l == r) {
                sum[root] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, root << 1);
            build(mid + 1, r, root << 1 | 1);
            pushUp(root);
        }

        public void update(int index, int val) {
            update(index + 1, index + 1, val);
        }

        public int sumRange(int left, int right) {
            return query(left + 1, right + 1);
        }

        public void update(int L, int R, int C) {
            update(L, R, C, 1, size - 1, 1);
        }

        public int query(int L, int R) {
            return query(L, R, 1, size - 1, 1);
        }

        private int query(int L, int R, int l, int r, int root) {
            if (L <= l && R >= r) {
                return sum[root];
            }
            int mid = (l + r) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, root << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, root << 1 | 1);
            }
            return ans;
        }

        private void update(int L, int R, int C, int l, int r, int root) {
            if (L <= l && R >= r) {
                update[root] = true;
                change[root] = C;
                lazy[root] = 0;
                sum[root] = C * (r - l + 1);
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, root << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, root << 1 | 1);
            }
            pushUp(root);
        }


        private void pushUp(int root) {
            sum[root] = sum[root << 1] + sum[root << 1 | 1];
        }

        private void pushDown(int root, int ln, int rn) {
            if (update[root]) {
                update[root << 1] = true;
                update[root << 1 | 1] = true;

                change[root << 1] = change[root];
                change[root << 1 | 1] = change[root];

                lazy[root << 1] = 0;
                lazy[root << 1 | 1] = 0;

                sum[root << 1] = ln * sum[root];
                sum[root << 1 | 1] = rn * sum[root];
                update[root] = false;
            }
            if (lazy[root] != 0) {
                lazy[root << 1] += lazy[root];
                lazy[root << 1 | 1] += lazy[root];

                sum[root << 1] += lazy[root] * ln;
                sum[root << 1 | 1] += lazy[root] * rn;

                lazy[root] = 0;
            }
        }
    }
}
