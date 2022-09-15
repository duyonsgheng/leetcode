package duys_code.day_13;

/**
 * @ClassName Code_04_803_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/bricks-falling-when-hit/
 * @Date 2021/10/21 13:53
 **/
public class Code_04_803_LeetCode {
    /**
     * 打砖块问题
     * 每一块转的上下左右四个方向具有粘性。每一块转会把相邻的转黏住，但是只有四个方向有粘性
     * 在二维数组中：第0行代表天花板，固定在天花板的转是稳定的。其他的转只要和天花板的转黏在一起了就会形成粘住
     * 现在来了一个炮弹数组。里面的值代表 [1,0] 打在了1行0列的位置。打中的转会碎掉。同时下方或者左右的转会掉落
     * 问：每一发炮弹对应掉落转的数量
     */
    /**
     * 很明显这是一个并查集的问题，但是炮弹先后顺序不同，掉落的转的数量不同
     * 1.首先我们把炮弹打中的位置全部标记为2
     * 2.建立并查集。然后初始化
     * 3，跟胡炮弹数组逆序来获得转增加的数量。也就是炮弹从前到后打中的时候掉落的数量
     */
    public static int[] hitBricks(int[][] grid, int[][] hits) {
        if (grid == null || grid.length < 1) {
            return null;
        }
        if (hits == null || hits.length < 1) {
            return new int[]{0};
        }
        for (int[] hi : hits) {
            if (grid[hi[0]][hi[1]] == 1) {
                grid[hi[0]][hi[1]] = 2;
            }
        }
        UnionFind unionFind = new UnionFind(grid);
        int[] ans = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 2) {
                ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
            }
        }
        return ans;
    }

    public static class UnionFind {
        private int N;
        private int M;
        // 有多少块转连接到的天花板
        private int cellingAll;
        // 原始矩阵 ，因为炮弹影响 1 -> 2
        private int[][] grid;
        // 当前位置的转是不是在天花板连着的
        private boolean[] cellingSet;
        // 下面是并查集的不可或缺的参数
        private int[] fatherMap;
        private int[] sizeMap;
        // 做路径压缩使用的
        private int[] stack;

        public UnionFind(int[][] matrix) {
            initSpace(matrix);
            initConnect();
        }

        // 这是当时炮弹打的位置
        public int finger(int row, int col) {
            // 设置成1.建立一个并查集单元
            grid[row][col] = 1;
            int curIndex = row * M + col;
            if (row == 0) {
                cellingSet[curIndex] = true;
                cellingAll++;
            }
            fatherMap[curIndex] = curIndex;
            sizeMap[curIndex] = 1;
            int preCelling = cellingAll;
            union(row, col, row - 1, col);
            union(row, col, row + 1, col);
            union(row, col, row, col - 1);
            union(row, col, row, col + 1);
            int now = cellingAll;
            if (row == 0) {
                // 如果在天花板上，不用减1了
                return now - preCelling;
            } else {
                // 打中的转不算
                return now == preCelling ? 0 : now - preCelling - 1;
            }
        }

        private void initSpace(int[][] matrix) {
            grid = matrix;
            N = grid.length;
            M = grid[0].length;
            int all = N * M;
            cellingAll = 0;
            cellingSet = new boolean[all];
            fatherMap = new int[all];
            sizeMap = new int[all];
            stack = new int[all];
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (grid[row][col] == 1) {
                        int index = row * M + col;
                        fatherMap[index] = index;
                        sizeMap[index] = 1;
                        // 可能是天花板
                        if (row == 0) {
                            cellingSet[index] = true;
                            cellingAll++;
                        }
                    }
                }
            }
        }

        private void initConnect() {
            // 每一块转得上下左右联合去吧
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    union(row, col, row - 1, col);
                    union(row, col, row + 1, col);
                    union(row, col, row, col - 1);
                    union(row, col, row, col + 1);
                }
            }
        }

        private int findFather(int row, int col) {
            int stackSize = 0;
            int index = row * M + col;
            while (index != fatherMap[index]) {
                stack[stackSize++] = index;
                index = fatherMap[index];
            }
            while (stackSize != 0) {
                fatherMap[stack[--stackSize]] = index;
            }
            return index;
        }

        private void union(int row1, int col1, int row2, int col2) {
            if (!valid(row1, col1) || !valid(row2, col2)) {
                return;
            }
            int fa1 = findFather(row1, col1);
            int fa2 = findFather(row2, col2);
            if (fa1 == fa2) {
                return;
            }
            int size1 = sizeMap[fa1];
            int size2 = sizeMap[fa2];
            boolean status1 = cellingSet[fa1];
            boolean status2 = cellingSet[fa2];
            if (size1 <= size2) {
                // 联合
                fatherMap[fa1] = fa2;
                sizeMap[fa2] = size1 + size2;
                // 如果有一个不是天花板。就联合起来
                if (status1 ^ status2) {
                    cellingSet[fa2] = true;
                    cellingAll += status1 ? size2 : size1;
                }
            } else {
                // 联合
                fatherMap[fa2] = fa1;
                sizeMap[fa1] = size1 + size2;
                // 如果有一个不是天花板。就联合起来
                if (status1 ^ status2) {
                    cellingSet[fa1] = true;
                    cellingAll += status1 ? size2 : size1;
                }
            }

        }

        private boolean valid(int row, int col) {
            return row >= 0 && row < N && col >= 0 && col < M && grid[row][col] == 1;
        }
    }

    public static void main(String[] args) {
        boolean b1 = true;
        boolean b2 = false;
        System.out.println(b1 ^ b2);
    }
}
