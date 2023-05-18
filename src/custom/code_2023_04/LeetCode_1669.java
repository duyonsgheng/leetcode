package custom.code_2023_04;

import custom.base.ListNode;

/**
 * @ClassName LeetCode_1669
 * @Author Duys
 * @Description
 * @Date 2023/4/25 14:21
 **/
// 1669. 合并两个链表
public class LeetCode_1669 {
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // 0 1 2 3 4 5 6
        ListNode cur = list1;
        ListNode start = null;
        ListNode end = null;
        int index = 0;
        while (cur != null) {
            index++;
            if (index == a) {
                start = cur;
            }
            cur = cur.next;
            if (index == b) {
                end = cur != null && cur.next != null ? cur.next : null;
            }
        }
        ListNode cur2 = list2;
        start.next = list2;
        while (cur2 != null && cur2.next != null) {
            cur2 = cur2.next;
        }
        cur2.next = end;
        return list1;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(0);
        list1.next = new ListNode(1);
        list1.next.next = new ListNode(2);
        list1.next.next.next = new ListNode(3);
        list1.next.next.next.next = new ListNode(4);
        list1.next.next.next.next.next = new ListNode(5);
        ListNode list2 = new ListNode(1000);
        list2.next = new ListNode(10001);
        list2.next.next = new ListNode(10002);
        ListNode listNode = mergeInBetween(list1, 3, 4, list2);
        System.out.println(listNode);
    }
}
