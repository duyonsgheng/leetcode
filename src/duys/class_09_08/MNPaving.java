package duys.class_09_08;

/**
 * @ClassName MNPaving
 * @Author Duys
 * @Description
 * @Date 2021/9/9 10:08
 **/
public class MNPaving {
    /**
     * 你有无限的1*2的砖块，要铺满M*N的区域，
     * 不同的铺法有多少种?
     */
    /**
     * 解答：
     * 1.每一个位置只向上或者向右，每一个位置都这样，并不会影响最终的方法数
     * 2.递归函数的设计
     * 参数1：i 来到第i行，每一个位置只能向上或者向右，潜台词：i-2以上的行全部是都铺满了
     * 参数2：pre 表示上一行(i-1)的情况，比如 0 1 1 0 0 1 0 0 为1的状态就是上一行已经铺了的位置
     * 参数3：一共有多少行N 固定函数
     * 返回值：从 1- N行所有的铺满，总共的方法数
     * 主函数 ： i= 0 ，pre = 111111111,N 表示当前来到第0行，上一行没有，那么就表示上一行是满的，可以认为-2行包括-2行以上的全部都是满的，N表示总共的行数
     * base case 当i = N 的时候，就是收集答案的时候，因为N是越界的行了，如果上一行全部都是1，此时已经找到了一个有效的答案
     * <p>
     * 那么第i行怎么做抉择：如果i-1行有1的，当前行不能向上，如果i-1行为0的，当前行只能向上，把i-1行塞满。
     * 如果i-1行为1的时候，当前行不能往上，但是可以往后，也可以不往右。然后把当前做的决定往下传，进行一个深度优先遍历
     */

    public static int ways1(int N, int M) {
        if (N < 1 || M < 1 || ((M * N) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        // 上一行的位置铺砖状况，位置上为1表示已经有转了，为0表示没有砖
        int[] pre = new int[M];
        // 从第0行开始。那么-1行 - 2 行都认为是满的
        for (int i = 0; i < pre.length; i++) {
            pre[i] = 1;
        }
        return process1(pre, 0, N);
    }

    // pre 表示level-1行的状态
    // level表示，正在level行做决定
    // N 表示一共有多少行 固定的
    // level-2行及其之上所有行，都摆满砖了
    // level做决定，让所有区域都满，方法数返回
    public static int process1(int[] pre, int level, int N) {
        // base case
        if (level == N) { // 来到了越界的位置 本来只有 0~N-1 这些行的，来到第N行了，就表示需要收集答案了
            // 如果上一行的已经全部填满了，也就是N-1行已经填满了，那么就已经在找到了一种有效的方法
            for (int i = 0; i < pre.length; i++) {
                if (pre[i] != 1) {
                    return 0;
                }
            }
            return 1;
        }
        // 如果还没有达到终止行，那么就需要生成当前行的状况了
        int[] op = getCurOp(pre);
        // 拿到了本行的数据情况，进行深度优先玩儿
        return dfs(op, 0, level, N);
    }

    // curOp[i] = 0 可以自由选择，可以向右，可以不向右，如果向右的话，那么需要保证这一列和下一列都是0才能向右，否则，不能，因为向右铺的话，是 1* 2的砖，占用两个格子
    // curOp[i] = 1 不能自由选择，当前位置只能向上，要把上一行的填满
    // 每一行都是从左向右的查看位置信息
    public static int dfs(int[] curOp, int col, int level, int N) {
        // 当前level行的最右一列的了，当前level行已经结束了，该level+1行做决定了
        if (col == curOp.length) {
            // 行做决定
            return process1(curOp, level + 1, N);
        }
        // 下面是同一行的列做决定
        // 这一行还有右边的列可以选择
        int ans = 0;
        // 可以不自由选择，也就是这一列我选择不去填为0的位置
        ans += dfs(curOp, col + 1, level, N);
        // 向右边铺砖。因为向右铺砖的话，是两列都占用了
        if (col + 1 < curOp.length && curOp[col] == 0 && curOp[col + 1] == 0) {
            // 先把自己占用的两列改为1，然后往以后的过程传递
            curOp[col] = 1;
            curOp[col + 1] = 1;
            ans += dfs(curOp, col + 2, level, N);
            // 还原现场
            curOp[col] = 0;
            curOp[col + 1] = 0;
        }
        return ans;

    }

    // 根据上一行的位置使用状况来生成当前行的情况，比如上一行是1，这一行
    public static int[] getCurOp(int[] pre) {
        int[] cur = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            // 比如上一行是1，那么这一行就是0，可以进行自由发挥，如果上一行是0，这一行只能向上，那么就只能是1了
            cur[i] = pre[i] ^ 1;
        }
        return cur;
    }

    // 如果 N 和 M 只有一个能超过 32的，可以使用int 的位信息来保存每一行的列信息上的位置信息
    public static int ways2(int N, int M) {
        if (N < 1 || M < 1 || ((M * N) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int min = Math.min(M, N);
        int max = Math.max(M, N);
        // 上一行的位置铺砖状况，位置上为1表示已经有转了，为0表示没有砖
        int pre = (1 << min) - 1;
        return process2(pre, 0, max, min);
    }

    // N = 表示行数
    // M = 表示列数
    public static int process2(int pre, int level, int N, int M) {
        // 来到了最后一行，检查上一行pre是不是全部为1
        if (level == N) {
            return pre == (1 << M) - 1 ? 1 : 0;
        }
        // 就是上一行是1 这一行就是0
        int op = (~(pre)) & ((1 << M) - 1);
        // 这里的列从二进制高位往地位走得意思
        return dfs2(op, M - 1, level, N, M);
    }

    public static int dfs2(int op, int col, int level, int N, int M) {
        // 最后一列，
        if (col == -1) {
            return process2(op, level + 1, N, M);
        }
        int ans = 0;
        ans += dfs2(op, col - 1, level, N, M);

        // 当前的col位置是0，并且col-1位置也是0，并且col - 1不到-1位置
        if ((op & (1 << col)) == 0 && (op & (1 << (col - 1))) == 0 && col - 1 >= 0) {
            // 那么就把 1 1 向左移动 col -1 位置 11 二进制是3
            ans += dfs2((op | (3 << (col - 1))), col - 2, level, N, M);
        }
        return ans;
    }

    // 记忆化搜索的解
    public static int ways3(int N, int M) {
        if (N < 1 || M < 1 || ((M * N) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int min = Math.min(M, N);
        int max = Math.max(M, N);
        // 上一行的位置铺砖状况，位置上为1表示已经有转了，为0表示没有砖
        int pre = (1 << min) - 1;
        // dp[i][j] 表示每一行的所有位置，所有的列不同状态的答案
        int[][] dp = new int[pre + 1][max + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(pre, 0, max, min, dp);
    }

    public static int process3(int pre, int level, int N, int M, int[][] dp) {
        if (dp[pre][level] != -1) {
            return dp[pre][level];
        }
        // 最后一行了
        int ans = 0;
        if (level == N) {
            // 上一行是否填满了
            ans = pre == (1 << M) - 1 ? 1 : 0;
        } else {
            int op = (~pre) & ((1 << M) - 1);
            ans += dfs3(op, M - 1, level, N, M, dp);
        }
        dp[pre][level] = ans;
        return ans;
    }

    public static int dfs3(int op, int col, int level, int N, int M, int[][] dp) {
        // 当前行的最优一列了
        if (col == -1) {
            return process3(op, level + 1, N, M, dp);
        }
        int ans = 0;
        ans += dfs3(op, col - 1, level, N, M, dp);
        if ((op & (1 << col)) == 0 && col - 1 >= 0 && (op & (1 << (col - 1))) == 0) {
            ans += dfs3((op | (3 << (col - 1))), col - 2, level, N, M, dp);
        }
        return ans;
    }
}
