package duys.class_07_01;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName GrahpBFS
 * @Author Duys
 * @Description 图的宽度优先遍历
 * @Date 2021/7/2 10:36
 **/
public class GrahpBFS {

    /**
     * 和二叉树的宽度优先遍历一样，但是需要增加一个set 来保证每一个点只被加载一次
     */

    public static void bfs(Node start) {
        if (start == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        Set<Node> set = new HashSet<>();
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node next : poll.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
