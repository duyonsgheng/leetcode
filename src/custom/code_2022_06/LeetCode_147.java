package custom.code_2022_06;

import custom.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_147
 * @Author Duys
 * @Description
 * @Date 2022/6/30 13:16
 **/
//147. 对链表进行插入排序
// 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
public class LeetCode_147 {

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> listNodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            listNodes.add(cur);
            cur = cur.next;
        }
        ListNode[] arr = listNodes.toArray(new ListNode[1]);
        for (int i = 1; i < arr.length; i++) {
            int tmp = i;
            while (tmp > 0 && arr[tmp].val < arr[tmp - 1].val) {
                swap(tmp, tmp - 1, arr);
                tmp--;
            }
        }
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        arr[arr.length - 1].next = null;
        return arr[0];
    }

    public static void swap(int i, int j, ListNode[] arr) {
        ListNode tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
