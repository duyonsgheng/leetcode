package duys.class_06_15;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LevelTraversalBT
 * @Author Duys
 * @Description 一棵树，按层从左到右遍历---按层遍历
 * @Date 2021/6/17 16:40
 **/
public class LevelTraversalBT {

    /**
     * 树的遍历：按层从左到右的遍历
     * 1.使用队列，先把头压入队列，然后进入循环，先压入左边，然后压入右边
     * 2.先压入队列的先出去，出去就打印，然后再有左边压入左边，有右边压入右边的
     */
    public static void level(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }
}
