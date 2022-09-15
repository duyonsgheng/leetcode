package pro;

/**
 * @ClassName SegmentTree
 * @Author Duys
 * @Description
 * @Date 2022/5/26 16:47
 **/
public class SegmentTree {
    public int size;
    // 线段树下标从1开始，用户数组来表示树
    public int[] arr;
    // 累加和
    public int[] sum;
    // 懒信息
    public int[] lazy;
    // update使用
    public int[] change;
    // update使用，判断update[]上的信息是否有效。因为0是区分不了的。所以需要一个boolean类型的数组来标识
    public boolean[] update;

    public SegmentTree(int[] origin) {
        size = origin.length + 1;
        arr = new int[size];
        sum = new int[size << 2];
        lazy = new int[size << 2];
        change = new int[size << 2];
        update = new boolean[size << 2];

        for (int i = 1; i < size; i++) {
            arr[i] = origin[i - 1];
        }
    }

    public void build(int l, int r, int rt) {
        // 范围上只有一个节点，说明是叶子节点
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, r << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }

    // 把L~R范围上全部改成C
    public void update(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && R >= r) {
            update[rt] = true;
            change[rt] = C;
            lazy[rt] = 0;
            sum[rt] = C * (r - l + 1);
            return;
        }
        int mid = (l + r) >> 1;
        // 左边是 l到mid  总共 mid -l +1 个数
        // 右边是 mid+1 到 r 总共 r-(mid+1)+1个数
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            update(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            update(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    // 把L~R范围上统一加上C
    public void add(int L, int R, int C, int l, int r, int rt) {
        if (R >= r && L <= l) {
            lazy[rt] += C;
            sum[rt] += C * (r - l + 1);
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            add(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    public int query(int L, int R, int l, int r, int rt) {
        if (L <= l && R >= r) {
            return sum[rt];
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        int ans = 0;
        if (L <= mid) {
            ans += query(L, R, l, mid, rt << 1);
        }
        if (R > mid) {
            ans += query(L, R, mid + 1, r, mid << 1 | 1);
        }
        return ans;
    }

    private void pushUp(int rt) {
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
            lazy[rt << 1 | 1] += lazy[rt];

            sum[rt << 1] += lazy[rt] * ln;
            sum[rt << 1 | 1] += lazy[rt] * rn;

            lazy[rt] = 0;
        }
    }
}
