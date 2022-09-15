package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_99
 * @Author Duys
 * @Description
 * @Date 2022/5/17 17:54
 **/
// 99. 恢复二叉搜索树
// 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
public class LeetCode_99 {
    // 根据 搜索二叉树-性质 可以得出中序遍历一定是升序
    // 找到两个值，交换值就可以了
    public static void recoverTree(TreeNode root) {
        // 先中序遍历
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);

        // 找到不合规矩的
        int[] tow = find(nums);
        // 交换
        swap(root, 2, tow[0], tow[1]);
    }

    public static void swap(TreeNode node, int rest, int x, int y) {
        if (node == null) {
            return;
        }
        if (node.val == x || node.val == y) {
            node.val = node.val == x ? y : x;
            if (rest-- == 0) {
                return;
            }
        }
        swap(node.left, rest, x, y);
        swap(node.right, rest, x, y);
    }

    public static int[] find(List<Integer> nums) {
        int n = nums.size();
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i + 1) < nums.get(i)) {
                index2 = i + 1;
                if (index1 == -1) {
                    index1 = i;
                } else {
                    break;
                }
            }
        }
        return new int[]{nums.get(index1), nums.get(index2)};
    }

    public static void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
