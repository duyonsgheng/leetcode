package duys.class_07_28;

/**
 * @ClassName NHuanghouQuestion
 * @Author Duys
 * @Description N皇后问题
 * @Date 2021/7/29 10:34
 **/
public class N_HuanghouQuestion {
    /**
     * N皇后问题是指在N*N的棋盘上要摆N个皇后，
     * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
     * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
     * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
     * n=8，返回92
     */
    /**
     * - 0  1  2  3  4  5  6  7
     * 0 N
     * 1       N
     * 2                N
     * 3    N
     * 4
     * 5
     * 6
     * 7
     */
    public static int findAns(int N) {

        // 再整个枚举中，先
        // 先看第0行皇后放在哪里
        // 再看第二行黄后放在哪里
        // ......
        // 收集有效答案。
        // 位置有效 比如上一行的皇后再 (a,b) 下一个皇后再(x,y) 那么 (b==y)|| (|a-x| == |b-y|) (共对角线了)，只要成立，就不满足要求,
        //不考虑行 因为我们规定一行一行的往下填皇后

        int[] record = new int[N];// 每一行皇后放的位置
        // record[x] = y，表示第x行的皇后放在了y列上
        return process1(0, record, N);
    }

    private static int process1(int index, int[] record, int n) {
        // 发现来到越界位置了，说明之前做的决定是有效的
        if (index == n) {
            return 1;
        }
        int ans = 0;
        // index行的皇后放在哪里，也就是说index行的皇后放在那一列上
        for (int i = 0; i < n; i++) {
            // 如果有效
            if (isValid(record, index, i)) {
                // 把皇后放在index行的，i位置，然后下一行去吧
                record[index] = i;
                ans += process1(index + 1, record, n);
            }
        }
        return ans;
    }

    // 是否冲突，第i行的皇后放在第j列，是否与之前放的有冲突
    private static boolean isValid(int[] record, int i, int j) {
        // 每一行都检查
        for (int k = 0; k < i; k++) {
            // 如果当前j列和之前的冲突了 ，或者 当前[i,j] 和之前的再同一个斜线上
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


    public static int findAns2(int n) {
        if (n < 1 || n > 32) {
            return 1;
        }
        // 否则。。。。
        // 比如 n= 4
        // 1 << 4 -> 0....0 1 0 0 0 0
        // 1 << 4 - 1  -> 0....1 1 1 1
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * int limit, 比如 n=7 那么 limit 是 0...... 0 1 1 1 1 1 1 1
     * int colLim, 表示之前的皇后放的位置影响
     * int leftDiaLim, 表示之前的皇后的左下位置影响
     * int rightDiaLim 表示之前的皇后的右下位置影响
     */
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        //
        if (limit == colLim) {
            return 1;
        }
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            // 把每一行可以填皇后的位置都去尝试（提取最右侧的1）
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findAns(4));
    }
}
