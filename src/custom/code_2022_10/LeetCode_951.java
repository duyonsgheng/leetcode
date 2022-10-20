package custom.code_2022_10;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_951
 * @Author Duys
 * @Description
 * @Date 2022/10/19 16:47
 **/
// 951. 翻转等价二叉树
public class LeetCode_951 {

    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        // 可以反转，也可以不反转
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }

    public static boolean flipEquiv2(TreeNode root1, TreeNode root2) {
        TreeNode root1New = reverse(root1);
        return real(root1New, root2);
    }

    public static boolean real(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        if (r1 == null || r2 == null) {
            return false;
        }
        boolean left = real(r1.left, r2.left);
        boolean right = real(r1.right, r2.right);
        return r1.val == r2.val && left && right;
    }

    public static TreeNode reverse(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 左右孩子交换
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = reverse(right);
        root.right = reverse(left);
        return root;
    }

    // root1 =
    // [1,2,3,4,5,6,null,null,null,7,8], root2 =
    // [1,3,2,null,6,4,5,null,null,null,null,8,7]
    // [1,2,3,4,5,6,null,null,null,7,8]
    // [1,3,2,null,6,4,5,null,null,null,null,8,7]

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);

        TreeNode reverse = reverse(root);
        System.out.println(reverse);
    }
}
