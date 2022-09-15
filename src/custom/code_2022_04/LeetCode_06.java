package custom.code_2022_04;

/**
 * @ClassName LeetCode_06
 * @Author Duys
 * @Description
 * @Date 2022/4/25 13:51
 **/
// https://leetcode-cn.com/problems/zigzag-conversion/
// 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
//比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
// P   A   H   N
// A P L S I I G
// Y   I   R
//来源：力扣（LeetCode）
public class LeetCode_06 {
    /**
     * 0 1 2  3  4  5  6  7  8  9  10 11 12 13
     * P A Y  P  A  L  I  S  H  I  R  I  N  G
     * 0 4 11 5  1  6 12  7  2  9  13 8  3  10
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     */

    public static String convert(String s, int numRows) {
        int n = s.length();
        if (numRows == 1) {
            return s;
        }
        char[] source = s.toCharArray();
        char[][] target = new char[numRows][n];
        // next
        target[0][0] = source[0];
        // 下一个位置 sx sy
        int sx = 0, sy = 0;
        // next = 1 向下，next = -1向右上方
        int next = 1;
        int step = 1;
        for (int i = 0; i < n; i++) {
            target[sx][sy] = source[i];
            if (next == 1) {
                sx = sx + 1;
            }
            if (next == -1) {
                sx = sx - 1;
                sy = sy + 1;
            }
            step++;
            if (step > numRows - 1) {
                next = -next;
                step = 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < target.length; j++) {
            for (int i = 0; i < target[0].length; i++) {
                if (target[j][i] - '0' != -48) {
                    sb.append(target[j][i]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "ab";
        System.out.println(convert(str, 1));
    }
}
