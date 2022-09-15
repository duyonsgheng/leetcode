package week.week_2022_05_04;

/**
 * @ClassName Code_01_SomeDPFromVT
 * @Author Duys
 * @Description
 * @Date 2022/5/27 10:26
 **/
// 来自弗吉尼亚理工大学(VT)，算法考试卷
// 精选了还可以的几道题
// 这些都是简单难度的动态规划，是面试中最常见的难度
// 这几个题都有一些非常小的常见技巧可说
public class Code_01_SomeDPFromVT {
    // 题目1
    // 找到一个最少张数的方案，让FunnyGoal、OffenseGoal，都超过一个值
    // 定义如下尝试过程
    // 贴纸数组stickers
    // stickers[i][0] : i号贴纸的Funny值
    // stickers[i][1] : i号贴纸的Offense值

    // 这就是一个背包问题
    public static int minStickers1(int[][] stickers, int funnyGoal, int offenseGoal) {
        return process(stickers, 0, funnyGoal, offenseGoal);
    }

    // 来到index位置做选择
    public static int process(int[][] stickers, int index, int restFunny, int restOffen) {
        if (restFunny <= 0 && restOffen <= 0) {
            return 0;// 不需要选择了
        }
        // 如果还能选，但是没有选项了，返回搞不定
        if (index == stickers.length) {
            return Integer.MAX_VALUE;
        }
        // 可能性1；不选
        int p1 = process(stickers, index + 1, restFunny, restOffen);

        int p2 = Integer.MAX_VALUE;
        int next = process(stickers, index + 1, Math.max(0, restFunny - stickers[index][0]), Math.max(0, restOffen - stickers[index][1]));
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        return Math.min(p1, p2);
    }

    // 严格的位置依赖
    public static int minStickers2(int[][] stickers, int funnyGoal, int offenseGoal) {
        int n = stickers.length;
        int[][][] dp = new int[n + 1][funnyGoal + 1][offenseGoal + 1];
        for (int f = 0; f <= funnyGoal; f++) {
            for (int o = 0; o <= offenseGoal; o++) {
                // 当来到n位置，如果不是全部0的，表示做不到
                if (f != 0 && o != 0) {
                    dp[n][f][o] = Integer.MAX_VALUE;
                }
            }
        }
        // 每个各自依赖我的下一个，从后往前填就可以了
        for (int i = n - 1; i >= 0; i--) {
            for (int f = 0; f <= funnyGoal; f++) {
                for (int o = 0; o <= offenseGoal; o++) {
                    if (f != 0 && o != 0) { // 如果都已经是0了，就不要填了，直接当前值为0就可以了
                        // 不选
                        int p1 = dp[i + 1][f][o];
                        int p2 = Integer.MAX_VALUE;
                        int next = dp[i + 1][Math.max(0, f - stickers[i][0])][Math.
                                max(0, o - stickers[i][1])];
                        if (next != Integer.MAX_VALUE) {
                            p2 = 1 + next;
                        }
                        dp[i][f][o] = Math.min(p1, p2);
                    }
                }
            }
        }
        return dp[0][funnyGoal][offenseGoal];
    }

    // 题目2
    // 绳子总长度为M
    // 每一个长度的绳子对应一个价格，比如(6, 10)表示剪成长度为6的绳子，对应价格10
    // 可以重复切出某个长度的绳子
    public static int maxValue(int[][] ropes, int m) {
        return process2(ropes, 0, m);
    }

    // 当前来到i位置，还剩下restLen长度绳子
    public static int process2(int[][] ropes, int index, int restLen) {
        if (restLen <= 0 || index == ropes.length) {
            return 0;
        }
        // 选和不选
        int p1 = process2(ropes, index + 1, restLen);
        // 选当前的，有条件，当前的长度是小于等于总长度的，可以选
        int p2 = -1;
        if (ropes[index][0] <= restLen) {
            // 可以重复选择，所以index位置可以不变，这里和纸币找零这个题有点区别
            p2 = process2(ropes, index, restLen - ropes[index][0]);
        }
        if (p2 != -1) {
            p2 = ropes[index][1] + p2;
        }
        return Math.max(p1, p2);
    }

    public static int maxValue1(int[][] ropes, int m) {
        if (ropes == null || ropes.length < 1 || ropes[0] == null || ropes[0].length < 1 || m < 1) {
            return 0;
        }
        int n = ropes.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= m; j++) {
                // 选和不选
                int p1 = dp[i + 1][j];
                // 选当前的，有条件，当前的长度是小于等于总长度的，可以选
                int p2 = -1;
                if (ropes[i][0] <= j) {
                    // 可以重复选择，所以index位置可以不变，这里和纸币找零这个题有点区别
                    p2 = ropes[i][1] + dp[i][j - ropes[i][0]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][m];
    }


    // 题目3
    // 每一个序列都是[a,b]的形式，a < b
    // 序列连接的方式为，前一个序列的b，要等于后一个序列的a
    // 比如 : [3, 7]、[7, 13]、[13, 26]这三个序列就可以依次连接
    // 给定若干个序列，求最大连接的数量
    public static int maxNumberSubsequence(int[][] arr) {
        return process3(arr, 0, -1);
    }

    // 当前来到i位置，之前选择是pre
    public static int process3(int[][] arr, int index, int pre) {
        if (index == arr.length) {
            return 0;
        }
        // 不选当前的
        int p1 = process3(arr, index + 1, pre);
        int p2 = 0;
        if (pre != -1 && arr[index][0] == arr[pre][1]) {
            p2 = 1 + process3(arr, index + 1, index);
        }
        return Math.max(p1, p2);
    }
}
