package custom.code_2023_04;

/**
 * @ClassName LeetCode_1653
 * @Author Duys
 * @Description
 * @Date 2023/4/23 16:06
 **/
// 1653. 使字符串平衡的最少删除次数
public class LeetCode_1653 {
    // aababbab
    // a得左边有几个b
    // b得右边有几个a
    public int minimumDeletions(String s) {
        int n = s.length();
        int leftb = 0;
        int righta = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') {
                righta++;
            }
        }
        int ans = righta;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                righta--;
            } else {
                leftb++;
            }
            ans = Math.min(ans, leftb + righta);
        }
        return ans;
    }
}
