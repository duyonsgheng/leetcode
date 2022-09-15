package custom.code_2022_06;

import custom.base.Node;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_117
 * @Author Duys
 * @Description
 * @Date 2022/6/14 14:19
 **/
//117. 填充每个节点的下一个右侧节点指针 II
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
//初始状态下，所有 next 指针都被设置为 NULL。
//链接：https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii
public class LeetCode_117 {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.addLast(root);
        while (!nodes.isEmpty()) {
           /* LinkedList<Node> copy = new LinkedList<>();
            for (int i = 0; i < nodes.size() - 1; i++) {
                Node n1 = nodes.get(i);
                if (n1 != null && n1.left != null) {
                    copy.addLast(n1.left);
                }
                if (n1 != null && n1.right != null) {
                    copy.addLast(n1.right);
                }
                n1.next = nodes.get(i + 1);
            }
            Node last = nodes.get(nodes.size() - 1);
            if (last != null && last.left != null) {
                copy.addLast(last.left);
            }
            if (last != null && last.right != null) {
                copy.addLast(last.right);
            }
            last.next = null;

            nodes = copy;*/
            int tmp = nodes.size();
            while (tmp != 0) {
                Node node = nodes.pollFirst();
                if (!nodes.isEmpty() && tmp != 1) {
                    node.next = nodes.peekFirst();
                } else {
                    node.next = null;
                }
                if (node.left != null) {
                    nodes.addLast(node.left);
                }
                if (node.right != null) {
                    nodes.addLast(node.right);
                }
                tmp--;
            }
        }
        return root;
    }
}
