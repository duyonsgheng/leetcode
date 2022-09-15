package custom.code_2022_04;

import java.util.LinkedList;

/**
 * @ClassName LeetCode_24
 * @Author Duys
 * @Description
 * @Date 2022/5/9 15:05
 **/
// 两两交换链表中的节点
// 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
public class LeetCode_24 {

    //  1  2  3  4
    //  2  1  4  3
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedList<ListNode> listNodes = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            listNodes.add(cur);
            cur = cur.next;
        }
        int index = 0;
        ListNode[] arr = new ListNode[listNodes.size()];
        for (int i = 0; i < listNodes.size(); i += 2) {
            ListNode l1 = listNodes.get(i);
            ListNode l2 = null;
            if (i + 1 < listNodes.size()) {
                l2 = listNodes.get(i + 1);
            }
            if (l2 == null) {
                arr[index++] = l1;
            } else {
                arr[index++] = l2;
                arr[index++] = l1;
            }
        }
        arr[arr.length-1].next = null;
        for (int i = arr.length - 1; i >= 1; i--) {
            arr[i - 1].next = arr[i];
        }
        return arr[0];
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        ListNode listNode = swapPairs(l1);
        ListNode head = listNode;
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // str -源字符串
/*    public static String process(String str, int mod) {
        // 以 3：4：3
        // 算出前后指针分别走多少
        int before = 3;
        int mid = 4;
        int after = 3;
        int index_before = -1;
        int index_after = -1;
        int len = str.length();
    }*/
}
