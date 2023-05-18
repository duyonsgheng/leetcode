package custom.code_2023_04;

/**
 * @ClassName LeetCode_1638
 * @Author Duys
 * @Description
 * @Date 2023/4/21 14:43
 **/
// 1638. 统计只差一个字符的子串数目
public class LeetCode_1638 {
    public int countSubstrings(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        // left[i][j] = 如果两边字符相等，算出最大相等长度，如果不能就是0
        int[][] left = new int[sl + 1][tl + 1];
        int[][] right = new int[sl + 1][tl + 1];
        // 从左往右填
        for (int i = 0; i < sl; i++) {
            for (int j = 0; j < tl; j++) {
                left[i + 1][j + 1] = s.charAt(i) == t.charAt(j) ? left[i][j] + 1 : 0;
            }
        }
        // 从右往左填
        for (int i = sl - 1; i >= 0; i--) {
            for (int j = tl - 1; j >= 0; j--) {
                right[i][j] = s.charAt(i) == t.charAt(j) ? right[i + 1][j + 1] + 1 : 0;
            }
        }
        int ans = 0;
        for (int i = 0; i < sl; i++) {
            for (int j = 0; j < tl; j++) {
                // 如果这不等了，那么左边最长和右边最长可以组成一个笛卡尔积
                if (s.charAt(i) != t.charAt(j)) {
                    ans += (left[i][j] + 1) * (right[i + 1][j + 1] + 1);
                }
            }
        }
        return ans;
    }
}
