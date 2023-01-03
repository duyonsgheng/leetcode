package custom.code_2022_12;

/**
 * @ClassName LeetCode_1759
 * @Author Duys
 * @Description
 * @Date 2022/12/26 10:30
 **/
// 1759. 统计同构子字符串的数目
public class LeetCode_1759 {

    public int countHomogenous(String s) {
        int mod = 1000000007;
        long ans = 0;
        char pre = s.charAt(0);
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == pre) {
                cnt++;
            } else {
                // 等差数列求和公式 N(a1+an)/2
                ans += (long) (cnt + 1) * cnt / 2;
                cnt = 1;
                pre = c;
            }
        }
        ans += (long) (cnt + 1) * cnt / 2;
        return (int) (ans % mod);
    }
}
