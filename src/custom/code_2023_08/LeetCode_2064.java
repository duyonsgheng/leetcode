package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2064
 * @date 2023年08月08日
 */
// 2064. 分配给商店的最多商品的最小值
// https://leetcode.cn/problems/minimized-maximum-of-products-distributed-to-any-store/submissions/
public class LeetCode_2064 {
    // 可以二分
    // 找一个下届，一个上届，然后看看按照当前的分法，店铺是否能分完。
    public int minimizedMaximum(int n, int[] quantities) {
        // m件产品，q[i] 表示第i中商品的数量
        long l = 1; //
        long r = 0;
        for (int q : quantities) r += q;
        while (l < r) {
            long mid = (r + l) / 2;
            int k = 0; // 每家分mid件商品，需要k家店能分完
            for (int num : quantities) {
                k += (num + mid - 1) / mid; // 向上取整，ceil()方法
            }
            if (k > n) { // 说明mid小了
                l = mid + 1;
            } else r = mid;
        }
        return (int) r;
    }
}
