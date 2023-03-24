package week.week_2023_02_02;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName Code_02_LeetCode_987
 * @Author Duys
 * @Description
 * @Date 2023/2/9 9:47
 **/
// 987. 二叉树的垂序遍历
public class Code_02_LeetCode_987 {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // 献给节点编号
        List<Node> list = new ArrayList<>();
        Node node = new Node(0, 0, root.val);
        list.add(node);
        process(list, root, node);
        Collections.sort(list, new NodeComparator());
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            // 如果列不一样，需要新加一个list
            if (i == 0 || list.get(i - 1).col != list.get(i).col) {
                ans.add(new ArrayList<>());
            }
            ans.get(ans.size() - 1).add(list.get(i).val);
        }
        return ans;
    }

    public void process(List<Node> list, TreeNode root, Node rootNode) {
        if (root.left != null) {
            Node left = new Node(rootNode.row + 1, rootNode.col - 1, root.left.val);
            list.add(left);
            process(list, root.left, left);
        }
        if (root.right != null) {
            Node right = new Node(rootNode.row + 1, rootNode.col + 1, root.right.val);
            list.add(right);
            process(list, root.right, right);
        }
    }

    public class Node {
        int row;
        int col;
        int val;

        public Node(int r, int c, int v) {
            row = r;
            col = c;
            val = v;
        }
    }

    public class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            // 列
            if (o1.col != o2.col) {
                return o1.col - o2.col;
            }
            // 行
            if (o1.row != o2.row) {
                return o1.row - o2.row;
            }
            // 值
            return o1.val - o2.val;
        }
    }
}
