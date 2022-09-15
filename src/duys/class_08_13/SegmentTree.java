package duys.class_08_13;

/**
 * @ClassName SegmentTree
 * @Author Duys
 * @Description 线段树
 * @Date 2021/8/13 9:39
 **/
public class SegmentTree {


    private int MAXN;
    private int[] arr; //源来序列的信息。这里从1开始
    private int[] sum;// 模拟线段树的维护区间和
    private int[] lazy;// 为累加和懒加载标记
    private int[] change; //为更新的值
    private boolean[] update;// 为更新懒标记，为true表示更新了。false表示无更新

    public SegmentTree(int[] origin) {
        MAXN = origin.length + 1;
        arr = new int[MAXN];
        for (int i = 1; i < MAXN; i++) {
            arr[i] = origin[i - 1];
        }
        sum = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围的累加和信息
        lazy = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围沒有往下传递的累加任務
        change = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围有没有更新操作的任务
        update = new boolean[MAXN << 2]; // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
    }

    private void pushUp(int rt) {
        // 某一个位置的左子节点和右子节点
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    // rt - 父节点是rt
    // ln -  左孩子上有多少数
    // rm -  右孩子上有多少数
    private void pushDown(int rt, int ln, int rn) {
        // 表示当前位置是更新
        if (update[rt]) {
            // 左右孩子都需要标记
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            // 左右孩子都需要
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            // 清空lazy
            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;
            // 累加和就变成了数的个数与当前位置的更新值相乘
            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1 | 1] = change[rt] * rn;
            // 因为任务下发了，那么把父的节点更新标记位清空
            update[rt] = false;
        }
        // 只下发一层信息
        if (lazy[rt] != 0) {
            // 左侧的懒信息
            lazy[rt << 1] += lazy[rt];
            sum[rt << 1] += lazy[rt] * ln;
            // 右侧的懒信息
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1 | 1] += lazy[rt] * rn;
            // 左 右孩子承接了自己的任务，那么清空自己的，
            lazy[rt] = 0;
        }
    }

    // 比如 1 ~ 1000
    // 1,1000,1// 表示 左边界1 右边界1000 从1位置开始
    // 那么1-500在左节点，501-1000 右节点
    // 从哪开始l，到哪结束r，个根节点放在哪里，然后递归去划分任务
    public void build(int l, int r, int rt) {
        if (l == r) {// 说明到了叶子节点了，叶子节点存的就是原数组种的数
            sum[rt] = arr[l];
        }
        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);// 左节点
        build(mid + 1, r, rt << 1 | 1);// 右节点
        // 左右节点填好了，那么父节点也就要跟着填好
        pushUp(rt);
    }

    // int L, int R, int C 当前的任务，L到R范围上，每一个值+C
    // int l, int r, int rt 当前sum数组上的位置（内部属性）。
    public void add(int L, int R, int C, int l, int r, int rt) {
        // 当前的 l-r 内部范围位置已经全部在任务的要求范围内了，那么整个范围直接操作，不用往下分了
        if (L <= l && R >= r) {
            // 总共有r-l+1个数，累加和就是C* 数的个数
            sum[rt] += C * (r - l + 1);
            // 懒更新
            lazy[rt] += C;
            return;
        }
        // 要根据实际的内部位置进行划分任务，可能有一部分位置不需要执行任务
        // 找当前内部的实际位置的中点
        int mind = (l + r) >> 1;
        // 校验是否存在懒住的老的任务
        pushDown(rt, mind - l + 1, r - mind);
        // 需要发给左节点
        if (L <= mind) {
            add(L, R, C, l, mind, rt << 1);
        }
        // 需要发给右边节点
        if (R > mind) {
            add(L, R, C, mind + 1, r, rt << 1 | 1);
        }
        // 调整自己的
        pushUp(rt);
    }

    // int L, int R, int C 当前的任务，L到R范围上，所有的值变成C
    // int l, int r, int rt 当前sum数组上的位置（内部属性）。
    public void update(int L, int R, int C, int l, int r, int rt) {
        // 当前的实际数组累加和元素数组在整个任务的范围内
        if (L <= l && r <= R) {
            update[rt] = true;
            change[rt] = C;
            // 累加和需要更新,因为所有的值，全部更新成C，所以累加和就是个数*C
            sum[rt] = C * (r - l + 1);
            // 跟新了，那么任务也需要清空
            lazy[rt] = 0;
            return;
        }
        int mind = (l + r) >> 1;
        pushDown(rt, mind - l + 1, r - mind);
        if (L <= mind) {
            update(L, R, C, l, mind, rt << 1);
        }
        if (R > mind) {
            update(L, R, C, mind + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    public long query(int L, int R, int l, int r, int rt) {
        if (L <= l && R >= r) {
            return sum[rt];
        }
        int mind = (l + r) >> 1;
        pushDown(rt, mind - l + 1, r - mind);
        long ans = 0;
        if (L <= mind) {
            ans += query(L, R, l, mind + 1, rt << 1);
        }
        if (R > mind) {
            ans += query(L, R, mind, r, rt << 1 | 1);
        }
        return ans;
    }
}
