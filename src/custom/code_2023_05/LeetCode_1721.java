package custom.code_2023_05;

import custom.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1721
 * @Author Duys
 * @Description
 * @Date 2023/5/9 16:41
 **/
// 1721. 交换链表中的节点
public class LeetCode_1721 {
    public static ListNode swapNodes(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int n = list.size();
        ListNode first = list.get(k - 1);
        ListNode second = list.get(n - k);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
        return head;
    }

    public static void main(String[] args) {
        // [7,9,6,6,7,8,3,0,9,5]
        //5
        ListNode listNode = new ListNode(100);
        listNode.next = new ListNode(90);
//        listNode.next.next = new ListNode(6);
//        listNode.next.next.next = new ListNode(6);
//        listNode.next.next.next.next = new ListNode(7);
//        listNode.next.next.next.next.next = new ListNode(8);
//        listNode.next.next.next.next.next.next = new ListNode(3);
//        listNode.next.next.next.next.next.next.next = new ListNode(0);
//        listNode.next.next.next.next.next.next.next.next = new ListNode(9);
//        listNode.next.next.next.next.next.next.next.next.next = new ListNode(5);
        ListNode listNode1 = swapNodes(listNode, 2);
        System.out.println(listNode1);
    }
}
