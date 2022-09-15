package duys.class_06_08;

import java.util.Stack;

/**
 * @ClassName NodeIsHuiWen
 * @Author Duys
 * @Description 给定一个链表头部，判断当前链表的元素是不是满足回文
 * @Date 2021/6/9 14:46
 **/
public class NodeIsHuiWen {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 使用栈
     */
    public static boolean isHuiWen1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur.next != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 不使用栈
     */
    public static boolean isHuiWen2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 找到中点
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow 就是中点，从中点开始把中点后的节点依赖改变
        fast = slow.next; // 记录中点的下一个节点
        slow.next = null;// 把中点的下一下节点置空
        Node tem = null;
        // 开始把中点以后的节点指针相反
        // 1 -> 2 -> 3  -> 4  <- 3  <-  2 <- 1
        // step 1
        //                       slow  fast   tmp
        // step end
        //                                  slow  fast   tmp
        // 把slow.next = null
        // fast.next = tmp
        // fast.next = flow
        while (fast != null) {
            // 记录fast的下一个节点，因为马上要把fast的next指向slow了
            tem = fast.next;
            // 把fast的next指向slow
            fast.next = slow;
            // 把 slow 和 fast往后移动，然后继下一个位置交换
            slow = fast;
            fast = tem;
        }
        // 因为执行完交换后，slow来到最后一个位置，记录下来，后面需要换回来
        tem = slow;
        fast = head;
        boolean ans = true;
        while (fast != null && slow != null) {
            if (fast.value != slow.value) {
                ans = false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        // 恢复现场
        slow = tem.next;
        tem.next = null;
        // 1 -> 2 -> 3  -> 4  <-  3  ->  2 -> 1
        //                       fast  slow  tmp
        //                fast  slow   tmp
        while (slow != null) {
            fast = slow.next;
            slow.next = tem;
            tem = slow;
            slow = fast;
        }
        return ans;
    }
}
