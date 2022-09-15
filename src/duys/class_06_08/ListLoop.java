package duys.class_06_08;

/**
 * @ClassName ListLoop
 * @Author Duys
 * @Description 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，
 * 如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * @Date 2021/6/9 16:59
 **/
public class ListLoop {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getInstanceNode(Node head1, Node head2) {
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoopNode(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 找到链表入环的第一个节点，如果没有，返回null
     * 快慢指针
     * 快指针走两步
     * 慢指针一步
     * 第一次相遇的时候，慢指针卡住，快指针回到head，
     * 快然后一步一步走，慢指针也是保持一步一步走，下一次相遇的时候，就是入环节点
     *
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            // 如果快指针走向空了，说明没环
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 相等了
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 两个无环的链表，求相交的位置
     */
    public static Node noLoopNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int step = 0;
        // 计算链表1的长度
        while (cur1.next != null) {
            step++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            step--;
            cur2 = cur2.next;
        }
        // 如果尾巴都不一样了，肯定不相交
        if (cur1 != cur2) {
            return null;
        }
        // step 目前代表了两个链表的高度差值
        // 如果step大于0，那么head1就是长链表，cur1就是哪个长链表的头部
        cur1 = step > 0 ? head1 : head2;
        // 如果当前的cur1是head1了，说明cur1是长的，
        cur2 = cur1 == head1 ? head2 : head1;
        step = Math.abs(step);
        // 长的先走step步
        // 8 - 1 - 2 - 3 - 4 - 5 - 6
        //         7 - 3 - 4 - 5 - 6
        while (step != 0) {
            step--;
            cur1 = cur1.next;
        }

        // 然后一起走，上面已经判断了，如果cur1 和cur2 都到尾巴了都不想等，之前就会返回null
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个有环链表，返回相交的第一个节点
     * 情况：
     * 1.两个环相交节点相同
     * 2.两个环相交节点不相同
     * 2.1 两个环不相交
     * 2.2 两个环相交（环上相交）
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        // 相交-且相等，都是入环节点，那么必定环是重合的，求相交的位置，跟上面无环链表求相交位置一样
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int step = 0;
            while (cur1 != loop1) {
                step++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                step--;
                cur2 = cur1.next;
            }
            // 如果step>0 表示cur1长，因为后面的迭代是谁长就把谁的头变成cur1，因为cur1要先走step步
            cur1 = step > 0 ? head1 : head2;
            // 短的赋给cur2，因为cur2是跟着走的，不能是长的哪一个
            cur2 = cur1 == head1 ? head2 : head1;
            step = Math.abs(step);
            // 长的先走step步
            while (step != 0) {
                step--;
                cur1 = cur1.next;
            }
            // 同时走，直到相等，相等就相交
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        // 不相等，分两种情况了
        else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                // 2. 如果遍历过程中找到了，那么随便返回哪一个都可以
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            // 1. 如果cur1遍历一圈都没找到loop2 则不相交
            return null;
        }
    }
}
