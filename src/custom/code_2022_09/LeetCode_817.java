package custom.code_2022_09;

import custom.base.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_817
 * @Author Duys
 * @Description
 * @Date 2022/9/23 14:24
 **/
public class LeetCode_817 {
    // 扫描链表
    // 长度最长的，从前往后，当前节点存在G种，但是下一个节点不在G中
    public int numComponents(ListNode head, int[] G) {
        if (head == null) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int g : G)
            set.add(g);
        int ans = 0;
        // 0-1-2-3-4-5
        // [0,3,1,4]
        // 0 1
        // 3 4
        while (head != null) {
            if (set.contains(head.val) && (head.next == null || !set.contains(head.next.val))) {
                ans++;
            }
            head = head.next;
        }
        return ans;
    }
}
