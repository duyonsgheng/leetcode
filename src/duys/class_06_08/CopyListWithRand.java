package duys.class_06_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CopyListWithRoand
 * @Author Duys
 * @Description 拷贝链表，不改变原来链表的结构
 * @Date 2021/6/9 16:20
 **/
public class CopyListWithRand {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 使用容器来处理
     *
     * @param head
     * @return
     */
    public static Node copy1(Node head) {
        // key   - 老节点
        // value - 新节点
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // 把cur节点的next节点找出来，赋给新节点的next
            map.get(cur).next = map.get(cur.next);
            // 把cur节点的rand节点找出来，赋给新节点的rand
            map.get(cur).rand = map.get(cur.rand);
        }
        return map.get(head);
    }

    public static Node copy2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // before  : 1 -> 2 -> 3
        // after   : 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        while (cur != null) {
            // 原链表的下一个 1
            next = cur.next;
            // 新链表的下一个 1‘
            cur.next = new Node(next.value);
            // 原来链表的下一个的下一个才是 之前的老数据所在位置
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        // 处理rand
        while (cur != null) {
            // 原来数据在当前位置的next 的 next位置
            next = cur.next.next;
            // 新的数据在next位置
            curCopy = cur.next;
            // 因为链表现在是 一个 老的node > 一个新的node
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        // 处理next
        cur = head;
        // 记录新链表的起始位置
        Node res = head.next;
        while (cur != null) {
            // 原来链表的数据
            next = cur.next.next;
            // 新链表的数据
            curCopy = cur.next;
            // 还原，把新得数据拿出来了，头部已经记录了
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
