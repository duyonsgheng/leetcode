package custom.code_2023_02;

/**
 * @ClassName LeetCode_1529
 * @Author Duys
 * @Description
 * @Date 2023/2/6 14:44
 **/
// 1529. 最少的后缀翻转次数
public class LeetCode_1529 {
    // 10101
    // 00000
    // 其实就是算有多少个分割区域
    public int minFlips(String target) {
        int ans = 0;
        char pre = '0';
        for (int i = 0; i < target.length(); i++) {
            if (pre != target.charAt(i)) {
                ans++;
                pre = target.charAt(i);
            }
        }
        return ans;
    }
}
