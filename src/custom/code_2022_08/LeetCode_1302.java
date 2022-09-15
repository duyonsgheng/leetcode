package custom.code_2022_08;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_1302
 * @Author Duys
 * @Description
 * @Date 2022/8/17 9:47
 **/
// 1302. 层数最深叶子节点的和
public class LeetCode_1302 {

    // 按曾遍历
    public static int deepestLeavesSum(TreeNode root) {
        int ans = 0;
        Deque<List<TreeNode>> queue = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        queue.offerLast(Arrays.asList(root));
        List<TreeNode> last = null;
        while (!queue.isEmpty()) {
            // 前一层
            List<TreeNode> treeNodes = queue.pollLast();
            last = treeNodes;
            List<TreeNode> curs = new ArrayList<>();
            for (TreeNode treeNode : treeNodes) {
                if (treeNode.left != null) {
                    curs.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    curs.add(treeNode.right);
                }
            }
            if (curs.size() > 0) {
                queue.offerLast(curs);
            }
        }
        for (TreeNode treeNode : last) {
            ans += treeNode.val;
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);

        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);

        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);


        treeNode.left.right.left = new TreeNode(5);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.right.right.right = new TreeNode(8);
        System.out.println(deepestLeavesSum(treeNode));
    }
}
