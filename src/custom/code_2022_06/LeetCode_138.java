package custom.code_2022_06;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_138
 * @Author Duys
 * @Description
 * @Date 2022/6/29 19:30
 **/
// 138. 复制带随机指针的链表
public class LeetCode_138 {
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Map<Node, Node> nodeMap = new HashMap<>();
        return process(head, nodeMap);
    }

    public static Node process(Node head, Map<Node, Node> nodeMap) {
        if (head == null) {
            return null;
        }
        if (!nodeMap.containsKey(head)) {
            Node cur = new Node(head.val);
            nodeMap.put(head, cur);
            cur.next = process(head.next, nodeMap);
            cur.random = process(head.random, nodeMap);
        }
        return nodeMap.get(head);
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
