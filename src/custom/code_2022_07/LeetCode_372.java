package custom.code_2022_07;

/**
 * @ClassName LeetCode_372
 * @Author Duys
 * @Description
 * @Date 2022/7/21 13:24
 **/
// 372. 超级次方
public class LeetCode_372 {

    // 比如 a^2345 = a^(234 * 10 +5) = a^(234*10) * a^5
    static int mod = 1337;

    public int superPow(int a, int[] b) {

        return dfs(a, b, b.length - 1);
    }

    public static int dfs(int a, int[] b, int index) {
        if (index == -1) {
            return 1;
        }
        return pow(dfs(a, b, index - 1), 10) * pow(a, b[index]) % mod;
    }

    public static int pow(int a, int b) {
        int ans = 1;
        a %= mod;
        while (b != 0) {
            // 奇数
            if ((b & 1) != 0) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }
        return ans;
    }
}
