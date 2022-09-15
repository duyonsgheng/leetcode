package duys.class_06_30;

/**
 * @ClassName NumberOfIslands_03
 * @Author Duys
 * @Description 并查集实现的方式做2
 * @Date 2021/7/1 10:46
 **/
public class NumberOfIslands_03 {

    /**
     * 例如 char[4][6] 那么准备一个 24长度的数组，(0,0)认为之数组下标为0的位置
     * (0,5)认为是数组下标为5的位置 (1,0)认为是下标是6的位置，所以原二维数组中[i][j]对应位置在数组中的位置，关系 i*列数+j
     */

    public static int numIslands(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind un = new UnionFind(board);
        // 第0行
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                un.union(i - 1, 0, i, 0);
            }
        }
        // 第0列
        for (int j = 1; j < col; j++) {
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                un.union(0, j - 1, 0, j);
            }
        }
        //
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    // 左
                    if (board[i - 1][j] == '1') {
                        un.union(i, j, i - 1, j);
                    }
                    // 右
                    if (board[i][j - 1] == '1') {
                        un.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return un.sets;
    }

    public static class UnionFind {
        //parent[i] = k : i 的父级是K
        private int[] parent;
        // size[i] =  k : i 所在集合的大小，当i是代表节点才有意义
        private int[] size;
        // 做路径压缩使用
        private int[] help;

        private int col;// 列数
        //
        private int sets;

        public UnionFind(char[][] board) {
            col = board[0].length;// 列数
            sets = 0;
            int row = board.length;
            // 并查集里面的数组需要囊括二维数组里面所有的数据
            int len = col * row;

            parent = new int[len];
            help = new int[len];
            size = new int[len];
            // 初始化
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    // 二维数组中，满足要求的 在并查集中建立模型
                    if (board[i][j] == '1') {
                        // 算出在二维数组中位置对应在并查集数组中的位置
                        int index = index(i, j);
                        // 初始化时，满足需求的在并查集元素数组中的父是自己
                        parent[index] = index;
                        // 大小是1
                        size[index] = 1;
                        // 有一个岛了
                        sets++;
                    }
                }
            }

        }

        public int index(int i, int j) {
            return i * col + j;
        }

        // i表示的是 并查集里面数组的下标，是通过原二维数组的位置算出来的
        public int findParent(int i) {
            int helpIndex = 0;
            while (i != parent[i]) {
                help[helpIndex++] = i;
                i = parent[i];
            }
            for (helpIndex--; helpIndex >= 0; helpIndex--) {
                parent[help[helpIndex]] = i;
            }
            return i;
        }

        public void union(int i1, int j1, int i2, int j2) {
            // 获取在并查集里数组的下标
            int index1 = index(i1, j1);
            int index2 = index(i2, j2);
            //
            int parent1 = findParent(index1);
            int parent2 = findParent(index2);
            //
            if (parent1 == parent2) {
                return;
            }
            if (size[parent1] >= size[parent2]) {
                size[parent1] += size[parent2];
                parent[parent2] = parent1;
            } else {
                size[parent2] += size[parent1];
                parent[parent1] = parent2;
            }
            sets--;
        }
    }

}
