package custom.code_2022_09;

/**
 * @ClassName LeetCode_01_09
 * @Author Duys
 * @Description
 * @Date 2022/9/29 8:49
 **/
// 面试题 01.09. 字符串轮转
public class LeetCode_01_09 {
    // s1 = "waterbottle", s2 = "erbottlewat"
    public static boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }

    public static boolean isFlipedString2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m != n) {
            return false;
        }
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (s1.charAt((i + j) % n) != s2.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "waterbottle", s2 = "erbottlewat";
        System.out.println(isFlipedString2(s1, s2));
    }
}
