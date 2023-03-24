package custom.code_2023_02;

/**
 * @ClassName LeetCode_1525
 * @Author Duys
 * @Description
 * @Date 2023/2/6 14:09
 **/
// 1525. 字符串的好分割数目
public class LeetCode_1525 {

    // a a c a b a
    public static int numSplits(String s) {
        int n = s.length();
        // 包括自己，左边出现了多少不同的字符
        int[] left = new int[n];
        // 不包括自己，右边出现了多少不同的字符
        int[] right = new int[n];
        int status = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            // 已经出现了，继续下一个
            if ((status & (1 << (s.charAt(i) - 'a'))) == 0) {
                status |= (1 << (s.charAt(i) - 'a'));
                cnt++;
            }
            left[i] = cnt;
        }
        status = 0;
        cnt = 1;
        right[n - 1] = 0;
        status |= (1 << (s.charAt(n - 1) - 'a'));
        for (int i = n - 2; i >= 0; i--) {
            // 已经出现了，继续下一个
            right[i] = cnt;
            if ((status & (1 << (s.charAt(i) - 'a'))) == 0) {
                status |= (1 << (s.charAt(i) - 'a'));
                cnt++;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++)
            ans += left[i] == right[i] ? 1 : 0;
        return ans;
    }

    public static void main(String[] args) {
        String s = "aacaba";
        // a a c a b a
        // 1 1 2 2 3 3
        // 3 3 2 2 1 0
        System.out.println(numSplits(s));
    }
}
