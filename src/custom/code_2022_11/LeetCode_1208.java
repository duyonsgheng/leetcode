package custom.code_2022_11;

import java.util.Map;

/**
 * @ClassName LeetCode_1208
 * @Author Duys
 * @Description
 * @Date 2022/11/23 15:43
 **/
// 1208. 尽可能使字符串相等
public class LeetCode_1208 {
    // 这肯定使用窗口啊
    public static int equalSubstring(String s, String t, int maxCost) {
        if (s.length() != t.length()) {
            return -1;
        }
        int n = s.length();
        // 每一个位置需要的代价搞出来
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int ans = 0, l = 0, r = 0, sum = 0;
        for (; r < n; r++) {
            sum += diff[r];
            while (l <= r && sum > maxCost) {
                sum -= diff[l++];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcd", t = "bcdf";
        int maxCost = 3;
        System.out.println(equalSubstring(s, t, maxCost));
    }
}
