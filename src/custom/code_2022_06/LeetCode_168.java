package custom.code_2022_06;

/**
 * @ClassName LeetCode_168
 * @Author Duys
 * @Description
 * @Date 2022/6/30 15:48
 **/
// 168. Excel表列名称
public class LeetCode_168 {

    public static String convertToTitle(int columnNumber) {
        StringBuilder builder = new StringBuilder();
        while (columnNumber != 0) {
            columnNumber--;
            builder.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        convertToTitle(26);
        System.out.println();
        System.out.println(2147483647 / 26);
        System.out.println(2147483647 % 26);
    }

}
