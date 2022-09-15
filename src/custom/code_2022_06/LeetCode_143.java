package custom.code_2022_06;

import custom.base.ListNode;

/**
 * @ClassName LeetCode_143
 * @Author Duys
 * @Description
 * @Date 2022/6/30 11:17
 **/
// 143. 重排链表
// L0 → L1 → … → Ln - 1 → Ln
// L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
public class LeetCode_143 {

    public static void reorderList(ListNode head) {
        int hig = 0;
        if (head == null || head.next == null) {
            return;
        }
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            hig++;
        }
        ListNode[] listNodes = new ListNode[hig];
        cur = head;
        int index = 0;
        while (cur != null) {
            listNodes[index++] = cur;
            cur = cur.next;
        }
        // 1 2 3 4 5
        // 1 5 2 4 3
        ListNode[] newLists = new ListNode[hig];
        int l = 0;
        int r = listNodes.length - 1;
        index = 0;
        while (l < r) {
            newLists[index++] = listNodes[l++];
            newLists[index++] = listNodes[r--];
            if (l == r) {
                newLists[index++] = listNodes[l];
                break;
            }
        }
        for (int i = 0; i < newLists.length - 1; i++) {
            newLists[i].next = newLists[i + 1];
        }
        newLists[newLists.length - 1].next = null;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        reorderList(l1);
    }
}
