package custom.code_2023_04;

/**
 * @ClassName LeetCode_1616
 * @Author Duys
 * @Description
 * @Date 2023/4/18 16:55
 **/
public class LeetCode_1616 {
    // aprefix + bsuffix 或者 bprefix + asuffix
    public boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }

    public boolean check(String a, String b) {
        int n = a.length();
        int left = 0;
        int right = n - 1;
        while (left < right && a.charAt(left) == b.charAt(right)) {
            left++;
            right--;
        }
        if (left >= right) {
            return true;
        }
        return check(a, left, right) || check(b, left, right);
    }

    public boolean check(String a, int left, int right) {
        while (left < right && a.charAt(left) == a.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
}
