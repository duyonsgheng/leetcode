package hope.class90;

/**
 * @author Mr.Du
 * @ClassName Code01_CuttingBamboo
 * @date 2024年08月26日 下午 04:53
 */
// 砍竹子II
// 现需要将一根长为正整数bamboo_len的竹子砍为若干段
// 每段长度均为正整数
// 请返回每段竹子长度的最大乘积是多少
// 答案需要对1000000007取模
// 测试链接 : https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
public class Code01_CuttingBamboo {
    // 快速幂的方法
    public static long power(long x, int n, int mod) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) { // 如果次方的二进制中包含了，那么需要累计到结果中去
                ans = (ans * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1;
        }
        return ans;
    }

    // 分析（观察）
    // n%3 ==0 全部拆分成3
    // n%3 ==1 结尾是2 * 2 ，n-4全部拆分成3
    // n%3 ==2 结尾是2，n全部拆分成3
    public static int cuttingBamboo(int n) {
        if (n == 2 || n == 3) {
            return n - 1;
        }
        int mod = 1_000_000_007;
        int tail = n % 3 == 0 ? 1 : (n % 3 == 1 ? 4 : 2);
        int power = (tail == 1 ? n : n - tail) / 3;
        return (int) (power(3, power, mod) * tail % mod);
    }
}
