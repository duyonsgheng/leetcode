package custom.code_2022_08;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_450
 * @Author Duys
 * @Description
 * @Date 2022/8/11 17:35
 **/
// 450. 删除二叉搜索树中的节点
public class LeetCode_450 {

    // 题目给出一个二叉搜索树
    // 当前节点大于key，去左边
    // 当前节点小于key，去右边
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 去右树的最左节点
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            // 去链接好
            root.right = deleteNode(root.right, cur.val);
            cur.right = root.right;
            cur.left = root.left;
            return cur;
        }
        // 去右树上找
        else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        // 去左树上找
        else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
    }
}
