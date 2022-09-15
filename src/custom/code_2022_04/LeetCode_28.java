package custom.code_2022_04;

/**
 * @ClassName LeetCode_28
 * @Author Duys
 * @Description
 * @Date 2022/4/27 13:24
 **/
//https://leetcode-cn.com/problems/implement-strstr/
//给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
public class LeetCode_28 {
    // 窗口
    public static int strStr(String haystack, String needle) {
        if (haystack == null || haystack.length() <= 0) {
            return -1;
        }
        if (needle == null || needle.length() <= 0) {
            return 0;
        }
        if (needle.length() >= haystack.length()) {
            return haystack.equals(needle) ? 0 : -1;
        }

        char[] source = haystack.toCharArray();
        char[] target = needle.toCharArray();
        int l = -1;
        int r = 0;
        int n = source.length;
        int m = target.length;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (source[i] == target[0]) {
                l = i;
                break;
            }
        }
        if (l == -1) {
            return ans;
        }
        // 开始了
        while (l < n && r < n) {
            r = l + m - 1;
            if (isOk(source, l, r, target)) {
                ans = l;
                break;
            } else
                l++;
        }
        return ans;
    }

    public static boolean isOk(char[] source, int l, int r, char[] target) {
        if (r - l + 1 != target.length || r > source.length - 1) {
            return false;
        }
        for (int j = l, i = 0; j <= r; j++, i++) {
            if (source[j] == target[i]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(strStr("mississippi", "sippia"));
    }
}
