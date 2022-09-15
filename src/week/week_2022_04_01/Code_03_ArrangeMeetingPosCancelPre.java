package week.week_2022_04_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_03_ArrangeMeetingPosCancelPre
 * @Author Duys
 * @Description
 * @Date 2022/4/7 10:11
 **/
// 来自通维数码
// 每个会议给定开始和结束时间
// 后面的会议如果跟前面的会议有任何冲突，完全取消冲突的、之前的会议，安排当前的
// 给定一个会议数组，返回安排的会议列表
public class Code_03_ArrangeMeetingPosCancelPre {
    // 分析。

    /**
     * 本题有一些出入：就是当后面的会议和前面的会议冲突了，那么前面的会议完全取消
     * 推荐线段树
     */
    public static List<int[]> arrange(int[][] meetings) {
        int n = meetings.length;// 会议数量
        int[] rank = new int[n << 1];// 准备线段树需要的，并且对元数据进行离散化
        for (int i = 0; i < n; i++) {
            rank[i] = meetings[i][0];
            rank[i + n] = meetings[i][1] - 1; // 为了避免头尾相接导致算重的问题。这里需要处理一下
        }
        Arrays.sort(rank);
        SegmentTree st = new SegmentTree(n << 1);
        List<int[]> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) { // 会议从后往前撸
            int[] cur = meetings[i];
            int start = rank(rank, cur[0]);
            int end = rank(rank, cur[1] - 1);
            if (st.get(start, end) == 0) {
                ans.add(cur);
            }
            st.add(start, end, 1);
        }
        return ans;
    }

    // 找到 >= num的最左位置的下一个
    public static int rank(int[] rank, int num) {
        int l = 0;
        int m = 0;
        int r = rank.length - 1;
        int ans = 0;
        while (l <= r) {
            m = (l + r) >> 1;
            if (rank[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1;
    }

    public static class SegmentTree {
        private int size;
        private int[] sum;
        private int[] lazy;

        public SegmentTree(int n) {
            size = n;
            sum = new int[n << 2];
            lazy = new int[n << 2];
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            // 向下，看看是否有被懒住的信息，有的化就更新
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;

                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        public void add(int L, int R, int C) {
            add(L, R, C, 1, 1, size);
        }

        private void add(int L, int R, int C, int rt, int l, int r) {
            if (L <= l && R >= r) { // 整个区间
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, rt << 1, l, mid);
            }
            if (R > mid) {
                add(L, R, C, rt << 1 | 1, mid + 1, r);
            }
            pushUp(rt);
        }

        public int get(int L, int R) {
            return query(L, R, 1, 1, size);
        }

        private int query(int L, int R, int rt, int l, int r) {
            if (L <= l && R >= r) {
                return sum[rt];
            }
            int ans = 0;
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                ans += query(L, R, rt << 1, l, mid);
            }
            if (R > mid) {
                ans += query(L, R, rt << 1 | 1, mid + 1, r);
            }
            return ans;
        }
    }
}
