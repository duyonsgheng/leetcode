package duys_code.day_34;

/**
 * @ClassName Code_07_328_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/odd-even-linked-list/
 * @Date 2021/12/7 14:21
 **/
public class Code_07_328_LeetCode {
    public static class ListNode {
        int val;
        ListNode next;
    }

    // 把链表中 奇数编号节点和偶数编号节点放在一起
    public ListNode oddEvenList(ListNode head) {
        ListNode firstOdd = null; // 奇数开始
        ListNode firstEven = null; // 偶数的开始
        ListNode even = null;
        ListNode odd = null;
        ListNode next = null;
        int count = 1;
        while (head != null) {
            next = head.next;
            head.next = null;//断开链接
            if ((count & 1) == 1) { // 编号是奇数
                firstOdd = firstOdd == null ? head : firstOdd;
                if (odd != null) {
                    odd.next = head;
                }
                odd = head;
            } else { // 编号是偶数
                firstEven = firstEven == null ? head : firstEven;
                if (even != null) {
                    even.next = head;
                }
                even = head;
            }
            // 节点编号往下走
            count++;
            head = next;
        }
        if (odd != null) {
            odd.next = firstEven;
        }
        return firstOdd != null ? firstOdd : firstEven;
    }
}
