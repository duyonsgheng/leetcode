package custom.code_2022_12;

/**
 * @ClassName LeetCode_1414
 * @Author Duys
 * @Description
 * @Date 2022/12/28 15:12
 **/
// 1414. 和为 K 的最少斐波那契数字数目
public class LeetCode_1414 {
    // 方式1：打表，把满足小于等于10e9的列全部存下来，然后每次从列中选择小于=等k的最大项减去，使用二分来做
    // 方式2：贪心
    public int findMinFibonacciNumbers(int k) {
        int a = 1;
        int b = 1;
        // 往后推，找到斐波那契数列中的最后两项
        while (b <= k) {
            int c = a + b;
            a = b;
            b = c;
        }
        int ans = 0;
        while (k != 0) {
            if (k >= b) {
                k -= b;
                ans++;
            }
            int c = b - a;
            b = a;
            a = c;
        }
        return ans;
    }
}
