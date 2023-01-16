package week.week_2023_01_01;

import java.util.Arrays;

/**
 * @ClassName Code_05_LeetCode_899
 * @Author Duys
 * @Description
 * @Date 2023/1/5 12:58
 **/
// 899. 有序队列
public class Code_05_LeetCode_899 {

    // 1.如果k大于等于2 那么无论如何都可以搞出一个排列。意思就是s的所有字符全排列
    public String orderlyQueue1(String s, int k) {
        if (k > 1) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            return String.valueOf(arr);
        } else {
            String pre = s;
            int n = s.length();
            StringBuffer buffer = new StringBuffer(s);
            for (int i = 1; i < n; i++) {
                char c = buffer.charAt(0);
                buffer.deleteCharAt(0);
                buffer.append(c);
                if (buffer.toString().compareTo(pre) < 0) {
                    pre = buffer.toString();
                }
            }
            return pre;
        }
    }
}
