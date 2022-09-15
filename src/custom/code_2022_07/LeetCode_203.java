package custom.code_2022_07;

import custom.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_203
 * @Author Duys
 * @Description
 * @Date 2022/7/6 14:30
 **/
// 203. 移除链表元素
// 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
public class LeetCode_203 {

    public ListNode removeElements1(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        List<ListNode> listNodes = new ArrayList<>();
        while (cur != null) {
            if (cur.val != val) {
                listNodes.add(cur);
            }
            cur = cur.next;
        }
        if (listNodes.size() == 0) {
            return null;
        }
        for (int i = 0; i < listNodes.size() - 1; i++) {
            listNodes.get(i).next = listNodes.get(i + 1);
        }
        listNodes.get(listNodes.size() - 1).next = null;
        return listNodes.get(0);
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
