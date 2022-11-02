package custom.code_2022_11;

/**
 * @ClassName LeetCode_1003
 * @Author Duys
 * @Description
 * @Date 2022/11/2 10:48
 **/
// 1003. 检查替换后的词是否有效
public class LeetCode_1003 {
    public static boolean isValid1(String s) {
        int n = s.length();
        if (n % 3 != 0) {
            return false;
        }
        String start = s;
        while (start.contains("abc")) {
            start = start.replaceAll("abc", "");
        }
        return start.equals("");
    }

    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 3 != 0) {
            return false;
        }
        char[] arr = new char[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                arr[p++] = c;
            } else if (c == 'b') {
                if (p < 1 || arr[p - 1] != 'a') {
                    return false;
                }
                arr[p++] = c;
            } else {
                if (p < 2 || arr[p - 1] != 'b') {
                    return false;
                }
                p -= 2;
            }
        }
        return p == 0;
    }

    public static void main(String[] args) {
        String str = "abcabcababcc";
        System.out.println(isValid1(str));
    }
}
