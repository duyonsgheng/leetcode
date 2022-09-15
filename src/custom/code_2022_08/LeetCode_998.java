package custom.code_2022_08;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_998
 * @Author Duys
 * @Description
 * @Date 2022/8/30 9:13
 **/
public class LeetCode_998 {

    // 1.如果根节点的值小于val，那么新的树以val作为根节点，之前的树作为新的树的左节点
    // 2. 我们从根节点开始不断地向右子节点进行遍历。这是因为，当遍历到的节点的值大于 val 时，由于 val 是新添加的位于数组末尾的元素，那么在构造的结果中，
    // val 一定出现在该节点的右子树中。
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null) {
            if (val > cur.val) {
                if (parent == null) {
                    return new TreeNode(val, root, null);
                }
                TreeNode node = new TreeNode(val, cur, null);
                parent.right = node;
                return root;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }
        parent.right = new TreeNode(val);
        return root;
    }
}
