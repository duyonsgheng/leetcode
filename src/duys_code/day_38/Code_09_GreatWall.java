package duys_code.day_38;

/**
 * @ClassName Code_09_GreatWall
 * @Author Duys
 * @Description
 * @Date 2021/12/20 15:30
 **/
public class Code_09_GreatWall {

    // 360笔试题
    // 长城守卫军
    // 题目描述：
    // 长城上有连成一排的n个烽火台，每个烽火台都有士兵驻守。
    // 第i个烽火台驻守着ai个士兵，相邻峰火台的距离为1。另外，有m位将军，
    // 每位将军可以驻守一个峰火台，每个烽火台可以有多个将军驻守，
    // 将军可以影响所有距离他驻守的峰火台小于等于x的烽火台。
    // 每个烽火台的基础战斗力为士兵数，另外，每个能影响此烽火台的将军都能使这个烽火台的战斗力提升k。
    // 长城的战斗力为所有烽火台的战斗力的最小值。
    // 请问长城的最大战斗力可以是多少？

    // wall 长城的原始战斗力
    // m 有m哥将军
    // 每个将军的辐射直径是x范围
    // k 每个将军在范围内加成是k
    public static int maxForce(int[] wall, int m, int x, int k) {
        // 1.找出原始长城最大的值，然后把m*k加上去，就是我们的最大值
        // 2.二分，然后在整个长城上，看看满足最低的是当前二分值的时候，m个将军能不能搞定（魔法师AOE问题，体系学习班）
        long l = 0;
        long r = 0;
        for (int i = 0; i < wall.length; i++) {
            r = Math.max(r, wall[i]);
        }
        r += m * k;
        long ans = 0;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            // 如果当前是mid 都可以做到，看看把mid提高，能不能做到，是需要找到最大的这个mid
            if (can(wall, m, x, k, mid)) {
                l = mid + 1;
                ans = mid;
            } else {
                r = mid - 1;
            }
        }
        return (int) ans;
    }

    // 魔法师AOE问题
    public static boolean can(int[] arr, int m, int x, int k, long limit) {
        int n = arr.length;
        SegmentTree segmentTree = new SegmentTree(arr);
        // 根节点从1开始的
        segmentTree.build(1, n, 1);
        int need = 0;
        for (int i = 0; i < n; i++) {
            long cur = segmentTree.query(i + 1, i + 1, 1, n, 1);
            if (cur < limit) {
                // 向上取整，当前位置满足需求，至少需要几个将军
                int add = (int) ((limit - cur + k - 1) / k);
                need += add;
                if (need > m) {
                    return false;
                }
                // 当前将军辐射的范围，前面的已经满足了，那么就从i开始向后辐射x范围，
                segmentTree.add(i + 1, Math.min(i + x, n), add * k, 1, n, 1);
            }
        }
        return true;
    }

    // 线段树，在某一个范围上增加一个值
    public static class SegmentTree {
        private int maxN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int[] n) {
            maxN = n.length + 1;
            arr = new int[maxN];
            // 下标从1开始
            for (int i = 1; i < maxN; i++) {
                arr[i] = n[i - 1];
            }
            // 用数组表述树，那么最大的下标就是 2*i +1
            sum = new int[maxN << 2];
            lazy = new int[maxN << 2];
            change = new int[maxN << 2];
            update = new boolean[maxN << 2];
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            // 整个范围
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            // 否则就分几个部分来
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (l <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

        private void pushUp(int rt) {
            // 左右孩子
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;

                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;

                lazy[rt] = 0;
            }
        }

    }
}
