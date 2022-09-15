package custom.code_2022_07;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_331
 * @Author Duys
 * @Description
 * @Date 2022/7/19 16:43
 **/
// 331. 验证二叉树的前序序列化
// 序列化二叉树的一种方法是使用 前序遍历 。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
public class LeetCode_331 {

    // 不能构建树
    // 一个 # 消耗一个槽位
    // 一个数字增加两个槽位
    public static boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                // 读取一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "9,#,#";
        System.out.println(isValidSerialization(str));
    }
}
