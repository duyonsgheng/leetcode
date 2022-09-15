package week.week_2022_01_02;

/**
 * @ClassName Code_05_QuietSum
 * @Author Duys
 * @Description
 * @Date 2022/4/8 9:36
 **/


// 给定一个非负数组arr，学生依次坐在0~N-1位置，每个值表示学生的安静值
// 如果在i位置安置插班生，那么i位置的安静值变成0，同时任何同学都会被影响到而减少安静值
// 同学安静值减少的量: N - 这个同学到插班生的距离
// 但是减到0以下的话，当做0处理
// 返回一个和arr等长的ans数组，ans[i]表示如果把插班生安排在i位置，所有学生的安静值的和
// 比如 : arr = {3,4,2,1,5}，应该返回{4,3,2,3,4}
// 比如 : arr = {10,1,10,10,10}，应该返回{24,27,20,20,22}
// arr长度 <= 10^5
// arr中值 <= 2 * 10^5
public class Code_05_QuietSum {

    // 暴力的方法，为了验证，当对数器使用
    public static long[] quiet1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new long[0];
        }
        int n = arr.length;
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < i; j++) {
                sum += Math.max(0, arr[j] - (n - Math.abs(i - j)));
            }
            for (int j = i + 1; j < n; j++) {
                sum += Math.max(0, arr[j] - (n - Math.abs(i - j)));
            }
            ans[i] = sum;
        }
        return ans;
    }


    // 正式的解答 size balance tree
    public static long[] quiet2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new long[0];
        }
        int n = arr.length;
        long[] ans = new long[n];
        SBTree sb = new SBTree();
        sb.add(arr[n - 1] - 1);
        for (int i = n - 2; i >= 0; i--) {
            long moreCount = sb.moreCount(i);
            long moreSum = sb.moreSum(i);
            ans[i] = moreSum - (moreCount * i);
            sb.add(arr[i] + i - n);
        }
        sb = new SBTree();
        sb.add(arr[0] - 1);
        for (int i = 1, j = n - 2; i < n; i++, j--) {
            long moreCount = sb.moreCount(j);
            long moreSum = sb.moreSum(j);
            ans[i] += moreSum - (moreCount * j);
            sb.add(arr[i] + j - n);
        }
        return ans;
    }

    public static class SBTree {
        public SBTNode root;


        // 右旋
        private SBTNode rightRotate(SBTNode cur) {
            int curCount = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            long curSum = cur.sum - (cur.l != null ? cur.l.sum : 0) - (cur.r != null ? cur.r.sum : 0);
            SBTNode leftNode = cur.l; // 拿出当前节点的左节点
            cur.l = leftNode.r;// 把左孩子的右节点给父节点的左孩子
            leftNode.r = cur;
            leftNode.size = cur.size;
            leftNode.all = cur.all;
            leftNode.sum = cur.sum;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + curCount;
            cur.sum = (cur.l != null ? cur.l.sum : 0) + (cur.r != null ? cur.r.sum : 0) + curSum;
            return leftNode;
        }

        // 左旋
        private SBTNode leftRotate(SBTNode cur) {
            int curCount = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            long curSum = cur.sum - (cur.l != null ? cur.l.sum : 0) - (cur.r != null ? cur.r.sum : 0);
            SBTNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            rightNode.sum = cur.sum;
            rightNode.all = cur.all;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + curCount;
            cur.sum = (cur.l != null ? cur.l.sum : 0) + (cur.r != null ? cur.r.sum : 0) + curSum;
            return rightNode;
        }

        // 平衡
        private SBTNode maintain(SBTNode cur) {
            if (cur == null) return null;
            long leftSize = cur.l != null ? cur.l.size : 0;
            long leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            long leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;

            long rightSize = cur.r != null ? cur.r.size : 0;
            long rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            long rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;

            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        public void add(int v) {
            root = add(root, v, contains(root, v));
        }

        private SBTNode add(SBTNode cur, int v, boolean contains) {
            if (cur == null) {
                return new SBTNode(v);
            } else {
                if (!contains) {
                    cur.size++;
                }
                cur.all++;
                cur.sum += v;
                if (cur.value == v) {
                    return cur;
                } else if (cur.value > v) {
                    cur.l = add(cur.l, v, contains);
                } else {
                    cur.r = add(cur.r, v, contains);
                }
            }
            return maintain(cur);
        }

        private boolean contains(SBTNode cur, int v) {
            if (cur == null) {
                return false;
            } else if (cur.value == v) {
                return true;
            } else if (cur.value > v) {
                return contains(cur.l, v);
            } else {
                return contains(cur.r, v);
            }
        }

        public long moreCount(int num) {
            return moreCount(root, num);
        }

        private long moreCount(SBTNode cur, int num) {
            if (cur == null) {
                return 0;
            }
            if (cur.value <= num) {
                return moreCount(cur.r, num);
            } else {
                return cur.all - (cur.l != null ? cur.l.all : 0) + moreCount(cur.l, num);
            }
        }

        public long moreSum(int num) {
            return moreSum(root, num);
        }

        private long moreSum(SBTNode cur, int num) {
            if (cur == null) {
                return 0;
            }
            if (cur.value <= num) {
                return moreSum(cur.r, num);
            } else {
                return cur.sum - (cur.l != null ? cur.l.sum : 0) + moreSum(cur.l, num);
            }
        }

    }

    public static class SBTNode {
        public int value;

        // 和业务无关，不通的key的size，为了调整树的平衡
        public int size;
        // 和业务有关，添加过几个数字
        public int all;
        // 和业务有关，子树的累加和
        public long sum;

        public SBTNode l;
        public SBTNode r;

        public SBTNode(int v) {
            value = v;
            size = 1;
            all = 1;
            sum = v;
        }

    }
}
