package duys_code.day_38;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName Code_06_739_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/daily-temperatures/
 * @Date 2021/12/20 13:53
 **/
public class Code_06_739_LeetCode {

    // 单调栈
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek().get(0)] < temperatures[i]) {
                List<Integer> pop = stack.pop();
                for (int popI : pop) {
                    ans[popI] = i - popI;
                }
            }
            if (!stack.isEmpty() && temperatures[stack.peek().get(0)] == temperatures[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        return ans;
    }
}
