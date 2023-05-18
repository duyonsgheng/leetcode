package custom.code_2023_05;

/**
 * @ClassName LeetCode_1734
 * @Author Duys
 * @Description
 * @Date 2023/5/16 11:12
 **/
// 1734. 解码异或后的排列
public class LeetCode_1734 {
    // perm = [1,3,2] ，那么 encoded = [2,1]
    // 1 xor 3 = 2
    // 3 xor 2 = 1
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1; // n一定是奇数
        int[] ans = new int[n];
        int all = 0;
        // 题目说了 n 个正整数，n是奇数
        for (int i = 1; i <= n; i++) {
            all ^= i;
        }
        int odd = 0; // 奇数位置
        for (int i = 1; i < n - 1; i += 2) {
            odd ^= encoded[i];
        }
        ans[0] = all ^ odd;
        for (int i = 0; i < n - 1; i++) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }
        return ans;
    }
}
