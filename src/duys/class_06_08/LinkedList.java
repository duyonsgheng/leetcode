package duys.class_06_08;

/**
 * @ClassName LinkedList
 * @Author Duys
 * @Description TODO
 * @Date 2021/6/9 14:17
 **/
public class LinkedList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 获取链表的中点（节点个数为奇数），或者中点的上节点（节点个数为偶数）
     *
     * @return
     */
    public static Node getMinOrUpMid(Node head) {
        // 至少3个节点以上
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // 快慢指针
        Node fast = head.next.next;
        Node slow = head.next;
        // 1 - 2 -3 - 4 - 5
        //     s  f
        //        s       f
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 获取中间节点或者中间节点的下节点
     *
     * @param head
     * @return
     */
    public static Node getMinOrDownMin(Node head) {
        // 至少3个节点以上
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // 快慢指针
        Node fast = head.next;
        Node slow = head.next;
        // 1 - 2 -3 - 4
        //    sf
        //        s    f
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 获取中间节点或者中间节点的上节点的前一个节点
     *
     * @param head
     * @return
     */
    public static Node getMinOrUpMidPreNode(Node head) {
        // 至少3个节点以上
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 快慢指针
        Node fast = head.next.next;
        Node slow = head;
        // 1 - 2 - 3 - 4 - 5
        // s       f
        //     s           f
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 获取中间节点或者中间节点的上节点的下一个节点
     *
     * @param head
     * @return
     */
    public static Node getMinOrDownMidPreNode(Node head) {
        // 至少3个节点以上
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        // 快慢指针
        Node fast = head.next;
        Node slow = head;
        // 1 - 2 - 3 - 4 - 5
        // s   f
        //     s       f
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
