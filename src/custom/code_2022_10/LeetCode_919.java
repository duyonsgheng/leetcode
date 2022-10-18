package custom.code_2022_10;

import custom.base.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName LeetCode_919
 * @Author Duys
 * @Description
 * @Date 2022/10/18 10:53
 **/
// 919. 完全二叉树插入器
public class LeetCode_919 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        CBTInserter cbtInserter = new CBTInserter(root);
        System.out.println(cbtInserter.insert(3));
        System.out.println(cbtInserter.insert(4));
        System.out.println(cbtInserter.insert(5));
        System.out.println(cbtInserter.insert(6));
    }

    static class CBTInserter {
        Queue<TreeNode> queue; // 记录上一次节点不双全的节点
        TreeNode root;

        public CBTInserter(TreeNode root) {
            this.queue = new LinkedList<>();
            this.root = root;
            Queue<TreeNode> curs = new LinkedList<>();
            curs.offer(root);
            while (!curs.isEmpty()) {
                TreeNode cur = curs.poll();
                if (cur.left != null) {
                    curs.offer(cur.left);
                }
                if (cur.right != null) {
                    curs.offer(cur.right);
                }
                // 如果节点不双全，那么就记录下来
                if (!(cur.left != null && cur.right != null)) {
                    queue.offer(cur);
                }
            }
        }

        public int insert(int val) {
            TreeNode cur = new TreeNode(val);
            // 拿到上一次添加后，节点不双全的节点
            TreeNode pre = queue.peek();
            // 往上一次节点上挂
            if (pre.left == null) {
                pre.left = cur;
            } else {
                pre.right = cur;
                queue.poll();// 右边节点都填满了，需要弹出，不在作为下一次的标杆了
            }
            queue.offer(cur);
            return pre.val;
        }


        public TreeNode get_root() {
            return root;
        }
    }
}
