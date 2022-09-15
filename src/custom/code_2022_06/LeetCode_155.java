package custom.code_2022_06;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_155
 * @Author Duys
 * @Description
 * @Date 2022/6/9 16:32
 **/
// 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
// https://leetcode.cn/problems/min-stack/
public class LeetCode_155 {

    public static class MinStack {
        Deque<Integer> stack;
        Deque<Integer> minStack;

        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(minStack.peek(), val));
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
