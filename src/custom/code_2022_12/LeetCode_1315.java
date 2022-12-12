package custom.code_2022_12;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_1315
 * @Author Duys
 * @Description
 * @Date 2022/12/8 16:51
 **/
// 1315. 祖父节点值为偶数的节点和
public class LeetCode_1315 {
    public static int sumEvenGrandparent(TreeNode root) {
        // 一层一层的来
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        queue.add(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 当前层的拿出来
            List<TreeNode> curLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                //当前层为偶数， 看看自己的孙节点是否存在，如果存在累加到答案去
                if (poll.val % 2 == 0) {
                    if (poll.left != null) {
                        ans += poll.left.left != null ? poll.left.left.val : 0;
                        ans += poll.left.right != null ? poll.left.right.val : 0;
                    }
                    if (poll.right != null) {
                        ans += poll.right.left != null ? poll.right.left.val : 0;
                        ans += poll.right.right != null ? poll.right.right.val : 0;
                    }
                }
                // 下一层
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(7);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(9);

        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(5);
        System.out.println(sumEvenGrandparent(root));
    }

}
