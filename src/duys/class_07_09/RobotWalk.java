package duys.class_07_09;

/**
 * @ClassName RobotWalk
 * @Author Duys
 * @Description 机器人
 * @Date 2021/7/13 9:19
 **/
public class RobotWalk {
    /**
     * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
     * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
     * 条件1：如果机器人来到1位置，那么下一步只能往右来到2位置；
     * 条件2：如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
     * 条件3：如果机器人来到中间位置，那么下一步可以往左走或者往右走；
     * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
     * 给定四个参数 N、M、K、P，返回方法数。
     */
    // N 总的位置（1 - N），start - 机器人开始位置，aim - 要去的目标位置， K总共多少步
    public static int walk1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    // 当前来到的位置，还剩下的步数，目标位置，
    public static int process1(int cur, int rest, int aim, int n) {
        // base case，如果还剩下0步，看看有没有达到目标位置
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        // 条件1
        if (cur == 1) {
            return process1(cur + 1, rest - 1, aim, n);
        }
        // 条件2
        if (cur == n) {
            return process1(n - 1, rest - 1, aim, n);
        }
        // 条件3
        return process1(cur - 1, rest - 1, aim, n) + process1(cur + 1, rest - 1, aim, n);
    }

    // dp的傻乎乎的使用，傻缓存
    public static int walk2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        // 建立一张dp表，横向是位置，纵向是步数，要囊括所有的情况
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start, K, aim, N, dp);
    }

    // cur范围是  1-N
    // rest范围是 0 -k
    public static int process2(int cur, int rest, int aim, int n, int[][] dp) {
        // 已经计算过位置了，直接从缓存返回
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        // base case，如果还剩下0步，看看有没有达到目标位置
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        }
        // 条件1
        if (cur == 1) {
            ans = process2(cur + 1, rest - 1, aim, n, dp);
        }
        // 条件2
        else if (cur == n) {
            ans = process2(n - 1, rest - 1, aim, n, dp);
        } else {
            // 条件3
            ans = process2(cur - 1, rest - 1, aim, n, dp) +
                    process2(cur + 1, rest - 1, aim, n, dp);
        }
        // 把缓存做好
        dp[cur][rest] = ans;
        return ans;
    }

    // 画依赖关系 比如 1 - 6  开始位置是start = 2，通过K = 4步，去到 aim = 4的位置
    //

    /**
     * -  1  2  3  4    rest
     * -  x  x  x  x  x 这一行是用不到的-cur不可能来到0 位置
     * 1  0  0  0  1  0
     * 2  0  0  1  0  4
     * 3  0  1  0  3  0
     * 4  1  0  2  0  6
     * 5  0  1  0  3  0
     * 6  0  0  1  0  3
     * cur
     */
    // 根据最原始的暴力递归，来填表，主函数要 [start][K]位置
    // 当rest = 0 的时候 恰好N = aim的时候 dp位置1，其他位置都是0
    // 当 cur = 1 的时候 当前位置依赖我的 cur=2 且rest - 1 位置，也就是左下
    // 当 cur = N 的时候 当前位置依赖我的 cur -1 且rest -1 位置，也就是左上
    // 其他位置既依赖左上，也依赖左下，依次填好整张表
    // 动态规划的核心-状态转移
    public static int walk3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        // 建立一张dp表，横向是位置，纵向是步数，要囊括所有的情况
        int[][] dp = new int[N + 1][K + 1];
        // base 1 :rest = 0,aim位置是1
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            // base 2条件得来
            // 当我的N =1 的时候，依赖的是 dp[2][rest-1]位置的值
            dp[1][rest] = dp[2][rest - 1];

            // 中间条件依赖左上和左下也就是 cur -1 和rest -1 以及cur +1 和 rest-1
            for (int cur = 2; cur <= N - 1; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            // base 3条件得来
            // 当cur =N的时候i，依赖是N-1 和 rest-1位置的值
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

}
