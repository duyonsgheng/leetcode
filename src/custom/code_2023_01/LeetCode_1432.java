package custom.code_2023_01;

/**
 * @ClassName LeetCode_1432
 * @Author Duys
 * @Description
 * @Date 2023/1/4 15:37
 **/
// 1432. 改变一个整数能得到的最大差值
public class LeetCode_1432 {
    // 123456
    // 923456
    // 103456
    // 第一次找一个高位变为9
    // 第二次找一个高位变为0
    public static int maxDiff(int num) {
        StringBuffer max = new StringBuffer(String.valueOf(num));
        StringBuffer min = new StringBuffer(String.valueOf(num));

        // 高位替换为9
        int len = max.length();
        for (int i = 0; i < len; i++) {
            char cur = max.charAt(i);
            if (cur != '9') {
                replace(max, cur, '9');
                break;
            }
        }
        for (int i = 0; i < len; i++) {
            char cur = min.charAt(i);
            if (i == 0) {
                // 如果是首位，那么不为1，就替换为1
                if (cur != '1') {
                    replace(min, cur, '1');
                    break;
                }
            } else {
                // 如果不是首位，并且当前位不为0，并且当前位和首位不同，则替换为0
                if (cur != '0' && cur != min.charAt(0)) {
                    replace(min, cur, '0');
                    break;
                }
            }
        }
        return Integer.valueOf(max.toString()) - Integer.valueOf(min.toString());
    }

    public static void replace(StringBuffer str, char x, char y) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == x) {
                str.setCharAt(i, y);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(maxDiff(123456));
    }
}
