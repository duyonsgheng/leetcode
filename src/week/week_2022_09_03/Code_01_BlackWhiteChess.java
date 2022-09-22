package week.week_2022_09_03;

/**
 * @ClassName Code_01_BlackWhiteChess
 * @Author Duys
 * @Description
 * @Date 2022/9/22 8:38
 **/
// 来自360
// 有n个黑白棋子，它们的一面是黑色，一面是白色
// 它们被排成一行，位置0~n-1上。一开始所有的棋子都是黑色向上
// 一共有q次操作，每次操作将位置标号在区间[L，R]内的所有棋子翻转
// 那么这个范围上的每一颗棋子的颜色也就都改变了
// 请在每次操作后，求这n个棋子中，黑色向上的棋子个数
// 1 <= n <= 10^18
// 1 <= q <= 300
// 0 <= 每一条操作的L、R <= n - 1
// 输出q行，每一行一个整数，表示操作后的所有黑色棋子的个数
// 注意 : 其实q <= 10^5也可以通过，360考试时候降低了难度
public class Code_01_BlackWhiteChess {
    public static void main(String[] args) {
        DynamicSegmentTree1 dst = new DynamicSegmentTree1(10);
        dst.reverse(1, 3);
        System.out.println(dst.blacks());
        dst.reverse(2, 4);
        System.out.println(dst.blacks());
    }

    public static class DynamicSegmentTree {
        // 1 ~ n
        public Node root;
        public long size;

        public DynamicSegmentTree(long n) {
            root = new Node(n);
            size = n;
        }

        public long blacks() {
            return root.sum;
        }

        // l++ r++
        // 0, 7  -> 1,8
        // 4, 19 -> 5, 20
        // 19, 4 -> 不操作
        public void reverse(long l, long r) {
            if (l <= r) {
                l++;
                r++;
                reverse(root, 1, size, l, r);
            }
        }

        // l...r 线段树范围  s...e 任务范围
        // Node cur
        private void reverse(Node cur, long l, long r, long s, long e) {
            if (s <= l && r <= e) {
                cur.change = !cur.change;
                cur.sum = (r - l + 1) - cur.sum;
            } else {
                long m = (l + r) >> 1;
                pushDown(cur, m - l + 1, r - m);
                if (s <= m) {
                    reverse(cur.left, l, m, s, e);
                }
                if (e > m) {
                    reverse(cur.right, m + 1, r, s, e);
                }
                pushUp(cur);
            }
        }

        private void pushDown(Node cur, long ln, long rn) {
            if (cur.left == null) {
                cur.left = new Node(ln);
            }
            if (cur.right == null) {
                cur.right = new Node(rn);
            }
            if (cur.change) {
                cur.left.change = !cur.left.change;
                cur.left.sum = ln - cur.left.sum;
                cur.right.change = !cur.right.change;
                cur.right.sum = rn - cur.right.sum;
                cur.change = false;
            }
        }

        private void pushUp(Node cur) {
            cur.sum = cur.left.sum + cur.right.sum;
        }

    }


    // 动态开点线段树
    public static class DynamicSegmentTree1 {
        public Node root;
        public long size;

        public DynamicSegmentTree1(long n) {
            root = new Node(n);
            size = n;
        }

        // 返回整个区间黑子的数量
        public long blacks() {
            return root.sum;
        }

        //l到r的范围上反转
        public void reverse(long l, long r) {
            if (l > r) {
                return;
            }
            // 线段树是1开始的
            l++;
            r++;
            reverse(root, 1, size, l, r);
        }

        private void reverse(Node cur, long L, long R, long l, long r) {
            // 包含了整个范围
            if (l <= L && r >= R) {
                // 之前这个范围上已经积压了，那么本次就不需要下发了
                // 首次是false，那么下一次一定会pushDown
                cur.change = !cur.change;
                cur.sum = (R - L + 1) - cur.sum;
                return;
            }
            long mid = (R + L) >> 1;
            pushDown(cur, mid - L + 1, R - mid);
            if (l <= mid) {
                reverse(cur.left, L, mid, l, r);
            }
            if (r > mid) {
                reverse(cur.right, mid + 1, R, l, r);
            }
            pushUp(cur);
        }

        private void reverse1(Node cur, long l, long r, long s, long e) {
            if (s <= l && r <= e) {
                cur.change = !cur.change;
                cur.sum = (r - l + 1) - cur.sum;
            } else {
                long m = (l + r) >> 1;
                pushDown(cur, m - l + 1, r - m);
                if (s <= m) {
                    reverse(cur.left, l, m, s, e);
                }
                if (e > m) {
                    reverse(cur.right, m + 1, r, s, e);
                }
                pushUp(cur);
            }
        }

        private void pushDown(Node cur, long ln, long rn) {
            if (cur.left == null) {
                cur.left = new Node(ln);
            }
            if (cur.right == null) {
                cur.right = new Node(rn);
            }
            // 需要下发
            if (cur.change) {
                cur.left.change = !cur.left.change;
                cur.left.sum = ln - cur.left.sum;

                cur.right.change = !cur.right.change;
                cur.right.sum = rn - cur.right.sum;

                cur.change = false;
            }
        }

        private void pushUp(Node cur) {
            cur.sum = cur.left.sum + cur.right.sum;
        }
    }

    // 构建开点线段树的节点
    //
    static class Node {
        public long sum;
        public boolean change;
        public Node left;
        public Node right;

        public Node(long len) {
            sum = len;
            change = false;
        }
    }
}
