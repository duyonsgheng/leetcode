package duys_code.day_02;

/**
 * @ClassName Code_03_Drive
 * @Author Duys
 * @Description 携程原题：司机分配问题
 * @Date 2021/9/17 13:06
 **/
public class Code_03_Drive {
    /**
     * 现有司机N*2人，调度中心会将所有司机平分给A、B两个区域
     * 第 i 个司机去A可得收入为income[i][0]，
     * 第 i 个司机去B可得收入为income[i][1]，
     * 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱
     */

    /**
     * 这个问题讨论 司机去A或者不去A ，去A就一定不会去B，去B了就一定不会去A
     * 涉及两个区域的平均分配问题，那么income的长度一定是偶数，也就是司机人数一定是偶数，不然拉到
     *
     * @param income
     * @return
     */
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        int goA = N >> 1;// 去A的人数
        return process1(income, 0, goA);
    }

    // index 当前来到第几号人做决定，rest-A区域还剩下多少个名额
    public static int process1(int[][] income, int index, int rest) {
        // base case
        // 来到了越界的位置。已经分配完了。
        if (index == income.length) {
            return 0;
        }
        // 如果还剩下的人竟然 都等于了A区域还剩下的名额rest，全部去A
        if (income.length - index == rest) {
            // 后面进行判断的时候，也会全部去A，index+1 了 rest - 1 了。以后都会相等
            return income[index][0] + process1(income, index + 1, rest - 1);
        }
        // 如果还剩下司机，但是A区域名额已经为0了，剩下的司机只能去B
        if (rest == 0) {
            return income[index][1] + process1(income, index + 1, rest);
        }
        // 这里表示还剩下司机，并且A号区域还剩下名额，当前index号人可以自由选择
        // 当前index位置的人去A，就不能去B了
        int p1 = income[index][0] + process1(income, index + 1, rest - 1);
        // 当前index位置的人不去A，只能去B了
        int p2 = income[index][1] + process1(income, index + 1, rest);
        return Math.max(p1, p2);
    }

    // 改动态规划
    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        int goA = N >> 1;// 去A的人数
        // dp[i][j] 来到第i号人，goA区域还剩下j名额
        int[][] dp = new int[N + 1][goA + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= goA; j++) {
                if (N - i == j) {
                    // 后面进行判断的时候，也会全部去A，index+1 了 rest - 1 了。以后都会相等
                    dp[i][j] = income[i][0] + dp[i + 1][j - 1];
                }
                // 如果还剩下司机，但是A区域名额已经为0了，剩下的司机只能去B
                else if (j == 0) {
                    dp[i][j] = income[i][1] + dp[i + 1][j];
                } else {
                    // 这里表示还剩下司机，并且A号区域还剩下名额，当前index号人可以自由选择
                    // 当前index位置的人去A，就不能去B了
                    int p1 = income[i][0] + dp[i + 1][j - 1];
                    // 当前index位置的人不去A，只能去B了
                    int p2 = income[i][1] + dp[i + 1][j];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][goA];
    }

    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
