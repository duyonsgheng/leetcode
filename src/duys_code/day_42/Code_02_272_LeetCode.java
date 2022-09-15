package duys_code.day_42;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName Code_02_272_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/closest-binary-search-tree-value-ii/
 * @Date 2021/12/31 16:30
 **/
public class Code_02_272_LeetCode {
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ret = new LinkedList<>();

        // >= target 的最近的节点，而且需要快速找到后继节点的结构
        Stack<TreeNode> moreTops = new Stack<>();
        // <= target 的最近的节点，而且需要快速找到前驱节点的机构
        Stack<TreeNode> lessTops = new Stack<>();
        getMoreTops(root, target, moreTops);
        getLessTops(root, target, lessTops);

        // 如果我的大于等于 target  和 小于等于 target是相等的
        if (!moreTops.isEmpty() && !lessTops.isEmpty() && moreTops.peek().val == lessTops.peek().val) {
            getLessTopsHeader(lessTops);
        }

        while (k-- > 0) {
            if (moreTops.isEmpty()) {
                ret.add(getLessTopsHeader(lessTops));
            } else if (lessTops.isEmpty()) {
                ret.add(getMoreTopsHeader(moreTops));
            } else {
                double diffs = Math.abs((double) moreTops.peek().val - target);
                double diffp = Math.abs((double) lessTops.peek().val - target);
                if (diffs < diffp) {
                    ret.add(getMoreTopsHeader(moreTops));
                } else {
                    ret.add(getLessTopsHeader(lessTops));
                }
            }
        }
        return ret;
    }

    // 快速找到当前弹出节点的后继节点，就是当前节点的右树的左边界
    public static int getMoreTopsHeader(Stack<TreeNode> moreTops) {
        TreeNode cur = moreTops.pop();
        int res = cur.val;
        // 右树的左边界
        cur = cur.right;
        while (cur != null) {
            moreTops.push(cur);
            cur = cur.left;
        }
        return res;
    }

    // 快速找到当前弹出节点的前驱节点，就是我们左树的右边界
    public static int getLessTopsHeader(Stack<TreeNode> lessTops) {
        TreeNode cur = lessTops.pop();
        int res = cur.val;

        cur = cur.left;
        while (cur != null) {
            lessTops.push(cur);
            cur = cur.right;
        }
        return res;
    }

    // 在二叉搜索树中，父节点大于左孩子，小于右孩子
    // 我们需要找到大于等于的所有节点
    // 来到一个节点，如果都小于了，直接往右走，大于了，就往左走，然后收集
    public static void getMoreTops(TreeNode root, double target, Stack<TreeNode> stack) {
        while (root != null) {
            if (root.val == target) {
                stack.push(root);
                break;
            } else if (root.val > target) {
                stack.push(root);
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    public static void getLessTops(TreeNode root, double target, Stack<TreeNode> stack) {
        while (root != null) {
            if (root.val == target) {
                stack.push(root);
                break;
            } else if (root.val < target) {
                stack.push(root);
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
