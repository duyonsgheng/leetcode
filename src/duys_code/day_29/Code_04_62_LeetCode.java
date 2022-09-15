package duys_code.day_29;

/**
 * @ClassName Code_04_62_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/unique-paths/
 * @Date 2021/11/25 13:42
 **/
public class Code_04_62_LeetCode {
    /**
     * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     */
    // 机器人从右上角到左下角一共要走多少步骤 m+n吧
    // 这就是一个排列组合问题
    public int uniquePaths(int m, int n) {
        int right = n - 1; // 下标从0开始
        int all = m + n - 2; // 下标从0开始
        // 下面就是算阶乘
        long o1 = 1;
        long o2 = 1;
        // C 4 3  4的阶乘 / 3的阶乘 * 1的阶乘
        for (int i = right + 1, j = 1; i <= all; i++, j++) {
            o1 *= i;// 每一步算阶乘
            o2 *= j;
            long gcd = gcb(o1, o2);
            o1 /= gcd;
            o2 /= gcd;
        }
        return (int) o1;
    }

    public static long gcb(long a, long b) {
        return b == 0 ? a : gcb(b, a % b);
    }
}
