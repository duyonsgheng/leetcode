package custom.code_2022_12;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1324
 * @Author Duys
 * @Description
 * @Date 2022/12/9 10:54
 **/
// 1324. 竖直打印单词
public class LeetCode_1324 {

    public static List<String> printVertically(String s) {
        String[] arr = s.split(" ");
        List<char[]> list = new ArrayList<>();
        int len = 0;
        for (String str : arr) {
            char[] chars = str.toCharArray();
            len = Math.max(len, chars.length);
            list.add(chars);
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            StringBuffer buffer = new StringBuffer();
            for (char[] chars : list) {
                if (i >= chars.length) {
                    buffer.append(" ");
                } else {
                    buffer.append(chars[i]);
                }
            }
            // 去掉尾部的空
            String string = buffer.toString();
            for (int k = string.length() - 1; k >= 0; k--) {
                char c = string.charAt(k);
                if (c != ' ') {
                    break;
                } else
                    buffer.deleteCharAt(k);
            }
            ans.add(buffer.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "TO BE OR NOT TO BE";
        System.out.println(printVertically(s));
    }
}
