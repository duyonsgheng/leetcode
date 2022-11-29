package custom.code_2022_11;

/**
 * @ClassName LeetCode_1758
 * @Author Duys
 * @Description
 * @Date 2022/11/29 9:40
 **/
// 1758. 生成交替二进制字符串的最少操作数
public class LeetCode_1758 {
    public static int minOperations(String s) {
        int once = 0;
        int zero = 0;
        int flag = 0;
        for (char c : s.toCharArray()) {
            if (c - '0' == flag) {
                zero++;
            } else {
                once++;
            }
            flag = 1 - flag;
        }
        return Math.min(zero, once);
    }

    public static void main(String[] args) {
        String s = "1010101";
        System.out.println(minOperations(s));
    }
}
