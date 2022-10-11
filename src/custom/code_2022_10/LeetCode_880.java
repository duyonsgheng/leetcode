package custom.code_2022_10;

/**
 * @ClassName LeetCode_880
 * @Author Duys
 * @Description
 * @Date 2022/10/11 9:55
 **/
// 880. 索引处的解码字符串
public class LeetCode_880 {

    public static String decodeAtIndex(String s, int k) {
        // 算出整体字符串长度
        long size = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) >= '1' && s.charAt(i) <= '9') {
                size *= s.charAt(i) - '0';
            } else {
                size++;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            k %= size;
            if (k == 0 && s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                return Character.toString(s.charAt(i));
            if (s.charAt(i) >= '1' && s.charAt(i) <= '9') {
                size /= s.charAt(i) - '0';
            } else size--;
        }
        return "";
    }

    public static void main(String[] args) {
        String a = "leet2code3";
        // leetleetcodeleetleetcodeleetleetcode
        int k = 10;
        System.out.println(decodeAtIndex(a, k));
        System.out.println("leetleetcodeleetleetcodeleetleetcode".length());
    }
}

