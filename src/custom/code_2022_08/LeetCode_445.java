package custom.code_2022_08;

import custom.base.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_445
 * @Author Duys
 * @Description
 * @Date 2022/8/11 15:12
 **/
// 445. 两数相加 II
// 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//你可以假设除了数字 0 之外，这两个数字都不会以零开头。
//链接：https://leetcode.cn/problems/add-two-numbers-ii
public class LeetCode_445 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> queue1 = new LinkedList<>();
        Deque<Integer> queue2 = new LinkedList<>();
        while (l1 != null) {
            queue1.offerLast(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            queue2.offerLast(l2.val);
            l2 = l2.next;
        }
        //
        int add = 0;
        Deque<Integer> queueL = queue1.size() >= queue2.size() ? queue1 : queue2;
        Deque<Integer> queueS = queue1.size() >= queue2.size() ? queue2 : queue1;
        Deque<Integer> queue3 = new LinkedList<>();
        while (!queueL.isEmpty()) {
            int c1 = !queueL.isEmpty() ? queueL.pollLast() : 0;
            int c2 = !queueS.isEmpty() ? queueS.pollLast() : 0;
            int cur = c1 + c2 + add;
            if (cur >= 10) {
                add = cur / 10;
                cur = cur % 10;
            } else {
                add = 0;
            }
            queue3.offerLast(cur);
        }
        if (add > 0) {
            queue3.offerLast(add);
        }
        if (queue3.isEmpty()) {
            return null;
        }
        ListNode root = new ListNode(queue3.pollLast());
        ListNode pre = root;
        while (!queue3.isEmpty()) {
            ListNode cur = new ListNode(queue3.pollLast());
            pre.next = cur;
            pre = cur;

        }
        return root;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(0);
//        r1.next = new ListNode(2);
//        r1.next.next = new ListNode(4);
//        r1.next.next.next = new ListNode(3);
        ListNode r2 = new ListNode(7);
        r2.next = new ListNode(3);
//        r2.next.next = new ListNode(4);
        ListNode listNode = addTwoNumbers(r1, r2);
        System.out.println();
    }
}
