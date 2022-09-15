package week.week_2022_05_01;

/**
 * @ClassName Code_04_SumOfQuadraticSum
 * @Author Duys
 * @Description
 * @Date 2022/5/7 17:17
 **/
// 来自蓝桥杯练习题
// f(i) : i的所有因子，每个因子都平方之后，累加起来
// 比如f(10) = 1平方 + 2平方 + 5平方 + 10平方 = 1 + 4 + 25 + 100 = 130
// 给定一个数n，求f(1) + f(2) + .. + f(n)
// n <= 10的9次方
// O(n)的方法都会超时！低于它的！
// O(根号N)的方法，就过了，一个思路
// O(log N)的方法，
public class Code_04_SumOfQuadraticSum {

    // 看题意给的数据范围 我们需要找一个什么样的解答
    // 至少都是 根号 N 级别的 最好 log N

    /**
     * 思路：
     * 1.根据打表法观察，发现1到根号N的这些因子的个数基本上就是 n/因子
     * 2.那么根号N到n这些因子的个数我们知道，现在就是二分找，同是相同个数的因为有哪些
     */

    public static long sum(long n) {

        // 找到根号
        long sqrt = (long) Math.pow((double) n, 0.5);
        // 1到sqrt这些因子我们直接硬算
        long ans = 0;
        for (int i = 1; i <= sqrt; i++)
            ans += i * i * (n / i);

        // 那么剩下的这一截我们用二分找到
        // 比如当前是因子数是4个，那么我们去找有几个数处在这4个数上
        for (long k = n / (sqrt + 1); k >= 1; k--) {
            // k 因子的个数
            ans += sumOfLimitNum(n, k);
        }
        return ans;
    }

    public static long sumOfLimitNum(long v, long n) {
        // 找找我们的上界在哪里，因子个数是4个，看看哪些数满足，个数少，因子一定大的
        long up = cover(v, n);
        // 下界，当前因子数5个看看哪些数满足了，个数变多了，那么因子一定减少了
        long down = cover(v, n + 1);
        // 平方和公式
        // 1... 20
        // 1... 16
        // 得出 17到20的
        return ((up * (up + 1) * ((up << 1) + 1) - down * (down + 1) * ((down << 1) + 1)) * n) / 6;
    }

    public static long cover(long v, long n) {
        long l = 1;
        long r = v;
        long m = 0;
        long ans = 0;
        while (l <= r) {
            m = l + ((r - l) >> 2);
            // 因子乘以个数如果是小于等于n的，说明当前是满足的
            // 看看还有没有更大的因子满足
            if ((m * v) <= n) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }
}
