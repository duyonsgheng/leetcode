package duys_code.day_06;

/**
 * @ClassName Code_03_MaxNumXor
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/
 * @Date 2021/9/26 16:53
 **/
public class Code_03_MaxNumXor {

    /**
     * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
     * 输出：[3,3,7]
     * 解释：
     * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
     * 2) 1 XOR 2 = 3.
     * 3) 5 XOR 2 = 7.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int[] maximizeXor(int[] nums, int[][] queries) {
        if (nums == null || nums.length < 1 || queries == null || queries.length < 1) {
            return new int[]{-1};
        }
        int N = nums.length;
        NodeTree nodeTree = new NodeTree();
        for (int i = 0; i < N; i++) {
            nodeTree.add(nums[i]);
        }
        int M = queries.length;
        int[] ans = new int[M];
        for (int i = 0; i < M; i++) {
            ans[i] = nodeTree.getNearTargetM(queries[i][0], queries[i][1]);
        }
        return ans;
    }


    // 对我们的前面的前缀树进行改造
    public static class Node {
        public int min;
        public Node[] nexts;

        public Node() {
            nexts = new Node[2];
            min = Integer.MAX_VALUE;
        }
    }

    public static class NodeTree {
        public Node head = new Node();


        public void add(int num) {
            Node cur = head;
            // 设置最小值
            head.min = Math.min(head.min, num);
            // 都是正数。所以只需要0-30位
            for (int mov = 30; mov >= 0; mov--) {
                // 当前num的第mov位置状态
                int path = (num >> mov) & 1;
                // 是否有当前状态的路，
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                // 因为mov要开始向右移动了
                cur = cur.nexts[path];
                // 设置当前位置的最小值
                cur.min = Math.min(cur.min, num);
            }
        }

        public int getNearTargetM(int num, int m) {
            if (head.min > m) {
                return -1;
            }
            Node cur = head;
            int ans = 0;
            for (int mov = 30; mov >= 0; mov--) {
                int path = (num >> mov) & 1;
                int best = path ^ 1;
                best ^= (cur.nexts[best] == null || cur.nexts[best].min > m) ? 1 : 0;
                ans |= (path ^ best) << mov;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }
}
