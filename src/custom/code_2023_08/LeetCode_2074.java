package custom.code_2023_08;

import custom.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2074
 * @date 2023年08月09日
 */
// 2074. 反转偶数长度组的节点
// https://leetcode.cn/problems/reverse-nodes-in-even-length-groups/
public class LeetCode_2074 {
    public static ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode cur = head;
        int flag = 0;

        for (int j = 1; flag == 0; j++) {

            int count = 0;// 统计结点个数
            ListNode reverse_head = root;//执行反转时候，反转区间的左端点为prehead
            ListNode reverse_tail = null;
            while (cur != null && count < j) {
                reverse_tail = cur;
                root = root.next;//不执行反转时候，root++以确定下一次反转区间的左端点
                cur = cur.next;
                count++;
                if (cur == null) {
                    flag = 1;//flag==1时候跳出大循环
                }
            }
            if (count % 2 == 0) {
                root = reverse(reverse_head, reverse_tail);//当执行反转操作时候，prehead需要重新设置，更新前:反转前区间的右端点 更新后:反转后的右端点
            }

        }
        return head;
    }

    //反转prehead到tail区间的结点 结点顺序: prehead->(head->...->tail)
    public static ListNode reverse(ListNode prehead, ListNode tail) {
        ListNode head = prehead.next;//反转区间内的第一个结点
        //结点顺序prehead head tail
        ListNode temp = head;//用于遍历
        while (temp != tail) {
            ListNode cur = temp;
            temp = temp.next;
            cur.next = tail.next;
            tail.next = cur;
        }
        prehead.next = tail;//左边界连接新头结点
        return head;//返回反转后的最后结点 提供给prehead
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode cur = root;
        for (int i = 2; i <= 10; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode node = reverseEvenLengthGroups(root);
        System.out.println();
    }

}
