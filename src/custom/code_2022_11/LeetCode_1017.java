package custom.code_2022_11;

/**
 * @ClassName LeetCode_1017
 * @Author Duys
 * @Description
 * @Date 2022/11/3 14:54
 **/
public class LeetCode_1017 {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        while (n != 0) {
            // 取出当前的余数
            int mod = Math.abs(n) % (-2);
            sb.append(mod);
            n -= mod;
            n /= -2;
        }
        return sb.reverse().toString();
    }
}
