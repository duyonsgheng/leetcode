package custom.code_2023_02;

/**
 * @ClassName LeetCode_1513
 * @Author Duys
 * @Description
 * @Date 2023/2/3 17:19
 **/
// 1513. 仅含 1 的子串数
public class LeetCode_1513 {

    // 找到长度最大的哪一段
    public int numSub(String s) {
        int MODULO = 1_000_000_007;
        char[] arr = s.toCharArray();
        int n = arr.length;
        long sum = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == '0') {
                ans += sum * (sum + 1) / 2;
                ans %= MODULO;
                sum = 0;
            } else {
                sum++;
            }
        }
        ans += sum * (sum + 1) / 2;
        ans %= MODULO;
        return (int) ans;
    }

}
