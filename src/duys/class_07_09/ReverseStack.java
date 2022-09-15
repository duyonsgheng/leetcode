package duys.class_07_09;

import java.util.Stack;

/**
 * @ClassName ReverseStack
 * @Author Duys
 * @Description 栈逆序
 * @Date 2021/7/9 17:56
 **/
public class ReverseStack {

    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 主函数 第一步  i = 3
        // 第二步 i = 2
        // 第三步 i = 1
        // 第三步结束往上返回 , 压 1 进栈
        // 第二部结束往上返回, 压2进栈
        // 第一步结束往上走，压3进栈，结束，栈逆序
        int i = process(stack);
        reverseStack(stack);
        stack.push(i);
    }

    //

    // 第一步 弹出栈顶 1  re = 1 , last = 第二部结果
    // 第二步 弹出栈顶 2  re = 2 , last = 第三步结果
    // 第三步 弹出栈顶 3  re = 3,  走 base返回3

    // 第三步 结束后往上返回，第二步拿到结果了，那么把re = 2 压栈  返回last = 3
    // 第二部 结束后往上返回，第一步拿到结果了，那么把re = 1 压栈  返回last = 3
    // 整个流程结束，返回主函数
    public static int process(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = process(stack);
            stack.push(result);
            return last;
        }
    }
}
