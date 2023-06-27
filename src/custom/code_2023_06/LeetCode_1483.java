package custom.code_2023_06;

/**
 * @ClassName LeetCode_1483
 * @Author Duys
 * @Description
 * @Date 2023/6/12 8:56
 **/
// 1483. 树节点的第 K 个祖先
public class LeetCode_1483 {
    class TreeAncestor {
        private int[][] parents;

        public TreeAncestor(int n, int[] parent) {
            int len = 32 - Integer.numberOfLeadingZeros(n);// n的二进制有效长度
            parents = new int[n][len];
            // 当前的节点i的父节点就是parent[i]
            for (int i = 0; i < n; i++) {
                parents[i][0] = parent[i];
            }
            for (int i = 0; i < len - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int c = parents[x][i];
                    parents[x][i + 1] = c < 0 ? -1 : parents[c][i];
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int len = 32 - Integer.numberOfLeadingZeros(k);// k的二进制有效长度
            for (int i = 0; i < len; i++) {
                if (((k >> i) & 1) != 0) {
                    node = parents[node][i];
                    if (node < 0) {
                        break;
                    }
                }
            }
            return node;
        }
    }

}
