package custom.code_2023_04;

/**
 * @ClassName LeetCode_1680
 * @Author Duys
 * @Description
 * @Date 2023/4/26 14:35
 **/
// 1680. 连接连续二进制数字
public class LeetCode_1680 {
    int mod = 1_000_000_007;

    public int concatenatedBinary(int n) {
        int ans = 0, shift = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                shift++;
            }
            ans = (int) ((((long) ans << shift) + i) % mod);
        }
        return ans;
    }
}
