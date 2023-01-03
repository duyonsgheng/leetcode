package custom.code_2022_12;

/**
 * @ClassName LeetCode_1750
 * @Author Duys
 * @Description
 * @Date 2022/12/28 9:31
 **/
// 1750. 删除字符串两端相同字符后的最短长度
public class LeetCode_1750 {
    public int minimumLength(String s) {
        int n = s.length();
        int l = 0, r = n - 1;
        // 如果两边字符是一样的
        while (l < r && s.charAt(l) == s.charAt(r)) {
            char lc = s.charAt(l);
            // 左边有几个相同的
            while (l <= r && s.charAt(l) == lc) {
                l++;
            }
            // 右边有几个跟左边相同的
            while (l <= r && s.charAt(r) == lc) {
                r--;
            }
        }
        return r - l + 1;
    }
}
