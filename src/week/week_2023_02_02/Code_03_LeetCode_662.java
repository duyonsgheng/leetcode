package week.week_2023_02_02;

import custom.base.TreeNode;

import java.util.LinkedList;

/**
 * @ClassName Code_03_LeetCode_662
 * @Author Duys
 * @Description
 * @Date 2023/2/9 10:06
 **/
// 662. 二叉树最大宽度
public class Code_03_LeetCode_662 {
    public int widthOfBinaryTree(TreeNode root) {
        int ans = 1;
        // 类似给堆编号
        LinkedList<Info> list = new LinkedList<>();
        list.add(new Info(root, 1));
        // 当前节点的左子节点编号 2*i 右节点是 2*i +1
        while (!list.isEmpty()) {
            // 当前层最后一个节点编号 - 当前层最开始节点的编号
            ans = Math.max(ans, list.peekLast().index - list.peekFirst().index + 1);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Info cur = list.pollFirst();
                if (cur.node.left != null) {
                    list.add(new Info(cur.node.left, cur.index * 2));
                }
                if (cur.node.right != null) {
                    list.add(new Info(cur.node.right, cur.index * 2 + 1));
                }
            }
        }
        return ans;
    }

    public class Info {
        // 当前节点，
        public TreeNode node;
        // 当前节点的编号
        public int index;

        public Info(TreeNode n, int i) {
            node = n;
            index = i;
        }
    }
}
