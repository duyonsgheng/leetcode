package custom.code_2022_12;

import java.util.Stack;

/**
 * @ClassName LeetCode_1945
 * @Author Duys
 * @Description
 * @Date 2022/12/15 14:42
 **/
// 1945. 字符串转化后的各位数字之和
public class LeetCode_1945 {
    public static int getLucky(String s, int k) {
        char[] arr = s.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (char c : arr) {
            buffer.append((c - 'a') + 1);
        }
        int num = 0;
        for (int i = 0; i < k; i++) {
            num = 0;
            for (int j = 0; j < buffer.length(); j++) {
                num += (buffer.charAt(j) - '0');
            }
            buffer.delete(0, buffer.length());
            buffer.append(num);
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(getLucky("leetcode", 2));
    }
}
