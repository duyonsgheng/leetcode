package duys_code.day_47;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName Code_03_428_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/serialize-and-deserialize-n-ary-tree/
 * @Date 2021/10/20 15:49
 **/
public class Code_03_428_LeetCode {
    /**
     * 多叉树的序列化
     */
    public static class Node {
        public int val;
        public List<Node> children; // 多叉树

        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static class Codec {

        // 1,[,2,[,3,4,],5,[,6,7,],]
        public static String serialize(Node root) {
            if (root == null) {
                return "#";
            }
            StringBuilder builder = new StringBuilder();
            serial(builder, root);
            return builder.toString();
        }

        public static Node deSerialize(String s) {
            if ("#".equals(s)) {
                return null;
            }
            // 先把头节点搞出来
            String[] split = s.split(",");
            Queue<String> queue = new LinkedList<>();
            for (String sp : split) {
                queue.offer(sp);
            }
            return deSerial(queue);
        }


        private static void serial(StringBuilder builder, Node cur) {
            builder.append(cur.val + ",");
            if (!cur.children.isEmpty()) {
                builder.append("[,");
                for (Node next : cur.children) {
                    serial(builder, next);
                }
                builder.append(",]");
            }
        }

        // 1,[,2,[,3,4,],5,[,6,7,],]
        // 1 [ 2 [ 3 4 ] 5 [ 6 7 ] ]
        private static Node deSerial(Queue<String> queue) {
            Node cur = new Node(Integer.valueOf(queue.poll()));
            cur.children = new ArrayList<>();
            if (!queue.isEmpty() && queue.peek().equals("[")) {
                queue.poll();
                while (!queue.peek().equals("]")) {
                    Node child = deSerial(queue);
                    cur.children.add(child);
                }
                queue.poll();
            }
            return cur;
        }
    }
}
