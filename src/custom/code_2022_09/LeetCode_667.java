package custom.code_2022_09;

/**
 * @ClassName LeetCode_667
 * @Author Duys
 * @Description
 * @Date 2022/9/6 10:56
 **/
// 优美的排列
public class LeetCode_667 {
    // 思路
    // 先凑齐差值是k k-1 k-2 ..... 1
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1, diff = k; i <= k; i++, diff--) {
            ans[i] = ans[i - 1] + ((i & 1) == 1 ? diff : -diff);
        }
        for (int i = k + 1; i <= n; i++) {
            ans[i] = i + 1;
        }
        return ans;
    }
}
