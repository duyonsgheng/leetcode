package duys.class_07_01;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @ClassName GrahpDFS
 * @Author Duys
 * @Description 图的深度优先遍历
 * @Date 2021/7/2 10:45
 **/
public class GrahpDFS {

    /**
     * 从一条路开始，入栈就打印，直到此条路遍历结束，然后弹出已经走得节点，看看有没有新得路没走
     *
     * @param start
     */
    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        // 当前的栈，就放着遍历过的节点整条路径
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        set.add(start);
        stack.push(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            // 弹出之前，所有的邻居都过一次，如果有邻居没有遍历，就遍历，然后加入遍历
            for (Node next : pop.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    // 这里是核心，因为当前可能存在其他得邻居节点没遍历，
                    //后面回溯得时候，需要从其他之路出发，所以需要压一次
                    stack.push(pop);
                    // 压入没有遍历过的邻居节点
                    stack.push(next);
                    System.out.println(next.value);
                    // 这里也是核心，只要遍历到某一个没有遍历过的邻居节点，先就不遍历了，回到栈中继续
                    break;
                }
            }
        }
    }
}
