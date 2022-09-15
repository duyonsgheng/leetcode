package duys_code.day_48;

/**
 * @ClassName Code_01_482_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/smallest-good-base/
 * @Date 2021/10/22 13:27
 **/
public class Code_01_483_LeetCode {
    /**
     * 给一个字符串类型的数：按照二进制的形式排列每一位。如果每一位都是1 表示是这数的 多少好进制
     */
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        // 1.首先我们看看这个数用二进制表示需要多少位置
        // 2.如果这个数能用二进制表示，而且每一位都是1.返回2
        // 3.如果不能用二进制表示。那么3.4.5...num-1。那么我们需要找一个上线和下限
        // 比如：
        /**
         * num 用 5位置来表示 k进制
         * 位信息：    -    -    -    -    -
         *          k^4  k^3  k^2  k^1  k^0
         *  如果 k^4 已经大于了num。说明k大了
         *  如果 k^5 已经小于了num。说明k小了
         *  那么我们k的上限和下限
         *
         */
        for (int m = (int) (Math.log(num + 1) / Math.log(2)); m > 2; m--) {
            long l = (long) Math.pow(num, (1.0 / m));
            long r = (long) Math.pow(num, (1.0 / (m - 1))) + 1L;
            while (l <= r) {
                long k = l + ((r - l) >> 1);
                long sum = 0;
                long base = 1;
                for (int i = 0; i < m && sum <= num; i++) {
                    sum += base;
                    base *= k;
                }
                if (sum < num) {
                    l = k + 1;
                } else if (sum > num) {
                    r = k - 1;
                } else {
                    return k + "";
                }
            }
        }
        return (num - 1) + "";
    }
}
