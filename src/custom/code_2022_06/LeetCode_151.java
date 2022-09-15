package custom.code_2022_06;

/**
 * @ClassName LeetCode_151
 * @Author Duys
 * @Description
 * @Date 2022/6/30 13:25
 **/
// 151. 颠倒字符串中的单词
public class LeetCode_151 {

    public static String reverseWords(String s) {
        if (s == null || s.length() <= 0) {
            return s;
        }
        String[] str = s.split(" ");
        String[] newStr = new String[str.length];
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("")) {
                continue;
            }
            newStr[index++] = str[i];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = index - 1; i >= 1; i--) {
            builder.append(newStr[i]);
            builder.append(" ");
        }
        builder.append(newStr[0]);
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }
}
