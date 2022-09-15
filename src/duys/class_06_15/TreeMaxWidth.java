package duys.class_06_15;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName TreeMaxWidth
 * @Author Duys
 * @Description 求一颗树的最大宽度(就是子节点最多的那一层 ， 宽度)
 * @Date 2021/6/22 17:57
 **/
public class TreeMaxWidth {

    /**
     * 思路：
     * 1.按层遍历，同时记录下当前层的节点
     * 2.在计算第一层的时候，实际上已经把第二层的都给压入队列了
     */
    /**
     *              a
     *          b       c
     *        e  f    g   h
     */
    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 因为是按层遍历，所以记录当前层最右的节点
        Node nextEnd = null; //  下一层，最右的节点
        int max = 0; // 全局的层节点数最多的
        int curLevelNodes = 0; // 当前层的节点数
        // 在处理头节点的时候，这时候，头节点的左右节点都已经压入队列了。
        // 同时呢，把nextEnd 更新为子节点的中的最右节点了
        // 每一层结束的时候，会把curEnd更新为当前的nextEnd，记录一下下一层的结束位置。
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 每当有节点出队列，当前层节点数++
            curLevelNodes++;
            // 从左到有把当前层节点放入队列
            if (cur.left != null) {
                queue.add(cur.left);
                // 把下一层的结束位置先给当前节点的左子节点
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                // 把下一层的结束位置先给当前节点的左子节点
                nextEnd = cur.right;
            }
            // 表示当前层结束
            if (cur == curEnd) {
                max = Math.max(curLevelNodes, max);
                // 当前层结束了，该去下一层了，那么情况当前层的节点数，下一层遍历使用
                curLevelNodes = 0;
                curEnd = nextEnd;
            }

        }
        return max;
    }


}
