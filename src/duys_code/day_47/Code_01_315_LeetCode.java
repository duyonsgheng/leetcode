package duys_code.day_47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_01_315_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 * @Date 2021/10/20 13:29
 **/
public class Code_01_315_LeetCode {
    /**
     * 给一个数组，按要求返回一个新得数组长度一样，新的这个数组元素记录的是nums里面每一个值对应的 右边比他小的元素个数
     */
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return ans;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            ans.add(0);
        }
        // 对应值出现的对应位置
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{nums[i], i};
        }
        // 按照值的大小给位置数组排序
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        DynamicSegmentTree dynamicSegmentTree = new DynamicSegmentTree(n);
        for (int[] a : arr) {
            // 原数组位置是是a[1] ，
            ans.set(a[1], dynamicSegmentTree.query(a[1] + 1, n));
            dynamicSegmentTree.add(a[1] + 1, 1);
        }
        return ans;
    }

    public static class TreeNode {
        public int sum;
        public TreeNode left;
        public TreeNode right;
    }

    // 单点线段树
    public static class DynamicSegmentTree {
        public TreeNode root;
        public int size;

        public DynamicSegmentTree(int n) {
            root = new TreeNode();
            size = n;
        }

        // 在哪一个位置 i ，增加一个值 v
        public void add(int i, int v) {
            add(root, 1, size, i, v);
        }

        private void add(TreeNode cur, int l, int r, int i, int v) {
            if (l == r) {
                cur.sum += v;
            } else {
                int mid = (l + r) / 2;
                ;// (l+r)/2
                if (i <= mid) {
                    if (cur.left == null) {
                        cur.left = new TreeNode();
                    }
                    add(cur.left, l, mid, i, v);
                } else {
                    if (cur.right == null) {
                        cur.right = new TreeNode();
                    }
                    add(cur.right, mid + 1, r, i, v);
                }
                cur.sum = (cur.left == null ? 0 : cur.left.sum) + (cur.right == null ? 0 : cur.right.sum);
            }
        }

        public int query(int s, int e) {
            return query(root, 1, size, s, e);
        }

        private int query(TreeNode cur, int l, int r, int s, int e) {
            if (cur == null) {
                return 0;
            }
            // 包含了这期间所有的，不需要划分了
            if (s <= l && r <= e) {
                return cur.sum;
            }
            int mid = (l + r) / 2;
            if (e <= mid) {
                return query(cur.left, l, mid, s, e);
            } else if (s > mid) {
                return query(cur.right, mid + 1, r, s, e);
            } else {
                return query(cur.left, l, mid, s, e) + query(cur.right, mid + 1, r, s, e);
            }
        }
    }
}
