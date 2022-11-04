package custom.code_2022_11;

import custom.base.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName LeetCode_1019
 * @Author Duys
 * @Description
 * @Date 2022/11/3 14:58
 **/
// 1019. 链表中的下一个更大节点
public class LeetCode_1019 {
    public static int[] nextLargerNodes(ListNode head) {
        // 先记录
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int n = list.size();
        int[] arr = new int[n];
        // 单调栈吧
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            // 栈不为空，并且当前数是大于栈顶的，弹出前面的并且设置值
            while (!stack.isEmpty() && list.get(i) > list.get(stack.peek())) {
                arr[stack.pop()] = list.get(i);
            }
            stack.push(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 7, 5, 1, 9, 2, 5, 1};
        ListNode root = new ListNode(1);
        ListNode head = root;
        for (int i = 1; i < arr.length; i++) {
            root.next = new ListNode(arr[i]);
            root = root.next;
        }
        int[] ints = nextLargerNodes(head);
        System.out.println();
    }
}
