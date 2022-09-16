package custom.code_2022_09;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName LeetCode_735
 * @Author Duys
 * @Description
 * @Date 2022/9/16 9:27
 **/
// 735. 行星碰撞
public class LeetCode_735 {
    public static void main(String[] args) {
        int[] arr = {-2, 2, 1, -2};
        asteroidCollision(arr);
    }

    public static int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length <= 1) {
            return asteroids;
        }
        Deque<Integer> stack = new LinkedList<>();
        // 10,2,-5
        for (int i : asteroids) {
            boolean isAdd = true;
            while (isAdd && i < 0 && !stack.isEmpty() && stack.peekLast() > 0) {
                isAdd = stack.peekLast() < -i; // 为false，说明当前要爆炸
                if (stack.peekLast() <= -i) {
                    stack.pollLast();
                }
            }
            if (isAdd) {
                stack.addLast(i);
            }
        }
        int[] ans = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            ans[i++] = stack.pollFirst();
        }
        return ans;
    }
}
