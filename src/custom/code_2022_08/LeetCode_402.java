package custom.code_2022_08;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_402
 * @Author Duys
 * @Description
 * @Date 2022/8/9 11:02
 **/
// 402. 移掉 K 位数字
// 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
// 请你以字符串形式返回这个最小的数字。
public class LeetCode_402 {

    public static String removeKdigits(String num, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            char cur = num.charAt(i);
            // 如果当前栈顶是比当前要大的，需要弹出，
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > cur - '0') {
                deque.pollLast();
                k--;
            }
            deque.offerLast(cur - '0');
        }
        // 如果k还剩余，那么就需要弹出
        if (k > 0) {
            for (int i = 0; i < k; i++) {
                deque.pollLast();
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty() && deque.peekFirst() == 0) {
            deque.pollFirst();
        }
        while (!deque.isEmpty()) {
            builder.append(deque.pollFirst());
        }
        return builder.length() <= 0 ? "0" : builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("10", 2));
    }
}
