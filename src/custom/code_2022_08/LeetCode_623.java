package custom.code_2022_08;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_623
 * @Author Duys
 * @Description
 * @Date 2022/8/30 13:03
 **/
// 623. 在二叉树中增加一行
public class LeetCode_623 {

    // 一层一层的来
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        List<TreeNode> cur = new ArrayList<>();
        cur.add(root);
        // 除去root
        // 还剩下depth-1
        for (int i = 1; i < depth - 1; i++) {
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode node : cur) {
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            cur = tmp;
        }
        for (TreeNode node : cur) {
            node.left = new TreeNode(val, node.left, null);
            node.right = new TreeNode(val, null, node.right);
        }
        return root;
    }
}
