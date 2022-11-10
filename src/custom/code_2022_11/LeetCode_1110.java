package custom.code_2022_11;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_1110
 * @Author Duys
 * @Description
 * @Date 2022/11/10 17:34
 **/
// 1110. 删点成林
public class LeetCode_1110 {
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        if (root == null || to_delete == null || to_delete.length <= 0) {
            return ans;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : to_delete) {
            set.add(i);
        }
        process(root, set, ans);
        return ans;
    }

    public static TreeNode process(TreeNode cur, Set<Integer> delete, List<TreeNode> ans) {
        if (cur == null) {
            return null;
        }
        cur.left = process(cur.left, delete, ans);
        cur.right = process(cur.right, delete, ans);
        if (delete.contains(cur.val)) {
            if (cur.left != null) {
            }
            if (cur.right != null) {

            }
        }
        return cur;
    }

    public static void main(String[] args) {
        // root = [1,2,3,4,5,6,7], to_delete = [3,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<TreeNode> treeNodes = delNodes(root, new int[]{5, 3});
        System.out.println();
    }
}
