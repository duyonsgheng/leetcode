package custom.code_2023_06;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1910
 * @date 2023年06月28日
 */
// 1910. 删除一个字符串中所有出现的给定子字符串
public class LeetCode_1910 {
    public static String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            int i = s.indexOf(part);
            s = s.substring(0, i) + s.substring(i + part.length());
        }
        return s;
    }

    public static void main(String[] args) {
        // "aabababa"
        //"aba"
        System.out.println(removeOccurrences("aabababa", "aba"));
    }
}
