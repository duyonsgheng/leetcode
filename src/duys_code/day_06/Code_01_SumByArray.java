package duys_code.day_06;

/**
 * @ClassName Code_01_SumByArray
 * @Author Duys
 * @Description
 * @Date 2021/9/24 17:31
 **/
public class Code_01_SumByArray {
    /**
     * 题意：
     * 数组中所有数都异或起来的结果，叫做异或和
     * 给定一个数组arr，返回arr的最大子数组异或和
     */
    // 难点：前缀和我们一定可以算出来，一个数加前缀和只可能变大，那么一个数异或上一个数，可能变大可能变小
    // 异或 ^ : 无进位相加
    // 与 &:  同1为1
    // 或 |: 有1就是1

    // 先来一个暴力解答 O(N^2)
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // 前缀异或和数组
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        for (int i = 1; i < eor.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i <= j; i++) {// 尝试j位置的时候，j之前所有的情况，0~j ,1~j,1~j....
                max = Math.max(max, i == 0 ? eor[j] : eor[i - 1] ^ eor[j]);
            }
        }
        return max;
    }

    // 使用前缀树 - O(N)
    public static int maxSum2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        NodeTree nodeTree = new NodeTree();
        int ans = 0;
        for (int j = 0; j < arr.length; j++) {
            ans ^= arr[j];
            max = Math.max(max, nodeTree.getMaxNum(ans));
            nodeTree.add(ans);
        }
        return max;
    }

    /**
     * 当前题意是异或和，也就是无尽为相加，每一位0或者1，如果当前是1，那么我期待我异或的是0，这样子我就可以保持当前为继续是1
     */
    public static class Node {
        // 0 和 1嘛，如果next[0] !=null 表示到0的有路，否贼没有
        public Node[] next = new Node[2];
    }

    // 根据当前题意来定制一个前缀树
    public static class NodeTree {
        // 头节点。我们用null 和 非空来表示有没有去往下一个节点的路
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int mov = 31; mov >= 0; mov--) {
                // 每一个位置看看是1还是0
                int path = num >> mov & 1;
                // 没有就创建一条路，有就复用
                cur.next[path] = cur.next[path] == null ? new Node() : cur.next[path];
                cur = cur.next[path];
            }
        }

        // 返回当前树中存在的数，谁和num异或最大
        public int getMaxNum(int num) {
            Node cur = head;
            int ans = 0;
            for (int mov = 31; mov >= 0; mov--) {
                int state = num >> mov & 1;
                // 如果是第31位，是符号位，我期望遇到的是相同的，因为都是1 异或后就是0，如果是其他位，我期望遇到跟我相反的
                int hope = mov == 31 ? state : state ^ 1;

                // 我实际遇到的，如果我的hope有路，那么就拿hope，如果没有，那么就只能去相反的地方了
                int cus = cur.next[hope] != null ? hope : hope ^ 1;
                hope = cus;
                ans |= (hope ^ state) << mov;
                cur = cur.next[hope];
            }
            return ans;
        }
    }

}
