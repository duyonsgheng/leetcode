package duys_code.day_28;

/**
 * @ClassName Code_11_38_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/count-and-say/
 * @Date 2021/11/23 16:36
 **/
public class Code_11_38_LeetCode {

    public String countAndSay(int n) {
        if (n < 1) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        char[] last = countAndSay(n - 1).toCharArray();
        StringBuilder sb = new StringBuilder();
        int times = 1;
        for (int i = 1; i < last.length; i++) {
            if (last[i - 1] == last[i]) {
                times++;
            } else {
                sb.append(times);
                sb.append(last[i - 1]);
                times = 1;
            }
        }
        sb.append(times);
        sb.append(last[last.length - 1]);
        return sb.toString();
    }
}
