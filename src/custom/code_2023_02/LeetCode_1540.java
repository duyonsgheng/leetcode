package custom.code_2023_02;

/**
 * @ClassName LeetCode_1540
 * @Author Duys
 * @Description
 * @Date 2023/2/8 10:01
 **/
// 1540. K 次操作转变字符串
public class LeetCode_1540 {
    public static boolean canConvertString1(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            int diff = (t.charAt(i) - s.charAt(i) + 26) % 26;
            if (diff == 0) {
                continue;
            }
            if (arr[diff] > k) {
                return false;
            }
            arr[diff] += 26;
        }
        return true;
    }

    public static boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counts = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            // 记录哪些位置需要变更，并且记录变更的次数
            int difference = t.charAt(i) - s.charAt(i);
            if (difference < 0) {
                difference += 26;
            }
            counts[difference]++;
        }
        for (int i = 1; i < 26; i++) {
            // 变更次数超过了，就没戏了
            int maxConvert = i + 26 * (counts[i] - 1);
            if (maxConvert > k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // a b c d e f g h i j k l m n o p q r s t u v w x y z
        System.out.println(canConvertString("input", "ouput", 9));
        System.out.println(canConvertString("abc", "bcd", 10));
        System.out.println(7 / 0.6);
    }
}
