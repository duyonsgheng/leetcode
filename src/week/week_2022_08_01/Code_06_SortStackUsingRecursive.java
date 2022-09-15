package week.week_2022_08_01;

import java.util.Map;
import java.util.Stack;

/**
 * @ClassName Code_06_SortStackUsingRecursive
 * @Author Duys
 * @Description
 * @Date 2022/8/8 13:57
 **/
// 栈只提供push、pop、isEmpty三个方法
// 请完成无序栈的排序，要求排完序之后，从栈顶到栈底从小到大
// 只能使用栈提供的push、pop、isEmpty三个方法、以及递归函数
// 除此之外不能使用任何的容器，任何容器都不许，连数组也不行
// 也不能自己定义任何结构体
// 就是只用：
// 1) 栈提供的push、pop、isEmpty三个方法
// 2) 简单返回值的递归函数
public class Code_06_SortStackUsingRecursive {

    public static void sortStack(Stack<Integer> stack) {
        int deep = deep(stack);
        while (deep > 0) {
            int max = max(stack, deep);
            int k = times(stack, deep, max);
            dowm(stack, deep, max, k);
            deep -= k;
        }
    }

    // 返回栈的深度
    public static int deep(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        // 先弹出自己
        int cur = stack.pop();
        // 算后面的
        int next = deep(stack);
        // 把自己加回去
        stack.push(cur);
        return next + 1;
    }

    // 返回当前栈里剩余元素最大的
    // 从栈顶开始往下deep层
    public static int max(Stack<Integer> stack, int deep) {
        if (deep == 0) {
            return Integer.MIN_VALUE;
        }
        int num = stack.pop();
        int nextMax = max(stack, deep - 1);
        int max = Math.max(num, nextMax);
        stack.push(num);
        return max;
    }

    // 从栈顶开始往下deep层，当前栈出现了num这个数几次
    public static int times(Stack<Integer> stack, int deep, int num) {
        if (deep == 0) {
            return 0;
        }
        int cur = stack.pop();
        int nextTimes = times(stack, deep - 1, num);
        int times = nextTimes + (cur == num ? 1 : 0);
        stack.push(cur);
        return times;
    }

    // 从当前栈的顶部开始，往下deep层，已知最大是max，出现了K次
    // 请把这k个max沉底
    public static void dowm(Stack<Integer> stack, int deep, int max, int k) {
        if (deep == 0) {
            for (int i = 0; i < k; i++) {
                stack.push(max);
            }
        } else {
            int num = stack.pop();
            dowm(stack, deep - 1, max, k);
            if (num != max) {
                stack.push(num);
            }
        }
    }
}
