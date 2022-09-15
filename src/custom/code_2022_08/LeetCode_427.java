package custom.code_2022_08;

/**
 * @ClassName LeetCode_427
 * @Author Duys
 * @Description
 * @Date 2022/8/9 17:22
 **/
//427. 建立四叉树
public class LeetCode_427 {
    public static void main(String[] args) {
        int[][] arr = {{0, 1}, {1, 0}};
        construct(arr);
    }

    public static Node construct(int[][] grid) {
        // 先将当前矩阵划分为4个大的模块，然后每一个模块跑递归去
        int n = grid.length;
        int m = grid[0].length;
        if (n != m) {
            return null;
        }
        int[][] sum = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return dfs(0, 0, n - 1, n - 1, sum, grid);
    }

    // 两个顶点 左上和右下
    public static Node dfs(int x1, int y1, int x2, int y2, int[][] sum, int[][] arr) {
        int cur = sum[x2 + 1][y2 + 1] - sum[x1][y2 + 1] - sum[x2 + 1][y1] + sum[x1][y1];
        int dx = x2 - x1 + 1;
        int dy = y2 - y1 + 1;
        int curTotal = dx * dy;
        if (cur == 0 || cur == curTotal) {
            return new Node(arr[x1][y1] == 1, true);
        }
        Node curNode = new Node(arr[x1][y1] == 1, false);
        /**
         * x1 y1    top
         *
         *          dot
         *
         *          bott       x2 y2
         */
        curNode.topLeft = dfs(x1, y1, x1 + dx / 2 - 1, y1 + dy / 2 - 1, sum, arr);
        curNode.topRight = dfs(x1, y1 + dy / 2, x1 + dx / 2 - 1, y2, sum, arr);
        curNode.bottomLeft = dfs(x1 + dx / 2, y1, x2, y1 + dy / 2 - 1, sum, arr);
        curNode.bottomRight = dfs(x1 + dx / 2, y1 + dy / 2, x2, y2, sum, arr);
        return curNode;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

}
