package week.week_2021_12_03;

/**
 * @ClassName Code_02
 * @Author Duys
 * @Description
 * @Date 2022/4/14 17:17
 **/
// 有一个二进制字符串，可以选择该串中的任意一段区间进行取反(可以进行一次或不进行)，
// 取反指将变为，将变为。那么取反之后的可能的最大的字典序是多少呢。如有，将区间取反变为是字典序最大的。
public class Code_02_BinaryNegate {

    // 这就是一个贪心。1的字典序一定比0的大，那么我就把第一段为0的全部改为1。然后返回
    public static String maxLexicographical(String num) {
        char[] arr = num.toCharArray();
        int i = 0;
        // 找到第一个为0的位置，因为高位是0
        while (i < arr.length) {
            if (arr[i] == '0') {
                break;
            }
            i++;
        }
        //
        while (i < arr.length) {
            if (arr[i] == '1') {
                break;
            }
            arr[i++] = '1';
        }
        return String.valueOf(arr);
    }
}
