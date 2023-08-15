package custom.code_2023_08;

import custom.base.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2058
 * @date 2023年08月04日
 */
// 2058. 找出临界点之间的最小和最大距离
// https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
public class LeetCode_2058 {
    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        //
        List<Integer> index = new ArrayList<>();
        ListNode root = head;
        while (root != null) {
            index.add(root.val);
            root = root.next;
        }
        if (index.size() <= 2) {
            return new int[]{-1, -1};
        }
        PriorityQueue<Integer> cnt = new PriorityQueue<>();
        for (int i = 1; i < index.size() - 1; i++) {
            if (index.get(i) > index.get(i - 1) && index.get(i) > index.get(i + 1)) {
                cnt.add(i);
            } else if (index.get(i) < index.get(i - 1) && index.get(i) < index.get(i + 1)) {
                cnt.add(i);
            }
        }
        if (cnt.size() < 2) {
            return new int[]{-1, -1};
        }
        int minA = Integer.MAX_VALUE;
        int last = cnt.poll(), max = last, min = last;
        while (!cnt.isEmpty()) {
            int cur = cnt.poll();
            minA = Math.min(Math.abs(cur - last), minA);
            max = Math.max(cur, max);
            min = Math.min(cur, min);
            last = cur;
        }
        return new int[]{minA, max - min};
    }

    public static void main(String[] args) {
        // 6,8,4,1,9,6,6,10,6
        ListNode node = new ListNode(6);
        node.next = new ListNode(8);
        node.next.next = new ListNode(4);
        node.next.next.next = new ListNode(1);
        node.next.next.next.next = new ListNode(9);
        node.next.next.next.next.next = new ListNode(6);
        node.next.next.next.next.next.next = new ListNode(6);
        node.next.next.next.next.next.next.next = new ListNode(10);
        node.next.next.next.next.next.next.next.next = new ListNode(6);
        int[] ints = nodesBetweenCriticalPoints(node);
        System.out.println(ints[0] + " " + ints[1]);
    }
}
