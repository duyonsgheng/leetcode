package custom.code_2022_11;

import custom.base.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1171
 * @Author Duys
 * @Description
 * @Date 2022/11/22 14:50
 **/
public class LeetCode_1171 {

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode listNode = new ListNode(0);
        listNode.next = head;
        // 把连续为0的节点覆盖掉
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        for (ListNode d = listNode; d != null; d = d.next) {
            sum += d.val;
            map.put(sum, d);
        }
        sum = 0;
        for (ListNode d = listNode; d != null; d = d.next) {
            sum += d.val;
            d.next = map.get(sum).next;
        }
        return listNode.next;
    }
}
