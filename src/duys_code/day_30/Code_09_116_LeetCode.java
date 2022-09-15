package duys_code.day_30;

/**
 * @ClassName Code_09_116_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 * @Date 2021/11/29 13:49
 **/
public class Code_09_116_LeetCode {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        MyQueue queue = new MyQueue();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每一层的当前节点的上一个节点
            Node pre = null;
            int curSize = queue.size;
            for (int i = 0; i < curSize; i++) {
                // 每一层的所有节点
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }
        return root;
    }

    // 一层一层的连接
    public static class MyQueue {
        public Node head;
        public Node tail;
        public int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(Node node) {
            size++;
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        public Node poll() {
            size--;
            Node ans = head;
            head = head.next;
            ans.next = null;
            return ans;
        }
    }
}
