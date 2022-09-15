package duys;

public class Code03_Knapsack {
    /**
     * @param w   货物的重量
     * @param v   货物的价值
     * @param bag 背包的容量
     * @return 不超重的情况下，能够得到最大价值的货物
     */
    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, 0, bag);
    }

    // index... 最大价值
    public static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1;
        }
        // 重量没超
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, alreadyW, bag);
        int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
        int p2 = -1;
        if (p2next != -1) {
            p2 = v[index] + p2next;
        }
        return Math.max(p1, p2);

    }

    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    /**
     * 尝试。。。
     *
     * @param w
     * @param v
     * @param index
     * @param rest
     * @return
     */
    // 只剩下rest的空间了，
    // index...货物自由选择，但是不要超过rest的空间
    // 返回能够获得的最大价值
    public static int process(int[] w, int[] v, int index, int rest) {
        // 背包重量如果等于0，有可能存在货物重量为0的货物
        if (rest < 0) { // base case 1
            return -1;
        }
        // rest >=0 最后的货物
        if (index == w.length) { // base case 2
            return 0;
        }
        // 有货也有空间
        // 不要当前的货物
        int p1 = process(w, v, index + 1, rest);
        int p2 = -1;
        // 要当前的货物
        int p2Next = process(w, v, index + 1, rest - w[index]);
        // 有可能存在只有一个货物，切大于背包的容量，会出现-1情况
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        // 就是返回两种情况的最大值
        return Math.max(p1, p2);
    }

    public static int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        // 行 - index  0 - N
        // 列 - bag  -1 - bag
        // 动态规划表，囊括所有情况，根据上面暴力递归来填表
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][...] = 0
        // 处理行的，N-1开始，因为N行全是0
        for (int index = N - 1; index >= 0; index--) {
            // 处理列的
            for (int rest = 0; rest <= bag; rest++) { // rest < 0
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (rest - w[index] >= 0) {
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }

}
