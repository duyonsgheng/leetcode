package week.week_2022_10_003;

/**
 * @ClassName Code_03_AddToSorted
 * @Author Duys
 * @Description
 * @Date 2022/10/20 10:51
 **/
// 来自阿里
// 给定一个长度n的数组，每次可以选择一个数x
// 让这个数组中所有的x都变成x+1，问你最少的操作次数
// 使得这个数组变成一个非降数组
// n <= 3 * 10^5
// 0<= 数值 <= 10^9
public class Code_03_AddToSorted {
    // 思路：
    // 遍历数组的过程中，记录遇到的最大，然后把所有的值抬到最大-1就可以做到整个数组都是非降序的了，那么我们怎么记录整个过程中抬的次数，而且不能算重，
    // 不能算重复的意思是：比如之前把1抬到3 了，中间也经历了2被抬了一次。那么一下次遇到2的时候，就不需要再次被抬上去了，所以我们需要一个很大的空间
    // 来记录，那么线段树无疑是最好的操作。但是看数据量是10^9，所以可能会爆内存，这里我们使用开点线段树
    public int minOps(int[] arr) {
        int n = arr.length;
        int max = -1;
        for (int num : arr)
            max = Math.max(max, num);
        DynamicSegmentTree ds = new DynamicSegmentTree(max);
        for (int i = 1, m = arr[0]; i < n; i++) {
            if (m > arr[i]) {
                ds.set(arr[i], m - 1);
            }
            m = Math.max(m, arr[i]);
        }
        return ds.sum();
    }

    class Node {
        public Node left;
        public Node right;
        public int sum;
        public boolean set; // 不需要重复set
    }

    class DynamicSegmentTree {
        public Node root;
        public int size;

        public DynamicSegmentTree(int max) {
            root = new Node();
            size = max;
        }

        public void set(int s, int e) {
            update(root, 0, size, s, e);
        }

        public int sum() {
            return root.sum;
        }

        private void update(Node cur, int l, int r, int s, int e) {
            if (s <= l && e >= r) {
                cur.set = true;
                cur.sum = r - l + 1;
                return;
            }
            int m = (l + r) >> 1;
            pushDown(cur, m - l + 1, r - m);
            if (s <= m) {
                update(cur.left, l, m, s, e);
            }
            if (e > m) {
                update(cur.right, m + 1, r, s, e);
            }
            cur.sum = cur.left.sum + cur.right.sum;
        }

        private void pushDown(Node cur, int ln, int rn) {
            if (cur.left == null) {
                cur.left = new Node();
            }
            if (cur.right == null) {
                cur.right = new Node();
            }
            if (cur.set) {
                cur.left.set = true;
                cur.right.set = true;
                cur.left.sum = ln;
                cur.right.sum = rn;
                cur.set = false;
            }
        }
    }

}
