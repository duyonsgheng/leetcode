package custom.code_2022_06;

/**
 * @ClassName LeetCode_141
 * @Author Duys
 * @Description
 * @Date 2022/6/9 16:15
 **/
// 141. 环形链表
// 给你一个链表的头节点 head ，判断链表中是否有环。
//如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
//评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
//如果链表中存在环，则返回 true 。 否则，返回 false 。
//链接：https://leetcode.cn/problems/linked-list-cycle
public class LeetCode_141 {

    // 快慢指针
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode low = head.next;
        ListNode fast = head.next.next;
        while (low != fast) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            low = low.next;
            fast = fast.next.next;
        }
        return low != null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
