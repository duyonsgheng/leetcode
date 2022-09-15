package duys.class_06_15;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EncodeNaryTreeToBinaryTree
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/encode-n-ary-tree-to-binary-tree 力扣的431题
 * 多叉树的转二叉树，并且要求能转回去
 * @Date 2021/6/22 17:30
 **/
public class EncodeNaryTreeToBinaryTree {

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 核心思想，把属于当前节点的所有孩子都放在左树的有边界上
     */
    public static class Code {
        // 转二叉树
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            // 构建二叉树的头部
            TreeNode head = new TreeNode(root.val);
            // 二叉树的左树上进行递归处理
            head.left = en(root.children);
            return head;
        }

        public TreeNode en(List<Node> childNode) {
            TreeNode head = null;
            TreeNode cur = null;
            // 构建二叉树，从头部开始
            for (Node node : childNode) {
                TreeNode treeNode = new TreeNode(node.val);
                // 如果当前这个所有的子节点列表，要构建成一个右树边界树。需要有一个来做头部
                if (head == null) {
                    head = treeNode;
                }
                // 否在往右边界上挂
                else {
                    head.right = treeNode;
                }
                cur = treeNode;
                // 继续挂下一层的，多叉树，每一个节点可能有多个子节点
                cur.left = en(node.children);
            }
            return head;
        }

        // 二叉树转多叉树
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        public List<Node> de(TreeNode root) {
            List<Node> child = new ArrayList<>();
            while (root != null) {
                // 每一个左树上的右边界上可能都是一个原多叉树的一个节点的子节点
                Node cur = new Node(root.val, de(root.left));
                child.add(cur);
                // 往后推
                root = root.right;
            }
            return child;
        }
    }
}
