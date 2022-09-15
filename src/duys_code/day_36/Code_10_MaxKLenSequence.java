package duys_code.day_36;

/**
 * @ClassName Code_10_MaxKLenSequence
 * @Author Duys
 * @Description TODO
 * @Date 2021/12/14 13:35
 **/
public class Code_10_MaxKLenSequence {

    //
    // 来自腾讯
    // 给定一个字符串str，和一个正数k
    // 返回长度为k的所有子序列中，字典序最大的子序列

    // 解法：单调栈，栈里面压的元素全部都是字典序大于自己的，但是有一个前提，就是当前栈里的和剩下的元素个数正好是k，那么直接返回栈里的和剩下的
    public static String maxString(String s, int k) {
        if (s == null || s.length() < k) {
            return "";
        }
        char[] str = s.toCharArray();
        int n = str.length;
        char[] stack = new char[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            // 栈顶元素是小于当前元素，并且剩下的元素和当前栈里的元素是大于k的
            while (size > 0 && stack[size - 1] < str[i] && size + n - i > k) {
                size--;// 依次弹出
            }
            if (size + n - i == k) {
                // 栈里的元素 + 剩下的元素
                return String.valueOf(stack, 0, size) + s.substring(i);
            }
            stack[size++] = str[i];
        }
        return String.valueOf(stack, 0, size);
    }
}
