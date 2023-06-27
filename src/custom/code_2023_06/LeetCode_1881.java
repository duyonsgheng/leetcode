package custom.code_2023_06;

/**
 * @ClassName LeetCode_1881
 * @Author Duys
 * @Description
 * @Date 2023/6/13 10:05
 **/
// 1881. 插入后的最大值
public class LeetCode_1881 {
    public static String maxValue(String n, int x) {
        int len = n.length();
        StringBuilder stringBuilder = new StringBuilder(n);
        if (n.charAt(0) == '-') {
            for (int i = 1; i < len; i++) {
                if (n.charAt(i) - '0' > x) {
                    stringBuilder.insert(i, x);
                    break;
                } else if (i == len - 1) {
                    stringBuilder.insert(i + 1, x);
                }
            }
        } else {
            for (int i = 0; i < len; i++) {
                if (n.charAt(i) - '0' < x) {
                    stringBuilder.insert(i, x);
                    break;
                } else if (i == len - 1) {
                    stringBuilder.insert(i + 1, x);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(maxValue("-132", 3));

    }
}
