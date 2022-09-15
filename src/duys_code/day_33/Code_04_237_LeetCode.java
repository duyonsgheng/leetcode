package duys_code.day_33;

/**
 * @ClassName Code_04_237_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @Date 2021/12/6 13:51
 **/
public class Code_04_237_LeetCode {

    public static class ListNode {
        int val;
        ListNode next;
    }

    // 删除当前节点，入参就是当前节点，就是拿着后面的节点覆盖调
    // 4 1 5 7 8     5
    // 4 1 7 8
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
