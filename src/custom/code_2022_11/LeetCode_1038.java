package custom.code_2022_11;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LeetCode_1038
 * @Author Duys
 * @Description
 * @Date 2022/11/7 14:11
 **/
public class LeetCode_1038 {
    static int sum = 0;

    public static TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return root;
        }
        bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst(root.left);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);
    }
}
