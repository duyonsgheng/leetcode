package duys_code.day_06;

/**
 * @ClassName Code_02_MaxSum
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * @Date 2021/9/26 16:22
 **/
public class Code_02_MaxSum {

    /**
     * 题意：数组中所有数都异或起来的结果，叫做异或和
     * 给定一个数组arr，想知道arr中哪两个数的异或结果最大
     * 返回最大的异或结果
     */
    public static int findMaximumXOR(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        NodeTree nodeTree = new NodeTree();
        nodeTree.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            ans = Math.max(ans, nodeTree.getMaxNumXor(arr[i]));
            nodeTree.add(arr[i]);
        }
        return ans;
    }

    public static class Node {
        public Node[] next = new Node[2];
    }

    public static class NodeTree {
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                // 取出int的每一位状态
                int path = (num >> i) & 1;
                cur.next[path] = cur.next[path] == null ? new Node() : cur.next[path];
                cur = cur.next[path];
            }
        }

        public int getMaxNumXor(int num) {
            Node cur = head;
            int res = 0;
            for (int mov = 31; mov >= 0; mov--) {
                int path = (num >> mov) & 1;
                // 除了符号位，其他位置都希望遇到与自己相反的
                int bast = mov == 31 ? path : (path ^ 1);
                // 实际的
                bast = cur.next[bast] != null ? bast : (bast ^ 1);
                res |= (path ^ bast) << mov;
                cur = cur.next[bast];
            }
            return res;
        }
    }

}
