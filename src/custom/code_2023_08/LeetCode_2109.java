package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2109
 * @date 2023年08月17日
 */
// 2109. 向字符串添加空格
// https://leetcode.cn/problems/adding-spaces-to-a-string/
public class LeetCode_2109 {
    public static String addSpaces(String s, int[] spaces) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (j < spaces.length && i == spaces[j]) {
                sb.append(' ');
                j++;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(addSpaces("LeetcodeHelpsMeLearn", new int[]{8, 13, 15}));
        System.out.println(addSpaces("icodeinpython", new int[]{1, 5, 7, 9}));
        System.out.println(addSpaces("spacing", new int[]{0, 1, 2, 3, 4, 5, 6}));
    }
}
