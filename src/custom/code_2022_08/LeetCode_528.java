package custom.code_2022_08;

/**
 * @ClassName LeetCode_528
 * @Author Duys
 * @Description
 * @Date 2022/8/22 15:19
 **/
// 528. 按权重随机选择
public class LeetCode_528 {

    class Solution {
        private int[] sum;

        public Solution(int[] w) {
            int n = w.length;
            sum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + w[i - 1];
            }
        }

        public int pickIndex() {
            int n = sum.length;
            int t = (int) (Math.random() * (sum[n - 1])) + 1;
            int l = 1;
            int r = n - 1;
            while (l < r) {
                int m = l + ((r - l) >> 1);
                if (sum[m] >= t) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            return r - 1;
        }
    }
}
