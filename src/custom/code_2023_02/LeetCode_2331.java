package custom.code_2023_02;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_2331
 * @Author Duys
 * @Description
 * @Date 2023/2/6 9:29
 **/
// 2331. 计算布尔二叉树的值
public class LeetCode_2331 {
    public boolean evaluateTree(TreeNode root) {
        // 叶子节点 要么值为 0 要么值为 1 ，其中 0 表示 False ，1 表示 True 。
        // 非叶子节点 要么值为 2 要么值为 3 ，其中 2 表示逻辑或 OR ，3 表示逻辑与 AND 。
        if (root.left == null || root.right == null) { // 叶子节点
            return root.val == 1;
        }
        if (root.val == 2) {
            return evaluateTree(root.right) || evaluateTree(root.left);
        } else {
            return evaluateTree(root.right) && evaluateTree(root.left);
        }
    }
}
