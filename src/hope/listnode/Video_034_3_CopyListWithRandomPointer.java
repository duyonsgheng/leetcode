package hope.listnode;


/**
 * @author Mr.Du
 * @ClassName Video_034_3_CopyListWithRandomPointer
 * @date 2023年08月16日
 */
// 138. 复制带随机指针的链表
// https://leetcode.cn/problems/copy-list-with-random-pointer/
public class Video_034_3_CopyListWithRandomPointer {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int v) {
            val = v;
        }
    }

    // 不适用额外的辅助空间
    // 先把原链表复制了，把复制的节点放到挨着原节点的后面
    // 然后复制random指针
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head, next = null;
        // 1 -> 2 -> 3 ...
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3' ....
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // 设置每一个新节点的random指针
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        // 链表分离
        Node ans = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return ans;
    }
}
