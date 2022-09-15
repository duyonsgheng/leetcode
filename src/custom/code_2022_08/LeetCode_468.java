package custom.code_2022_08;

/**
 * @ClassName LeetCode_468
 * @Author Duys
 * @Description
 * @Date 2022/8/15 10:02
 **/
//468. 验证IP地址
// 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
//链接：https://leetcode.cn/problems/validate-ip-address
public class LeetCode_468 {
    public static void main(String[] args) {
        String atr = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        System.out.println(validIPAddress(atr));
    }

    public static String validIPAddress(String queryIP) {
        if (queryIP.indexOf(".") >= 0 && checkIPV4(queryIP)) return "IPv4";
        if (queryIP.indexOf(":") >= 0 && checkIPV6(queryIP)) return "IPv6";
        return "Neither";
    }

    public static boolean checkIPV4(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n && count <= 3; ) {
            int j = i;
            int cur = 0;
            while (j < n && arr[j] >= '0' && arr[j] <= '9' && cur <= 255) {
                cur = cur * 10 + (arr[j++] - '0');
            }

            // 两个 . 连续出现
            if (j == i) {
                return false;
            }
            // 数字区间开始是0，或者当前数字大于255了
            if ((j - i > 1 && arr[i] == '0') || cur > 255) {
                return false;
            }
            i = j + 1;// 下一个开始的区间
            if (j == n) {
                continue;
            }
            // 数字区间结束后，竟然不是.
            if (arr[j] != '.') {
                return false;
            }
            // 四段数字区间。三个 .
            count++;
        }
        return count == 3 && arr[0] != '.' && arr[n - 1] != '.';
    }

    public static boolean checkIPV6(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n && count <= 7; ) {
            int j = i;
            while (j < n && ((arr[j] >= 'a' && arr[j] <= 'f') || (arr[j] >= 'A' && arr[j] <= 'F') || (arr[j] >= '0' && arr[j] <= '9'))) {
                j++;
            }
            // 两个 .之间没有字符，或者字符长度超过4了
            if (j == i || j - i > 4) {
                return false;
            }
            i = j + 1;
            if (j == n) {
                continue;
            }
            if (arr[j] != ':') {
                return false;
            }
            count++;
        }
        return count == 7 && arr[0] != ':' && arr[n - 1] != ':';
    }
}
