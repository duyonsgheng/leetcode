package custom.code_2023_02;

/**
 * @ClassName LeetCode_1492
 * @Author Duys
 * @Description
 * @Date 2023/2/2 15:11
 **/
// 1492. n 的第 k 个因子
public class LeetCode_1492 {
    public int kthFactor(int n, int k) {
        int count = 0, p;
        for (p = 1; p * p <= n; p++) {
            if (n % p == 0) {
                count++;
                if (count == k) {
                    return p;
                }
            }
        }
        // 如果不够，需要算大点的数了
        p--;
        if (p * p == n) {
            p--;
        }
        for (; p > 0; p--) {
            if (n % p == 0) {
                count++;
                // 对应有一个
                if (count == k) {
                    return n / p;
                }
            }
        }
        return -1;
    }
}
