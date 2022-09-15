package custom.code_2022_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_430
 * @Author Duys
 * @Description
 * @Date 2022/8/10 11:08
 **/
// 430. 扁平化多级双向链表
public class LeetCode_430 {

    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        process(head, nodes);
        if (nodes.size() < 3) {
            if (nodes.size() == 1) {
                nodes.get(0).prev = null;
                nodes.get(0).child = null;
                nodes.get(0).next = null;
            } else if (nodes.size() == 2) {
                nodes.get(0).prev = null;
                nodes.get(0).child = null;
                nodes.get(0).next = nodes.get(1);
                nodes.get(1).prev = nodes.get(0);
                nodes.get(1).child = null;
                nodes.get(1).next = null;
            }
        } else {
            for (int i = 1; i < nodes.size() - 1; i++) {
                Node pre = nodes.get(i - 1);
                Node cur = nodes.get(i);
                Node next = nodes.get(i + 1);
                pre.child = null;
                cur.child = null;
                next.child = null;
                pre.next = cur;
                cur.prev = pre;
                cur.next = next;
                next.prev = cur;
            }
            nodes.get(0).prev = null;
            nodes.get(nodes.size() - 1).next = null;
        }
        return nodes.get(0);
    }

    public static void process(Node cur, List<Node> nodes) {
        if (cur == null) {
            return;
        }
        // 优先处理子节点
        nodes.add(cur);
        Node child = cur.child;
        if (child != null) {
            process(child, nodes);
        }
        if (cur.next != null) {
            process(cur.next, nodes);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node n1 = new Node(2);
        root.next = n1;
        n1.prev = root;
        Node n2 = new Node(3);
        n1.next = n2;
        n2.prev = n1;
        Node n3 = new Node(4);
        n2.next = n3;
        n3.prev = n2;

        Node c1 = new Node(5);
        n2.child = c1;

        Node c2 = new Node(6);
        c1.next = c2;
        c2.prev = c1;

        Node c3 = new Node(7);
        c2.next = c3;
        c3.prev = c2;

        Node c11 = new Node(8);
        c2.child = c11;
        flatten(root);
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int v) {
            val = v;
        }
    }
}
