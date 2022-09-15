package custom.code_2022_05;

/**
 * @ClassName LeetCode_67
 * @Author Duys
 * @Description
 * @Date 2022/5/10 10:44
 **/
// 67. 二进制求和
// 给你两个二进制字符串，返回它们的和（用二进制表示）。
//输入为 非空 字符串且只包含数字 1 和 0。
public class LeetCode_67 {

    public static String addBinary(String a, String b) {
        if (a == null || a.length() <= 0) {
            return b;
        }
        if (b == null || b.length() <= 0) {
            return a;
        }
        char[] strA = a.toCharArray();
        char[] strB = b.toCharArray();
        int na = strA.length;
        int nb = strB.length;
        int len = na >= nb ? na : nb;
        char[] nstrA = new char[len];
        char[] nstrB = new char[len];
        int index = 0;
        int diff = 0;
        if (len == na) {
            diff = len - nb;
            for (int i = 0; i < len; i++) {
                nstrA[i] = strA[i];
            }
            for (int i = 0; i < diff; i++) {
                nstrB[i] = '0';
            }
            for (int i = diff; i < len; i++) {
                nstrB[i] = strB[index++];
            }
        } else {
            diff = len - na;
            for (int i = 0; i < len; i++) {
                nstrB[i] = strB[i];
            }
            for (int i = 0; i < diff; i++) {
                nstrA[i] = '0';
            }
            for (int i = diff; i < len; i++) {
                nstrA[i] = strA[index++];
            }
        }
        int add = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = len - 1; i >= 0; i--) {
            int cur = nstrA[i] - '0' + nstrB[i] - '0' + add;
            add = cur / 2;
            cur = cur % 2;
            sb.append(cur);
        }
        if (add > 0) {
            sb.append(add);
        }
        return sb.reverse().toString();
    }

    public static String addBinary2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int add = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0) + add;
            int cur = sum % 2;
            add = sum / 2;
            sb.append(cur);
        }
        if (add > 0) {
            sb.append(add);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s1 = "11";
        String s2 = "1";
        System.out.println(addBinary2(s1, s2));
    }
}
