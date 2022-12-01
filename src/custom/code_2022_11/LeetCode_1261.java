package custom.code_2022_11;

import custom.base.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_1261
 * @Author Duys
 * @Description
 * @Date 2022/11/30 10:32
 **/
// 1261. 在受污染的二叉树中查找元素
public class LeetCode_1261 {
    class FindElements {
        private Set<Integer> set;

        public FindElements(TreeNode root) {
            root.val = 0;
            set = new HashSet<>();
            set.add(0);
            process(root);
        }

        public boolean find(int target) {
            return set.contains(target);
        }

        private void process(TreeNode root) {
            if (root == null) {
                return;
            }
            int v = root.val;
            if (root.left != null) {
                root.left.val = 2 * v + 1;
                set.add(2 * v + 1);
                process(root.left);
            }
            if (root.right != null) {
                root.right.val = 2 * v + 2;
                set.add(2 * v + 2);
                process(root.right);
            }
        }
    }
}
