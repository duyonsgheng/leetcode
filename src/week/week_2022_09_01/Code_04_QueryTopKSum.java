package week.week_2022_09_01;

/**
 * @ClassName Code_04_QueryTopKSum
 * @Author Duys
 * @Description
 * @Date 2022/9/8 12:03
 **/
// 给你一个长度为n的数组，并询问q次
// 每次询问区间[l,r]之间是否存在小于等于k个数的和大于等于x
// 每条查询返回true或者false
// 1 <= n, q <= 10^5
// k <= 10
// 1 <= x <= 10^8
public class Code_04_QueryTopKSum {

    public static class SegmentTree {
        private int n;
        private int k;
        private int[][] max;
        private int[][] query;

        public SegmentTree(int[] arr, int ki) {
            n = arr.length;
            k = ki;
            max = new int[(n + 1) << 2][k];
            query = new int[(n + 1) << 2][k];

        }

        public int topKSum(int l, int r) {
            l++;
            r++;
            collect(l, r, 1, n, 1);
            int sum = 0;
            for (int num : query[1]) {
                sum += num;
            }
            return sum;
        }

        private void update(int i, int v) {
            i++;
            update(i, i, v, 1, n, 1);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                max[rt][0] = C;
                return;
            }
            int mid = (l + r) >> 1;
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
        }

        private void merge(int[] father, int[] left, int[] right) {
            for (int i = 0, p1 = 0, p2 = 0; i < k; i++) {
                if (left == null || p1 == k) {
                    father[i] = right[p2++];
                } else if (right == null || p2 == k) {
                    father[i] = left[p1++];
                } else {
                    father[i] = left[p1] >= right[p2] ? left[p1++] : right[p2++];
                }
            }
        }

        private void collect(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                for (int i = 0; i < k; i++) {
                    query[rt][i] = max[rt][i];
                }
            } else {
                int mid = (l + r) >> 1;
                boolean leftUpdate = false;
                boolean rightUpdate = false;
                if (L <= mid) {
                    leftUpdate = true;
                    collect(L, R, l, mid, rt << 1);
                }
                if (R > mid) {
                    rightUpdate = true;
                    collect(L, R, mid + 1, r, rt << 1 | 1);
                }
                merge(query[rt], leftUpdate ? query[rt << 1] : null, rightUpdate ? query[rt << 1 | 1] : null);
            }
        }
    }
}
