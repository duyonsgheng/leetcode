package custom.code_2023_01;

/**
 * @ClassName LeetCode_1803
 * @Author Duys
 * @Description
 * @Date 2023/1/5 13:47
 **/
// 1803. 统计异或值在范围内的数对有多少
public class LeetCode_1803 {
    private Node root = null;
    private int higBit = 14;

    public int countPairs(int[] nums, int low, int high) {
        return process(nums, high) - process(nums, low - 1);
    }

    public int process(int[] nums, int limit) {
        root = new Node();
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            add(nums[i - 1]);
            ans += get(nums[i], limit);
        }
        return ans;
    }

    public void add(int num) {
        Node cur = root;
        for (int i = higBit; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (cur.son[bit] == null) {
                cur.son[bit] = new Node();
            }
            cur = cur.son[bit];
            cur.sum++;
        }
    }

    public int get(int num, int index) {
        Node cur = root;
        int sum = 0;
        for (int i = higBit; i >= 0; i--) {
            int r = (num >> i) & 1;
            if (((index >> i) & 1) != 0) {
                if (cur.son[r] != null) {
                    sum += cur.son[r].sum;
                }
                if (cur.son[r ^ 1] == null) {
                    return sum;
                }
                cur = cur.son[r ^ 1];
            } else {
                if (cur.son[r] == null) {
                    return sum;
                }
                cur = cur.son[r];
            }
        }
        sum += cur.sum;
        return sum;
    }

    class Node {
        // 0 是左子树
        // 1 是右子树
        Node[] son = new Node[2];
        int sum = 0;

        public Node() {
            sum = 0;
        }
    }
}
