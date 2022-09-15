package custom.code_2022_08;

/**
 * @ClassName LeetCode_1417
 * @Author Duys
 * @Description
 * @Date 2022/8/11 13:35
 **/
// 1417. 重新格式化字符串
public class LeetCode_1417 {

    public static String reformat(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        char[] nums = new char[n];
        char[] chars = new char[n];
        int ni = 0;
        int ci = 0;
        for (char c : str) {
            if (c >= '0' && c <= '9') {
                nums[ni++] = c;
            } else if (c >= 'a' && c <= 'z') {
                chars[ci++] = c;
            }
        }
        // 不行，做不到
        if (Math.abs(ni - ci) > 1) {
            return "";
        }
        char[] str_ = new char[n];
        char[] lang = ni >= ci ? nums : chars;
        char[] sort = ni >= ci ? chars : nums;
        int li = 0;
        int si = 0;
        // 把长的放偶数位置，短的放奇数位置

        for (int i = 0; i < n; i++) {
            char cur = 0;
            if (i % 2 == 0) {
                cur = lang[li++];
            } else {
                cur = sort[si++];
            }
            str_[i] = cur;
        }
        return new String(str_);
    }

    public static void main(String[] args) {
        String str = "a0b1c2";
        System.out.println(reformat(str));
    }
}
