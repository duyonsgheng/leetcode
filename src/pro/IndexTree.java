package pro;

/**
 * @ClassName IndexTree
 * @Author Duys
 * @Description
 * @Date 2022/5/30 11:16
 **/

// 树状数组 ，下标从1开始，顾名思义，肯定跟index有关。
public class IndexTree {

    // 一维的
    public static class IndexTree1D {
        // 解决的问题：也是求某一个区间内累加和问题，但是如果整个数组不方便变化，可以使用前缀合数组，但是如果某一个位置的值变化了，那么就需要使用线段树或者indexTree

        // 比如数组是 1 2 3 4 5 6 7 8
        // 当前来到1位置，前面有没有能跟当前位置组成一对的。1单独一对
        // 来到2位置，前面有没有能跟自己组成一对的，有一个1，此时 1 和 2组成一对
        // 来到3位置，前面有没有能跟自己组成一对的，没有，3位置单独一对，
        // 来到4位置，前面有没有能跟自己组成一对的，有，3位置和4位置组成一对，但是此时 这一对和之前的1，2位置那一对能组成一大队。

        // 5 6 7 8位置的时候重复上面的步骤，但是来到8的时候，5 6 7 8这一大队和前面 1 2 3 4组成的那一大队又能组成一大队。

        // 规律1：index 的二进制 0101101000 管了哪些数？ 从 0101100001 到 0101101000 也就是把最后的1提取出来变成0整个数+1 到自己，这些数数当前位置管的数
        // 规律2：求1到i的前缀和，我们只需要把i的二进制为1的位置数拿出来累加。比如i=33 -> 100001 只需要把 100001 的累加和 + 100000的累加和，依次抹掉最右侧的1的累加。
        // 也就是 i = 1011101010 = help[1011101010] + help[1011101000]+help[1011100000]+help[1011000000] ++help[1010000000]++help[1000000000]
        public int[] tree;
        public int size;

        public IndexTree1D(int n) {
            size = n;
            tree = new int[size + 1];
        }

        // index & -index 提取最右侧的1，-index 就是 ~(index)+1

        // 求1到index的累加和
        public int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

        // 把index位置值修改为d
        public void add(int index, int d) {
            while (index <= size) {
                tree[index] += d;
                index += index & -index;
            }
        }
    }

    // 二维的
    // 把行和列拆开，然后哪些行收到影响，哪些列收到影响，这些受影响的行和列的所有组合。
    public static class IndexTree2D {
        public int[][] tree;
        public int[][] nums;
        public int N;
        public int M;

        public IndexTree2D(int[][] matrix) {
            if (matrix == null || matrix.length < 1 || matrix[0] == null || matrix[0].length < 1) {
                return;
            }
            N = matrix.length;
            M = matrix[0].length;
            tree = new int[N + 1][M + 1];
            nums = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public int sum(int row, int col) {
            int sum = 0;
            for (int i = row + 1; i > 0; i -= i & (-i)) {
                for (int j = col + 1; j > 0; j -= j & (-j)) {
                    sum += tree[i][j];
                }
            }
            return sum;
        }

        public void update(int row, int col, int val) {
            if (N == 0 || M == 0) {
                return;
            }
            int add = val - nums[row][col];
            nums[row][col] = val;
            for (int i = row + 1; i <= N; i += i & (-i)) {
                for (int j = col + 1; j <= M; j += j & (-j)) {
                    tree[i][j] += add;
                }
            }
        }

        // 区域内的累加和
        public int sumRegion(int r1, int c1, int r2, int c2) {
            return sum(r2, c2) - sum(r2, c1 - 1) - sum(r1 - 1, c2) + sum(r1 - 1, c1 - 1);
        }
    }
}
