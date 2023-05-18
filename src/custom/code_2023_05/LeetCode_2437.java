package custom.code_2023_05;

/**
 * @ClassName LeetCode_2437
 * @Author Duys
 * @Description
 * @Date 2023/5/9 13:02
 **/
// 2437. 有效时间的数目
public class LeetCode_2437 {
    public static int countTime(String time) {
        String[] split = time.split(":");
        String pre = split[0];
        String sub = split[1];
        int ans1 = 1;
        // 00 -23
        if (pre.equals("??")) {
            ans1 = 24;
        } else if (pre.charAt(0) == '?') {
            // ? x
            if (pre.charAt(1) - '0' < 4) {
                ans1 = 3;
            } else {
                ans1 = 2;
            }
        } else if (pre.charAt(1) == '?') {
            // x?
            if (pre.charAt(0) - '0' < 2) {
                ans1 = 10;
            } else {
                ans1 = 4;
            }
        }
        // 00  - 59
        int ans2 = 1;
        if (sub.equals("??")) {
            ans2 = 60;
        } else if (sub.charAt(0) == '?') {
            // ? x
            ans2 = 6;
        } else if (sub.charAt(1) == '?') {
            // x ?
            ans2 = 10;
        }
        return ans2 * ans1;
    }

    public static void main(String[] args) {
        System.out.println(countTime("0?:0?"));
    }
}
